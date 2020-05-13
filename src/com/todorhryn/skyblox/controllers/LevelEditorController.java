package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.game.LevelEditor;
import com.todorhryn.skyblox.game.LevelEditorState;
import com.todorhryn.skyblox.game.LevelLoader;
import com.todorhryn.skyblox.game.Player;
import com.todorhryn.skyblox.game.tiles.*;
import com.todorhryn.skyblox.views.Alert;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;

public class LevelEditorController {
    private LevelEditor levelEditor;
    private String levelName;
    private int lastDraggedTileX = -1,
            lastDraggedTileY = -1;

    @FXML
    private TabPane tabpane;
    @FXML
    private CheckBox selectControlledTilesCheckbox, tileVisibleCheckbox;
    @FXML
    private Spinner<Integer> levelWidthSpinner, levelHeightSpinner;

    public LevelEditorController(LevelEditor levelEditor, String levelName) {
        this.levelEditor = levelEditor;
        this.levelName = levelName;
    }

    @FXML
    public void initialize() {
        levelWidthSpinner.getValueFactory().setValue(levelEditor.getWidth() - 4);
        levelHeightSpinner.getValueFactory().setValue(levelEditor.getHeight() - 4);
    }

    @FXML
    public void onButtonTileClicked() {
        levelEditor.setNewTileClass(Tile.class);
    }

    @FXML
    public void onButtonFragileTileClicked() {
        levelEditor.setNewTileClass(FragileTile.class);
    }

    @FXML
    public void onButtonEmptyTileClicked() {
        levelEditor.setNewTileClass(EmptyTile.class);
    }

    @FXML
    public void onButtonExitTileClicked() {
        levelEditor.setNewTileClass(ExitTile.class);
    }

    @FXML
    public void onButtonLightSwitchClicked() {
        levelEditor.setNewTileClass(LightSwitch.class);
    }

    @FXML
    public void onButtonHeavySwitchClicked() {
        levelEditor.setNewTileClass(HeavySwitch.class);
    }

    @FXML
    public void onButtonSaveClicked() {
        LevelLoader.getInstance().save(levelEditor, levelName);
    }

    @FXML
    public void onCheckboxTileVisibleClicked() {
        if (levelEditor.getSelectedTile() != null) {
            levelEditor.getSelectedTile().setVisible(tileVisibleCheckbox.isSelected());
            levelEditor.render();
        }
    }

    @FXML
    public void onCheckboxSelectControlledTilesClicked() {
        if (selectControlledTilesCheckbox.isSelected())
            levelEditor.setState(LevelEditorState.SELECT_CONTROLLED_TILES);
        else
            levelEditor.setState(LevelEditorState.SELECT_TILE);
    }

    @FXML
    public void onChangeLevelPropertiesClicked() {
        levelEditor.setSize(levelWidthSpinner.getValue() + 4, levelHeightSpinner.getValue() + 4);
    }

    @FXML
    public void onMovePlayerButtonClicked() {
        levelEditor.setState(LevelEditorState.MOVE_PLAYER);
    }

    @FXML
    public void onRotatePlayerButtonClicked() {
        Player player = levelEditor.getPlayer();

        if (player.getMainBlockX() != player.getSecondBlockX())
            player.setPosition(player.getMainBlockX(), player.getMainBlockY(), player.getMainBlockX(), player.getMainBlockY() + 1);
        else if (player.getMainBlockY() != player.getSecondBlockY())
            player.setPosition(player.getMainBlockX(), player.getMainBlockY(), player.getMainBlockX(), player.getMainBlockY());
        else
            player.setPosition(player.getMainBlockX(), player.getMainBlockY(), player.getMainBlockX() + 1, player.getMainBlockY());
    }

    @FXML
    public void onMouseMoved(MouseEvent mouseEvent) {
        int x = (int) mouseEvent.getX() / 32;
        int y = (int) mouseEvent.getY() / 32;

        if (levelEditor.getState() == LevelEditorState.ADD_NEW_TILE) {
            levelEditor.setNewTileX(x);
            levelEditor.setNewTileY(y);
        }
        else if (levelEditor.getState() == LevelEditorState.MOVE_PLAYER) {
            Player player = levelEditor.getPlayer();
            int dx = player.getSecondBlockX() - player.getMainBlockX();
            int dy = player.getSecondBlockY() - player.getMainBlockY();

            player.setPosition(x, y, x + dx, y + dy);
        }
    }

    @FXML
    public void onMouseDragged(MouseEvent mouseEvent) {
        int x = (int) (mouseEvent.getX() / 32);
        int y = (int) (mouseEvent.getY() / 32);

        if (x != lastDraggedTileX || y != lastDraggedTileY)
            onMousePressed(mouseEvent);
    }

    @FXML
    public void onMousePressed(MouseEvent mouseEvent) {
        int x = (int) (mouseEvent.getX() / 32);
        int y = (int) (mouseEvent.getY() / 32);
        lastDraggedTileX = x;
        lastDraggedTileY = y;

        try {
            levelEditor.onTileClicked(x, y);

            if (levelEditor.getState() == LevelEditorState.SELECT_TILE) {
                selectControlledTilesCheckbox.setVisible(levelEditor.getTile(x, y) instanceof TileController);
                tileVisibleCheckbox.setSelected(levelEditor.getTile(x, y).isVisible());
            } else if (levelEditor.getState() == LevelEditorState.MOVE_PLAYER)
                levelEditor.setState(LevelEditorState.DO_NOTHING);
        }
        catch (Exception e) {
            Alert.showError("Error", e.getLocalizedMessage());
        }
    }

    @FXML
    public void onTabSelectionChanged() {
        int index = tabpane.getSelectionModel().getSelectedIndex();

        if (index == 0)
            levelEditor.setState(LevelEditorState.ADD_NEW_TILE);
        else if (index == 1)
            levelEditor.setState(LevelEditorState.SELECT_TILE);
        else if (index == 2)
            levelEditor.setState(LevelEditorState.DO_NOTHING);
    }
}
