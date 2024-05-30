package ui.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import ui.controllers.AttractionCardController;

import java.io.IOException;

public class AttractionCard extends Pane {
    private AttractionCardController controller;
    public AttractionCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/cards/attraction_card.fxml"));
        try {
            fxmlLoader.load();
            this.controller = fxmlLoader.getController();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        getChildren().add(fxmlLoader.getRoot());
    }

    public void setController(AttractionCardController controller) {
        this.controller = controller;
    }

    public AttractionCardController getController() {
        return controller;
    }
}
