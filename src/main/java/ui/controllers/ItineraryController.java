package ui.controllers;

import core.itinerary.Itinerary;
import core.itinerary.ItineraryDay;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import objects.City;
import objects.Transportation;
import objects.TransportationType;

import java.time.Duration;
import java.time.LocalTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class ItineraryController {
    @FXML
    private GridPane mainGrid;
    private Itinerary itinerary;

    public void initData(City city, Date start, Date end) throws IOException {
        this.itinerary = new Itinerary(city, start, end, new ArrayList<ItineraryDay>());
        this.start();
    }

    public void start() throws IOException {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/components/attraction_card.fxml"));
        GridPane card1 = loader1.load();
        AttractionCardComponentController controller1 = loader1.getController();
        controller1.setData("Title 1", "9:00 AM - 5:00 PM", "Lorem ipsum dolor sit amet", "#FF5722");

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/components/attraction_card.fxml"));
        GridPane card2 = loader2.load();
        AttractionCardComponentController controller2 = loader2.getController();
        controller2.setData("Title 2", "10:00 AM - 6:00 PM", "Consectetur adipiscing elit", "#00BCD4");

        mainGrid.add(card1, 0, 3); // Add first card to grid
        mainGrid.add(card2, 0, 5); // Add second card to grid

        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/components/displacement_card.fxml"));
        HBox card3 = loader3.load();
        DisplacementCardComponentController controller3 = loader3.getController();
        controller3.setData(new Transportation(TransportationType.BUS, Duration.ofMinutes(2), LocalTime.of(10, 0, 0)));
        mainGrid.add(card3, 0, 4);
    }
}