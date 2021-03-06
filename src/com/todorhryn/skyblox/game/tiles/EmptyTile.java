package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.LevelState;
import com.todorhryn.skyblox.game.Playfield;

public class EmptyTile extends Tile {
    public EmptyTile(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        super.steppedOn(weight);

        getPlayfield().setLevelState(LevelState.FAILED);
    }
}
