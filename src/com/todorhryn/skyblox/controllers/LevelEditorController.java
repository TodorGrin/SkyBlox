package com.todorhryn.skyblox.controllers;

import com.todorhryn.skyblox.game.*;
import com.todorhryn.skyblox.game.tiles.*;
import com.todorhryn.skyblox.views.Alert;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class LevelEditorController extends Controller {
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
    @FXML
    private Button selectMainBlockPositionButton, selectSecondBlockPositionButton;

    @FXML
    public void initialize() {
        if (levelEditor == null)
            return;

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
    public void onButtonSplittingTileClicked() {
        levelEditor.setNewTileClass(SplittingTile.class);
    }

    @FXML
    public void onButtonSaveClicked() {
        LevelLoader.getInstance().save(levelEditor, levelName);
    }

    @FXML
    public void onButtonSelectMainBlockPositionClicked() {
        levelEditor.setState(LevelEditorState.SELECT_MAIN_BLOCK_POSITION);
    }

    @FXML
    public void onButtonSelectSecondBlockPositionClicked() {
        levelEditor.setState(LevelEditorState.SELECT_SECOND_BLOCK_POSITION);
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
        else if (levelEditor.getState() == LevelEditorState.SELECT_MAIN_BLOCK_POSITION) {
            SplittingTile splittingTile = (SplittingTile) levelEditor.getSelectedTile();
            splittingTile.setMainBlockPosition(x, y);
        }
        else if (levelEditor.getState() == LevelEditorState.SELECT_SECOND_BLOCK_POSITION) {
            SplittingTile splittingTile = (SplittingTile) levelEditor.getSelectedTile();
            splittingTile.setSecondBlockPosition(x, y);
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
                selectMainBlockPositionButton.setVisible(levelEditor.getTile(x, y) instanceof SplittingTile);
                selectSecondBlockPositionButton.setVisible(levelEditor.getTile(x, y) instanceof SplittingTile);

                tileVisibleCheckbox.setSelected(levelEditor.getTile(x, y).isVisible());
            }
            else if (levelEditor.getState() == LevelEditorState.MOVE_PLAYER)
                levelEditor.setState(LevelEditorState.DO_NOTHING);
            else if (levelEditor.getState() == LevelEditorState.SELECT_MAIN_BLOCK_POSITION || levelEditor.getState() == LevelEditorState.SELECT_SECOND_BLOCK_POSITION)
                levelEditor.setState(LevelEditorState.SELECT_TILE);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            Alert.showError("Error", e.getLocalizedMessage());
        }
    }

    @FXML
    public void onTabSelectionChanged() {
        if (levelEditor == null)
            return;

        int index = tabpane.getSelectionModel().getSelectedIndex();

        if (index == 0)
            levelEditor.setState(LevelEditorState.ADD_NEW_TILE);
        else if (index == 1)
            levelEditor.setState(LevelEditorState.SELECT_TILE);
        else if (index == 2)
            levelEditor.setState(LevelEditorState.DO_NOTHING);
    }

    @FXML
    public void backButtonClicked() {
        getSceneController().showLevelsList(true);
    }

    public void setLevelEditor(LevelEditor levelEditor) {
        this.levelEditor = levelEditor;

        levelWidthSpinner.getValueFactory().setValue(levelEditor.getWidth() - 4);
        levelHeightSpinner.getValueFactory().setValue(levelEditor.getHeight() - 4);
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
