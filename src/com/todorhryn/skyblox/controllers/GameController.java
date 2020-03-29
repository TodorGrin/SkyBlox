package com.todorhryn.skyblox.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class GameController extends PlayfieldController {
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
}
