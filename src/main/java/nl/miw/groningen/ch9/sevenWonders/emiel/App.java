package nl.miw.groningen.ch9.sevenWonders.emiel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.miw.groningen.ch9.sevenWonders.emiel.controller.FinalScoreController;
import nl.miw.groningen.ch9.sevenWonders.emiel.controller.StatsController;
import nl.miw.groningen.ch9.sevenWonders.emiel.databases.mysql.DBAccess;
import nl.miw.groningen.ch9.sevenWonders.emiel.databases.mysql.PlayersDAO;
import nl.miw.groningen.ch9.sevenWonders.emiel.model.GameResults;
import java.io.IOException;

/**
 * SevenWondersScoreBoard JavaFX App. This app can replace your scorecard from the boardgames Seven Wonders Duel.
 * All results are saved in a MYSQL DB, the insert script can be found in the resources map.
 */
public class App extends Application {


    private static Scene scene;
    private static DBAccess dbAccess = null;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("menu"), 500, 760);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void showFinalResults(GameResults gameResults) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/displayResults.fxml"));
        try {
            Parent parent = fxmlLoader.load();
            FinalScoreController finalScoreController = fxmlLoader.getController();
            finalScoreController.getGameResults(gameResults);
            scene.setRoot(parent);
        } catch (IOException e) {
            System.err.println("Unable to load finalScore screen");
        }
    }

    public static void showLast2Matches() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/showStats.fxml"));
        try {
            Parent parent = fxmlLoader.load();
            StatsController statsController = fxmlLoader.getController();
            statsController.showLast2Matches();
            scene.setRoot(parent);
        } catch (IOException e) {
            System.err.println("Unable to load finalScore screen");
        }
    }

    public static DBAccess getDbAccess() {
        if (dbAccess == null) {
            dbAccess=new DBAccess("SevenWondersScoreboard","admin", "password");
        }
        return dbAccess;
    }
    public static String[] getAllPlayersForChoiceBox() {
        App.getDbAccess().openConnection();
        PlayersDAO playersDAO = new PlayersDAO(App.getDbAccess());
        String[] names = new String[playersDAO.getALlPlayers().size()-1];
        for (int i = 0; i < playersDAO.getALlPlayers().size(); i++) {
            if (!playersDAO.getALlPlayers().get(i).getName().equals("Unknown")) {
                names[i] = playersDAO.getALlPlayers().get(i).getName();
            }
        }
        App.getDbAccess().closeConnection();
        return names;
    }

    public static void main(String[] args) {
        launch(args);
    }
}