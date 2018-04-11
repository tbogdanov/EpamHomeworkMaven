package org.tbogdanov.epamhw.module2.officesupplies.supplies;

import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Message;

/**
 * Created by Timur Bogdanov on 08.04.18.
 */
public class Paper extends OfficeSupply {

    private final String typeName = "Paper";
    private final int typePrice = 1;

    private Message[] messages;
    private int capacity;
    private int messageCount;

    public Paper(Manufacturer manufacturer, int capacity) {
        super(manufacturer, "Paper");
        this.capacity = capacity;
        messages = new Message[capacity];
        messageCount = 0;
    }

    public void addMessage(String message, boolean canBeErased) throws Exception {
        Message newMessage = new Message(message, canBeErased);
        addMessage(newMessage);
    }

    public void addMessage(Message msg) throws Exception  {
        if (messageCount >= capacity) {
            throw new ArrayIndexOutOfBoundsException("This paper is already full");
        }

        messages[messageCount] = msg;

        messageCount++;
    }

    public Message[] getAllMessages() {
        Message[] retMessages = new Message[messageCount];
        for (int i = 0; i < messageCount; i++) {
            retMessages[i] = this.messages[i];
        }
        return retMessages;
    }

    public Message getMessage(int i) throws IllegalArgumentException {
        if (i < 0 || i >= capacity) {
            throw new IllegalArgumentException("Out of paper bounds");
        }
        if (i >= messageCount) {
            throw new IllegalArgumentException("No such message");
        }
        return messages[i];
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public boolean isFull() {
        return (messageCount >= capacity);
    }

    public boolean isEmpty() {
        return (messageCount == 0);
    }

    @Override
    public String toString() {
        return String.format("Paper %s %s", manufacturer.toString(), name);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 13 + (manufacturer != null? manufacturer.ordinal() : 0);  // By manufacturer id
        hash = hash * 13 + name.hashCode();     // By name
        for (int i = 0; i < messageCount; i++) {    // Order matters too
            hash = hash * 13 + (messages[i] != null ? messages[i].hashCode() : 0);
        }
        return hash;
    }

    @Override
    public boolean equals(Object cmp) {
        if (this == cmp) {
            return true;
        }

        if (!(cmp instanceof Paper)) {
            return false;
        }

        Paper cmpAsPaper = (Paper) cmp;

        return (this.manufacturer == cmpAsPaper.getManufacturer() &&
                this.name.equals(cmpAsPaper.getName()));
    }

}
