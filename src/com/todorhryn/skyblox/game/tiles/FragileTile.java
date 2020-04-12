package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.LevelState;
import com.todorhryn.skyblox.game.Playfield;

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
