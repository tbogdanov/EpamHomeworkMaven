package org.tbogdanov.epamhw.module1.task6;

import java.util.Arrays;

/**
 * Created by Timur Bogdanov on 10.02.18.
 */

/**
 * The notebook class that contains NotebookEntry objects in an array.
 */
public class Notebook {
    /**
     * The entries' array of NotebookEntry.
     */
    private NotebookEntry[] entries;
    /**
     * Default length of the entries' array
     * (don't mix with the actual number of entries)
     */
    private static final int DEF_LEN = 10;
    /**
     * The factor that enlarge() uses to enlarge the entries' array when it's full
     */
    private static final int LEN_ENLARGE_FACTOR = 2;
    /**
     * The factor that shrink() uses to shrink the entries' array when it has too
     * few actual entries (too few as in LEN_SHRINK_FACTOR * 100%,
     * for example for LEN_SHRINK_FACTOR = 0.5 it's 50% of the array's length)
     */
    private static final double LEN_SHRINK_FACTOR = 0.5;
    /**
     * The current actual number of entries
     */
    private int length;

    /**
     * Initiates a new notebook with DEF_LEN empty entries.
     */
    Notebook() {
        length = 0;
        entries = new NotebookEntry[DEF_LEN];
    }

    /**
     * Adds an instance of class NootebookEntry to the entries' array.
     * @param title Specifies the title of the entry.
     * @param text Specifies the text of the entry.
     */
    public void addEntry(String title, String text) {
        NotebookEntry newEntry = new NotebookEntry(title, text);
        entries[length] = newEntry;
        length++;
        optimizeLength();
    }

    /**
     * Removes an entry with specific id.
     * @param id Specifies the id of the entry to be deleted.
     */
    public void removeEntry(int id) {
        if ((id >= length) || (id < 0)) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds");
        }

        if (id == length - 1) {
            entries[id] = null;
        }
        else {
            for (int i = id; i < length - 1; i++) {
                entries[i] = entries[i+1];
            }
            entries[length] = null;
        }

        length--;
        optimizeLength();
    }

    /**
     * Edit the entry specified by the id.
     * @param id An id of the entry to edit.
     * @param title A new title for the entry.
     * @param text A new text for the entry.
     * @throws Exception
     */
    public void editEntry(int id, String title, String text)  throws Exception {
        if ((id >= length) || (id < 0)) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds");
        }

        if (entries[id] == null) {
            throw new Exception("No such entry");
        }

        entries[id].setText(text);
        entries[id].setTitle(title);
    }

    /**
     * Get the title of an entry of the specific id.
     * @param id Specifies an id of the entry.
     * @return A string with the title.
     * @throws Exception A "No such entry" exception is thrown when the entry is null.
     */
    public String getTitle(int id) throws Exception {
        if ((id >= length) || (id < 0)) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds");
        }

        if (entries[id] == null) {
            throw new Exception("No such entry");
        }

        return entries[id].getTitle();
    }

    /**
     * Get titles of all entries
     * @return A string with titles in the "#id: Title" format
     * @throws Exception A "No such entry" exception is thrown when one of entries is null.
     */
    public String getAllTitles() throws Exception {
        String allTitleStr = new String();
        for (int i = 0; i < length; i++) {
            allTitleStr = allTitleStr.concat(String.format("#%d: %s\n", i, getTitle(i)));
        }
        return allTitleStr;
    }

    /**
     * Get the text of an entry of the specific id.
     * @param id Specifies an id of the entry.
     * @return A string with the text.
     * @throws Exception A "No such entry" exception is thrown when the entry is null.
     */
    public String getText(int id) throws Exception {
        if ((id >= length) || (id < 0)) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds");
        }

        if (entries[id] == null) {
            throw new Exception("No such entry");
        }

        return entries[id].getText();
    }

    /**
     * Get titles and texts of all entries
     * @return A string with titles in the following format:<br/>
     * "#id: Title<br/>
     * Text"
     * @throws Exception A "No such entry" exception is thrown when one of entries is null.
     */
    public String getAllStrings() throws Exception {
        String allStr = new String();
        for (int i = 0; i < length; i++) {
            allStr = allStr.concat(String.format("#%d: %s\n", i, getTitle(i)));
            allStr = allStr.concat(getText(i) + "\n\n");
        }
        return allStr;
    }


    /**
     * Get the actual number of entries that may be less than the length of the entries' array.
     * @return The actual number of entries.
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Optimizes the length of entries' array.<br/>
     * When the actual number of entries is greater than or equals to maximum length of the array,
     * this method calls enlarge()
     * When the actual number of entries is less than the maximum length * LEN_SHRINK_FACTOR,
     * this method calls shrink()
     * Refer to that methods' description.
     */
    private void optimizeLength() {
        if (length >= entries.length) {
            enlarge();
        }
        else if (length < (int)Math.round(this.length*LEN_SHRINK_FACTOR)) {
            shrink();
        }
    }

    /**
     * Enlarges the length of the entries' array by LEN_ENLARGE_FACTOR
     */
    private void enlarge() {
        entries = Arrays.copyOf(entries, this.entries.length*LEN_ENLARGE_FACTOR);
    }

    /**
     * Shrinks the entries' array so its length equals to length * LEN_SHRINK_FACTOR
     * (for example if we have LEN_SHRINK_FACTOR = 0.5, it will be shrunk twofold)
     */
    private void shrink() {
        int newLength = (int)Math.round(this.entries.length*LEN_SHRINK_FACTOR);
        this.entries = Arrays.copyOf(this.entries, newLength);
    }

}
