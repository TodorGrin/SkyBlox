package com.todorhryn.skyblox.game;

import com.todorhryn.skyblox.game.tiles.EmptyTile;
import com.todorhryn.skyblox.game.tiles.Tile;
import com.todorhryn.skyblox.views.PlayfieldView;

import java.io.Serializable;

public class Playfield implements Serializable {
    private Tile[][] field;
    private int width, height;
    private Player player;
    private transient PlayfieldView view;
    private LevelState levelState = LevelState.ACTIVE;

    public Playfield(PlayfieldView view, int width, int height) {
        field = new Tile[width][height];
        player = new Player(this);
        this.width = width;
        this.height = height;
        this.view = view;

        for (int x = 2; x < width - 2; ++x) {
            for (int y = 2; y < height - 2; ++y) {
                field[x][y] = new Tile(this);
            }
        }

        clearBorders();
    }

    private void clearBorders() {
        for (int x = 0; x < width; ++x) {
            field[x][0] = new EmptyTile(this);
            field[x][1] = new EmptyTile(this);
            field[x][height - 2] = new EmptyTile(this);
            field[x][height - 1] = new EmptyTile(this);
        }

        for (int y = 2; y < height - 2; ++y) {
            field[0][y] = new EmptyTile(this);
            field[1][y] = new EmptyTile(this);
            field[width - 2][y] = new EmptyTile(this);
            field[width - 1][y] = new EmptyTile(this);
        }
    }

    public void render() {
        view.render();
    }

    public void setLevelState(LevelState levelState) {
        this.levelState = levelState;
    }

    public void setTile(int x, int y, Tile tile) {
        field[x][y] = tile;
        render();
    }

    public void setView(PlayfieldView view) {
        this.view = view;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSize(int width, int height) {
        if (width < 5 || height < 5)
            return;

        Tile[][] newField = new Tile[width][height];

        int minWidth = Math.min(this.width, width);
        int minHeight = Math.min(this.height, height);

        for (int x = 0; x < minWidth; ++x) {
            System.arraycopy(field[x], 0, newField[x], 0, minHeight);
        }

        for (int x = 0; x < width; ++x) {
            for (int y = minHeight; y < height; ++y) {
                newField[x][y] = new EmptyTile(this);
            }
        }

        for (int x = minWidth; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                newField[x][y] = new EmptyTile(this);
            }
        }

        field = newField;
        this.width = width;
        this.height = height;
        player.setPosition(Math.min(width - 3, player.getMainBlockX()), Math.min(height - 3, player.getMainBlockY()), Math.min(width - 3, player.getSecondBlockX()), Math.min(height - 3, player.getSecondBlockY()));

        clearBorders();
        render();
    }

    public Tile getTile(int x, int y) {
        return field[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public LevelState getLevelState() {
        return levelState;
    }
}
