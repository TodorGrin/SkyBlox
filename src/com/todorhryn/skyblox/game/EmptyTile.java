package com.todorhryn.skyblox.game;

public class EmptyTile extends Tile {
    public EmptyTile(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        playfield.setLevelState(LevelState.FAILED);
    }
}
