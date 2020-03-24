package com.todorhryn.skyblox;

public class Playfield {
    private Tile[][] field;
    private int width, height;
    private Player player;
    private MainView mainView;

    Playfield(MainView mainView, int width, int height) {
        field = new Tile[width][height];
        player = new Player(this);
        this.width = width;
        this.height = height;
        this.mainView = mainView;

        for (int x = 0; x < 10; ++x) {
            for (int y = 0; y < 10; ++y) {
                field[x][y] = new Tile();
            }
        }

        field[0][0] = new FragileTile();
    }

    public void render() {
        mainView.drawField();
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
}
