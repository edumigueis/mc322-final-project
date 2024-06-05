package ui.components;

import entities.Transportation;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import ui.controllers.DisplacementCardController;

import java.io.IOException;

public class DisplacementCard extends HBox {

    public DisplacementCard(Transportation transportation) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/cards/displacement_card.fxml"));
        try {
            fxmlLoader.load();
            DisplacementCardController controller = fxmlLoader.getController();
            controller.setData(transportation);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        getChildren().add(fxmlLoader.getRoot());
    }
}
