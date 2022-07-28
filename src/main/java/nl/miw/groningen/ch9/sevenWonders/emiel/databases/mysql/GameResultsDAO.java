package nl.miw.groningen.ch9.sevenWonders.emiel.databases.mysql;

import nl.miw.groningen.ch9.sevenWonders.emiel.model.GameResults;
import nl.miw.groningen.ch9.sevenWonders.emiel.model.Player;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Emiel Bloem
 * <p>
 * Saves and get gamresults
 */
public class GameResultsDAO extends AbstractDAO{
    public GameResultsDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    public void saveGameStats(GameResults gameResults){
        String sql = "INSERT INTO gameresults (date, player_1, player_2, winner, won_by, score_p1, score_p2) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, LocalDateTime.now().toString());
            preparedStatement.setString(2, gameResults.getPlayer1().getName());
            preparedStatement.setString(3, gameResults.getPlayer2().getName());
            preparedStatement.setString(4, gameResults.getWinner().getName());
            preparedStatement.setString(5, gameResults.getWonBy());
            preparedStatement.setInt(6, gameResults.getScoreP1());
            preparedStatement.setInt(7, gameResults.getScoreP2());
            executeManipulateStatement();
        } catch (SQLException e) {
            sqlErrorMessage(e);
        }
    }

    public List<GameResults> getLatest2Games() {
        List<GameResults> list = new ArrayList<>();
        String sql = "SELECT * FROM sevenwondersscoreboard.gameresults order by gameresults_id desc LIMIT 2;";
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
              GameResults gameResults = new GameResults(
                      LocalDateTime.parse(resultSet.getString(2),formatter),
                      new Player(resultSet.getString(3)),
                      new Player(resultSet.getString(4)),
                      new Player(resultSet.getString(5)),
                      resultSet.getString(6),
                      resultSet.getInt(7),
                      resultSet.getInt(8));
                list.add(gameResults);
            }
        } catch (SQLException e) {
            System.err.println("Error: "+ e);
        }
        return list;
    }

    public int[] getGameStatsByPlayer(Player player) {
        List<String> list = new ArrayList<>();
        int totalGamesPlayed = totalGamesPlayedByPlayer(player);
        String sql = "SELECT won_by FROM sevenwondersscoreboard.players JOIN gameresults on " +
                "`name` = winner where winner = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, player.getName());
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                list.add(resultSet.getString("won_by"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int war = 0;
        int science = 0;
        int points = 0;
        for (String s : list) {
            switch (s) {
                case "War":
                    war++;
                    break;
                case "Science":
                    science++;
                    break;
                case "Points":
                    points++;
                    break;
            }
        }
        return new int[]{totalGamesPlayed, war,science,points};
    }

    public int totalGamesPlayedByPlayer(Player player){
        String sql = "SELECT COUNT(gameresults_id) FROM sevenwondersscoreboard.gameresults WHERE player_1 = ? OR player_2 = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getName());
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            sqlErrorMessage(e);
        }
        return 0;
    }

    private void sqlErrorMessage(SQLException e) {
        System.err.println("SQL error:" +  e.getMessage());
    }
}
