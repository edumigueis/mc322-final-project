package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import entities.Transportation;

public class DisplacementCardController {
    @FXML
    private Label typeLabel;
    @FXML
    private Label durationLabel;

    public void setData(Transportation transportation) {
        typeLabel.setText(transportation.getType().toString());
        durationLabel.setText(transportation.getEstimatedDuration().toString());
    }
}

