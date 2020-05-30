package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.SceneController;
import com.todorhryn.skyblox.controllers.LoginController;
import com.todorhryn.skyblox.game.AccountManager;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class LoginView extends View {
    private Label loginLabel;
    private TabPane tabPane;
    private Tab accountTab, loginTab, signupTab;

    public LoginView(SceneController sceneController) throws IOException {
        super(sceneController, "/scenes/Login.fxml");

        LoginController loginController = (LoginController) getController();
        loginController.setLoginView(this);
    }

    @Override
    public void show() {
        loginLabel.setText(AccountManager.getInstance().getCurrentAccount().getUsername());

        if (AccountManager.getInstance().loggedInAsGuest()) {
            tabPane.getTabs().clear();
            tabPane.getTabs().add(loginTab);
            tabPane.getTabs().add(signupTab);
        }
        else {
            tabPane.getTabs().clear();
            tabPane.getTabs().add(accountTab);
        }

        super.show();
    }

    public void setLoginLabel(Label loginLabel) {
        this.loginLabel = loginLabel;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;

        accountTab = tabPane.getTabs().get(0);
        loginTab = tabPane.getTabs().get(1);
        signupTab = tabPane.getTabs().get(2);
    }
}
