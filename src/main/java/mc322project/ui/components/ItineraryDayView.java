package mc322project.ui.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import mc322project.GUIStarter;
import mc322project.ui.controllers.ItineraryDayViewController;

import java.io.IOException;

public class ItineraryDayView extends Pane {
    private final ItineraryDayViewController controller;

    public ItineraryDayView() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(GUIStarter.class.getResource("components/itinerary_day_view.fxml"));
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
