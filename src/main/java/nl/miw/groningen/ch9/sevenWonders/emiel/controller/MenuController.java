package nl.miw.groningen.ch9.sevenWonders.emiel.controller;

import javafx.fxml.FXML;
import nl.miw.groningen.ch9.sevenWonders.emiel.App;

import java.io.IOException;

/**
 * @author Emiel Bloem
 * <p>
 * Starting menu
 */
public class MenuController {
    @FXML
    private void switchToEnterScores() throws IOException {
        App.setRoot("enterscores");
    }

    @FXML
    private void switchToEnterNames() throws IOException {
        App.setRoot("enterNames");
    }
    @FXML
    private void switchToShowStats() throws IOException{
        try {
            App.showLast2Matches();
        } catch (Exception e) {
            System.err.println("Error: " +e);
            App.setRoot("showStats");
        }
    }

    @FXML
    private void switchToSoundeffects() throws IOException {
        App.setRoot("soundeffects");
    }

}
