package org.tbogdanov.epamhw.module3.polyglot;

import java.util.Scanner;

/**
 * Created by Timur Bogdanov on 11.04.18.
 */
public class PolyglotCLI {

    private static Scanner in;
    private static Polyglot polyglot;

    public void chooseLanguageMenu() {
        boolean exitFlag = false;
        String choice;
        while (!exitFlag) {
            System.out.println(polyglot.getChooseLanguageMessage());
            choice = in.next();
            switch (choice) {
                case "en":
                    polyglot.setLocale(Polyglot.LocaleEnum.EN);
                    questionMenu();
                    break;
                case "ru":
                    polyglot.setLocale(Polyglot.LocaleEnum.RU);
                    questionMenu();
                    break;
                case "q":
                    exitFlag = true;
                    break;
                default:
                    System.out.println(polyglot.getLanguageErrorMessage());
            }
        }
    }

    public void questionMenu() {
        boolean exitFlag = false;
        int choice;
        while (!exitFlag) {
            String[] questionStr = polyglot.getAllQuestionsStr();
            for (int i = 0; i < questionStr.length; i++) {
                System.out.println(String.format("%d. %s", i+1, questionStr[i]));
            }
            try {
                choice = in.nextInt();
                if (choice != 0) {
                    System.out.println(polyglot.getAnswerStr(choice-1));
                }
                else {
                    exitFlag = true;
                }
            } catch (Exception ex) {
                System.out.println(polyglot.getNoSuchQuestionMessage());
            }
        }
    }

    public static void main(String[] args) {
        polyglot = new Polyglot();
        in = new Scanner(System.in);
    }

}
