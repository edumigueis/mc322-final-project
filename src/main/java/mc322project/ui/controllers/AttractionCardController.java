package mc322project.ui.controllers;


import javafx.scene.layout.GridPane;
import mc322project.GUIStarter;
import mc322project.entities.activities.I_Activity;
import mc322project.entities.activities.Places;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mc322project.ui.components.ExpandableText;
import mc322project.ui.components.ResponsiveImage;

import java.util.Objects;


public class AttractionCardController {
    @FXML
    private GridPane rootPane;
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
        rootPane.getStylesheets().add(Objects.requireNonNull(GUIStarter.class.getResource("styling/styles.css")).toExternalForm());
        descriptionLabel.setFullText(activity.getDescription());
        titleLabel.setText(activity.getName().toUpperCase());
        businessTimeLabel.setText("Open: " + ((Places) activity).getOpenTime().getCurrentOpenHours());
        priceLabel.setText("$" + activity.getPrice());
        attractionCardImage.setImageUrl(activity.getImageThumbURL());
    }
}
