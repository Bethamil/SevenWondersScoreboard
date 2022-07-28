package nl.miw.groningen.ch9.sevenWonders.emiel.controller;

import javafx.fxml.FXML;
import nl.miw.groningen.ch9.sevenWonders.emiel.App;
import nl.miw.groningen.ch9.sevenWonders.emiel.model.SoundPlayer;

import java.io.IOException;

/**
 * @author Emiel Bloem
 * <p>
 * sounds for Soundeffects
 */
public class SoundEffectsController {

    SoundPlayer soundPlayer = new SoundPlayer();

    @FXML
    public void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
    @FXML
    public void warHorn() {
        soundPlayer.playSoundEffectWithButton("/sounds/war/war_horn.wav");
    }
    @FXML
    public void warDrum() {
        soundPlayer.playSoundEffectWithButton("/sounds/war/war_drums.wav");
    }
    @FXML
    public void warFight() {
        soundPlayer.playSoundEffectWithButton("/sounds/war/medieval_combat.mp3");
    }
    @FXML
    public void scienceAccelerator() {
        soundPlayer.playSoundEffectWithButton("/sounds/science/accelerator.wav");
    }

    @FXML
    public void scienceSine() {
        soundPlayer.playSoundEffectWithButton("/sounds/science/sine_sweep.wav");
    }
    @FXML
    public void scienceMachine() {
        soundPlayer.playSoundEffectWithButton("/sounds/science/vintage_machine.mp3");
    }

    @FXML
    public void otherFantasy() {
        soundPlayer.playSoundEffectWithButton("/sounds/other/fantasy_classical.mp3");
    }

    @FXML
    public void otherVictory() {
        soundPlayer.playSoundEffectWithButton("/sounds/other/victory.wav");
    }

    @FXML
    public void otherTrumpets() {
        soundPlayer.playSoundEffectWithButton("/sounds/other/trumpets.wav");
    }


}
