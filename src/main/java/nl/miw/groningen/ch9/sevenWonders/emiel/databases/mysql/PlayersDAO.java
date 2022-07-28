package nl.miw.groningen.ch9.sevenWonders.emiel.databases.mysql;

import nl.miw.groningen.ch9.sevenWonders.emiel.model.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emiel Bloem
 * <p>
 * Adds and get players from a MYSQL DB
 */
public class PlayersDAO extends AbstractDAO {
    public PlayersDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    public List<Player> getALlPlayers() {
        String sql = "SELECT name FROM sevenwondersscoreboard.players;";
        List<Player> allNames = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                allNames.add(new Player(name));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allNames;
    }

    public String addPlayer(Player player){
        String sql = "INSERT INTO SevenWondersScoreboard.players (`name`) VALUES (?)";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1,player.getName());
            executeManipulateStatement();
        } catch (SQLException e) {
            System.err.println("An error occurred in SQL: " + e.getMessage());
            return String.format("%s already exists", player.getName());
        }
        return String.format("%s has been added", player.getName());
    }
}
