package ui.controllers;

import core.itinerary.Itinerary;
import core.itinerary.TimeSlot;
import entities.activities.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import entities.City;
import javafx.scene.layout.VBox;
import ui.components.ItineraryDayCarousel;
import ui.components.ItineraryDayView;
import ui.components.TimeSlotCard;

import java.time.LocalDate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ItineraryController {
    @FXML
    private VBox mainBox;
    @FXML
    private ItineraryDayCarousel carousel;

    private Itinerary itinerary;
    private LocalDate currentStartDate;

    public LocalDate getCurrentStartDate() {
        return currentStartDate;
    }

    public void setCurrentStartDate(LocalDate currentStartDate) {
        this.currentStartDate = currentStartDate;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void initData(City city, LocalDate start, LocalDate end) throws IOException {
        this.itinerary = new Itinerary(city, start, end);
        this.currentStartDate = start;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/days_header.fxml"));
        GridPane header = loader.load();
        DaysHeaderController controller = loader.getController();
        controller.setItineraryController(this);
        mainBox.getChildren().add(header); // Add first card to grid
        this.start();
    }

    //TO DO - REMOVE THIS
    public void start() throws IOException {
        List<ItineraryDayView> views = new ArrayList<ItineraryDayView>();
        LocalDate startUp =this.itinerary.getStartDate();
        for(int i = 0; i < this.itinerary.getDuration(); i++){
            ItineraryDayView view = new ItineraryDayView();
            ItineraryDayViewController controller = view.getController();
            controller.initialize(startUp.plusDays(i));
            views.add(view);
        }
        carousel.setChildren(views);
        /*TimeSlotCard card1 = new TimeSlotCard();
        card1.getController().setData("Title 1", "9:00 AM - 5:00 PM", "Lorem ipsum dolor sit amet");
        TimeSlotCard card2 = new TimeSlotCard();
        card2.getController().setData("Title 1", "9:00 AM - 5:00 PM", "Lorem ipsum dolor sit amet");

        mainGrid.add(card1, 0, 3); // Add first card to grid
        mainGrid.add(card2, 0, 5); // Add second card to grid

        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/components/displacement_card.fxml"));
        HBox card3 = loader3.load();
        DisplacementCardController controller3 = loader3.getController();
        controller3.setData(new Transportation(TransportationType.BUS, Duration.ofMinutes(2), LocalTime.of(10, 0, 0)));
        mainGrid.add(card3, 0, 4);*/
    }

    /*public void addActivity(Activity result, int day) {
        // TO DO: Adicionar horário padrão - depois de todos, mil vezes mais fácil
        this.itinerary.getItineraryDayList().get(day).addActivity(new TimeSlot());
        TimeSlotCard card1 = new TimeSlotCard();
        card1.getController().setData(result.getName(), "9:00 AM - 5:00 PM", "Lorem ipsum dolor sit amet");

        mainGrid.add(card1, 0, 3);
    }*/

    public void advanceWeek(){
        this.carousel.getController().updateDays();
    }
}