package ui.controllers;

import java.io.IOException;

import entities.activities.I_Activity;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.components.AttractionCardCell;
import viewmodels.CityViewModel;
import viewmodels.ItineraryDayViewModel;

public class AttractionModalController implements FilterBarController.FilterChangeListener{
    private ItineraryDayViewModel viewModel;
    private CityViewModel cityViewModel;

    @FXML
    private Button closeButton;
    @FXML
    private ListView<I_Activity> cardsContainer;
    @FXML
    private Pane filterBarContainer;



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

    public void setViewModel(ItineraryDayViewModel viewModel) {
        this.viewModel = viewModel;
    }
    public void loadCity(CityViewModel viewModel) {
        this.cityViewModel = viewModel;
        cardsContainer.setItems(cityViewModel.getThingsToDo());
        cardsContainer.setCellFactory(param -> new AttractionCardCell());
    }

    @Override
    public void onFilterChange(Filter filter) {
        cardsContainer.setItems(cityViewModel.getThingsToDo());
        FilteredList<I_Activity> filteredActivities;

        if(filter.getCategory().equals("All")){
            filteredActivities = new FilteredList<>(cardsContainer.getItems(), i -> (i.getPrice()<=filter.getPriceRange()));

        } else{
            filteredActivities = new FilteredList<>(cardsContainer.getItems(), i -> (i.getCategory().getStringValue().equals(filter.getCategory()))&&(i.getPrice()<=filter.getPriceRange()));
        }
        cardsContainer.setItems(filteredActivities);
    }

    @FXML
    private void handleReturnValue() {
        I_Activity value = cardsContainer.getSelectionModel().getSelectedItem();

        // Call the callback method with the return value
        if(value != null)
            viewModel.addActivity(value);

        // Close the modal window
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
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
