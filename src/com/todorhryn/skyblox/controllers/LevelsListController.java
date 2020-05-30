package com.todorhryn.skyblox.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class LevelsListController extends Controller {
    private boolean openLevelEditor = false;

    @FXML
    public void levelButtonClicked(MouseEvent ev) {
        Button button = (Button) ev.getSource();
        String levelName = button.getText();

        if (openLevelEditor)
            getSceneController().showLevelEditor(levelName);
        else
            getSceneController().showGameView(levelName);
    }

    @FXML
    public void backButtonClicked() {
        getSceneController().showMainMenu();
    }

    public void setOpenLevelEditor(boolean openLevelEditor) {
        this.openLevelEditor = openLevelEditor;
    }
}
