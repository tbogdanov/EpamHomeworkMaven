package org.tbogdanov.epamhw.module2.officesupplies.supplies;

import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Message;

/**
 * Created by Timur Bogdanov on 10.04.18.
 */
public class Eraser extends OfficeSupply {

    public Eraser(Manufacturer manufacturer, String name) {
        super(manufacturer, name);
        this.typeName = "Eraser";
        this.typePrice = 30;
    }

    public Paper getPaperWithoutErasableMessages(Paper paper) {
        Manufacturer paperManufacturer = paper.getManufacturer();
        int paperSize = paper.getMessageCount();
        int paperCapacity = paper.getCapacity();
        int messageCount = paper.getMessageCount();
        Message[] paperMessages = paper.getAllMessages();

        Paper retPaper = new Paper(paperManufacturer, paperCapacity);
        for (int i = 0; i < messageCount; i++) {
            if (paperMessages[i] != null && !paperMessages[i].isErasable()) {
                try {
                    retPaper.addMessage(paperMessages[i]);
                } catch (Exception ex) {
                    System.out.println("Something went wrong with adding a new message: " +
                            ex.getMessage());
                }
            }
        }

        return retPaper;
    }

    @Override
    public String toString() {
        return String.format("Eraser %s %s", manufacturer.toString(), name);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 19;    // Let's distinguish it from other office supplies by this magic number
        hash = hash * 13 + (manufacturer != null? manufacturer.ordinal() : 0);  // By manufacturer id
        hash = hash * 31 + name.hashCode();     // By name
        return hash;
    }

    @Override
    public boolean equals(Object cmp) {
        if (this == cmp) {
            return true;
        }

        if (!(cmp instanceof Eraser)) {
            return false;
        }

        Eraser cmpAsEraser = (Eraser) cmp;

        return (this.manufacturer == cmpAsEraser.getManufacturer() &&
                this.name.equals(cmpAsEraser.getName()));
    }

}
