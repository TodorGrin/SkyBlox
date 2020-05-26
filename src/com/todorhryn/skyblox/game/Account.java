package com.todorhryn.skyblox.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class Account implements Serializable {
    private String username;
    private HashMap<String, Integer> highscores = new HashMap<>();

    public Account(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void addHighscore(String levelName, int score) {
        if (!highscores.containsKey(levelName) || highscores.get(levelName) > score)
            highscores.put(levelName, score);
    }

    public int getHighscore(String levelName) {
        Integer score = highscores.get(levelName);

        return Objects.requireNonNullElse(score, -1);
    }
}
