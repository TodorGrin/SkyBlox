package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.LevelState;
import com.todorhryn.skyblox.game.Playfield;

public class ExitTile extends Tile {
    public ExitTile(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        super.steppedOn(weight);

        if (weight == 2 && getPlayfield().getLevelState() == LevelState.ACTIVE)
            getPlayfield().setLevelState(LevelState.PASSED);
    }
}
