package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.Playfield;

public class HeavySwitch extends TileController {
    public HeavySwitch(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        super.steppedOn(weight);

        if (weight == 2) {
            for (Tile tile : getControlledTiles())
                tile.setVisible(!tile.isVisible());
        }
    }
}
