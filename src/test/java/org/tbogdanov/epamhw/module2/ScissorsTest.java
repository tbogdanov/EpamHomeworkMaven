package org.tbogdanov.epamhw.module2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.Paper;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.Scissors;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by Timur Bogdanov on 10.04.18.
 */
public class ScissorsTest {

    private static Scissors scissors;
    private static Paper paper;

    @BeforeEach
    public void init() {
        scissors = new Scissors(Manufacturer.BEIFA, "Sharp Scissors");
        paper = new Paper(Manufacturer.ATTACHE, 3);
    }

    @Test
    public void paperCutTest() {

        String[] messageStrings = {"msg1", "msg2", "msg3"};
        boolean[] erasable = {true, true, false};
        for (int i = 0; i < 3; i++) {
            try {
                paper.addMessage(messageStrings[i], erasable[i]);
            } catch (Exception ex) {
                fail(ex.getMessage());
            }
        }
        Paper[] targetPapers = scissors.cutPaperToMessages(paper);

        assertEquals(3, targetPapers.length);
        for (int i = 0; i < 3; i++) {
            assertEquals(paper.getManufacturer(), targetPapers[i].getManufacturer());
            assertEquals(1, targetPapers[i].getCapacity());
            assertEquals(1, targetPapers[i].getMessageCount());
            assertEquals(paper.getMessage(i).toString(), targetPapers[i].getMessage(0).toString());
        }
    }

    @Test
    public void emptyPaperCutTest() {

        Paper[] targetPapers = scissors.cutPaperToMessages(paper);

        assertTrue(targetPapers.length == 0);

    }


}
