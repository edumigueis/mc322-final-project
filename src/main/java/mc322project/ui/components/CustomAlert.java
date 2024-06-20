package mc322project.ui.components;

import javafx.scene.control.Alert;

public class CustomAlert extends Alert {

    public CustomAlert(AlertType alertType, String contentText) {
        super(alertType);
        setContentText(contentText);
        // Apply custom styles
        getDialogPane().getStylesheets().add(getClass().getResource("/styling/alert.css").toExternalForm());
    }

    public static CustomAlert createInfoAlert(String contentText) {
        return new CustomAlert(AlertType.INFORMATION, contentText);
    }

    public static CustomAlert createWarningAlert(String contentText) {
        return new CustomAlert(AlertType.WARNING, contentText);
    }

    public static CustomAlert createErrorAlert(String contentText) {
        return new CustomAlert(AlertType.ERROR, contentText);
    }
}