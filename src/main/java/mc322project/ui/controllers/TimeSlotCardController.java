package mc322project.ui.controllers;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import mc322project.GUIStarter;
import mc322project.core.itinerary.TimeSlot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mc322project.entities.activities.I_Activity;
import mc322project.entities.activities.Places;
import mc322project.entities.tours.Tour;
import mc322project.ui.components.CustomAlert;
import mc322project.ui.components.ExpandableText;
import mc322project.ui.components.ResponsiveImage;
import mc322project.viewmodels.ItineraryDayViewModel;
import mc322project.viewmodels.TimeSlotViewModel;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

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
    @FXML
    private Label tourLanguage;
    @FXML
    private VBox tourData;
    @FXML
    private GridPane mainContainer;

    private ItineraryDayViewModel itineraryViewModel;
    private TimeSlotViewModel timeSlotViewModel;

    public void initData(TimeSlotViewModel viewModel, ItineraryDayViewModel itineraryViewModel) {
        I_Activity data = viewModel.dataProperty().get();

        if (data instanceof Tour) {
            mainContainer.setPrefHeight(230);
            tourLanguage.setText(((Tour) data).getLanguage());

            for (I_Activity activity : ((Tour) data).getAttractionList()) {
                Label l = new Label(activity.getName());
                l.getStyleClass().add("small-text-list-item");
                tourData.getChildren().add(l);
            }
        } else if (data instanceof Places) {
            tourData.setVisible(false);
            tourLanguage.setVisible(false);
        }

        this.itineraryViewModel = itineraryViewModel;
        this.timeSlotViewModel = viewModel;
        titleLabel.textProperty().bind(timeSlotViewModel.nameBinding());
        optionsMenu.textProperty().bind(timeSlotViewModel.startTimeBinding());
        durationLabel.textProperty().bind(timeSlotViewModel.durationBinding());
        descriptionLabel.fullTextProperty().bind(timeSlotViewModel.descriptionBinding());
        imageContainer.setImageUrl(data.getImageThumbURL());
    }

    @FXML
    private void removeSelf() {
        optionsMenu.hide();
        this.itineraryViewModel.removeActivity(this.timeSlotViewModel.dataProperty().get(), this.timeSlotViewModel.appearancesProperty().get());
    }

    @FXML
    private void alterDuration() {
        try {
            // Load the FXML for the modal
            FXMLLoader loader = new FXMLLoader(GUIStarter.class.getResource("components/selectors/duration_selector.fxml"));
            Parent root = loader.load();
            DurationSelectorController controller = loader.getController();

            // Create a new stage for the modal
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Select Duration");

            Scene scene = new Scene(root);
            String stylesheet = Objects.requireNonNull(GUIStarter.class.getResource("styling/styles.css")).toExternalForm();
            String stylesheet2 = Objects.requireNonNull(GUIStarter.class.getResource("styling/listview.css")).toExternalForm();
            scene.getStylesheets().addAll(stylesheet, stylesheet2);
            modalStage.setScene(scene);

            // Pass the stage reference to the modal controller
            controller.setStage(modalStage);

            // Show the modal
            modalStage.showAndWait();

            Duration selectedDuration = controller.getSelectedDuration();
            if (selectedDuration != null) {
                try {
                    this.itineraryViewModel.alterDuration(timeSlotViewModel.dataProperty().get(), timeSlotViewModel.appearancesProperty().get(), selectedDuration);
                } catch (UnsupportedOperationException e) {
                    CustomAlert alert = CustomAlert.createWarningAlert("This duration is too long for this day.");
                    alert.setTitle("Warning");
                    alert.setHeaderText(null); // Remove header text
                    alert.showAndWait();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void alterOrder() {
        try {
            // Load the FXML for the modal
            FXMLLoader loader = new FXMLLoader(GUIStarter.class.getResource("components/selectors/reorder_selector.fxml"));
            Parent root = loader.load();
            DraggableListController controller = loader.getController();
            controller.setViewModel(itineraryViewModel);

            // Create a new stage for the modal
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Reorder");

            Scene scene = new Scene(root);
            String stylesheet = Objects.requireNonNull(GUIStarter.class.getResource("styling/styles.css")).toExternalForm();
            String stylesheet2 = Objects.requireNonNull(GUIStarter.class.getResource("styling/listview.css")).toExternalForm();
            scene.getStylesheets().addAll(stylesheet, stylesheet2);
            modalStage.setScene(scene);

            // Show the modal
            modalStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

