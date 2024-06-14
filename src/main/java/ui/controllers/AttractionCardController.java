package ui.controllers;


import entities.activities.I_Activity;
import entities.activities.Places;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.components.ExpandableText;
import ui.components.ResponsiveImage;


public class AttractionCardController {
    @FXML
    private ExpandableText descriptionLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label businessTimeLabel;
    @FXML
    private ResponsiveImage attractionCardImage;

    public void setData(I_Activity activity) {
        assert activity instanceof Places;
        descriptionLabel.setFullText(activity.getDescription());
        titleLabel.setText(activity.getName().toUpperCase());
        businessTimeLabel.setText("Open hours: " + ((Places) activity).getOpenTime().getCurrentOpenHours());
        priceLabel.setText("$" + activity.getPrice());
        attractionCardImage.setImageUrl(((Places) activity).getImageThumbURL());
    }
}
