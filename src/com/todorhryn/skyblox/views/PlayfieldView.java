package com.todorhryn.skyblox.views;

import com.todorhryn.skyblox.controllers.PlayfieldController;
import com.todorhryn.skyblox.game.LevelLoader;
import com.todorhryn.skyblox.game.Playfield;
import com.todorhryn.skyblox.game.tiles.EmptyTile;
import com.todorhryn.skyblox.game.tiles.ExitTile;
import com.todorhryn.skyblox.game.tiles.FragileTile;
import com.todorhryn.skyblox.game.tiles.Tile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;

public class PlayfieldView {
    protected GraphicsContext ctx;
    protected Playfield playfield;
    protected Scene scene;

    protected PlayfieldView() {}

    public PlayfieldView(String scenePath, Scene scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scenePath));
        Parent root = fxmlLoader.load();
        scene.setRoot(root);

        this.scene = scene;
        this.ctx = ((Canvas) scene.lookup("#canvas")).getGraphicsContext2D();
        this.playfield = LevelLoader.getInstance().load(this);

        PlayfieldController controller = fxmlLoader.getController();
        controller.setPlayfield(playfield);

        render();
    }

    protected Color getTileColor(Class<? extends Tile> tileClass) {
        if (tileClass == FragileTile.class)
            return Color.web("FF7C15");
        else if (tileClass == EmptyTile.class)
            return Color.web("FFFFFF");
        else if (tileClass == ExitTile.class)
            return Color.web("000000");
        else
            return Color.web("717171");
    }

    public void render() {
        if (ctx == null)
            return;

        ctx.getCanvas().setWidth(playfield.getWidth() * 32);
        ctx.getCanvas().setHeight(playfield.getHeight() * 32);
        ctx.setFill(Color.web("FFFFFF"));
        ctx.fillRect(0, 0, ctx.getCanvas().getWidth(), ctx.getCanvas().getHeight());

        for (int x = 0; x < playfield.getWidth(); ++x) {
            for (int y = 0; y < playfield.getHeight(); ++y) {
                Tile tile = playfield.getTile(x, y);
                ctx.setFill(getTileColor(tile.getClass()));
                ctx.fillRect(x * 32, y * 32, 30, 30);
            }
        }

        ctx.setFill(Color.web("322593"));

        if (playfield.getPlayer().getMainBlockX() == playfield.getPlayer().getSecondBlockX() && playfield.getPlayer().getMainBlockY() + 1 == playfield.getPlayer().getSecondBlockY())
            ctx.fillRect(playfield.getPlayer().getMainBlockX() * 32 + 2, playfield.getPlayer().getMainBlockY() * 32 + 2, 26, 58);
        else if (playfield.getPlayer().getMainBlockX() == playfield.getPlayer().getSecondBlockX() && playfield.getPlayer().getMainBlockY() - 1 == playfield.getPlayer().getSecondBlockY())
            ctx.fillRect(playfield.getPlayer().getSecondBlockX() * 32 + 2, playfield.getPlayer().getSecondBlockY() * 32 + 2, 26, 58);
        else if (playfield.getPlayer().getMainBlockY() == playfield.getPlayer().getSecondBlockY() && playfield.getPlayer().getMainBlockX() + 1 == playfield.getPlayer().getSecondBlockX())
            ctx.fillRect(playfield.getPlayer().getMainBlockX() * 32 + 2, playfield.getPlayer().getMainBlockY() * 32 + 2, 58, 26);
        else if (playfield.getPlayer().getMainBlockY() == playfield.getPlayer().getSecondBlockY() && playfield.getPlayer().getMainBlockX() - 1 == playfield.getPlayer().getSecondBlockX())
            ctx.fillRect(playfield.getPlayer().getSecondBlockX() * 32 + 2, playfield.getPlayer().getSecondBlockY() * 32 + 2, 58, 26);
        else {
            ctx.fillRect(playfield.getPlayer().getMainBlockX() * 32 + 2, playfield.getPlayer().getMainBlockY() * 32 + 2, 26, 26);
            ctx.fillRect(playfield.getPlayer().getSecondBlockX() * 32 + 2, playfield.getPlayer().getSecondBlockY() * 32 + 2, 26, 26);
        }
    }

    public void setPlayfield(Playfield playfield) {
        this.playfield = playfield;
    }
}
