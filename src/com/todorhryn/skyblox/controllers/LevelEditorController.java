package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.game.FragileTile;
import com.todorhryn.skyblox.game.Tile;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class LevelEditorController extends PlayfieldController {
    int x = 3, y = 3;

    @FXML
    public void onButtonTileClicked() {
        playfield.setTile(x, y, new Tile(playfield));
        playfield.render();
    }

    @FXML
    public void onButtonFragileTileClicked() {
        playfield.setTile(x, y, new FragileTile(playfield));
        playfield.render();
    }

    @FXML
    public void onMouseClicked(MouseEvent mouseEvent) {
        int newX = (int) (mouseEvent.getX() / 32);
        int newY = (int) (mouseEvent.getY() / 32);

        if (newX > 1 && newY > 1) {
            x = newX;
            y = newY;
        }
    }
}
