package mc322project.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mc322project.entities.Transportation;
import org.kordamp.ikonli.javafx.FontIcon;
import mc322project.ui.helpers.DurationFormatConverter;

public class DisplacementCardController {
    @FXML
    private Label typeLabel;
    @FXML
    private Label durationLabel;
    @FXML
    private FontIcon transportIcon;

    public void setData(Transportation transportation) {
        typeLabel.setText(transportation.getType().toString());
        durationLabel.setText(DurationFormatConverter.durationToString(transportation.getEstimatedDuration()));
        switch (transportation.getType()) {
            case BUS -> transportIcon.setIconLiteral("jam-bus");
            case CAR -> transportIcon.setIconLiteral("jam-car");
            case WALK -> transportIcon.setIconLiteral("jam-gps");
        }
    }
}

