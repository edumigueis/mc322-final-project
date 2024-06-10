package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import org.kordamp.ikonli.javafx.FontIcon;
import ui.helpers.DurationFormatConverter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DurationSelectorController {

    private Duration selectedDuration;

    @FXML
    private ListView<Duration> timeListView;

    private Stage stage;

    private final ObservableList<Duration> timeList = FXCollections.observableArrayList();

    public void initialize() {
        populateTimeList();
        timeListView.setItems(timeList);

        // Set custom cell factory to add style class to each item
        timeListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Duration> call(ListView<Duration> listView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Duration item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            // Create HBox with Text and Icon
                            HBox hBox = new HBox();
                            Label durationText = new Label(DurationFormatConverter.durationToString(item));
                            durationText.getStyleClass().add("text-label");

                            // Add an example icon
                            FontIcon icon = new FontIcon("jam-arrow-circle-left");

                            hBox.getChildren().addAll(icon, durationText);
                            setGraphic(hBox);
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
        List<Duration> times = new ArrayList<>();
        for (int hours = 0; hours <= 4; hours++) {
            for (int minutes = 15; minutes < 60; minutes += 15) {
                times.add(Duration.ofHours(hours).plusMinutes(minutes));
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
    public Duration getSelectedDuration() {
        return selectedDuration;
    }
}
