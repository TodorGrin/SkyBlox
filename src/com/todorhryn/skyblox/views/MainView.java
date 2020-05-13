package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.controllers.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scenes/MainMenu.fxml"));
            Parent root = fxmlLoader.load();
            primaryStage.setTitle("SkyBlox");
            scene = new Scene(root, 800, 500);
            primaryStage.setScene(scene);

            MainMenuController controller = fxmlLoader.getController();
            controller.setMainView(this);

            primaryStage.show();
        }
        catch (Exception e) {
            Alert.showError("Error while creating GUI", e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showLevel() {
        try {
            LevelsListView levelsListView = new LevelsListView(scene, false);
        }
        catch (Exception e) {
            Alert.showError("Error while creating GUI", e.getLocalizedMessage());
        }
    }

    public void showLevelEditor() {
        try {
            LevelsListView levelsListView = new LevelsListView(scene, true);
        }
        catch (Exception e) {
            Alert.showError("Error while creating GUI", e.getLocalizedMessage());
        }
    }
}
