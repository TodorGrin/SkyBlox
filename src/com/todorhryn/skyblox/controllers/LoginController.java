package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.game.AccountManager;
import com.todorhryn.skyblox.views.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class LoginController extends Controller {
    @FXML
    private TextField loginTextField, loginTextField2;
    @FXML
    private Label loginLabel;
    @FXML
    private TabPane tabPane;
    @FXML
    private PasswordField signupPasswordField1, signupPasswordField2, loginPasswordField;

    private LoginView loginView;

    @FXML
    public void backButtonClicked() {
        getSceneController().showMainMenu();
    }

    @FXML
    public void loginButtonClicked() {
        String login = loginTextField.getText();
        String password = loginPasswordField.getText();

        if (AccountManager.getInstance().login(login, password)) {
            getSceneController().showMainMenu();
        }
    }

    @FXML
    public void createAccountButtonClicked() {
        String login = loginTextField2.getText();
        String password = signupPasswordField1.getText();
        String password2 = signupPasswordField2.getText();

        if (!password.equals(password2))
            return;

        if (AccountManager.getInstance().createAccount(login, password)) {
            AccountManager.getInstance().login(login, password);
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
