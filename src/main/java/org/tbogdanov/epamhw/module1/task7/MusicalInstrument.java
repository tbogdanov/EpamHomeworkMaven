package org.tbogdanov.epamhw.module1.task7;

/**
 * Created by Timur Bogdanov on 11.02.18.
 */

/* Inheritance hierarchy:
   Bagpipe -> MusicalInstrument
   GrandPiano -> FortePiano -> MusicalInstrument
 */
public abstract class MusicalInstrument {

    public abstract String getSound();

    public String getSound(int n) {
        String sound = new String();
        for (int i = 0; i < n; i++) {
            sound = sound.concat(getSound() + " ");
        }
        return sound;
    }

}
