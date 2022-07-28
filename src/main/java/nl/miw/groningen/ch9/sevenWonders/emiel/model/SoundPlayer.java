package nl.miw.groningen.ch9.sevenWonders.emiel.model;

import javafx.scene.media.AudioClip;

import java.util.Objects;

/**
 * @author Emiel Bloem
 * <p>
 * Dit doet het programma
 */
public class SoundPlayer {

    Boolean isPlaying;

    public SoundPlayer() {
        isPlaying = false;
    }

    public void playSoundEffect(String localPath) {
        AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource(localPath)).toExternalForm());
        audioClip.play();
    }

    public void playSoundEffectWithButton(String localPath) {
        AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource(localPath)).toExternalForm());
        if (!isPlaying) {
            audioClip.play();
            isPlaying = true;
        } else {
            audioClip.stop();
            isPlaying = false;
        }
    }
}
