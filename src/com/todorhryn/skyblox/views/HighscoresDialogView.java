package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.controllers.HighscoresDialogController;
import com.todorhryn.skyblox.game.Account;
import com.todorhryn.skyblox.game.AccountManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HighscoresDialogView {
    private String levelName;

    public HighscoresDialogView(String levelName) throws IOException {
        this.levelName = levelName;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scenes/HighscoresDialog.fxml"));
        Parent parent = fxmlLoader.load();

        HighscoresDialogController controller = fxmlLoader.getController();
        controller.setHighscoresDialogView(this);

        Scene scene = new Scene(parent, 500, 400);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void render(TableView<Account> highscoresTableView) {
        highscoresTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        highscoresTableView.setPlaceholder(new Label("Тут ещё нет рекордов"));

        TableColumn<Account, String> usernameColumn = new TableColumn<>("Имя");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.prefWidthProperty().bind(highscoresTableView.widthProperty().divide(2));

        TableColumn<Account, String> highscoreColumn = new TableColumn<>("Количество шагов");
        highscoreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getHighscore(levelName))));
        highscoreColumn.prefWidthProperty().bind(highscoresTableView.widthProperty().divide(2));

        highscoresTableView.getSortOrder().add(highscoreColumn);

        highscoresTableView.getColumns().add(usernameColumn);
        highscoresTableView.getColumns().add(highscoreColumn);

        for (Account account : AccountManager.getInstance().getAccounts())
            if (account.getHighscore(levelName) >= 0)
                highscoresTableView.getItems().add(account);
    }
}
