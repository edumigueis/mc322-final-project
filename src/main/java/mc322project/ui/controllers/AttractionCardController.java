package mc322project.ui.controllers;


import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import mc322project.GUIStarter;
import mc322project.entities.activities.I_Activity;
import mc322project.entities.activities.Places;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mc322project.entities.tours.Tour;
import mc322project.ui.components.ExpandableText;
import mc322project.ui.components.ResponsiveImage;

import java.util.List;
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
    private ScrollPane tourDataContainer;
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
            descriptionLabel.setMaximumWidth(240);
            descriptionLabel.setMaxLength(120);
            rootPane.setPrefHeight(300);

            businessTimeLabel.setVisible(false);
            businessTimeLabel.setManaged(false);
            List<I_Activity> activities = ((Tour) activity).getAttractionList();
            if(activities != null && !activities.isEmpty())
                for (I_Activity ac : ((Tour) activity).getAttractionList()) {
                    Label l = new Label(ac.getName());
                    l.getStyleClass().add("small-text-list-item");
                    l.setWrapText(true);
                    tourData.getChildren().add(l);
                }
            else{
                Label l = new Label("The tour varies depending on season and day.");
                l.getStyleClass().add("small-text-list-item");
                l.setWrapText(true);
                tourData.getChildren().add(l);
            }

            languageLabel.setText("Available in: " + ((Tour) activity).getLanguage());
            expandableTextPlaceholder2.getChildren().clear();
            expandableTextPlaceholder1.getChildren().add(descriptionLabel);
            tourSpecifics.setText(((Tour) activity).getCategory().getDisplayName());
        } else if (activity instanceof Places) {
            descriptionLabel.setMaximumWidth(230);
            descriptionLabel.setMaxLength(190);

            languageLabel.setVisible(false);
            tourDataContainer.setVisible(false);
            tourSpecifics.setVisible(false);
            languageLabel.setManaged(false);
            tourDataContainer.setManaged(false);
            tourSpecifics.setManaged(false);

            expandableTextPlaceholder1.getChildren().clear();
            expandableTextPlaceholder2.getChildren().add(descriptionLabel);
            businessTimeLabel.setText("Open: " + ((Places) activity).getOpenTime().getCurrentOpenHours());
        }

        titleLabel.setText(activity.getName().toUpperCase());
        priceLabel.setText("$" + activity.getPrice());
        descriptionLabel.setFullText(activity.getDescription().replaceAll("[\\n\\t\\r]", ""));
        attractionCardImage.setImageUrl(activity.getImageThumbURL());
    }
}
