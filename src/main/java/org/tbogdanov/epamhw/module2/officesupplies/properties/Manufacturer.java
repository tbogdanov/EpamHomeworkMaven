package org.tbogdanov.epamhw.module2.officesupplies.properties;

/**
 * Created by Timur Bogdanov on 08.04.18.
 */
public enum Manufacturer {

    ERICH_KRAUSE("Erich Krause", 10),
    BIC("BIC", 8),
    BEIFA("Beifa", 5),
    ATTACHE("Attache", 2);

    private String name;
    private int price;

    Manufacturer(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getPrice() {
        return price;
    }

}
