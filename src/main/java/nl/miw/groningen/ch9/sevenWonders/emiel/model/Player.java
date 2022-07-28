package nl.miw.groningen.ch9.sevenWonders.emiel.model;

import javafx.scene.control.TextField;

/**
 * @author Emiel Bloem
 * <p>
 * Makes a player
 */
public class Player {
    private final String name;
    private int[] scoreArray;
    public Player(String name) {
        this.name = name;
    }
    public void fillScoreArray(TextField blue, TextField green, TextField yellow, TextField purple,
                               TextField wonders, TextField science, TextField coins, TextField war) {
        this.scoreArray = new int[8];
        scoreArray[0] = Integer.parseInt(blue.getText());
        scoreArray[1] = Integer.parseInt(green.getText());
        scoreArray[2] = Integer.parseInt(yellow.getText());
        scoreArray[3] = Integer.parseInt(purple.getText());
        scoreArray[4] = Integer.parseInt(wonders.getText());
        scoreArray[5] = Integer.parseInt(science.getText());
        scoreArray[6] = Integer.parseInt(coins.getText());
        scoreArray[7] = Integer.parseInt(war.getText());
    }

    public int getScore() {
        int sum = 0;
        for (int i : scoreArray) {
            sum += i;
        }
        return sum;
    }

    @Override
    public String toString() {
        return String.format("%s", this.name);
    }

    public String getName() {
        return name;
    }
}


