package ui.controllers;

import helpers.PriceRange;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.Duration;
import ui.components.Filter;

public class FilterBarController {

    @FXML
    private ToggleButton allToggleButton;

    @FXML
    private ToggleButton museumsToggleButton;

    @FXML
    private ToggleButton parksToggleButton;

    @FXML
    private ToggleButton restaurantsToggleButton;
    @FXML
    private ToggleButton theatersToggleButton;
    @FXML
    private ToggleButton touristicToggleButton;


    @FXML
    private TextField minPriceTextField;

    @FXML
    private TextField maxPriceTextField;


    private ToggleGroup categoryToggleGroup;

    private FilterChangeListener filterChangeListener;

    @FXML
    public void initialize() {
        categoryToggleGroup = new ToggleGroup();
        allToggleButton.setToggleGroup(categoryToggleGroup);
        museumsToggleButton.setToggleGroup(categoryToggleGroup);
        parksToggleButton.setToggleGroup(categoryToggleGroup);
        restaurantsToggleButton.setToggleGroup(categoryToggleGroup);
        theatersToggleButton.setToggleGroup(categoryToggleGroup);
        touristicToggleButton.setToggleGroup(categoryToggleGroup);

        // Select "All" by default
        categoryToggleGroup.selectToggle(allToggleButton);

        // Add listeners to update the filter whenever a change occurs
        categoryToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> notifyFilterChange());

        PauseTransition pause = new PauseTransition(Duration.seconds(1));//adds a second before the listener catches the change

        minPriceTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                    pause.setOnFinished(event -> notifyFilterChange());
                    pause.playFromStart();
                }
        );

        maxPriceTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                    pause.setOnFinished(event -> notifyFilterChange());
                    pause.playFromStart();
                }
        );
    }

    public void setFilterChangeListener(FilterChangeListener listener) {
        this.filterChangeListener = listener;
    }

    private void notifyFilterChange() {
        if (filterChangeListener != null) {
            String selectedCategory = ((ToggleButton) categoryToggleGroup.getSelectedToggle()).getText();
            double selectedMinPrice = minPriceTextField.getText().isEmpty() ? 0 : Double.parseDouble(minPriceTextField.getText());
            double selectedMaxPrice = maxPriceTextField.getText().isEmpty() ? Double.POSITIVE_INFINITY : Double.parseDouble(maxPriceTextField.getText());
            PriceRange selectedPriceRange = new PriceRange(selectedMinPrice, selectedMaxPrice);

            Filter filter = new Filter(selectedCategory, selectedPriceRange);
            filterChangeListener.onFilterChange(filter);
        }
    }

    public interface FilterChangeListener {
        void onFilterChange(Filter filter);
    }
}
