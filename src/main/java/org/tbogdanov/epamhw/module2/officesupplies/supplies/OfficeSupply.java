package org.tbogdanov.epamhw.module2.officesupplies.supplies;

import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;

/**
 * Created by Timur Bogdanov on 08.04.18.
 */
public abstract class OfficeSupply {

    private final int typePrice = 0;

    protected Manufacturer manufacturer;
    protected String name;

    OfficeSupply(Manufacturer manufacturer, String name) {
        this.manufacturer = manufacturer;
        this.name = name;
    }

    public abstract boolean equals(Object cmp);
    public abstract int hashCode();
    public abstract String toString();

    public int getPrice() { return typePrice + manufacturer.getPrice(); }

    public Manufacturer getManufacturer() { return manufacturer; }

    public String getName() { return name; }

    public String getFullName() { return String.format("%s %s", name, manufacturer); }


}
