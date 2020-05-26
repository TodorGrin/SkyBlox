package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.game.AccountManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController extends Controller {
    @FXML
    private TextField loginTextField, loginTextField2;


    @FXML
    public void backButton_onClicked() {
        getSceneController().showMainMenu();
    }

    @FXML
    public void loginButton_onClicked() {
        if (AccountManager.getInstance().login(loginTextField.getText())) {
            getSceneController().showMainMenu();
        }
    }

    @FXML
    public void createAccountButton_onClicked() {
        String login = loginTextField2.getText();

        if (AccountManager.getInstance().createAccount(login)) {
            AccountManager.getInstance().login(login);
            getSceneController().showMainMenu();
        }
    }
}
