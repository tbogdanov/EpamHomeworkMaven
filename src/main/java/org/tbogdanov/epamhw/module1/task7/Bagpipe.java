package org.tbogdanov.epamhw.module1.task7;

/**
 * Created by Timur Bogdanov on 11.02.18.
 */

// Overriding example
public class Bagpipe extends MusicalInstrument implements VolumeSetInstrument {

    private static final String SINGLE_SOUND = "skirl";
    private static final String LONG_SOUND_BEGIN = "sk";
    private static final String LONG_SOUND_MID = "i";
    private static final String LONG_SOUND_END = "rl";

    private static final double VOLUME_CAPS_THRESOLD = 50.0;

    private double volume;

    Bagpipe() {
        volume = MAX_VOLUME;
    }

    public String getSound() {
        return affectSoundByVolume(SINGLE_SOUND);
    }

    @Override
    public String getSound(int n) {
        StringBuilder soundSB = new StringBuilder(LONG_SOUND_BEGIN);
        for (int i = 0; i < n; i++) {
            soundSB.append(LONG_SOUND_MID);
        }
        soundSB.append(LONG_SOUND_END);
        return affectSoundByVolume(soundSB.toString());
    }


    @Override
    public void setVolume(double vol) throws IllegalArgumentException {
        if (vol < MIN_VOLUME || vol > MAX_VOLUME) {
            throw new IllegalArgumentException(String.format("Wrong volume! Allowed volume range: %f-%f",
                    MIN_VOLUME, MAX_VOLUME));
        }
        volume = vol;
    }

    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public String affectSoundByVolume(String snd) {
        return (volume >= VOLUME_CAPS_THRESOLD? snd.toUpperCase() : snd);
    }
}
