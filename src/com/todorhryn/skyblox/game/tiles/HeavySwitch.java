package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.Playfield;

public class HeavySwitch extends Tile {
    public HeavySwitch(Playfield playfield) {
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
