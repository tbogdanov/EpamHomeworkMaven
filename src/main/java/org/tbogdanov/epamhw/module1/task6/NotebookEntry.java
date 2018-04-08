package org.tbogdanov.epamhw.module1.task6;

/**
 * Created by Timur Bogdanov on 10.02.18.
 */

/**
 * The notebook entry class that has a title and a text.
 */
public class NotebookEntry {
    /**
     * The string with a title of the entry.
     */
    private String title;
    /**
     * The string with a text of the entry.
     */
    private String text;

    /**
     * Initiates a new notebook entry.
     * @param title Specifies the title of the entry.
     * @param text Specifies the text of the entry.
     */
    NotebookEntry(String title, String text) {
        this.title = title;
        this.text = text;
    }

    /**
     * Returns the title of the entry.
     * @return A string with the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a new title for the entry.
     * @param title Specifies a string with the new title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the text of the entry.
     * @return A string with the text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets a new text for the entry.
     * @param text Specifies a string with the new text.
     */
    public void setText(String text) {
        this.text = text;
    }

}
