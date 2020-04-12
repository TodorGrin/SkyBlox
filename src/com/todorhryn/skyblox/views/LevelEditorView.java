package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.controllers.LevelEditorController;
import com.todorhryn.skyblox.game.LevelEditor;
import com.todorhryn.skyblox.game.LevelLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

import java.io.IOException;

public class LevelEditorView extends PlayfieldView {
    public LevelEditorView(Scene scene) throws IOException {
        LevelEditor levelEditor = new LevelEditor(this, LevelLoader.getInstance().load(this));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LevelEditorScene.fxml"));
        LevelEditorController controller = new LevelEditorController(levelEditor);
        fxmlLoader.setController(controller);

        Parent root = fxmlLoader.load();
        scene.setRoot(root);

        this.scene = scene;
        this.ctx = ((Canvas) scene.lookup("#canvas")).getGraphicsContext2D();
        this.playfield = levelEditor;

        render();
    }

    @Override
    public void render() {
        if (ctx == null)
            return;

        super.render();

        LevelEditor levelEditor = (LevelEditor) playfield;

        ctx.setFill(getTileColor(levelEditor.getSelectedTile()));
        ctx.fillRect(levelEditor.getSelectedTileX() * 32 - 1, levelEditor.getSelectedTileY() * 32 - 1, 32, 32);
    }
}
