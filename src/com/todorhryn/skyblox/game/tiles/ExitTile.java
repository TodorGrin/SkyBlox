package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.LevelState;
import com.todorhryn.skyblox.game.Playfield;

public class ExitTile extends Tile {
    public ExitTile(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        if (weight == 2)
            playfield.setLevelState(LevelState.PASSED);
    }
}
