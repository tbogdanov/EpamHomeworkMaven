package org.tbogdanov.epamhw.module2.officesupplies.properties;

import org.tbogdanov.epamhw.module2.officesupplies.supplies.Scissors;

/**
 * Created by Timur Bogdanov on 10.04.18.
 */
public class Message {

    private String message;
    private boolean canBeErased;

    public Message(String msgString, boolean canBeErased) {
        setMessage(msgString, canBeErased);
    }

    public Message(Message orig) {
        this.message = orig.toString();
        this.canBeErased = orig.isErasable();
    }



    public void setMessage(String msgString, boolean canBeErased) {
        this.message = msgString;
        this.canBeErased = canBeErased;
    }

    public boolean isErasable() {
        return canBeErased;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 13 + (message != null? message.hashCode() : 0);
        hash = hash * 31 + (canBeErased ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object cmp) {
        if (this == cmp) {
            return true;
        }

        if (!(cmp instanceof Message)) {
            return false;
        }

        Message cmpAsMessage = (Message) cmp;

        return (this.message.equals(cmpAsMessage.toString()) &&
                this.canBeErased == cmpAsMessage.isErasable());
    }

}
