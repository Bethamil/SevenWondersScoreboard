package nl.miw.groningen.ch9.sevenWonders.emiel.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import nl.miw.groningen.ch9.sevenWonders.emiel.App;
import nl.miw.groningen.ch9.sevenWonders.emiel.databases.mysql.GameResultsDAO;
import nl.miw.groningen.ch9.sevenWonders.emiel.model.GameResults;
import nl.miw.groningen.ch9.sevenWonders.emiel.model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

/**
 * @author Emiel Bloem
 * <p>
 * Get stats from last played matches
 */
public class StatsController implements Initializable {
    @FXML
    public Label infoLabelTotal, infoLabelTotalWon, infoLabelWar, infoLabelScience, infoLabelPoints;
    @FXML
    public StackPane statPlayerPanel, statLast2MatchesPanel;
    @FXML
    public Label labelLast2Matches1, labelLast2Matches2;
    @FXML
    private ChoiceBox<String> nameBox;

    public void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
    public void getStatsByChosenPlayer() {
        statPlayerPanel.setVisible(true);
        statLast2MatchesPanel.setVisible(false);
        GameResultsDAO gameResultsDAO = new GameResultsDAO(App.getDbAccess());
        App.getDbAccess().openConnection();
        int[] stats = gameResultsDAO.getGameStatsByPlayer(new Player(nameBox.getValue()));

        infoLabelTotal.setText(String.valueOf(stats[0]));
        infoLabelTotalWon.setText(String.valueOf(IntStream.of(stats).sum() - stats[0]));
        infoLabelWar.setText(String.format(String.valueOf(stats[1])));
        infoLabelScience.setText(String.format(String.valueOf(stats[2])));
        infoLabelPoints.setText(String.format(String.valueOf(stats[3])));

        App.getDbAccess().closeConnection();
    }

    public void showLast2Matches() {
        statPlayerPanel.setVisible(false);
        statLast2MatchesPanel.setVisible(true);
        GameResultsDAO gameResultsDAO = new GameResultsDAO(App.getDbAccess());
        App.getDbAccess().openConnection();
        List<GameResults> listLast2Matches = gameResultsDAO.getLatest2Games();
        App.getDbAccess().closeConnection();
        labelLast2Matches1.setText(listLast2Matches.get(0).toString());
        if (listLast2Matches.size() > 1) {
            labelLast2Matches2.setText(listLast2Matches.get(1).toString());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] names = App.getAllPlayersForChoiceBox();
        nameBox.getItems().addAll(names);
    }
}
