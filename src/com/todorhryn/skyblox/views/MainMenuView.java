package com.todorhryn.skyblox.views;
import com.todorhryn.skyblox.SceneController;
import com.todorhryn.skyblox.game.Account;
import com.todorhryn.skyblox.game.AccountManager;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuView extends View {
    public MainMenuView(SceneController sceneController) throws IOException {
        super(sceneController, "/scenes/MainMenu.fxml");
        update();
    }

    @Override
    public void show() {
        update();
        super.show();
    }

    public void update() {
        Account account = AccountManager.getInstance().getCurrentAccount();
        ((Button) getRoot().lookup("#selectAccountButton")).setText("Аккаунт: " + (account != null ? account.getUsername() : "нет"));
        getRoot().lookup("#continueButton").setVisible(account != null && account.getLastPlayfield() != null);
    }
}
