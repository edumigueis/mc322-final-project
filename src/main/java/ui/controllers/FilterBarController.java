package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import ui.controllers.AttractionModalController.Filter;

public class FilterBarController {

    @FXML
    private ToggleButton allToggleButton;

    @FXML
    private ToggleButton museumsToggleButton;

    @FXML
    private ToggleButton parksToggleButton;

    @FXML
    private ToggleButton galleriesToggleButton;

    @FXML
    private Slider priceRangeSlider;

    private ToggleGroup categoryToggleGroup;

    private FilterChangeListener filterChangeListener;

    @FXML
    public void initialize() {
        categoryToggleGroup = new ToggleGroup();
        allToggleButton.setToggleGroup(categoryToggleGroup);
        museumsToggleButton.setToggleGroup(categoryToggleGroup);
        parksToggleButton.setToggleGroup(categoryToggleGroup);
        galleriesToggleButton.setToggleGroup(categoryToggleGroup);

        // Select "All" by default
        categoryToggleGroup.selectToggle(allToggleButton);

        // Add listeners to update the filter whenever a change occurs
        categoryToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> notifyFilterChange());
        priceRangeSlider.valueProperty().addListener((observable, oldValue, newValue) -> notifyFilterChange());
    }

    public void setFilterChangeListener(FilterChangeListener listener) {
        this.filterChangeListener = listener;
    }

    private void notifyFilterChange() {
        if (filterChangeListener != null) {
            String selectedCategory = ((ToggleButton) categoryToggleGroup.getSelectedToggle()).getText();
            double selectedPrice = priceRangeSlider.getValue();
            Filter filter = new Filter(selectedCategory, selectedPrice);
            filterChangeListener.onFilterChange(filter);
        }
    }

    public interface FilterChangeListener {
        void onFilterChange(Filter filter);
    }
}
