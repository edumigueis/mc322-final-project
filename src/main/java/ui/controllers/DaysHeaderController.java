package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objects.Activity;
import objects.Attraction;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class DaysHeaderController implements AttractionModalController.Callback, Initializable {
    private int iterations = 0;
    private LocalDate startDate = LocalDate.of(1999, 6, 2); // Start date: June 2, 1999

    @FXML
    private GridPane grid;

    @FXML
    private void nextWeek() {
        startDate = startDate.plusDays(3); // Advance the week by one week
        updateLabels();
    }

    public void updateLabels() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE");
        for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
            iterations++;

            Label labelDate = (Label) grid.lookup("#dayLabel" + (columnIndex + 1));
            Label labelIndex = (Label) grid.lookup("#indexLabel" + (columnIndex + 1));

            // Set the text to the label if found
            if (labelDate != null)
                labelDate.setText(formatter.format(startDate.plusDays(columnIndex)));
            if (labelIndex != null)
                labelIndex.setText(iterations + "");
        }
    }

    @FXML
    private void handleLabelClick(MouseEvent event) {
        Label label = (Label) event.getSource();

        // Retrieve the day information from the label's userData property
        int day = this.iterations - Integer.parseInt((String) label.getUserData());
        clickShow(event, day);
    }

    private void clickShow(MouseEvent event, int day) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/attraction_modal.fxml"));
            Parent root = loader.load();
            AttractionModalController modalController = loader.getController();
            modalController.setCallback(this);
            //modalController.setData(this.parent);
            stage.setScene(new Scene(root));
            stage.setTitle("My modal window");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnResult(Attraction picked) {
        // Handle the returned result
        // Aqui será retornada a atração nova
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.updateLabels();
    }

    public void setData(List<Activity> attractions){

    }
}
