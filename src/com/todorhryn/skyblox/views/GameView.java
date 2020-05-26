package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.controllers.GameController;
import com.todorhryn.skyblox.controllers.SceneController;
import com.todorhryn.skyblox.game.LevelLoader;
import com.todorhryn.skyblox.game.LevelState;
import com.todorhryn.skyblox.game.Playfield;
import javafx.scene.control.Label;

import java.io.IOException;

public class GameView extends PlayfieldView {
    public GameView(SceneController sceneController, String levelName) throws IOException {
        super(sceneController, "/scenes/GameScene.fxml");
        setPlayfield(LevelLoader.getInstance().load(this, levelName));
    }

    @Override
    public void render() {
        super.render();

        if (getPlayfield().getLevelState() == LevelState.FAILED) {
            Label message = (Label) getRoot().lookup("#message");
            message.setText("Level failed");
        }
        else if (getPlayfield().getLevelState() == LevelState.PASSED) {
            Label message = (Label) getRoot().lookup("#message");
            message.setText("Level completed!");
        }
    }

    @Override
    public void setPlayfield(Playfield playfield) {
        super.setPlayfield(playfield);
        ((GameController) getController()).setPlayfield(playfield);
    }
}
