package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.SceneController;

public abstract class Controller {
    private SceneController sceneController;

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public SceneController getSceneController() {
        return sceneController;
    }
}
