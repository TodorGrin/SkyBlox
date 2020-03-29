package com.todorhryn.skyblox.game;

public class FragileTile extends Tile {
    public FragileTile(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        if (weight >= 2)
            playfield.setLevelState(LevelState.FAILED);
    }
}
