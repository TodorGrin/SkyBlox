package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.controllers.LevelEditorController;
import com.todorhryn.skyblox.game.LevelEditor;
import com.todorhryn.skyblox.game.LevelEditorState;
import com.todorhryn.skyblox.game.LevelLoader;
import com.todorhryn.skyblox.game.tiles.LightSwitch;
import com.todorhryn.skyblox.game.tiles.Tile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LevelEditorView extends PlayfieldView {
    public LevelEditorView(Scene scene) throws IOException {
        LevelEditor levelEditor = new LevelEditor(this, LevelLoader.getInstance().load(this));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LevelEditorScene.fxml"));
        LevelEditorController controller = new LevelEditorController(levelEditor);
        fxmlLoader.setController(controller);

        Parent root = fxmlLoader.load();
        scene.setRoot(root);

        setScene(scene);
        setCtx(((Canvas) scene.lookup("#canvas")).getGraphicsContext2D());
        setPlayfield(levelEditor);

        render();
    }

    @Override
    public void renderTile(int x, int y) {
        super.renderTile(x, y);

        LevelEditor levelEditor = (LevelEditor) getPlayfield();

        if (levelEditor.getState() == LevelEditorState.SELECT_TILE || levelEditor.getState() == LevelEditorState.SELECT_CONTROLLED_TILES) {
            Tile selectedTile = levelEditor.getSelectedTile();
            Tile tile = getPlayfield().getTile(x, y);

            if (selectedTile != null) {
                if (selectedTile == tile) {
                    getCtx().setFill(getTileColor(selectedTile.getClass()));
                    getCtx().fillRect(x * 32, y * 32, 32, 32);
                }

                if (selectedTile.getClass() == LightSwitch.class && ((LightSwitch) selectedTile).getControlledTiles().contains(tile)) {
                    getCtx().setFill(Color.web("B2B2B290"));
                    getCtx().fillRect(x * 32, y * 32, 32, 32);
                }
            }
        }
    }

    @Override
    public void render() {
        if (getCtx() == null)
            return;

        super.render();

        LevelEditor levelEditor = (LevelEditor) getPlayfield();

        if (levelEditor.getState() == LevelEditorState.ADD_NEW_TILE) {
            getCtx().setFill(getTileColor(levelEditor.getNewTileClass()));
            getCtx().fillRect(levelEditor.getNewTileX() * 32, levelEditor.getNewTileY() * 32, 32, 32);
        }
    }
}
