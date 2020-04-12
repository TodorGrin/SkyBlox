package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.game.LevelState;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import java.io.IOException;

public class GameView extends PlayfieldView {
    public GameView(Scene scene) throws IOException {
        super("/GameScene.fxml", scene);
    }

    @Override
    public void render() {
        super.render();

        if (playfield.getLevelState() == LevelState.FAILED) {
            Text message = (Text) scene.lookup("#message");
            message.setText("Level failed");
        }
        else if (playfield.getLevelState() == LevelState.PASSED) {
            Text message = (Text) scene.lookup("#message");
            message.setText("Level completed!");
        }
    }
}
