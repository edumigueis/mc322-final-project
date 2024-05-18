package ui.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.controllers.ItineraryDayCarouselController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItineraryDayCarousel extends Pane {
    private ItineraryDayCarouselController controller;
    private List<ItineraryDayView> children;

    public ItineraryDayCarousel() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/itinerary_day_carousel.fxml"));
        try {
            fxmlLoader.load();
            this.controller = fxmlLoader.getController();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        getChildren().add(fxmlLoader.getRoot());
    }

    public void setChildren(List<ItineraryDayView> children) {
        this.controller.start(children);
    }

    public ItineraryDayCarouselController getController() {
        return controller;
    }

}
