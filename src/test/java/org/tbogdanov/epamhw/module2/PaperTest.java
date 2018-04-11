package org.tbogdanov.epamhw.module2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Message;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.Paper;

/**
 * Created by Timur Bogdanov on 10.04.18.
 */
public class PaperTest {

    Paper paper;

    @BeforeEach
    public void init() {
        paper = new Paper(Manufacturer.ATTACHE, 3);
    }

    @Test
    public void emptyTest() {
        assertTrue(paper.isEmpty());
        assertEquals(0, paper.getMessageCount());
    }

    @Test
    public void getSizeTest() {
        try {
            paper.addMessage("Test1", true);
            paper.addMessage("Test2", true);
            assertEquals(2, paper.getMessageCount());
            paper.addMessage("Test3", true);
            assertEquals(3, paper.getMessageCount());
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void overflowTest() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            for (int i = 0; i < 5; i++) {
                paper.addMessage("Test", true);
            }
        });
    }

    @Test
    public void getAllMessagesTest() {
        String[] messageStrings = {"msg1", "msg2", "msg3"};
        boolean[] erasable = {true, true, false};
        for (int i = 0; i < 3; i++) {
            try {
                paper.addMessage(messageStrings[i], erasable[i]);
            } catch (Exception ex) {
                fail(ex.getMessage());
            }
        }
        Message[] messages = paper.getAllMessages();
        for (int i = 0; i < 3; i++) {
            assertEquals(messageStrings[i], messages[i].toString());
        }
    }

    @Test
    public void getSpecificMessageTest() {
        String[] messageStrings = {"msg1", "msg2", "msg3"};
        boolean[] erasable = {true, true, false};
        for (int i = 0; i < 3; i++) {
            try {
                paper.addMessage(messageStrings[i], erasable[i]);
            } catch (Exception ex) {
                fail(ex.getMessage());
            }
        }
        String messageStr1 = paper.getMessage(1).toString();
        assertEquals(messageStrings[1], messageStr1);
    }

    @Test
    public void getWrongMessageIndexTest() {
        String[] messageStrings = {"msg1", "msg2"};
        boolean[] erasable = {true, true};
        for (int i = 0; i < 2; i++) {
            try {
                paper.addMessage(messageStrings[i], erasable[i]);
            } catch (Exception ex) {
                fail(ex.getMessage());
            }
        }
        assertThrows(IllegalArgumentException.class, () -> {
            paper.getMessage(2);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            paper.getMessage(-1);
        });
    }
}
