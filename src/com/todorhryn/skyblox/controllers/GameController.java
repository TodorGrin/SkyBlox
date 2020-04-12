package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.game.Playfield;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class GameController  {
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

    public void setPlayfield(Playfield playfield) {
        this.playfield = playfield;
    }
}
