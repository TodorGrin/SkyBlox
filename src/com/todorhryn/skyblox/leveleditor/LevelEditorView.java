package com.todorhryn.skyblox.leveleditor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LevelEditorView {
    private Scene scene;
    private GraphicsContext ctx;

    public LevelEditorView(Scene scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LevelEditorScene.fxml"));
        Parent root = fxmlLoader.load();
        scene.setRoot(root);

        this.scene = scene;
        this.ctx = ((Canvas) scene.lookup("#canvas")).getGraphicsContext2D();

        LevelEditorController controller = fxmlLoader.getController();
        controller.setLevelEditorView(this);
        draw();
    }

    public void draw() {
        ctx.setFill(Color.GREEN);
        ctx.fillRect(0, 0, ctx.getCanvas().getWidth(), ctx.getCanvas().getHeight());
    }
}
