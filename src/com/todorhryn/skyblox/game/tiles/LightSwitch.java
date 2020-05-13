package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.Playfield;

public class LightSwitch extends TileController {
    public LightSwitch(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        super.steppedOn(weight);

        for (Tile tile : getControlledTiles())
            tile.setVisible(!tile.isVisible());
    }
}
