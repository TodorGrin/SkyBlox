package com.todorhryn.skyblox;

public class EmptyTile extends Tile {
    EmptyTile(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        playfield.setLevelState(LevelState.FAILED);
    }
}