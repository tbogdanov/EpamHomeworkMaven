package org.tbogdanov.epamhw.module1.task6;

import java.util.Random;

/**
 * Created by Timur Bogdanov on 10.02.18.
 */

/**
 * Just an example on usage of Notebook class.
 */
public class Main {
    /**
     * A template for entry titles from the "99 bottles of beer on the wall" song.
     * It's a format string that includes a decimal field for the number of bottles.
     */
    private static final String BEER_TITLE_F = "%d bottles of beer on the wall";
    /**
     * A template for entry texts from the "99 bottles of beer on the wall" song.
     * It's a format string that includes 3 decimal fields for the number of bottles.
     */
    private static final String BEER_TEXT_F = "%d bottles of beer on the wall, %d bottles of beer.\n" +
            "Take one down and pass it around, %d bottles of beer on the wall.";

    /**
     * An example on usage of Notebook class that involves adding, deleting and reading entries.
     * @param args A number of entries added.
     */
    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("At least 1 argument (n) needed!");
            System.exit(1);
        }

        Notebook noteBook = new Notebook();

        int entryNumber = Integer.parseInt(args[0]);

        if (entryNumber < 1) {
            System.err.println("Too few entries to test!");
            System.exit(1);
        }

        for (int i = entryNumber; i > 0; i--) {
            noteBook.addEntry(String.format(BEER_TITLE_F, i), String.format(BEER_TEXT_F, i, i, i-1));
        }

        try {
            System.out.println(noteBook.getAllTitles());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        // Remove a random entry
        Random random = new Random();
        int entryDeletedId = random.nextInt(entryNumber);
        noteBook.removeEntry(entryDeletedId);

        // Edit the last entry
        try {
            noteBook.editEntry(noteBook.getLength() - 1, "Edited", "No beer for you!");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        // Show all entries with titles and texts
        try {
            System.out.println(noteBook.getAllStrings());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        // Remove all entries
        while (noteBook.getLength() > 0) {
            noteBook.removeEntry(0);
        }

        // Show the empty notebook
        try {
            System.out.println(noteBook.getAllTitles());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
