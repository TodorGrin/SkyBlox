package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.game.Playfield;
import com.todorhryn.skyblox.views.Alert;
import com.todorhryn.skyblox.views.HighscoresDialogView;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class GameController extends Controller {
    private Playfield playfield;

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case D:
                playfield.getPlayer().moveRight();
                break;
            case A:
                playfield.getPlayer().moveLeft();
                break;
            case W:
                playfield.getPlayer().moveUp();
                break;
            case S:
                playfield.getPlayer().moveDown();
                break;
            case SPACE:
                playfield.getPlayer().switchBlocks();
                break;
        }
    }

    @FXML
    public void backButtonClicked() {
        getSceneController().showLevelsList(false);
    }

    @FXML
    public void highscoresButtonClicked() {
        try {
            HighscoresDialogView highscoresDialogView = new HighscoresDialogView(playfield.getLevelName());
        }
        catch (IOException e) {
            Alert.showError("Error while loading highscores GUI", e.getMessage());
        }
    }

    public void setPlayfield(Playfield playfield) {
        this.playfield = playfield;
    }
}
