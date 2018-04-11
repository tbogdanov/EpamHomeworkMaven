package org.tbogdanov.epamhw.module2.officesupplies.officedeskstats;

import org.tbogdanov.epamhw.module2.officesupplies.noviceset.NoviceSet;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Color;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.*;

import java.security.InvalidParameterException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Timur Bogdanov on 11.04.18.
 */
public class OfficeDeskStatsCLI {

    private static Scanner in;
    private static OfficeDeskStats officeDeskStats;

    public static void deskListMenu() {
        String choice;
        int index;
        boolean exitFlag = false;
        String employeeName;
        List<OfficeDesk> desks = officeDeskStats.getDeskList();

        while (!exitFlag) {
            System.out.println("DESK LIST");
            System.out.printf("%5s %20s %6s\n", "#", "Name", "Sum");
            System.out.println("=================================");
            int i = 0;
            for (OfficeDesk desk : desks) {
                System.out.printf("%5s %20s %6s\n", i, desk.getEmployeeName(), desk.getDeskPriceSum());
                i++;
            }
            System.out.println("Enter an option");
            System.out.println("a name - add an empty desk");
            System.out.println("n name - add a novice desk");
            System.out.println("s desk_number - select a desk");
            System.out.println("d desk_number - delete a desk");
            System.out.println("q - quit");
            choice = in.next();
            switch (choice) {
                case "n":
                    employeeName = in.next();
                    NoviceSet noviceSet = new NoviceSet(Manufacturer.ERICH_KRAUSE);
                    officeDeskStats.addDesk(employeeName, noviceSet.getAllSuppliesAsList());
                    break;
                case "a":
                    employeeName = in.next();
                    officeDeskStats.addDesk(employeeName);
                    break;
                case "s":
                    try {
                        index = in.nextInt();
                        OfficeDesk selectedDesk = officeDeskStats.getDesk(index);
                        deskMenu(selectedDesk);
                    } catch (InputMismatchException ex) {
                        System.out.println("Choose a number");
                        continue;
                    } catch (InvalidParameterException ex) {
                        System.out.println("Invalid number");
                        continue;
                    }
                    break;
                case "d":
                    try {
                        index = in.nextInt();
                        OfficeDesk selectedDesk = officeDeskStats.removeDesk(index);
                    } catch (InputMismatchException ex) {
                        System.out.println("Choose a number");
                        continue;
                    } catch (InvalidParameterException ex) {
                        System.out.println("Invalid number");
                        continue;
                    }
                    break;
                case "q":
                    exitFlag = true;
                    break;
                default:
                    System.out.println("Choose an option a, n, s, d, or q");
            }
        }
    }

    public static void addDeskMenu() {
        System.out.println("Enter the employee name for a new desk:");

    }

    public static void deskMenu(OfficeDesk currentDesk) {
        String choice;
        int index;
        boolean exitFlag = false;
        List<OfficeDesk> desks = officeDeskStats.getDeskList();

        while (!exitFlag) {
            System.out.printf("DESK of %s\n", currentDesk.getEmployeeName());
            System.out.printf("%5s %50s %6s\n", "#", "Supply", "Price");
            System.out.println("=================================================================");
            int i = 0;
            for (OfficeSupply supply : currentDesk.getSupplies()) {
                System.out.printf("%5s %50s %6s\n", i, supply.getFullName(), supply.getPrice());
                i++;
            }
            System.out.println("Enter an option");
            System.out.println("a - add a supply");
            System.out.println("d supply_number - delete supply");
            System.out.println("s - sort this list");
            System.out.println("q - return");
            choice = in.next();
            switch (choice) {
                case "a":
                    supplyAddMenu(currentDesk);
                    break;
                case "d":
                    try {
                        index = in.nextInt();
                        OfficeDesk selectedDesk = officeDeskStats.removeDesk(index);
                    } catch (InputMismatchException ex) {
                        System.out.println("Choose a number");
                        continue;
                    } catch (InvalidParameterException ex) {
                        System.out.println("Invalid number");
                        continue;
                    }
                    break;
                case "s":
                    sortMenu(currentDesk);
                    break;
                case "q":
                    exitFlag = true;
                    break;
                default:
                    System.out.println("Choose an option a, s, d, or q");
            }
        }
    }

    public static void supplyAddMenu(OfficeDesk currentDesk) {
        String supplyChoice;
        String manufacturerChoice;
        String colorChoice;
        System.out.println("What do you want to add?");
        System.out.println("Format: type manufacturer [color]");
        System.out.println("Types: paper, pen, pencil, scissors, eraser");
        System.out.println("Manufacturers: erich, bic, beifa, attache");
        supplyChoice = in.next();


        Manufacturer manufacturer = null;
        if (in.hasNext()) {
            manufacturerChoice = in.next();
            switch (manufacturerChoice) {
                case "erich":
                    manufacturer = Manufacturer.ERICH_KRAUSE;
                    break;
                case "bic":
                    manufacturer = Manufacturer.BIC;
                    break;
                case "beifa":
                    manufacturer = Manufacturer.BEIFA;
                    break;
                case "attache":
                    manufacturer = Manufacturer.ATTACHE;
                    break;
                default:
                    System.out.println("Wrong manufacturer. Cancelling");
                    return;
            }
        }
        else {
            System.out.println("No manufacturer specified. Cancelling");
        }

        Color color = null;
        if (in.hasNext()) {
            colorChoice = in.next();
            try {
                color = Color.valueOf(colorChoice.toUpperCase());
            } catch (IllegalArgumentException ex) {
                System.out.println("Wrong color. Cancelling");
            }
        }

        switch (supplyChoice) {
            case "paper":
                currentDesk.addSupply(new Paper(manufacturer, 10));
                break;
            case "pen":
                if (color != null) {
                    currentDesk.addSupply(new Pen(manufacturer, "Pen", color));
                }
                else {
                    System.out.println("No color specified. Cancelling");
                }
                break;
            case "pencil":
                if (color != null) {
                    currentDesk.addSupply(new Pencil(manufacturer, "Pencil", color));
                }
                else {
                    System.out.println("No color specified. Cancelling");
                }
                break;
            case "scissors":
                currentDesk.addSupply(new Scissors(manufacturer, "Scissors"));
                break;
            case "eraser":
                currentDesk.addSupply(new Eraser(manufacturer, "Eraser"));
                break;
            default:
                System.out.println("Wrong type specified. Cancelling");
        }
    }

    public static void sortMenu(OfficeDesk currentDesk) {
        int choice = -1;
        boolean exitFlag = false;
        while (!exitFlag) {
            System.out.println("How would you like to sort it?");
            System.out.println("1 - by name, ascending order");
            System.out.println("2 - by name, descending order");
            System.out.println("3 - by price, ascending order");
            System.out.println("4 - by price, descending order");
            System.out.println("5 - by price then by name, ascending order");
            System.out.println("6 - by price then by name, decending order");
            System.out.println("0 - cancel");
            try {
                choice = in.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Choose a number");
                continue;
            }
            switch (choice) {
                case 1: currentDesk.sortSuppliesByName(false); break;
                case 2: currentDesk.sortSuppliesByName(true); break;
                case 3: currentDesk.sortSuppliesByPrice(false); break;
                case 4: currentDesk.sortSuppliesByPrice(true); break;
                case 5: currentDesk.sortSuppliesByPriceAndName(false); break;
                case 6: currentDesk.sortSuppliesByPriceAndName(true); break;
                case 0: break;
                default:
                    System.out.println("Choose a number");
                    continue;
            }
            exitFlag = true;
        }
    }

    public static void main(String[] args) {
        in = new Scanner(System.in);
        officeDeskStats = new OfficeDeskStats();
        deskListMenu();
    }
}
