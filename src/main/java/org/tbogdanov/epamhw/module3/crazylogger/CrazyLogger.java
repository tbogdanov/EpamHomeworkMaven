package org.tbogdanov.epamhw.module3.crazylogger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Timur Bogdanov on 11.04.18.
 */
public class CrazyLogger {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY ':' hh-mm");

    private StringBuilder logs;

    public CrazyLogger() {
        logs = new StringBuilder();
    }

    public void put(String msg) {
        logs.append(String.format("%s - %s\n", dateFormat.format(new Date()), msg));
    }

    public List<String> getAll() {
        List<String> messages = new LinkedList<>();
        int leftIndex = 0;
        int rightIndex = logs.indexOf("\n", leftIndex);
        while (rightIndex >= 0) {
            messages.add(logs.substring(leftIndex, rightIndex));
            leftIndex = rightIndex+1;
            rightIndex = logs.indexOf("\n", leftIndex);
        }
        return messages;
    }

    public String getMessageByKeyword(String keyword) {
        if (logs.length() == 0 || keyword.length() == 0) {
            return null;
        }
        int startIndex = logs.indexOf(keyword);
        if (startIndex < 0) {
            return null;
        }
        int rightMessageIndex = logs.indexOf("\n", startIndex);
        int leftMessageIndex = logs.substring(0, startIndex).lastIndexOf("\n") + 1;
        return logs.substring(leftMessageIndex, rightMessageIndex);
    }
}
