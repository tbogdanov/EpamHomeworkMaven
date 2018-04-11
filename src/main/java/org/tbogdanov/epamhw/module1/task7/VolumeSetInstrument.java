package org.tbogdanov.epamhw.module1.task7;

/**
 * Created by Timur Bogdanov on 08.04.18.
 */
public interface VolumeSetInstrument {
    /* Assume that we have volume sliders on our instruments.
       Instruments can handle volume in different ways:
       for example the string representation of a sound may
       turn caps ("ding" -> "DING") when the volume
       reaches a specific thresold. It should be specified in
       the affectSoundByVolume() method.
     */

    final double MIN_VOLUME = 0.0;
    final double MAX_VOLUME = 100.0;
    /* You can't really define a non-final non-static field for current module.
     */

    void setVolume(double vol);
    double getVolume();
    String affectSoundByVolume(String snd);

}
