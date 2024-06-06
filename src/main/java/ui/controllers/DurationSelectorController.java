package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class DurationSelectorController {

    private String selectedDuration;

    @FXML
    private ListView<String> timeListView;

    private Stage stage;

    private final ObservableList<String> timeList = FXCollections.observableArrayList();

    public void initialize() {
        populateTimeList();
        timeListView.setItems(timeList);

        // Set custom cell factory to add style class to each item
        timeListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Label durationText = new Label(item);
                            durationText.getStyleClass().add("text-label");
                            setGraphic(durationText);
                        }
                    }
                };
            }
        });
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
