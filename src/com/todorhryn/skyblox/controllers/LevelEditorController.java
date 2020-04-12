package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.game.LevelEditor;
import com.todorhryn.skyblox.game.LevelLoader;
import com.todorhryn.skyblox.game.Playfield;
import com.todorhryn.skyblox.game.tiles.EmptyTile;
import com.todorhryn.skyblox.game.tiles.ExitTile;
import com.todorhryn.skyblox.game.tiles.FragileTile;
import com.todorhryn.skyblox.game.tiles.Tile;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class LevelEditorController {
    private LevelEditor levelEditor;

    public LevelEditorController(LevelEditor levelEditor) {
        this.levelEditor = levelEditor;
    }

    @FXML
    public void onButtonTileClicked() {
        levelEditor.setSelectedTile(Tile.class);
    }

    @FXML
    public void onButtonFragileTileClicked() {
        levelEditor.setSelectedTile(FragileTile.class);
    }

    @FXML
    public void onButtonEmptyTileClicked() {
        levelEditor.setSelectedTile(EmptyTile.class);
    }

    @FXML
    public void onButtonExitTileClicked() {
        levelEditor.setSelectedTile(ExitTile.class);
    }

    @FXML
    public void onButtonSaveClicked() {
        LevelLoader.getInstance().save(levelEditor);
    }

    @FXML
    public void onMouseMoved(MouseEvent mouseEvent) {
        levelEditor.setSelectedTileX((int) mouseEvent.getX() / 32);
        levelEditor.setSelectedTileY((int) mouseEvent.getY() / 32);
    }

    @FXML
    public void onMouseClicked(MouseEvent mouseEvent) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        int x = (int) (mouseEvent.getX() / 32);
        int y = (int) (mouseEvent.getY() / 32);

        levelEditor.setTile(x, y, levelEditor.getSelectedTile().getDeclaredConstructor(Playfield.class).newInstance(levelEditor));
    }
}
