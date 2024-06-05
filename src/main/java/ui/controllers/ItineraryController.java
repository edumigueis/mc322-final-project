package ui.controllers;

import core.itinerary.Itinerary;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import entities.City;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.components.ItineraryDayCarousel;
import ui.components.ItineraryDayView;
import viewmodels.CityViewModel;

import java.time.LocalDate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ItineraryController {
    @FXML
    private VBox mainBox;
    private final ItineraryDayCarousel carousel = new ItineraryDayCarousel();
    private Itinerary itinerary;

    public void initData(City city, LocalDate start, LocalDate end) throws IOException {
        this.itinerary = new Itinerary(city, start, end);
        this.loadHeader();
        this.startCards();
        mainBox.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Once the scene is available, bind the maxHeight of the VBox to the height of the scene
                mainBox.maxHeightProperty().bind(newValue.heightProperty());
            }
        });
    }

    private void loadHeader() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/days_header.fxml"));
        HBox header = loader.load();
        DaysHeaderController controller = loader.getController();
        controller.setItineraryController(this);
        controller.setCityViewModel(new CityViewModel(this.itinerary.getCity()));
        mainBox.getChildren().add(header); // Add first card to grid
    }

    //TO DO - REMOVE THIS
    private void startCards() throws IOException {
        List<ItineraryDayView> views = new ArrayList<ItineraryDayView>();
        LocalDate startUp =this.itinerary.getStartDate();
        for(int i = 0; i < this.itinerary.getDuration() + 1; i++){
            ItineraryDayView view = new ItineraryDayView();
            ItineraryDayViewController controller = view.getController();
            controller.initialize(startUp.plusDays(i));
            controller.setCityViewModel(new CityViewModel(this.itinerary.getCity()));
            views.add(view);
        }
        carousel.setChildren(views);

        mainBox.getChildren().add(carousel);
        /*
        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/components/displacement_card.fxml"));
        HBox card3 = loader3.load();
        DisplacementCardController controller3 = loader3.getController();
        controller3.setData(new Transportation(TransportationType.BUS, Duration.ofMinutes(2), LocalTime.of(10, 0, 0)));
        mainGrid.add(card3, 0, 4);*/
    }

    public void advanceWeek(){
        this.carousel.getController().next();
    }

    public void prevWeek(){
        this.carousel.getController().previous();
    }
}