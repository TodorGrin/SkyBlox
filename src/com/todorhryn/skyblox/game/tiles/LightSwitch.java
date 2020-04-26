package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.Playfield;

import java.io.Serializable;
import java.util.ArrayList;

public class LightSwitch extends Tile implements Serializable {
    private ArrayList<Tile> controlledTiles = new ArrayList<>();

    public LightSwitch(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        super.steppedOn(weight);

        for (Tile tile : controlledTiles)
            tile.setVisible(!tile.isVisible());
    }

    @Override
    public void steppedOff() {
        super.steppedOff();
    }

    public void addControlledTile(Tile tile) {
        if (!controlledTiles.contains(tile))
            controlledTiles.add(tile);
    }

    public ArrayList<Tile> getControlledTiles() {
        return controlledTiles;
    }
}
