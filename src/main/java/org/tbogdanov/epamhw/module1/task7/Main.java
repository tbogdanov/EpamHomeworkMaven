package org.tbogdanov.epamhw.module1.task7;

import java.util.Random;

/**
 * Created by Timur Bogdanov on 11.02.18.
 */
public class Main {
    private static final Random random = new Random();

    public static void printSound(MusicalInstrument instrument) {
        System.out.printf("%s goes %s!\n", instrument.getClass().getName(), instrument.getSound());
    }

    public static void printSound(MusicalInstrument instrument, int n) {
        System.out.printf("%s goes %s!\n", instrument.getClass().getName(), instrument.getSound(n));
    }

    public static void main(String[] args) {
        Bagpipe bagpipe = new Bagpipe();
        FortePiano fortePiano = new FortePiano();
        GrandPiano grandPiano = new GrandPiano();
        MusicalInstrument[] orchestra = {bagpipe, fortePiano, grandPiano};

        // Example 1: Polymorphism
        for (MusicalInstrument instrument: orchestra) {
            printSound(instrument, random.nextInt(5) + 2);
        }
        /* Result:
           All instruments play in a single loop using their similar method.
           We used GrandPiano.getSound(int n) which hasn't been overriden
           from FortePiano, so we got the sounds from static FortePiano.PIANO_SOUNDS
           array. In Example 3 we'll try another case: getting from PIANO_SOUNDS
           using a unique method.
         */

        // Example 2: Setting volume levels for an instance of Bagpipe
        try {
            bagpipe.setVolume(25.0);
            System.out.println(bagpipe.getSound());
            bagpipe.setVolume(75.0);
            System.out.println(bagpipe.getSound());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        /* Result: works as intended.
           volume 25.0: skirl
           volume 75.0: SKIRL
         */

        // Example 3: GrandPiano.getSpecificSound()
        System.out.println(grandPiano.getSpecificSound(2));
        /* Result:
           Unique methods, like the overridden ones, use GrandPiano.PIANO_SOUNDS,
           as opposed to those who haven't been overridden, that use FortePiano.PIANO_SOUNDS.
           Examples 1 and 3 demonstrate hiding of static fields.
         */

        // Example 4: Setting an octave supported by GrandPiano, but unsupported by FortePiano
        try {
            grandPiano.setOctave(5);
            System.out.println(grandPiano.getSound());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        /* Result:
           We haven't overridden GrandPiano.getSound(), so GrandPiano
           uses the octave range from its ancestor, FortePiano.
           The exception will be thrown.
         */

        // Example 5: GrandPiano as FortePiano
        try {
            System.out.println(((FortePiano) grandPiano).getSound());
            //((FortePiano) grandPiano).getSpecificSound(1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        /* Result:
           ((FortePiano) grandPiano).getSpecificSound(1) won't work for an obvious reason:
           FortePiano doesn't have this method.
           getSound() will use FortePiano.PIANO_SOUNDS.
         */

        // Example 6: FortePiano as GrandPiano
        try {
            printSound((GrandPiano) fortePiano);
            ((GrandPiano) fortePiano).getSpecificSound(1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        /* Result:
           This casting is illegal. Any of those 2 lines will throw an exception.
         */





    }






}
