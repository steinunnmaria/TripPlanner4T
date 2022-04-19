package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.VacationDeal;
import com.example.tripplanner.FlightDataBase.Flight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ReviewBookingController {
    @FXML
    private VBox fxDayTripsCont, fxRoomsCont;
    @FXML
    private Label fxHotelOverview, fxHotelPrice, fxFlightsPassPrice, fxFlightsTotalPrice,
            fxDayTripsCount, fxDayTripsPrice, fxTotalPrice;
    @FXML
    private Label fxOutDate, fxBackDate, fxOutFlightNo, fxBackFlightNo, fxOutAirline, fxBackAirline,
            fxOutDepTime, fxOutArrTime, fxBackDepTime, fxBackArrTime, fxOutTickets, fxBackTickets,
            fxOutPrice, fxBackPrice, fxOutDepLoc, fxOutArrLoc, fxBackDepLoc, fxBackArrLoc;
    private VacationDeal vd;

    public void editHandler(ActionEvent actionEvent) {
    }

    public void setVacationDeal(VacationDeal vd) {
        this.vd=vd;
    }

    public void setOverview(VacationDeal vd) {
        if (vd.isFlight()) {
            fxFlightsTotalPrice.setVisible(true);
            setFlightBack(vd.getMyFlightBack());
            setFlightOut(vd.getMyFlightThere());
        }
        else {
            fxFlightsPassPrice.setText("No flights booked");
            fxFlightsTotalPrice.setVisible(false);
        }
        if (vd.isHotel()) {
            fxHotelPrice.setVisible(true);
            fxHotelOverview.setText(vd.getMyHotel().getName());
            fxHotelPrice.setText(String.format("%,.0f", (double) vd.getHotelPrice()));
        }
        else {
            fxHotelOverview.setText("No hotel booked");
            fxHotelPrice.setVisible(false);
        }
        if (vd.isDayTrip()) {
            fxDayTripsPrice.setVisible(true);
            fxDayTripsCount.setText("Halló");
        }
        else {
            fxDayTripsCount.setText("No day trips booked");
            fxDayTripsPrice.setVisible(false);
        }


    }

    public void setFlightOut(Flight f) {
        fxOutDate.setText(f.getDate().toString());
        fxOutDepLoc.setText(f.getDeparture());
        fxOutArrLoc.setText(f.getDestination());
        fxOutAirline.setText(f.getAirlineName());
        fxOutFlightNo.setText(f.getFlightNumber());
        fxOutDepTime.setText(f.getTime().toString());
        fxOutArrTime.setText(f.getTime().plusHours((long) f.getDuration()).toString());
        fxOutPrice.setText(String.format("%,.0f", (double) f.getPrice()) + " kr.");
    }
    public void setFlightBack(Flight f) {
        fxBackDate.setText(f.getDate().toString());
        fxBackDepLoc.setText(f.getDeparture());
        fxBackArrLoc.setText(f.getDestination());
        fxBackAirline.setText(f.getAirlineName());
        fxBackFlightNo.setText(f.getFlightNumber());
        fxBackDepTime.setText(f.getTime().toString());
        fxBackArrTime.setText(f.getTime().plusHours((long) f.getDuration()).toString());
        fxBackPrice.setText(String.format("%,.0f", (double) f.getPrice()) + " kr.");
    }

    public void confirmHandler(ActionEvent actionEvent) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToSceneFinish(actionEvent, vd);
    }
}
