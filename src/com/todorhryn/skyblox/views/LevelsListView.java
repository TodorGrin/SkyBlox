package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.controllers.LevelsListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class LevelsListView {
    private Scene scene;
    private boolean openLevelEditor = false;

    public LevelsListView(Scene scene, boolean openLevelEditor) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scenes/LevelsList.fxml"));
        Parent root = fxmlLoader.load();
        scene.setRoot(root);

        this.scene = scene;
        this.openLevelEditor = openLevelEditor;

        LevelsListController levelsListController = fxmlLoader.getController();
        levelsListController.setLevelsListView(this);
    }

    public void openLevel(String levelName) {
        try {
            if (openLevelEditor) {
                LevelEditorView view = new LevelEditorView(scene, levelName);
            }
            else {
                GameView view = new GameView(scene, levelName);
            }
        }
        catch (Exception e) {
            Alert.showError("Error while creating GUI", e.getLocalizedMessage());
        }
    }
}
