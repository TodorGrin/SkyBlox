package com.todorhryn.skyblox.mainmenu;

import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuController {
    MainView mainView;

    @FXML
    public void onPlayClicked() throws Exception {
        mainView.showLevel();
    }

    @FXML
    public void onLevelEditorClicked() throws IOException {
        mainView.showLevelEditor();
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
