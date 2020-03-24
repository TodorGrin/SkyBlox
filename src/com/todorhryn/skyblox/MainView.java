package com.todorhryn.skyblox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainView extends Application {
    private Playfield field;
    private GraphicsContext ctx;

    @Override
    public void start(Stage primaryStage) throws Exception{
        field = new Playfield(this,10, 10);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("SkyBlox");
        primaryStage.setScene(new Scene(root, 640, 500));

        Controller controller = fxmlLoader.getController();
        controller.setPlayfield(field);

        ctx = ((Canvas) root.lookup("#canvas")).getGraphicsContext2D();
        drawField();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Color getTileColor(Class<? extends Tile> tileClass) {
        if (tileClass == FragileTile.class)
            return Color.web("FF7C15");
        else
            return Color.web("717171");
    }

    public void drawField() {
        ctx.getCanvas().setWidth(field.getWidth() * 32);
        ctx.getCanvas().setHeight(field.getHeight() * 32);
        ctx.clearRect(0, 0, ctx.getCanvas().getWidth(), ctx.getCanvas().getHeight());

        for (int x = 0; x < field.getWidth(); ++x) {
            for (int y = 0; y < field.getHeight(); ++y) {
                Tile tile = field.getTile(x, y);
                ctx.setFill(getTileColor(tile.getClass()));
                ctx.fillRect(x * 32, y * 32, 30, 30);
            }
        }

        ctx.setFill(Color.web("322593"));

        if (field.getPlayer().getMainBlockX() == field.getPlayer().getSecondBlockX() && field.getPlayer().getMainBlockY() + 1 == field.getPlayer().getSecondBlockY())
            ctx.fillRect(field.getPlayer().getMainBlockX() * 32 + 2, field.getPlayer().getMainBlockY() * 32 + 2, 26, 58);
        else if (field.getPlayer().getMainBlockX() == field.getPlayer().getSecondBlockX() && field.getPlayer().getMainBlockY() - 1 == field.getPlayer().getSecondBlockY())
            ctx.fillRect(field.getPlayer().getSecondBlockX() * 32 + 2, field.getPlayer().getSecondBlockY() * 32 + 2, 26, 58);
        else if (field.getPlayer().getMainBlockY() == field.getPlayer().getSecondBlockY() && field.getPlayer().getMainBlockX() + 1 == field.getPlayer().getSecondBlockX())
            ctx.fillRect(field.getPlayer().getMainBlockX() * 32 + 2, field.getPlayer().getMainBlockY() * 32 + 2, 58, 26);
        else if (field.getPlayer().getMainBlockY() == field.getPlayer().getSecondBlockY() && field.getPlayer().getMainBlockX() - 1 == field.getPlayer().getSecondBlockX())
            ctx.fillRect(field.getPlayer().getSecondBlockX() * 32 + 2, field.getPlayer().getSecondBlockY() * 32 + 2, 58, 26);
        else {
            ctx.fillRect(field.getPlayer().getMainBlockX() * 32 + 2, field.getPlayer().getMainBlockY() * 32 + 2, 26, 26);
            ctx.fillRect(field.getPlayer().getSecondBlockX() * 32 + 2, field.getPlayer().getSecondBlockY() * 32 + 2, 26, 26);
        }
    }
}
