package com.todorhryn.skyblox;

public class FragileTile extends Tile {
    FragileTile(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        if (weight >= 2)
            playfield.setLevelState(LevelState.FAILED);
    }
}
