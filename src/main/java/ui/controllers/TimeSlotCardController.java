package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ui.helpers.DurationFormatConverter;
import viewmodels.TimeSlotViewModel;

import java.io.IOException;

public class TimeSlotCardController {
    @FXML
    private Region imageContainer;
    @FXML
    private Label titleLabel;
    @FXML
    private Label durationLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private MenuButton optionsMenu;

    public void initData(TimeSlotViewModel viewModel) {
        titleLabel.textProperty().bind(viewModel.nameBinding());
        viewModel.durationProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                durationLabel.setText(DurationFormatConverter.durationToString(newVal));
            } else {
                durationLabel.setText("");
            }
        });
        //TO DO: REMOVE COMMENT AND FIX LAYOUT (EXPANDABLETEXT)
        //descriptionLabel.textProperty().bind(viewModel.descriptionBinding());
    }
    @FXML
    private void removeSelf(){}
    @FXML
    private void alterDuration(){
        try {
            // Load the FXML for the modal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/duration_selector.fxml"));
            Parent root = loader.load();
            DurationSelectorController controller = loader.getController();

            // Create a new stage for the modal
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Select Duration");
            modalStage.setScene(new Scene(root));

            // Pass the stage reference to the modal controller
            controller.setStage(modalStage);

            // Show the modal
            modalStage.showAndWait();

            String selectedDuration = controller.getSelectedDuration();
            if (selectedDuration != null) {
                System.out.println("Selected duration: " + selectedDuration);
                // Pass the selected duration to further processing if needed
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void alterOrder(){}
}

