package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.controllers.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView extends Application {
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainMenu.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("SkyBlox");
        scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);

        MainMenuController controller = fxmlLoader.getController();
        controller.setMainView(this);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showLevel() throws Exception {
        GameView view = new GameView(scene);
    }

    public void showLevelEditor() throws IOException {
        LevelEditorView view = new LevelEditorView(scene);
    }
}
