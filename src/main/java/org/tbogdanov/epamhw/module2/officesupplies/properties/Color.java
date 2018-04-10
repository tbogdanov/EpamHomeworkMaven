package org.tbogdanov.epamhw.module2.officesupplies.properties;

/**
 * Created by Timur Bogdanov on 08.04.18.
 */
public enum Color {

    RED("Red", 8),
    GREEN("Green", 4),
    BLUE("Blue", 1),
    YELLOW("Yellow", 10),
    BLACK("Black", 2);

    private String name;
    private int price;

    Color(String name, int price) {
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
