package ui.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import ui.controllers.ItineraryDayCarouselController;

import java.io.IOException;
import java.util.List;

public class ItineraryDayCarousel extends Pane {
    private final ItineraryDayCarouselController controller;
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
