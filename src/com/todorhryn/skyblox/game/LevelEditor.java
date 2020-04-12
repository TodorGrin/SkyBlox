package com.todorhryn.skyblox.game;

import com.todorhryn.skyblox.game.tiles.Tile;
import com.todorhryn.skyblox.views.PlayfieldView;

public class LevelEditor extends Playfield {
    private int selectedTileX = 2, selectedTileY = 2;
    private Class<? extends Tile> selectedTile;

    public LevelEditor(PlayfieldView view, int width, int height) {
        super(view, width, height);
    }

    public LevelEditor(PlayfieldView view, Playfield playfield) {
        super(view, playfield.getWidth(), playfield.getHeight());

        try {
            for (int x = 0; x < playfield.getWidth(); ++x) {
                for (int y = 0; y < playfield.getHeight(); ++y) {
                    Tile cloned = (Tile) playfield.getTile(x, y).clone();
                    cloned.setPlayfield(this);
                    setTile(x, y, cloned);
                }
            }
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void setSelectedTileX(int selectedTileX) {
        if (selectedTileX < 2 || selectedTileX >= getWidth() - 2)
            return;

        this.selectedTileX = selectedTileX;
        render();
    }

    public void setSelectedTileY(int selectedTileY) {
        if (selectedTileY < 2 || selectedTileY >= getHeight() - 2)
            return;

        this.selectedTileY = selectedTileY;
        render();
    }

    public void setSelectedTile(Class<? extends Tile> selectedTile) {
        this.selectedTile = selectedTile;
        render();
    }

    public int getSelectedTileX() {
        return selectedTileX;
    }

    public int getSelectedTileY() {
        return selectedTileY;
    }

    public Class<? extends Tile> getSelectedTile() {
        return selectedTile;
    }
}
