package org.tbogdanov.epamhw.module1.task7;

import java.security.InvalidParameterException;
import java.util.Random;

/**
 * Created by Timur Bogdanov on 11.02.18.
 */
// Additional fields and methods example
public class FortePiano extends MusicalInstrument {
    private static final Random random = new Random();    // No need to generate random seed for each instance.
    private static String[] PIANO_SOUNDS = { "c", "d", "e", "f", "g", "a", "h" }; // see GrandPiano
    private static int MIN_OCTAVE = 1;
    private static int MAX_OCTAVE = 4;

    protected int octave;

    FortePiano() {
        octave = 1;
    }

    @Override
    public String getSound() {
        return PIANO_SOUNDS[random.nextInt(PIANO_SOUNDS.length)]+Integer.toString(octave);
    }

    public void setOctave(int octave) throws InvalidParameterException {
        if (octave < MIN_OCTAVE || octave > MAX_OCTAVE) {
            throw new IllegalArgumentException(String.format("Wrong octave! Allowed octaves: %d-%d",
                    MIN_OCTAVE, MAX_OCTAVE));
        }
        this.octave = octave;
    }

    public int getOctave() {
        return this.octave;
    }

}
