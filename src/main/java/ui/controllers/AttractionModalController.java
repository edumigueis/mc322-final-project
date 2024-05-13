package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import objects.Attraction;

public class AttractionModalController {
    private Callback callback;

    @FXML
    private Button closeButton;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @FXML
    private void handleReturnValue() {
        // Get the value you want to return
        Attraction value = new Attraction();

        // Call the callback method with the return value
        if (callback != null) {
            callback.returnResult(value);
        }

        // Close the modal window
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public interface Callback {
        void returnResult(Attraction result);
    }
}
