package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.game.AccountManager;
import com.todorhryn.skyblox.views.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class LoginController extends Controller {
    @FXML
    private TextField loginTextField, loginTextField2;
    @FXML
    private Label loginLabel;
    @FXML
    private TabPane tabPane;

    private LoginView loginView;

    @FXML
    public void backButtonClicked() {
        getSceneController().showMainMenu();
    }

    @FXML
    public void loginButtonClicked() {
        if (AccountManager.getInstance().login(loginTextField.getText())) {
            getSceneController().showMainMenu();
        }
    }

    @FXML
    public void createAccountButtonClicked() {
        String login = loginTextField2.getText();

        if (AccountManager.getInstance().createAccount(login)) {
            AccountManager.getInstance().login(login);
            getSceneController().showMainMenu();
        }
    }

    @FXML
    public void exitAccountButtonClicked() {
        AccountManager.getInstance().loginAsGuest();
        getSceneController().showMainMenu();
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;

        if (loginLabel != null)
            initialize();
    }

    @FXML
    public void initialize() {
        if (loginView != null) {
            loginView.setLoginLabel(loginLabel);
            loginView.setTabPane(tabPane);
        }
    }
}
