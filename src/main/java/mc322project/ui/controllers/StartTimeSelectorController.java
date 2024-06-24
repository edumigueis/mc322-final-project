package mc322project.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class StartTimeSelectorController implements Initializable {
    private LocalTime selectedStart;

    @FXML
    private ComboBox<String> hourComboBox;
    @FXML
    private ComboBox<String> minuteComboBox;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 19; i++) {
            hourComboBox.getItems().add(String.format("%02d", i));
        }
        for (int i = 0; i < 60; i += 5) {
            minuteComboBox.getItems().add(String.format("%02d", i));
        }

        // Set default values
        hourComboBox.setValue("00");
        minuteComboBox.setValue("00");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void selectTime() {
        int selectedHour = Integer.parseInt(hourComboBox.getValue());
        int selectedMinute = Integer.parseInt(minuteComboBox.getValue());
        this.selectedStart = LocalTime.of(selectedHour, selectedMinute);
        if (selectedStart != null) {
            // Close the modal
            stage.close();
        }
    }

    // Method to get the selected duration
    public LocalTime getSelectedStart() {
        return selectedStart;
    }
}
