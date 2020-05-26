package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.controllers.LevelsListController;
import com.todorhryn.skyblox.controllers.SceneController;

import java.io.IOException;

public class LevelsListView extends View {
    public LevelsListView(SceneController sceneController) throws IOException {
        super(sceneController, "/scenes/LevelsList.fxml");
    }

    public void show(boolean openLevelEditor) {
        ((LevelsListController) getController()).setOpenLevelEditor(openLevelEditor);
        show();
    }
}
