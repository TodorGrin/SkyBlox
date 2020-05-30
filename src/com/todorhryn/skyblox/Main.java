package com.todorhryn.skyblox;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        SceneController sceneController = new SceneController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
