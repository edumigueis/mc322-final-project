package mc322project.ui.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import mc322project.ui.controllers.ItineraryDayCarouselController;

import java.io.IOException;
import java.util.List;

public class ItineraryDayCarousel extends HBox {
    private final ItineraryDayCarouselController controller;

    public ItineraryDayCarousel() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/itinerary_day_carousel.fxml"));
        try {
            fxmlLoader.load();
            this.controller = fxmlLoader.getController();
            this.getChildren().add(fxmlLoader.getRoot());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setChildren(List<ItineraryDayView> children) {
        this.controller.start(children);
    }

    public ItineraryDayCarouselController getController() {
        return controller;
    }
}
