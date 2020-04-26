package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.LevelState;
import com.todorhryn.skyblox.game.Playfield;

import java.io.Serializable;

public class Tile implements Serializable {
    private Playfield playfield;
    private boolean visible = true;

    public Tile(Playfield playfield) {
        this.playfield = playfield;
    }

    public void steppedOn(int weight) {
        if (!visible)
            getPlayfield().setLevelState(LevelState.FAILED);
    }

    public void steppedOff() {

    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public Playfield getPlayfield() {
        return playfield;
    }

    public void setPlayfield(Playfield playfield) {
        this.playfield = playfield;
    }
}
