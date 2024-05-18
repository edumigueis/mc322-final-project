package ui.controllers;

import helpers.BusinessHours;
import helpers.Location;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import entities.activities.Activity;
import entities.activities.Attraction;
import ui.components.AttractionCardCell;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AttractionModalController implements FilterBarController.FilterChangeListener{
    private Callback callback;

    @FXML
    private Button closeButton;
    @FXML
    private ListView<Activity> cardsContainer;
    @FXML
    private Pane filterBarContainer;
    private List<Activity> activityList;

    @FXML
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/filter_bar.fxml"));
            AnchorPane filterBar = loader.load();
            FilterBarController controller = loader.getController();
            controller.setFilterChangeListener(this);
            filterBarContainer.getChildren().add(filterBar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setData(List<Activity> activities) throws IOException {
        this.activityList = activities;
        updateListView(activities);
    }

    private void updateListView(List<Activity> activities) {
        ObservableList<Activity> list = FXCollections.observableArrayList(activities);
        cardsContainer.setItems(list);
        cardsContainer.setCellFactory(param -> new AttractionCardCell());
    }

    @Override
    public void onFilterChange(Filter filter) {
        /* NEEDS WORK TO DO
        List<Activity> filteredAttractions = activityList.stream()
                .filter(attraction -> filter.getCategory().equals("All") || attraction.getCategory().equals(filter.getCategory()))
                .filter(attraction -> attraction.getPrice() <= filter.getPriceRange())
                .collect(Collectors.toList());
        updateListView(filteredAttractions);*/
    }

    @FXML
    private void handleReturnValue() {
        Activity value = cardsContainer.getSelectionModel().getSelectedItem();

        // Call the callback method with the return value
        if (callback != null)
            callback.returnResult(value);

        // Close the modal window
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public interface Callback {
        void returnResult(Activity result);
    }

    public static class Filter {
        private final String category;
        private final double priceRange;

        public Filter(String category, double priceRange) {
            this.category = category;
            this.priceRange = priceRange;
        }

        public String getCategory() {
            return category;
        }

        public double getPriceRange() {
            return priceRange;
        }
    }
}
