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
        //Hotel h = vd.getMyHotel();
        fxHotelOverview.setText(vd.getDestFrom());
    }

    public void setFlightOut(Flight f) {
        fxOutDate.setText(f.getDate().toString());
        fxOutDepLoc.setText(f.getDeparture());
        fxOutArrLoc.setText(f.getDestination());
        fxOutAirline.setText(f.getAirlineName());
        fxOutFlightNo.setText(f.getFlightNumber());
        fxOutDepTime.setText(f.getTime().toString());
        fxOutArrTime.setText(f.getTime().plusHours((long) f.getDuration()).toString());
    }
    public void setFlightBack(Flight f) {
        fxBackDate.setText(f.getDate().toString());
        fxBackDepLoc.setText(f.getDeparture());
        fxBackArrLoc.setText(f.getDestination());
        fxBackAirline.setText(f.getAirlineName());
        fxBackFlightNo.setText(f.getFlightNumber());
        fxBackDepTime.setText(f.getTime().toString());
        fxBackArrTime.setText(f.getTime().plusHours((long) f.getDuration()).toString());
    }

    public void confirmHandler(ActionEvent actionEvent) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToSceneFinish(actionEvent, vd);
    }
}
