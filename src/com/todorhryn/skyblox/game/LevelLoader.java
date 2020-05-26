package com.todorhryn.skyblox.game;

import com.todorhryn.skyblox.views.Alert;
import com.todorhryn.skyblox.views.PlayfieldView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LevelLoader {
    private static LevelLoader levelLoader;

    private LevelLoader() {
    }

    private String getLevelPath(String levelName) {
        return "data/levels/" + levelName + ".skyblox";
    }

    public static LevelLoader getInstance() {
        if (levelLoader == null)
            levelLoader = new LevelLoader();

        return levelLoader;
    }

    public Playfield load(PlayfieldView playfieldView, String levelName) {
        Playfield playfield;

        try (
            FileInputStream file = new FileInputStream(getLevelPath(levelName));
            ObjectInputStream in = new ObjectInputStream(file);
        ){
            playfield = (Playfield) in.readObject();
            playfield.setView(playfieldView);
        }
        catch (IOException | ClassNotFoundException ex) {
            Alert.showError("Error while loading level", ex.getLocalizedMessage());
            playfield = new Playfield(playfieldView,14, 14);
        }

        playfield.setLevelName(levelName);

        return playfield;
    }

    public void save(Playfield playfield, String levelName) {
        File file = new File(getLevelPath(levelName));
        file.getParentFile().mkdirs();

        try (
                FileOutputStream os = new FileOutputStream(getLevelPath(levelName));
                ObjectOutputStream out = new ObjectOutputStream(os);
        ){
            out.writeObject(playfield);
        }
        catch (IOException e) {
            Alert.showError("Error while saving level", e.getLocalizedMessage());
        }
    }
}
