package org.tbogdanov.epamhw.module2.officesupplies.supplies;

import org.tbogdanov.epamhw.module2.officesupplies.properties.Color;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;

/**
 * Created by Timur Bogdanov on 08.04.18.
 */
public class Pen extends WritableSupply {

    public Pen(Manufacturer manufacturer, String name, Color color) {
        super(manufacturer, name, color);
        this.typeName = "Pen";
        this.typePrice = 20;
    }

    public Pen(Pen orig) {
        super(orig.getManufacturer(), orig.getName(), orig.getColor());
    }

    public void writeOnPaper(Paper targetPaper, String what)  {
        try {
            targetPaper.addMessage(String.format("%s pen writes: %s", getColor().toString(), what),
                    false);
        } catch (Exception ex) {
            System.out.println("Something went wrong with adding a new message: " +
                    ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("Pen %s %s (%s)", manufacturer.toString(), name, getColor().toString());
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 7;    // Let's distinguish pens from pencils by this magic number
        hash = hash * 13 + (manufacturer != null? manufacturer.ordinal() : 0);  // By manufacturer id
        hash = hash * 13 + (getColor() != null? getColor().ordinal() : 0);    // By color id
        hash = hash * 31 + name.hashCode();     // By name
        return hash;
    }

    @Override
    public boolean equals(Object cmp) {
        if (this == cmp) {
            return true;
        }

        if (!(cmp instanceof Pen)) {
            return false;
        }

        Pen cmpAsPen = (Pen) cmp;

        return (this.getColor() == cmpAsPen.getColor() &&
                this.manufacturer == cmpAsPen.getManufacturer() &&
                this.name.equals(cmpAsPen.getName()));
    }

}
