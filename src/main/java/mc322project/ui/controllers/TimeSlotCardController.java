package mc322project.ui.controllers;

import mc322project.core.itinerary.TimeSlot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mc322project.ui.components.ExpandableText;
import mc322project.ui.components.ResponsiveImage;
import mc322project.viewmodels.ItineraryDayViewModel;
import mc322project.viewmodels.TimeSlotViewModel;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class TimeSlotCardController {
    @FXML
    private ResponsiveImage imageContainer;
    @FXML
    private Label titleLabel;
    @FXML
    private Label durationLabel;
    @FXML
    private ExpandableText descriptionLabel;
    @FXML
    private MenuButton optionsMenu;

    private ItineraryDayViewModel itineraryViewModel;
    private TimeSlotViewModel timeSlotViewModel;

    public void initData(TimeSlotViewModel viewModel, ItineraryDayViewModel itineraryViewModel) {
        this.itineraryViewModel = itineraryViewModel;
        this.timeSlotViewModel = viewModel;
        titleLabel.textProperty().bind(timeSlotViewModel.nameBinding());
        optionsMenu.textProperty().bind(timeSlotViewModel.startTimeBinding());
        durationLabel.textProperty().bind(timeSlotViewModel.durationBinding());
        descriptionLabel.fullTextProperty().bind(timeSlotViewModel.descriptionBinding());
        imageContainer.setImageUrl(timeSlotViewModel.dataProperty().get().getImageThumbURL());
    }

    @FXML
    private void removeSelf() {
        this.itineraryViewModel.removeActivity(this.timeSlotViewModel.dataProperty().get());
    }

    @FXML
    private void alterDuration() {
        try {
            // Load the FXML for the modal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/selectors/duration_selector.fxml"));
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

            Duration selectedDuration = controller.getSelectedDuration();
            if (selectedDuration != null) {
                this.itineraryViewModel.alterStart(timeSlotViewModel.dataProperty().get(), timeSlotViewModel.startProperty().get().plus(selectedDuration));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void alterOrder() {
        try {
            // Load the FXML for the modal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/selectors/reorder_selector.fxml"));
            Parent root = loader.load();
            DraggableListController controller = loader.getController();
            controller.setTimeSlots(itineraryViewModel.getActivities());

            // Create a new stage for the modal
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Reorder");
            modalStage.setScene(new Scene(root));

            // Show the modal
            modalStage.showAndWait();

            List<TimeSlot> newOrder = controller.getCurrentTimeSlotsOrder();
            this.itineraryViewModel.setAllActivities(newOrder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

