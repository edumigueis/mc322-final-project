package mc322project.ui.controllers;


import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mc322project.GUIStarter;
import mc322project.entities.activities.I_Activity;
import mc322project.entities.activities.Places;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mc322project.entities.tours.Tour;
import mc322project.ui.components.ExpandableText;
import mc322project.ui.components.ResponsiveImage;

import java.util.Objects;


public class AttractionCardController {
    @FXML
    private GridPane rootPane;
    @FXML
    private Label priceLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label businessTimeLabel;
    @FXML
    private Label languageLabel;
    @FXML
    private ResponsiveImage attractionCardImage;
    @FXML
    private VBox tourData;
    @FXML
    private VBox expandableTextPlaceholder1;
    @FXML
    private VBox expandableTextPlaceholder2;
    @FXML
    private Label tourSpecifics;

    public void setData(I_Activity activity) {
        rootPane.getStylesheets().add(Objects.requireNonNull(GUIStarter.class.getResource("styling/styles.css")).toExternalForm());
        ExpandableText descriptionLabel = new ExpandableText();
        descriptionLabel.getStyleClass().add("expandable-text");

        if (activity instanceof Tour) {
            descriptionLabel.setMaximumWidth(270);
            descriptionLabel.setMaxLength(70);
            rootPane.setPrefHeight(300);
            businessTimeLabel.setVisible(false);
            businessTimeLabel.setManaged(false);
            for (I_Activity ac : ((Tour) activity).getAttractionList()) {
                Label l = new Label(ac.getName());
                l.getStyleClass().add("small-text-list-item");
                tourData.getChildren().add(l);
            }
            languageLabel.setText("Available in: " + ((Tour) activity).getLanguage());
            expandableTextPlaceholder2.getChildren().clear();
            expandableTextPlaceholder1.getChildren().add(descriptionLabel);
            tourSpecifics.setText(((Tour) activity).getCategory().getDisplayName());
        } else if (activity instanceof Places) {
            descriptionLabel.setMaximumWidth(260);
            descriptionLabel.setMaxLength(100);
            languageLabel.setVisible(false);
            tourData.setVisible(false);
            tourSpecifics.setVisible(false);
            languageLabel.setManaged(false);
            tourData.setManaged(false);
            tourSpecifics.setManaged(false);
            expandableTextPlaceholder1.getChildren().clear();
            expandableTextPlaceholder2.getChildren().add(descriptionLabel);
            businessTimeLabel.setText("Open: " + ((Places) activity).getOpenTime().getCurrentOpenHours());
        }
        titleLabel.setText(activity.getName().toUpperCase());
        priceLabel.setText("$" + activity.getPrice());
        descriptionLabel.setFullText(activity.getDescription());
        attractionCardImage.setImageUrl(activity.getImageThumbURL());
    }
}
