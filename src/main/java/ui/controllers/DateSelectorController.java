package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class DateSelectorController {
    @FXML
    private DatePicker datePickerStart;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private void initialize() {
        datePickerStart.setValue(LocalDate.now()); // Set today's date as default
        datePickerEnd.setValue(LocalDate.now().plusDays(1)); // Set today's date as default
    }

}
