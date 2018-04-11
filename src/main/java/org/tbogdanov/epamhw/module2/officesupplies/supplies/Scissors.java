package org.tbogdanov.epamhw.module2.officesupplies.supplies;

import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Message;

/**
 * Created by Timur Bogdanov on 08.04.18.
 */
public class Scissors extends OfficeSupply {

    public Scissors(Manufacturer manufacturer, String name) {
        super(manufacturer, name);
        this.typeName = "Scissors";
        this.typePrice = 60;
    }

    public Paper[] cutPaperToMessages(Paper paper) {
        Manufacturer paperManufacturer = paper.getManufacturer();
        int messageCount = paper.getMessageCount();
        Message[] paperMessages = paper.getAllMessages();

        Paper[] retPapers = new Paper[messageCount];
        for (int i = 0; i < messageCount; i++) {
            retPapers[i] = new Paper(paperManufacturer, 1);
            if (paperMessages[i] != null) {
                try {
                    retPapers[i].addMessage(paperMessages[i]);
                } catch (Exception ex) {
                    System.out.println("Something went wrong with adding a new message: " +
                            ex.getMessage());
                }
            }
        }
        return retPapers;
    }

    @Override
    public String toString() {
        return String.format("Scissors %s %s", manufacturer.toString(), name);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 13;    // Let's distinguish it from other office supplies by this magic number
        hash = hash * 13 + (manufacturer != null? manufacturer.ordinal() : 0);  // By manufacturer id
        hash = hash * 31 + name.hashCode();     // By name
        return hash;
    }

    @Override
    public boolean equals(Object cmp) {
        if (this == cmp) {
            return true;
        }

        if (!(cmp instanceof Scissors)) {
            return false;
        }

        Scissors cmpAsScissors = (Scissors) cmp;

        return (this.manufacturer == cmpAsScissors.getManufacturer() &&
                this.name.equals(cmpAsScissors.getName()));
    }
}
