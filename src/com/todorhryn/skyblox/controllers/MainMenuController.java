package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.views.MainView;
import javafx.fxml.FXML;

public class MainMenuController {
    private MainView mainView;

    @FXML
    public void onPlayClicked() {
        mainView.showLevel();
    }

    @FXML
    public void onLevelEditorClicked() {
        mainView.showLevelEditor();
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
