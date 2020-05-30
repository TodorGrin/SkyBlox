package com.todorhryn.skyblox;

import com.todorhryn.skyblox.views.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Scene scene;
    private MainMenuView mainMenuView;
    private LevelsListView levelsListView;

    public SceneController(Stage primaryStage) {
        try {
            mainMenuView = new MainMenuView(this);
            primaryStage.setTitle("SkyBlox");
            scene = new Scene(mainMenuView.getRoot(), 800, 500);
            primaryStage.setScene(scene);

            levelsListView = new LevelsListView(this);

            primaryStage.show();
        }
        catch (IOException e) {
            Alert.showError("Error while creating GUI", e.getLocalizedMessage());
        }
    }

    public void showMainMenu() {
        mainMenuView.show();
    }

    public void showLevelsList(boolean openLevelEditor) {
        levelsListView.show(openLevelEditor);
    }

    public void showLevelEditor(String levelName) {
        try {
            LevelEditorView view = new LevelEditorView(this, levelName);
            view.show();
        }
        catch (IOException e) {
            Alert.showError("Error while creating GUI", e.getLocalizedMessage());
        }
    }

    public void showGameView(String levelName) {
        try {
            GameView view = new GameView(this, levelName);
            view.show();
        }
        catch (IOException e) {
            Alert.showError("Error while creating GUI", e.getLocalizedMessage());
        }
    }

    public void showLoginView() {
        try {
            LoginView loginView = new LoginView(this);
            loginView.show();
        }
        catch (IOException e) {
            Alert.showError("Error while creating GUI", e.getLocalizedMessage());
        }
    }

    public Scene getScene() {
        return scene;
    }
}
