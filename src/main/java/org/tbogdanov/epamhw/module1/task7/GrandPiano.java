package org.tbogdanov.epamhw.module1.task7;

/**
 * Created by Timur Bogdanov on 11.02.18.
 */

// Multiple inheritance with additional methods example
public class GrandPiano extends FortePiano {
    private static String[] PIANO_SOUNDS = { "C", "D", "E", "F", "G", "A", "H" };
    private static int MIN_OCTAVE = 0;
    private static int MAX_OCTAVE = 5;

    private boolean pedal = false;

    GrandPiano() {
        this.octave = 2;
    }



    public String getSpecificSound(int note) {
        if (note >= 0 || note < PIANO_SOUNDS.length) {
            String sound = PIANO_SOUNDS[note]+Integer.toString(octave);
            return (pedal ? sound.toLowerCase() : sound);
        }
        else {
            return "";
        }
    }

    public void setPedal(boolean pedal) {
        this.pedal = pedal;
    }

    public boolean getPedal() {
        return this.pedal;
    }

}
