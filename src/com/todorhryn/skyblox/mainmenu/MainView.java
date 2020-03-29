package com.todorhryn.skyblox.mainmenu;

import com.todorhryn.skyblox.game.*;
import com.todorhryn.skyblox.leveleditor.LevelEditorView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView extends Application {
    private GraphicsContext ctx;
    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("SkyBlox");
        scene = new Scene(root, 640, 500);
        primaryStage.setScene(scene);

        MainMenuController controller = fxmlLoader.getController();
        controller.setMainView(this);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showLevel() throws Exception {
        GameView view = new GameView(scene);
    }

    public void showLevelEditor() throws IOException {
        LevelEditorView view = new LevelEditorView(scene);
    }
}
