package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.controllers.GameController;
import com.todorhryn.skyblox.game.LevelLoader;
import com.todorhryn.skyblox.game.Playfield;
import com.todorhryn.skyblox.game.tiles.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.HashMap;

public class PlayfieldView {
    public static final Color blockColor = Color.web("322593");
    public static final Color backgroundColor = Color.web("FFFFFF");

    private GraphicsContext ctx;
    private Playfield playfield;
    private Scene scene;
    private HashMap<Class<? extends Tile>, Image> images = new HashMap<>();

    protected PlayfieldView() {}

    public PlayfieldView(String scenePath, Scene scene, String levelName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scenePath));
        Parent root = fxmlLoader.load();
        scene.setRoot(root);

        this.scene = scene;
        this.ctx = ((Canvas) scene.lookup("#canvas")).getGraphicsContext2D();
        this.playfield = LevelLoader.getInstance().load(this, levelName);

        GameController controller = fxmlLoader.getController();
        controller.setPlayfield(playfield);

        render();
    }

    protected Image getTileImage(Class<? extends Tile> tileClass) {
        if (images.containsKey(tileClass))
            return images.get(tileClass);
        else {
            try {
                Image image = new Image("images/" + tileClass.getSimpleName() + ".png");
                images.put(tileClass, image);
                return image;
            }
            catch (Exception e) {
                Alert.showError("Error while loading " + tileClass.getSimpleName() + " image", e.getLocalizedMessage());
                return null;
            }
        }
    }

    public void renderTile(int x, int y) {
        Tile tile = playfield.getTile(x, y);

        if (tile.isVisible()) {
            ctx.drawImage(getTileImage(tile.getClass()), x * 32 + 1, y * 32 + 1, 30, 30);
        }
    }

    public void render() {
        if (ctx == null)
            return;

        ctx.getCanvas().setWidth(playfield.getWidth() * 32);
        ctx.getCanvas().setHeight(playfield.getHeight() * 32);
        ctx.setFill(backgroundColor);
        ctx.fillRect(0, 0, ctx.getCanvas().getWidth(), ctx.getCanvas().getHeight());

        for (int x = 0; x < playfield.getWidth(); ++x)
            for (int y = 0; y < playfield.getHeight(); ++y)
                renderTile(x, y);

        ctx.setFill(blockColor);

        if (playfield.getPlayer().getMainBlockX() == playfield.getPlayer().getSecondBlockX() && playfield.getPlayer().getMainBlockY() + 1 == playfield.getPlayer().getSecondBlockY())
            ctx.fillRect(playfield.getPlayer().getMainBlockX() * 32 + 3, playfield.getPlayer().getMainBlockY() * 32 + 3, 26, 58);
        else if (playfield.getPlayer().getMainBlockX() == playfield.getPlayer().getSecondBlockX() && playfield.getPlayer().getMainBlockY() - 1 == playfield.getPlayer().getSecondBlockY())
            ctx.fillRect(playfield.getPlayer().getSecondBlockX() * 32 + 3, playfield.getPlayer().getSecondBlockY() * 32 + 3, 26, 58);
        else if (playfield.getPlayer().getMainBlockY() == playfield.getPlayer().getSecondBlockY() && playfield.getPlayer().getMainBlockX() + 1 == playfield.getPlayer().getSecondBlockX())
            ctx.fillRect(playfield.getPlayer().getMainBlockX() * 32 + 3, playfield.getPlayer().getMainBlockY() * 32 + 3, 58, 26);
        else if (playfield.getPlayer().getMainBlockY() == playfield.getPlayer().getSecondBlockY() && playfield.getPlayer().getMainBlockX() - 1 == playfield.getPlayer().getSecondBlockX())
            ctx.fillRect(playfield.getPlayer().getSecondBlockX() * 32 + 3, playfield.getPlayer().getSecondBlockY() * 32 + 3, 58, 26);
        else {
            ctx.fillRect(playfield.getPlayer().getMainBlockX() * 32 + 3, playfield.getPlayer().getMainBlockY() * 32 + 3, 26, 26);
            ctx.fillRect(playfield.getPlayer().getSecondBlockX() * 32 + 3, playfield.getPlayer().getSecondBlockY() * 32 + 3, 26, 26);
        }
    }

    public Playfield getPlayfield() {
        return playfield;
    }
    public Scene getScene() {
        return scene;
    }
    public GraphicsContext getCtx() {
        return ctx;
    }

    public void setPlayfield(Playfield playfield) {
        this.playfield = playfield;
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public void setCtx(GraphicsContext ctx) {
        this.ctx = ctx;
    }
}
