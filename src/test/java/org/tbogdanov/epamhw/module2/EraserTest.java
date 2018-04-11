package org.tbogdanov.epamhw.module2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Message;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.Eraser;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.Paper;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.Scissors;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by Timur Bogdanov on 10.04.18.
 */
public class EraserTest {

    private static Eraser eraser;
    private static Paper paper;

    @BeforeEach
    public void init() {
        eraser = new Eraser(Manufacturer.BEIFA, "Rubber Eraser");
        paper = new Paper(Manufacturer.ATTACHE, 3);
    }

    @Test
    public void eraseMessagesTest() {
        String[] messageStrings = {"msg1", "msg2", "msg3"};
        boolean[] erasable = {false, true, false};
        for (int i = 0; i < 3; i++) {
            try {
                paper.addMessage(messageStrings[i], erasable[i]);
            } catch (Exception ex) {
                fail(ex.getMessage());
            }
        }

        Paper targetPaper = eraser.getPaperWithoutErasableMessages(paper);

        assertEquals(3, targetPaper.getCapacity());
        assertEquals(2, targetPaper.getMessageCount());

        Message[] remainingMessages = targetPaper.getAllMessages();

        for (Message msg : remainingMessages) {
            assertFalse(msg.isErasable());
        }
    }

    @Test
    public void emptyPaperEraseTest() {
        Paper targetPaper = eraser.getPaperWithoutErasableMessages(paper);
        assertEquals(paper, targetPaper);
    }


}
