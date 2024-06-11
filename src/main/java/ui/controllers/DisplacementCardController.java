package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import entities.Transportation;
import org.kordamp.ikonli.javafx.FontIcon;

public class DisplacementCardController {
    @FXML
    private Label typeLabel;
    @FXML
    private Label durationLabel;
    @FXML
    private FontIcon transportIcon;

    public void setData(Transportation transportation) {
        typeLabel.setText(transportation.getType().toString());
        durationLabel.setText(transportation.getEstimatedDuration().toString());
        switch (transportation.getType()){
            case BUS -> transportIcon.setIconLiteral("jam-bus");
            case CAR -> transportIcon.setIconLiteral("jam-car");
            case WALK -> transportIcon.setIconLiteral("jam-gps");
        }
    }
}

