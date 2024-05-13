package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DaysHeaderController {
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
            // Lookup each label by its ID
            Label labelDate = (Label) grid.lookup("#dayLabel" + (columnIndex + 1));
            Label labelIndex = (Label) grid.lookup("#indexLabel" + (columnIndex + 1));

            // Set the text to the label if found
            if (labelDate != null) {
                labelDate.setText(formatter.format(startDate.plusDays(columnIndex)));
            }
            if (labelIndex != null) {
                labelIndex.setText(iterations + "");
            }
        }
    }
}
