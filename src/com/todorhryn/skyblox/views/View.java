package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.controllers.Controller;
import com.todorhryn.skyblox.controllers.SceneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public abstract class View {
    private Parent root;
    private SceneController sceneController;
    private Controller controller;

    public View(SceneController sceneController, String scenePath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scenePath));
        root = fxmlLoader.load();

        this.sceneController = sceneController;

        controller = fxmlLoader.getController();
        controller.setSceneController(sceneController);
    }

    public void show() {
        sceneController.getScene().setRoot(root);
    }

    public Parent getRoot() {
        return root;
    }

    public SceneController getSceneController() {
        return sceneController;
    }

    public Controller getController() {
        return controller;
    }
}
