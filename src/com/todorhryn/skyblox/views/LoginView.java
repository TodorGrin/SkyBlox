package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.controllers.SceneController;

import java.io.IOException;

public class LoginView extends View {
    public LoginView(SceneController sceneController) throws IOException {
        super(sceneController, "/scenes/Login.fxml");
    }
}
