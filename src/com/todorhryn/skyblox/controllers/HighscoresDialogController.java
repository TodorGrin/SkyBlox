package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.game.Account;
import com.todorhryn.skyblox.views.HighscoresDialogView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HighscoresDialogController {
    @FXML
    private TableView<Account> highscoresTableView;

    private HighscoresDialogView highscoresDialogView;

    @FXML
    public void okButton_onClicked(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        if (highscoresDialogView != null)
            highscoresDialogView.render(highscoresTableView);
    }

    public void setHighscoresDialogView(HighscoresDialogView highscoresDialogView) {
        this.highscoresDialogView = highscoresDialogView;

        if (highscoresTableView != null)
            highscoresDialogView.render(highscoresTableView);
    }
}
