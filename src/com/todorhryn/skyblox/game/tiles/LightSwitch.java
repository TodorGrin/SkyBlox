package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.Playfield;

public class LightSwitch extends Tile {
    public LightSwitch(Playfield playfield) {
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
}
