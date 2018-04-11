package org.tbogdanov.epamhw.module3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tbogdanov.epamhw.module3.crazylogger.CrazyLogger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Timur Bogdanov on 11.04.18.
 */
public class CrazyLoggerTest {

    private CrazyLogger crazyLogger;

    @BeforeEach
    public void init() {
        crazyLogger = new CrazyLogger();
    }

    @Test
    public void getAllEmptyTest() {
        assertTrue(crazyLogger.getAll().isEmpty());
    }

    @Test
    public void getAllMessagesTest() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY ':' hh-mm");
        String datePrefix = dateFormat.format(new Date()) + " - ";

        String[] messages = {"First", "Second", "Third"};

        for (String message : messages) {
            crazyLogger.put(message);
        }

        List<String> logMessages = crazyLogger.getAll();

        assertEquals(messages.length, logMessages.size());

        int i = 0;
        for (String logMessage: logMessages) {
            assertEquals(datePrefix+messages[i], logMessage);
            i++;
        }
    }

    @Test
    public void getMessageByKeywordTest() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY ':' hh-mm");
        String datePrefix = dateFormat.format(new Date()) + " - ";

        String[] messages = {"KERNEL PANIC", "THE SERVER IS DOWN", "WE ARE DOOMED"};

        for (String message : messages) {
            crazyLogger.put(message);
        }

        String foundMessage = crazyLogger.getMessageByKeyword("SERVER");
        assertEquals(datePrefix+messages[1], foundMessage);
    }

    @Test
    public void getMessageByInvalidKeywordTest() {
        crazyLogger.put("We have this");

        String foundMessage = crazyLogger.getMessageByKeyword("that");

        assertNull(foundMessage);
    }

    @Test
    public void getMessageByEmptyKeywordTest() {
        crazyLogger.put("We have this");

        String foundMessage = crazyLogger.getMessageByKeyword("");

        assertNull(foundMessage);
    }

    @Test
    public void getMessagesFromEmptyLoggerTest() {
        String foundMessage = crazyLogger.getMessageByKeyword("that");
        assertNull(foundMessage);
    }
}
