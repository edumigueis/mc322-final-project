package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DurationSelectorController {

    private String selectedDuration;

    @FXML
    private ListView<String> timeListView;

    private Stage stage;

    private final ObservableList<String> timeList = FXCollections.observableArrayList();

    public void initialize() {
        // Populate the time list
        populateTimeList();

        // Bind time list to ListView
        timeListView.setItems(timeList);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void populateTimeList() {
        List<String> times = new ArrayList<>();
        for (int hours = 0; hours <= 4; hours++) {
            for (int minutes = 0; minutes < 60; minutes += 15) {
                times.add(String.format("%dh %02dmin", hours, minutes));
            }
        }
        timeList.setAll(times);
    }

    @FXML
    private void handleSelect() {
        selectedDuration = timeListView.getSelectionModel().getSelectedItem();
        if (selectedDuration != null) {
            // Close the modal
            stage.close();
        }
    }

    // Method to get the selected duration
    public String getSelectedDuration() {
        return selectedDuration;
    }

}
