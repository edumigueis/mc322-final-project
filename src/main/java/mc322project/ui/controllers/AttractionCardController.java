package mc322project.ui.controllers;


import mc322project.entities.activities.I_Activity;
import mc322project.entities.activities.Places;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mc322project.ui.components.ExpandableText;
import mc322project.ui.components.ResponsiveImage;


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
        businessTimeLabel.setText("Open: " + ((Places) activity).getOpenTime().getCurrentOpenHours());
        priceLabel.setText("$" + activity.getPrice());
        attractionCardImage.setImageUrl(activity.getImageThumbURL());
    }
}
