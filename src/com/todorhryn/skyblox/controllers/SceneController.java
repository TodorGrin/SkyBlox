package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.views.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController extends Application {
    private Scene scene;
    private MainMenuView mainMenuView;
    private LevelsListView levelsListView;

    @Override
    public void start(Stage primaryStage) {
        try {
            mainMenuView = new MainMenuView(this);
            primaryStage.setTitle("SkyBlox");
            scene = new Scene(mainMenuView.getRoot(), 800, 500);
            primaryStage.setScene(scene);

            levelsListView = new LevelsListView(this);

            primaryStage.show();
        }
        catch (Exception e) {
            Alert.showError("Error while creating GUI", e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showMainMenu() {
        mainMenuView.show();
    }

    public void showLevelsList(boolean openLevelEditor) {
        try {
            levelsListView.show(openLevelEditor);
        }
        catch (Exception e) {
            Alert.showError("Error while creating GUI", e.getLocalizedMessage());
        }
    }

    public void showLevelEditor(String levelName) {
        try {
            LevelEditorView view = new LevelEditorView(this, levelName);
            view.show();
        }
        catch (Exception e) {
            Alert.showError("Error while creating GUI", e.getLocalizedMessage());
        }
    }

    public void showGameView(String levelName) {
        try {
            GameView view = new GameView(this, levelName);
            view.show();
        }
        catch (Exception e) {
            Alert.showError("Error while creating GUI", e.getLocalizedMessage());
        }
    }

    public void showLoginView() {
        try {
            LoginView loginView = new LoginView(this);
            loginView.show();
        }
        catch (Exception e) {
            Alert.showError("Error while creating GUI", e.getLocalizedMessage());
        }
    }

    public Scene getScene() {
        return scene;
    }
}
