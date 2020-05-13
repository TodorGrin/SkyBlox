package com.todorhryn.skyblox.views;

import javafx.scene.control.Label;

public abstract class Alert {
    public static void showError(String errorTitle, String errorMessage) {
        javafx.scene.control.Alert errorAlert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        errorAlert.setHeaderText(errorTitle);
        errorAlert.getDialogPane().setContent(new Label(errorMessage));
        errorAlert.showAndWait();
    }
}
