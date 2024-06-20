package mc322project.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.ikonli.javafx.FontIcon;
import mc322project.ui.helpers.DurationFormatConverter;

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
                            hBox.setPadding(new Insets(2, 10, 2, 10));
                            hBox.setAlignment(Pos.CENTER);
                            Region spacer = new Region();
                            HBox.setHgrow(spacer, Priority.ALWAYS);
                            Label durationText = new Label(DurationFormatConverter.durationToString(item));
                            durationText.getStyleClass().add("text-label");

                            // Add an example icon
                            FontIcon icon = new FontIcon("jam-arrow-right");
                            icon.setFill(Paint.valueOf("#070606"));
                            icon.setIconSize(17);

                            hBox.getChildren().addAll(durationText, spacer, icon);
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
