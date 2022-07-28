package nl.miw.groningen.ch9.sevenWonders.emiel.model;

import java.time.LocalDateTime;

/**
 * @author Emiel Bloem
 * <p>
 * Makes it able to play sound.
 */
public class GameResults {

    public static final int DEFAULT_SCORE = 0;
    public static final String DEFAULT_PLAYER_NAME = "Unknown";
    public static final String DEFAULT_WON_BY = "null";
    private String wonBy;
    private final Player player1, player2;
    private Player winner;
    private final LocalDateTime localDateTime;
    private int scoreP1, scoreP2;

    public GameResults(LocalDateTime localDateTime, Player player1, Player player2, Player winner, String wonBy,
                       int scoreP1, int scoreP2) {
        this.localDateTime = localDateTime;
        this.player1 = player1;
        this.player2 = player2;
        this.wonBy = wonBy;
        this.winner = winner;
        this.scoreP1 = scoreP1;
        this.scoreP2 = scoreP2;
    }

    public GameResults(Player player1, Player player2) {
        this(LocalDateTime.now(), player1, player2, new Player(DEFAULT_PLAYER_NAME),
                DEFAULT_WON_BY, DEFAULT_SCORE, DEFAULT_SCORE);
    }

    public void calculateWinnerByScore(Player player1, Player player2) {
        scoreP1 = player1.getScore();
        scoreP2 = player2.getScore();

        if (scoreP1 > scoreP2) {
            winner = player1;
            wonBy = "Points";
        } else if (scoreP2 > scoreP1) {
            winner = player2;
            wonBy = "Points";
        } else if (scoreP1 > 0) {
            wonBy = "Draw";
        }
    }

    @Override
    public String toString() {
        String month = localDateTime.getMonth().toString().toLowerCase();
        month = month.substring(0, 1).toUpperCase() + month.substring(1);

        String myOwnFormattedDateTime = String.format("%d:%d %d %s %d", localDateTime.getHour(),
                localDateTime.getMinute(), localDateTime.getDayOfMonth(),
                month, localDateTime.getYear());

        if( wonBy.equals("Draw")) {return String.format("%-19s\n%s vs %s\nDraw\nScore: %d-%d",
                myOwnFormattedDateTime, this.player1, this.player2, scoreP1, scoreP2);

        }
        return String.format("%-19s\n%s vs %s\nwinner: %s\nWon by: %s",
                        myOwnFormattedDateTime, this.player1, this.player2, this.winner, wonBy)
                .replace("Won by: Points", String.format("Score: %d-%d", scoreP1, scoreP2));
    }

    public void setWonBy(String wonBy) {
        this.wonBy = wonBy;
    }

    public String getWonBy() {
        return wonBy;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }

    public int getScoreP1() {
        return scoreP1;
    }

    public int getScoreP2() {
        return scoreP2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
