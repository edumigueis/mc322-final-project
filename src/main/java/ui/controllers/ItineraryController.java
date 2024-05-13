package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class ItineraryController {

    @FXML
    private Label dayLabel1;

    @FXML
    private Label dayLabel2;

    @FXML
    private Label dayLabel3;

    @FXML
    private Label dayLabel4;

    @FXML
    private Label dayLabel5;

    @FXML
    private Label dayLabel6;

    @FXML
    private Label dayLabel7;

    private LocalDate startDate = LocalDate.of(1999, 6, 2); // Start date: June 2, 1999

    @FXML
    private void nextWeek() {
        startDate = startDate.plusWeeks(1); // Advance the week by one week
        updateLabels();
    }

    public void updateLabels() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE");

        // Update labels with dates for each day of the week
        dayLabel1.setText(formatter.format(startDate));
        dayLabel2.setText(formatter.format(startDate.plusDays(1)));
        dayLabel3.setText(formatter.format(startDate.plusDays(2)));
        dayLabel4.setText(formatter.format(startDate.plusDays(3)));
        dayLabel5.setText(formatter.format(startDate.plusDays(4)));
        dayLabel6.setText(formatter.format(startDate.plusDays(5)));
        dayLabel7.setText(formatter.format(startDate.plusDays(6)));
    }
}