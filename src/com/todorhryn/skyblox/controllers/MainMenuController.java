package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.game.AccountManager;
import com.todorhryn.skyblox.game.Playfield;
import com.todorhryn.skyblox.views.Alert;
import com.todorhryn.skyblox.views.GameView;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuController extends Controller {
    @FXML
    public void playButtonClicked() {
        getSceneController().showLevelsList(false);
    }

    @FXML
    public void openLevelEditorButtonClicked() {
        getSceneController().showLevelsList(true);
    }

    @FXML
    public void selectAccountButtonClicked() {
        getSceneController().showLoginView();
    }

    @FXML
    public void continueButtonClicked() {
        Playfield playfield = AccountManager.getInstance().getCurrentAccount().getLastPlayfield();

        if (playfield == null)
            return;

        try {
            GameView gameView = new GameView(getSceneController(), playfield);
            gameView.show();
        }
        catch (IOException e) {
            Alert.showError("Error while loading GUI", e.getLocalizedMessage());
        }
    }
}
