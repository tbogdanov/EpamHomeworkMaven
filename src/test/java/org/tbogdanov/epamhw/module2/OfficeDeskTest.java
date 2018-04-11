package org.tbogdanov.epamhw.module2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tbogdanov.epamhw.module2.officesupplies.noviceset.NoviceSet;
import org.tbogdanov.epamhw.module2.officesupplies.officedeskstats.OfficeDesk;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Color;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.OfficeSupply;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.Pen;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.Pencil;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.Scissors;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Timur Bogdanov on 10.04.18.
 */
public class OfficeDeskTest {

    OfficeDesk officeDesk;

    @BeforeEach
    public void init() {
        officeDesk = new OfficeDesk("Vasiliy Pupkin");
    }

    @Test
    public void emptySuppliesTest() {
        //assertTrue(officeDesk.supp)
    }

    @Test
    public void getDeskPriceSumTest() {
        Pen pen = new Pen(Manufacturer.BIC, "Pen", Color.GREEN);
        Pencil pencil = new Pencil(Manufacturer.ATTACHE, "Pencil", Color.BLACK);
        Scissors scissors = new Scissors(Manufacturer.BEIFA, "Scissors");
        int penPrice = pen.getPrice();
        int pencilPrice = pencil.getPrice();
        int scissorsPrice = scissors.getPrice();

        assertEquals(0, officeDesk.getDeskPriceSum());

        officeDesk.addSupply(pen);
        officeDesk.addSupply(pencil);

        assertEquals(penPrice+pencilPrice, officeDesk.getDeskPriceSum());

        officeDesk.addSupply(scissors);

        assertEquals(penPrice+pencilPrice+scissorsPrice, officeDesk.getDeskPriceSum());
    }

    @Test
    public void sortSuppliesByNameTest() {
        NoviceSet noviceSet = new NoviceSet(Manufacturer.BEIFA);
        officeDesk.addSupplies(noviceSet.getAllSuppliesAsList());

        officeDesk.sortSuppliesByName(false); // ascending order

        List<OfficeSupply> supplies = officeDesk.getSupplies();
        ListIterator<OfficeSupply> nameIter = supplies.listIterator();

        String previousName = nameIter.next().getFullName();
        String nextName;

        while (nameIter.hasNext()) {
            nextName = nameIter.next().getFullName();
            if (previousName.compareTo(nextName) > 0) {
                fail(String.format("Errors in ordering! %s > %s", previousName, nextName));
            }
            previousName = nextName;
        }

        officeDesk.sortSuppliesByName(true); // descending order

        nameIter = supplies.listIterator();
        previousName = nameIter.next().getFullName();

        while (nameIter.hasNext()) {
            nextName = nameIter.next().getFullName();
            if (previousName.compareTo(nextName) < 0) {
                fail(String.format("Errors in ordering! %s < %s", previousName, nextName));
            }
            previousName = nextName;
        }
    }

    @Test
    public void sortSuppliesByPriceTest() {
        NoviceSet noviceSet = new NoviceSet(Manufacturer.BEIFA);
        officeDesk.addSupplies(noviceSet.getAllSuppliesAsList());

        officeDesk.sortSuppliesByPrice(false); // ascending order

        List<OfficeSupply> supplies = officeDesk.getSupplies();
        ListIterator<OfficeSupply> priceIter = supplies.listIterator();

        int previousPrice = priceIter.next().getPrice();
        int nextPrice;

        while (priceIter.hasNext()) {
            nextPrice = priceIter.next().getPrice();
            if (previousPrice-nextPrice > 0) {
                fail(String.format("Errors in ordering! %d > %d", previousPrice, nextPrice));
            }
            previousPrice = nextPrice;
        }

        officeDesk.sortSuppliesByPrice(true); // descending order

        priceIter = supplies.listIterator();
        previousPrice = priceIter.next().getPrice();

        while (priceIter.hasNext()) {
            nextPrice = priceIter.next().getPrice();
            if (previousPrice-nextPrice < 0) {
                fail(String.format("Errors in ordering! %s < %s", previousPrice, nextPrice));
            }
            previousPrice = nextPrice;
        }
    }

    @Test
    public void sortSuppliesByNameAndPriceTest() {
        OfficeSupply[] supplies =  { new Pen(Manufacturer.ERICH_KRAUSE, "1", Color.BLUE),
                new Pen(Manufacturer.ERICH_KRAUSE, "2", Color.BLUE),
                new Pencil(Manufacturer.ERICH_KRAUSE, "3", Color.BLUE) };
        // 2 < 0 < 1

        officeDesk.addSupplies(Arrays.asList(supplies));

        officeDesk.sortSuppliesByPriceAndName(false);
        assertEquals(supplies[2], officeDesk.getSupply(0));
        assertEquals(supplies[0], officeDesk.getSupply(1));
        assertEquals(supplies[1], officeDesk.getSupply(2));

        officeDesk.sortSuppliesByPriceAndName(true);
        assertEquals(supplies[1], officeDesk.getSupply(0));
        assertEquals(supplies[0], officeDesk.getSupply(1));
        assertEquals(supplies[2], officeDesk.getSupply(2));

    }



}
