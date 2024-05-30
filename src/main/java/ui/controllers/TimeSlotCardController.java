package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import viewmodels.TimeSlotViewModel;

public class TimeSlotCardController {
    @FXML
    private Region imageContainer;
    @FXML
    private Label titleLabel;
    @FXML
    private Label businessTimeLabel;
    @FXML
    private Label descriptionLabel;

    public void initData(TimeSlotViewModel viewModel) {
        titleLabel.textProperty().bind(viewModel.nameBinding());
        /*businessTimeLabel.textProperty().bindBidirectional("Business Time: " + viewModel.dataProperty());
        descriptionLabel.textProperty().bindBidirectional("Description: " + description);*/
    }
    //TO DO: AQUI TEREMOS INTERAÇÕES USER-UI
}

