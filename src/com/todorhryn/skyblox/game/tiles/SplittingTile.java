package com.todorhryn.skyblox.game.tiles;

import com.todorhryn.skyblox.game.Playfield;

public class SplittingTile extends Tile {
    private int mainBlockX, mainBlockY;
    private int secondBlockX, secondBlockY;

    public SplittingTile(Playfield playfield) {
        super(playfield);
    }

    @Override
    public void steppedOn(int weight) {
        super.steppedOn(weight);

        if (weight == 2) {
            getPlayfield().getPlayer().setPosition(mainBlockX, mainBlockY, secondBlockX, secondBlockY);
        }
    }

    public void setMainBlockPosition(int x, int y) {
        mainBlockX = x;
        mainBlockY = y;
        getPlayfield().render();
    }

    public void setSecondBlockPosition(int x, int y) {
        secondBlockX = x;
        secondBlockY = y;
        getPlayfield().render();
    }

    public int getMainBlockX() {
        return mainBlockX;
    }

    public int getMainBlockY() {
        return mainBlockY;
    }

    public int getSecondBlockX() {
        return secondBlockX;
    }

    public int getSecondBlockY() {
        return secondBlockY;
    }
}
