package org.tbogdanov.epamhw.module2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Color;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Message;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.Paper;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.Pen;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.Pencil;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Timur Bogdanov on 10.04.18.
 */
public class PencilTest {

    private Pencil pencil;

    @BeforeEach
    public void init() {
        pencil = new Pencil(Manufacturer.ERICH_KRAUSE, "Golden Pen", Color.BLUE);
    }

    @Test
    public void writeOnTest() {
        Paper paper = new Paper(Manufacturer.ERICH_KRAUSE, 1);
        pencil.writeOnPaper(paper, "Hello World!");
        Message msg = paper.getMessage(0);
        assertEquals("Blue pencil writes: Hello World!", msg.toString());
    }

}
