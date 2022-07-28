package nl.miw.groningen.ch9.sevenWonders.emiel.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nl.miw.groningen.ch9.sevenWonders.emiel.App;
import nl.miw.groningen.ch9.sevenWonders.emiel.databases.mysql.GameResultsDAO;
import nl.miw.groningen.ch9.sevenWonders.emiel.model.GameResults;
import nl.miw.groningen.ch9.sevenWonders.emiel.model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Emiel Bloem
 * <p>
 * Enter and calculate scores
 */

public class ScoreBoardController implements Initializable {
    @FXML private TextField p1blue, p1green, p1yellow, p1purple, p1wonders, p1science, p1coins, p1war;
    @FXML private TextField p2blue, p2green, p2yellow, p2purple, p2wonders, p2science, p2coins, p2war;
    @FXML private Label errorLabel;
    @FXML private CheckBox p1WonByWar, p1WonByScience, p2WonByWar, p2WonByScience;
    @FXML private ChoiceBox<String> p1NameFromBox, p2NameFromBox;


    @FXML private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
    @FXML private void enterScores() {
        String p1Name = p1NameFromBox.getValue();
        String p2Name = p2NameFromBox.getValue();

        Player player1 = new Player(p1Name);
        Player player2 = new Player(p2Name);
        GameResults gameResults = new GameResults(player1, player2);

        if (p1WonByScience.isSelected()) {
            gameResults.setWonBy("Science");
            gameResults.setWinner(player1);
        } else if (p2WonByScience.isSelected()) {
            gameResults.setWonBy("Science");
            gameResults.setWinner(player2);
        } else if (p1WonByWar.isSelected()) {
            gameResults.setWonBy("War");
            gameResults.setWinner(player1);
        } else if (p2WonByWar.isSelected()) {
            gameResults.setWonBy("War");
            gameResults.setWinner(player2);
        } else {
            try {
                player1.fillScoreArray(p1blue, p1green, p1yellow, p1purple, p1wonders, p1science, p1coins, p1war);
                player2.fillScoreArray(p2blue, p2green, p2yellow, p2purple, p2wonders, p2science, p2coins, p2war);
                gameResults.calculateWinnerByScore(player1, player2);

            } catch (NumberFormatException e) {
                errorLabel.setText("Enter all fields with numbers");
                System.err.println("Error: " + e);
            }
        }

        if (p1Name == null || p2Name == null) {
            errorLabel.setText("Choose a name for both players");
        } else if (!gameResults.getWonBy().equals("null")) {
            System.out.println(gameResults);

            try {
                GameResultsDAO gameResultsDAO = new GameResultsDAO(App.getDbAccess());
                App.getDbAccess().openConnection();
                gameResultsDAO.saveGameStats(gameResults);
                App.getDbAccess().closeConnection();
            }
            catch (NullPointerException n){
                errorLabel.setText("SQL error - Your game will not be saved");
                System.out.println("SQL error" + n.getMessage());
            }
            System.out.println(gameResults);
            App.showFinalResults(gameResults);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String[] names = App.getAllPlayersForChoiceBox();
            p1NameFromBox.getItems().addAll(names);
            p2NameFromBox.getItems().addAll(names);

        } catch (NullPointerException n) {
            p1NameFromBox.getItems().addAll("Player 1");
            p2NameFromBox.getItems().addAll("Player 2");
            errorLabel.setText("SQL error - Your game will not be saved");
            System.out.println("SQL error" + n.getMessage());
        }


    }
}