package ui.controllers;

import entities.Tour;
import entities.activities.Activity;
import entities.activities.Attraction;
import entities.activities.Museum;
import javafx.fxml.FXML;
import ui.components.ExpandableText;

public class AttractionCardController {
    @FXML
    private ExpandableText descriptionLabel;

    public void setData(Activity activity) {
        if (activity instanceof Tour) {
            Tour tour = (Tour) activity;

        } else {
            if (activity instanceof Museum) {
                Museum museum = (Museum) activity;
            }
            // ... TO DO
        }
        descriptionLabel.setFullText(((Attraction)activity).getDescription());
    }
}
