package ui.controllers;

import core.itinerary.Itinerary;
import core.itinerary.ItineraryDay;
import entities.activities.Activity;
import entities.activities.CulturalEvent;
import entities.activities.Museum;
import entities.activities.Sight;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import entities.City;
import entities.Transportation;
import entities.TransportationType;
import ui.components.TimeSlotCard;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ItineraryController {
    @FXML
    private GridPane mainGrid;
    private Itinerary itinerary;

    public void initData(City city, LocalDate start, LocalDate end) throws IOException {
        this.itinerary = new Itinerary(city, start, end, new ArrayList<ItineraryDay>());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/days_header.fxml"));
        GridPane header = loader.load();
        DaysHeaderController controller = loader.getController();
        controller.initData(this.itinerary.getCity(), this.itinerary.getStartDate());
        mainGrid.add(header, 0, 0, 3, 1); // Add first card to grid
        this.start();
    }

    //TO DO - REMOVE THIS
    public void start() throws IOException {
        /*TimeSlotCard card1 = new TimeSlotCard();
        card1.getController().setData("Title 1", "9:00 AM - 5:00 PM", "Lorem ipsum dolor sit amet", "#FF5722");
        TimeSlotCard card2 = new TimeSlotCard();
        card2.getController().setData("Title 1", "9:00 AM - 5:00 PM", "Lorem ipsum dolor sit amet", "#FF5722");

        mainGrid.add(card1, 0, 3); // Add first card to grid
        mainGrid.add(card2, 0, 5); // Add second card to grid

        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/components/displacement_card.fxml"));
        HBox card3 = loader3.load();
        DisplacementCardController controller3 = loader3.getController();
        controller3.setData(new Transportation(TransportationType.BUS, Duration.ofMinutes(2), LocalTime.of(10, 0, 0)));
        mainGrid.add(card3, 0, 4);*/
    }
}