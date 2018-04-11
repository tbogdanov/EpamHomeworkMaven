package org.tbogdanov.epamhw.module2.officesupplies.supplies;

import org.tbogdanov.epamhw.module2.officesupplies.properties.Color;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;

/**
 * Created by Timur Bogdanov on 08.04.18.
 */
public abstract class WritableSupply extends OfficeSupply {

    private Color color;

    WritableSupply(Manufacturer manufacturer, String name, Color color) {
        super(manufacturer, name);
        this.color = color;
    }


    public abstract int hashCode();
    public abstract String toString();
    public abstract boolean equals(Object cmp);

    public abstract void writeOnPaper(Paper target, String message);

    @Override
    public int getPrice() {
        return typePrice + manufacturer.getPrice() + color.getPrice();
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String getFullName() { return String.format("%s %s %s %s", typeName, manufacturer, name, color); }
}
