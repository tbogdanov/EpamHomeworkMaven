package org.tbogdanov.epamhw.module2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tbogdanov.epamhw.module2.officesupplies.noviceset.NoviceSet;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Timur Bogdanov on 10.04.18.
 */
public class NoviceSetTest {

    private static NoviceSet noviceSet;

    @BeforeEach
    public void init() {
        noviceSet = new NoviceSet(Manufacturer.BIC);
    }

    @Test
    public void getAllSuppliesAsListTest() {
        List<OfficeSupply> supplies = noviceSet.getAllSuppliesAsList();
        Pen[] pens = noviceSet.getPens();
        Pencil[] pencils = noviceSet.getPencils();
        Scissors scissors = noviceSet.getScissors();
        Eraser eraser = noviceSet.getEraser();
        Paper paper = noviceSet.getPaper();

        assertTrue(supplies.containsAll(Arrays.asList(pens)));
        assertTrue(supplies.containsAll(Arrays.asList(pencils)));
        assertTrue(supplies.contains(scissors));
        assertTrue(supplies.contains(eraser));
        assertTrue(supplies.contains(paper));
    }

}
