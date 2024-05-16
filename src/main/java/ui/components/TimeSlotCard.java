package ui.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import ui.controllers.TimeSlotCardController;

import java.io.IOException;

public class TimeSlotCard extends Pane {
    private TimeSlotCardController controller;

    public TimeSlotCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/time_slot_card.fxml"));
        try {
            fxmlLoader.load();
            this.controller = fxmlLoader.getController();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        getChildren().add(fxmlLoader.getRoot());
    }

    public void setController(TimeSlotCardController controller) {
        this.controller = controller;
    }

    public TimeSlotCardController getController() {
        return controller;
    }
}
