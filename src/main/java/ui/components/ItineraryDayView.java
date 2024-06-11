package ui.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ui.controllers.ItineraryDayViewController;

import java.io.IOException;

// TO DO: way to refactor
public class ItineraryDayView extends Pane {
    private final ItineraryDayViewController controller;

    public ItineraryDayView() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/itinerary_day_view.fxml"));
        try {
            fxmlLoader.load();
            this.controller = fxmlLoader.getController();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        getChildren().add(fxmlLoader.getRoot());
    }

    public ItineraryDayViewController getController() {
        return controller;
    }
}