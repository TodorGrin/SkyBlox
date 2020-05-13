package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.LevelState;
import com.todorhryn.skyblox.game.Playfield;

public class FragileTile extends Tile {
    public FragileTile(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        super.steppedOn(weight);

        if (weight >= 2) {
            setVisible(false);
            getPlayfield().setLevelState(LevelState.FAILED);
        }
    }
}
