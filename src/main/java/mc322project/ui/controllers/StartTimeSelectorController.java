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
    private ComboBox<Integer> hourComboBox;
    @FXML
    private ComboBox<Integer> minuteComboBox;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 18; i++) {
            hourComboBox.getItems().add(i);
        }
        for (int i = 0; i < 59; i++) {
            minuteComboBox.getItems().add(i);
        }

        // Set default values
        hourComboBox.setValue(0);
        minuteComboBox.setValue(0);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void selectTime() {
        int selectedHour = hourComboBox.getValue();
        int selectedMinute = minuteComboBox.getValue();
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
