package nl.miw.groningen.ch9.sevenWonders.emiel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nl.miw.groningen.ch9.sevenWonders.emiel.App;
import nl.miw.groningen.ch9.sevenWonders.emiel.databases.mysql.PlayersDAO;
import nl.miw.groningen.ch9.sevenWonders.emiel.model.Player;

import java.io.IOException;

/**
 * @author Emiel Bloem
 * <p>
 * Enter a name in the DB
 */
public class EnterNameController {
    @FXML
    public TextField enterNameTextField;
    @FXML
    Label infoLabel;

    public void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
    public void addNameToDB() {
        String namePlayer = enterNameTextField.getText().trim();
        if (namePlayer.equals("")) {
            infoLabel.setText("Enter a valid name");
        } else {
            System.out.println(namePlayer);
            try {
                App.getDbAccess().openConnection();
                PlayersDAO playersDAO = new PlayersDAO(App.getDbAccess());

                String message = playersDAO.addPlayer(new Player(namePlayer));
                infoLabel.setText(message);
                enterNameTextField.clear();
            } catch (NullPointerException n) {
                infoLabel.setText("SQL Error");
                System.out.println("SQL error: " + n);
            }
            App.getDbAccess().closeConnection();
        }
    }
}
