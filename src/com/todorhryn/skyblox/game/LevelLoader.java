package com.todorhryn.skyblox.game;

import com.todorhryn.skyblox.views.PlayfieldView;

import java.io.*;

public class LevelLoader {
    private static LevelLoader levelLoader;
    private static String saveFile = "levels\\levelSave.skyblox";

    private LevelLoader() {

    }

    public static LevelLoader getInstance() {
        if (levelLoader == null)
            levelLoader = new LevelLoader();

        return levelLoader;
    }

    public Playfield load(PlayfieldView playfieldView) {
        Playfield playfield;

        try {
            FileInputStream file = new FileInputStream(saveFile);
            ObjectInputStream in = new ObjectInputStream(file);

            playfield = (Playfield) in.readObject();
            playfield.setView(playfieldView);

            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException ex) {
            playfield = new Playfield(playfieldView,14, 14);
        }

        return playfield;
    }

    public void save(Playfield playfield) {
        try {
            File file = new File(saveFile);
            file.getParentFile().mkdirs();
            FileOutputStream os = new FileOutputStream(saveFile);
            ObjectOutputStream out = new ObjectOutputStream(os);

            out.writeObject(playfield);

            out.close();
            os.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
