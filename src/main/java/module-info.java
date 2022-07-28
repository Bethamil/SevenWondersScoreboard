module nl.miw.groningen.ch9.sevenWonders.emiel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;

    opens nl.miw.groningen.ch9.sevenWonders.emiel to javafx.fxml;
    exports nl.miw.groningen.ch9.sevenWonders.emiel;
    exports nl.miw.groningen.ch9.sevenWonders.emiel.controller;
    opens nl.miw.groningen.ch9.sevenWonders.emiel.controller to javafx.fxml;
}
