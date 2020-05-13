package com.todorhryn.skyblox.game;

import com.todorhryn.skyblox.game.tiles.Tile;
import com.todorhryn.skyblox.game.tiles.TileController;
import com.todorhryn.skyblox.views.PlayfieldView;

import java.lang.reflect.InvocationTargetException;

public class LevelEditor extends Playfield {
    private int newTileX = 2;
    private int newTileY = 2;
    private Class<? extends Tile> newTileClass = Tile.class;
    private Tile selectedTile;
    private LevelEditorState state = LevelEditorState.ADD_NEW_TILE;

    public LevelEditor(PlayfieldView view, Playfield playfield) {
        super(view, playfield.getWidth(), playfield.getHeight());

        for (int x = 0; x < playfield.getWidth(); ++x) {
            for (int y = 0; y < playfield.getHeight(); ++y) {
                Tile tile = playfield.getTile(x, y);
                tile.setPlayfield(this);
                setTile(x, y, tile);
            }
        }

        setPlayer(playfield.getPlayer());
        getPlayer().setPlayfield(this);
    }

    public void onTileClicked(int x, int y) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (invalidCoordinates(x, y))
            return;

        if (state == LevelEditorState.ADD_NEW_TILE) {
            setTile(x, y, newTileClass.getDeclaredConstructor(Playfield.class).newInstance(this));
        }
        else if (state == LevelEditorState.SELECT_TILE) {
            setSelectedTile(getTile(x, y));
        }
        else if (state == LevelEditorState.SELECT_CONTROLLED_TILES) {
            if (selectedTile instanceof TileController) {
                TileController tileController = (TileController) selectedTile;

                if (tileController.getControlledTiles().contains(getTile(x, y)))
                    tileController.getControlledTiles().remove(getTile(x, y));
                else
                    tileController.getControlledTiles().add(getTile(x, y));

                render();
            }
        }
    }

    public void setNewTileX(int newTileX) {
        if (invalidCoordinates(newTileX, newTileY))
            return;

        this.newTileX = newTileX;
        render();
    }

    public void setNewTileY(int newTileY) {
        if (invalidCoordinates(newTileX, newTileY))
            return;

        this.newTileY = newTileY;
        render();
    }

    @Override
    public void setTile(int x, int y, Tile tile) {
        if (invalidCoordinates(x, y))
            return;

        super.setTile(x, y, tile);
    }

    public void setNewTileClass(Class<? extends Tile> newTileClass) {
        this.newTileClass = newTileClass;
        render();
    }

    public int getNewTileX() {
        return newTileX;
    }

    public int getNewTileY() {
        return newTileY;
    }

    public Class<? extends Tile> getNewTileClass() {
        return newTileClass;
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
        render();
    }

    public LevelEditorState getState() {
        return state;
    }

    public void setState(LevelEditorState state) {
        this.state = state;
        render();
    }
}
