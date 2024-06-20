package mc322project.ui.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import mc322project.ui.controllers.CityCardController;
import mc322project.ui.helpers.CardParent;

import java.io.IOException;

public class CityCard extends Pane {
    private CityCardController controller;

    public CityCard(CardParent parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/cards/city_card.fxml"));
        try {
            fxmlLoader.load();
            this.controller = fxmlLoader.getController();
            this.controller.setParentController(parent);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        getChildren().add(fxmlLoader.getRoot());
    }

    public void setController(CityCardController controller) {
        this.controller = controller;
    }

    public CityCardController getController() {
        return controller;
    }
}
    
