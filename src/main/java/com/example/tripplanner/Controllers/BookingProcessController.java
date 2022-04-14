package com.example.tripplanner.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class BookingProcessController {
    @FXML
    private TabPane fxTabCont;
    @FXML
    private Tab fxDayTripsTab, fxHotelsTab, fxFlightsTab;
    @FXML
    private ComboBox<String> fxSortPriceFlights;
    @FXML
    private VBox fxDayTripsCont;
    @FXML
    private AnchorPane fxBookRoot;
    @FXML
    private ScrollPane fxHotelList;



    public void dayTripConfirmHandler(ActionEvent actionEvent) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToSceneReview(actionEvent);
    }

    public void dayTripSkipHandler(ActionEvent actionEvent) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToSceneReview(actionEvent);
    }

    public void fxFilterFlights(ActionEvent actionEvent) {
    }

    public void fxFilterHotels(ActionEvent actionEvent) {
    }

    public void hotelConfirmHandler(ActionEvent actionEvent) throws IOException {
        try {
            loadCards();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        fxDayTripsTab.setDisable(false);
        fxTabCont.getSelectionModel().selectNext();
    }

    public void hotelSkipHandler(ActionEvent actionEvent) throws IOException {
        try {
            loadCards();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        fxDayTripsTab.setDisable(false);
        fxTabCont.getSelectionModel().selectNext();

    }

    public void loadCards() throws IOException {

        ArrayList<DayTripCardController> listi = new ArrayList<DayTripCardController>();
        DayTripCardController dt = new DayTripCardController("Fyrsti");
        DayTripCardController dtt = new DayTripCardController("Annar");
        DayTripCardController dta = new DayTripCardController("Fyrsti");
        DayTripCardController dtta = new DayTripCardController("Annar");
        DayTripCardController dtb = new DayTripCardController("Fyrsti");
        DayTripCardController dttb = new DayTripCardController("Annar");

        listi.add(dt);
        listi.add(dtt);
        listi.add(dta);
        listi.add(dtta);
        listi.add(dtb);
        listi.add(dttb);
        fxDayTripsCont.getChildren().addAll(listi);

    }

    public void flightsConfirmHandler(ActionEvent actionEvent) {
        fxHotelsTab.setDisable(false);
        fxTabCont.getSelectionModel().selectNext();
    }

    public void flightsSkipHandler(ActionEvent actionEvent) {
        fxHotelsTab.setDisable(false);
        fxTabCont.getSelectionModel().selectNext();
    }
}
