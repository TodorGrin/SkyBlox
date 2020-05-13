package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.Playfield;

import java.io.Serializable;
import java.util.ArrayList;

public class TileController extends Tile implements Serializable {
    private ArrayList<Tile> controlledTiles = new ArrayList<>();

    public TileController(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        super.steppedOn(weight);
    }

    @Override
    public void steppedOff() {
        super.steppedOff();
    }

    public ArrayList<Tile> getControlledTiles() {
        return controlledTiles;
    }
}
