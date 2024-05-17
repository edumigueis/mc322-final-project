package ui.controllers;

import helpers.BusinessHours;
import helpers.Location;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import entities.activities.Activity;
import entities.activities.Attraction;
import ui.components.AttractionCardCell;

import java.io.IOException;
import java.util.List;

public class AttractionModalController {
    private Callback callback;

    @FXML
    private Button closeButton;
    @FXML
    private ListView<Activity> cardsContainer;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setData(List<Activity> activities) throws IOException {
        ObservableList<Activity> activitiesList = FXCollections.observableArrayList(activities);

        cardsContainer.setItems(activitiesList);
        cardsContainer.setCellFactory(param -> new AttractionCardCell());
    }

    @FXML
    private void handleReturnValue() {
        Attraction value = new Attraction(new Location(1,2), "a", new BusinessHours(), "a", "");

        // Call the callback method with the return value
        if (callback != null)
            callback.returnResult(value);

        // Close the modal window
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public interface Callback {
        void returnResult(Attraction result);
    }
}
