package com.todorhryn.skyblox.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class Account implements Serializable {
    private String username;
    private Password password;
    private HashMap<String, Integer> highscores = new HashMap<>();
    private Playfield lastPlayfield;

    public Account(String username, Password password) {
        this.username = username;
        this.password = password;
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

    public void setLastPlayfield(Playfield lastPlayfield) {
        this.lastPlayfield = lastPlayfield;
    }

    public Playfield getLastPlayfield() {
        return lastPlayfield;
    }

    public Password getPassword() {
        return password;
    }
}
