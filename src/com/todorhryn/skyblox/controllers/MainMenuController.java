package com.todorhryn.skyblox.controllers;

import javafx.fxml.FXML;

public class MainMenuController extends Controller {
    @FXML
    public void playButton_onClicked() {
        getSceneController().showLevelsList(false);
    }

    @FXML
    public void openLevelEditorButton_onClicked() {
        getSceneController().showLevelsList(true);
    }

    @FXML
    public void selectAccountButton_onClicked() {
        getSceneController().showLoginView();
    }
}
