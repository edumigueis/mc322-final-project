package ui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;

public class DateSelectorController {
    @FXML
    private DatePicker datePickerStart;

    @FXML
    private DatePicker datePickerEnd;

    public LocalDate getStartDate() {
        return datePickerStart.getValue();
    }

    public LocalDate getEndDate() {
        return datePickerEnd.getValue();
    }

    @FXML
    private void initialize() {
        datePickerStart.setValue(LocalDate.now()); // Set today's date as default
        datePickerEnd.setValue(LocalDate.now().plusDays(1)); // Set tomorrow's date as default

        datePickerEnd.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (datePickerStart.getValue() != null && item != null) {
                            boolean isBeforeStart = item.isBefore(datePickerStart.getValue());
                            setDisable(isBeforeStart);
                        }
                    }
                };
            }
        });

        datePickerStart.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (datePickerEnd.getValue() != null && newValue != null && datePickerEnd.getValue().isBefore(newValue))
                datePickerEnd.setValue(newValue.plusDays(1));
        });
    }
}
