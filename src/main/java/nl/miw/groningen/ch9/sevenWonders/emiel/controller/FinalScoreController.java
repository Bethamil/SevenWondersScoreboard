package nl.miw.groningen.ch9.sevenWonders.emiel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import nl.miw.groningen.ch9.sevenWonders.emiel.App;
import nl.miw.groningen.ch9.sevenWonders.emiel.model.GameResults;
import nl.miw.groningen.ch9.sevenWonders.emiel.model.SoundPlayer;

import java.io.IOException;


/**
 * @author Emiel Bloem
 * <p>
 * Shows the game results
 */
public class FinalScoreController {
    @FXML
    private Label winnerLabel;
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

    public void getGameResults(GameResults gameResult) {
        if (gameResult.getWonBy().equals("Draw")) {
            winnerLabel.setText(String.format("Draw\n%d-%d", gameResult.getScoreP1(), gameResult.getScoreP2()));
        } else {
            StringBuilder str = new StringBuilder(gameResult.getWinner().getName() + "\nWon by\n");
            if (gameResult.getWonBy().equals("Points")) {
                str.append(String.format("%d-%d", gameResult.getScoreP1(), gameResult.getScoreP2()));
            }
            else {
                str.append(gameResult.getWonBy());
            }
            winnerLabel.setText(str.toString());
        }
        soundsFinalScore(gameResult);
    }

    public void soundsFinalScore(GameResults gameResults){
        int random = (int) (Math.random()*3);
        SoundPlayer soundPlayer = new SoundPlayer();
        String wonBy = gameResults.getWonBy();
        switch (wonBy) {
            case "War": {
                String[] musicPath = {"medieval_combat.mp3", "medieval-rpg-music.wav", "war_drums.wav"};
                soundPlayer.playSoundEffect("/sounds/war/" + musicPath[random]);
                break;
            }
            case "Science": {
                String[] musicPath = {"accelerator.wav", "sine_sweep.wav", "vintage_machine.mp3"};
                soundPlayer.playSoundEffect("/sounds/science/" + musicPath[random]);
                break;
            }
            case "Points": {
                String[] musicPath = {"trumpets.wav", "victory.wav", "victory_group.wav"};
                soundPlayer.playSoundEffect("/sounds/other/" + musicPath[random]);
                break;
            }
            default:
                soundPlayer.playSoundEffect("/sounds/other/draw.wav");
                break;
        }
    }
}


