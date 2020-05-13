package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.views.LevelsListView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class LevelsListController {
    private LevelsListView levelsListView;

    public LevelsListController() {

    }

    @FXML
    public void levelButton_onClicked(MouseEvent ev) {
        Button button = (Button) ev.getSource();
        String levelName = button.getText();
        levelsListView.openLevel(levelName);
    }

    public void setLevelsListView(LevelsListView levelsListView) {
        this.levelsListView = levelsListView;
    }
}
