package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.Playfield;

import java.io.Serializable;

public class Tile implements Serializable, Cloneable {
    private Playfield playfield;

    public Tile(Playfield playfield) {
        this.playfield = playfield;
    }

    public void steppedOn(int weight) {

    }

    public void steppedOff() {

    }

    public Playfield getPlayfield() {
        return playfield;
    }

    public void setPlayfield(Playfield playfield) {
        this.playfield = playfield;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
