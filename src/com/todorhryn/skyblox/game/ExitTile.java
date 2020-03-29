package com.todorhryn.skyblox.game;

public class ExitTile extends Tile {
    ExitTile(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        if (weight == 2)
            playfield.setLevelState(LevelState.PASSED);
    }
}
