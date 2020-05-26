package com.todorhryn.skyblox.game;

import java.io.Serializable;

public class Player implements Serializable {
    private int mainBlockX = 5,
                mainBlockY = 5;
    private int secondBlockX = 6,
                secondBlockY = 5;
    private Playfield playfield;

    public Player(Playfield playfield) {
        this.playfield = playfield;
    }

    private void moveBlocks(int mainBlockXOffset, int mainBlockYOffset, int secondBlockXOffset, int secondBlockYOffset) {
        if (playfield.getLevelState() != LevelState.ACTIVE)
            return;

        if (mainBlockX + mainBlockXOffset < 0 || mainBlockX + mainBlockXOffset >= playfield.getWidth() || mainBlockY + mainBlockYOffset < 0 || mainBlockY + mainBlockYOffset >= playfield.getHeight() ||
                secondBlockX + secondBlockXOffset < 0 || secondBlockX + secondBlockXOffset >= playfield.getWidth() || secondBlockY + secondBlockYOffset < 0 || secondBlockY + secondBlockYOffset >= playfield.getHeight())
            return;

        if (secondBlockXOffset == 0 && secondBlockYOffset == 0) {
            playfield.getTile(mainBlockX, mainBlockY).steppedOff();
            mainBlockX += mainBlockXOffset;
            mainBlockY += mainBlockYOffset;
            playfield.getTile(mainBlockX, mainBlockY).steppedOn(1);
        }
        else if (mainBlockXOffset == 0 && mainBlockYOffset == 0) {
            playfield.getTile(secondBlockX, secondBlockY).steppedOff();
            secondBlockX += secondBlockXOffset;
            secondBlockY += secondBlockYOffset;
            playfield.getTile(secondBlockX, secondBlockY).steppedOn(1);
        }
        else {
            if (mainBlockX == secondBlockX && mainBlockY == secondBlockY) {
                playfield.getTile(mainBlockX, mainBlockY).steppedOff();
            }
            else {
                playfield.getTile(mainBlockX, mainBlockY).steppedOff();
                playfield.getTile(secondBlockX, secondBlockY).steppedOff();
            }

            mainBlockX += mainBlockXOffset;
            mainBlockY += mainBlockYOffset;
            secondBlockX += secondBlockXOffset;
            secondBlockY += secondBlockYOffset;

            if (mainBlockX == secondBlockX && mainBlockY == secondBlockY) {
                playfield.getTile(mainBlockX, mainBlockY).steppedOn(2);
            }
            else {
                playfield.getTile(mainBlockX, mainBlockY).steppedOn(1);
                playfield.getTile(secondBlockX, secondBlockY).steppedOn(1);
            }
        }

        playfield.increaseStepsCount();
        playfield.render();
    }

    public void moveRight() {
        if (mainBlockX == secondBlockX && mainBlockY == secondBlockY)
            moveBlocks(1, 0, 2, 0);
        else if (mainBlockX == secondBlockX && (mainBlockY == secondBlockY - 1 || mainBlockY == secondBlockY + 1))
            moveBlocks(1, 0, 1, 0);
        else if (mainBlockY == secondBlockY && mainBlockX == secondBlockX - 1)
            moveBlocks(2, 0, 1, 0);
        else if (mainBlockY == secondBlockY && mainBlockX == secondBlockX + 1)
            moveBlocks(1, 0, 2, 0);
        else
            moveBlocks(1, 0, 0, 0);
    }

    public void moveLeft() {
        if (mainBlockX == secondBlockX && mainBlockY == secondBlockY)
            moveBlocks(-1, 0, -2, 0);
        else if (mainBlockX == secondBlockX && (mainBlockY == secondBlockY - 1 || mainBlockY == secondBlockY + 1))
            moveBlocks(-1, 0, -1, 0);
        else if (mainBlockY == secondBlockY && mainBlockX == secondBlockX - 1)
            moveBlocks(-1, 0, -2, 0);
        else if (mainBlockY == secondBlockY && mainBlockX == secondBlockX + 1)
            moveBlocks(-2, 0, -1, 0);
        else
            moveBlocks(-1, 0, 0, 0);
    }

    public void moveUp() {
        if (mainBlockX == secondBlockX && mainBlockY == secondBlockY)
            moveBlocks(0, -1, 0, -2);
        else if (mainBlockY == secondBlockY && (mainBlockX == secondBlockX - 1 || mainBlockX == secondBlockX + 1))
            moveBlocks(0, -1, 0, -1);
        else if (mainBlockX == secondBlockX && mainBlockY == secondBlockY - 1)
            moveBlocks(0, -1, 0, -2);
        else if (mainBlockX == secondBlockX && mainBlockY == secondBlockY + 1)
            moveBlocks(0, -2, 0, -1);
        else
            moveBlocks(0, -1, 0, 0);
    }

    public void moveDown() {
        if (mainBlockX == secondBlockX && mainBlockY == secondBlockY)
            moveBlocks(0, 1, 0, 2);
        else if (mainBlockY == secondBlockY && (mainBlockX == secondBlockX - 1 || mainBlockX == secondBlockX + 1))
            moveBlocks(0, 1, 0, 1);
        else if (mainBlockX == secondBlockX && mainBlockY == secondBlockY - 1)
            moveBlocks(0, 2, 0, 1);
        else if (mainBlockX == secondBlockX && mainBlockY == secondBlockY + 1)
            moveBlocks(0, 1, 0, 2);
        else
            moveBlocks(0, 1, 0, 0);
    }

    public void switchBlocks() {
        int tmp = mainBlockX;
        mainBlockX = secondBlockX;
        secondBlockX = tmp;

        tmp = mainBlockY;
        mainBlockY = secondBlockY;
        secondBlockY = tmp;
    }

    public void setPosition(int mainBlockX, int mainBlockY, int secondBlockX, int secondBlockY) {
        if (playfield.invalidCoordinates(mainBlockX, mainBlockY) || playfield.invalidCoordinates(secondBlockX, secondBlockY))
            return;

        this.mainBlockX = mainBlockX;
        this.mainBlockY = mainBlockY;
        this.secondBlockX = secondBlockX;
        this.secondBlockY = secondBlockY;
        playfield.render();
    }

    public void setPlayfield(Playfield playfield) {
        this.playfield = playfield;
    }

    public int getMainBlockX() {
        return mainBlockX;
    }

    public int getMainBlockY() {
        return mainBlockY;
    }

    public int getSecondBlockX() {
        return secondBlockX;
    }

    public int getSecondBlockY() {
        return secondBlockY;
    }
}
