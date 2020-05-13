package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.game.LevelState;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import java.io.IOException;

public class GameView extends PlayfieldView {
    public GameView(Scene scene, String levelName) throws IOException {
        super("/scenes/GameScene.fxml", scene, levelName);
    }

    @Override
    public void render() {
        super.render();

        if (getPlayfield().getLevelState() == LevelState.FAILED) {
            Text message = (Text) getScene().lookup("#message");
            message.setText("Level failed");
        }
        else if (getPlayfield().getLevelState() == LevelState.PASSED) {
            Text message = (Text) getScene().lookup("#message");
            message.setText("Level completed!");
        }
    }
}
