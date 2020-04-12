package com.todorhryn.skyblox.game;

import com.todorhryn.skyblox.game.tiles.EmptyTile;
import com.todorhryn.skyblox.game.tiles.ExitTile;
import com.todorhryn.skyblox.game.tiles.FragileTile;
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

        for (int x = 2; x < width - 2; ++x) {
            for (int y = 2; y < height - 2; ++y) {
                field[x][y] = new Tile(this);
            }
        }

        field[3][3] = new FragileTile(this);
        field[9][9] = new ExitTile(this);
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
