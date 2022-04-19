package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.VacationDeal;
import com.example.tripplanner.FlightDataBase.Flight;
import com.example.tripplanner.HotelDataBase.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

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

    public void setOverview(VacationDeal vd) throws Exception {
        this.vd = vd;
        setFlights();
        setHotel();
        setDayTrips();
        fxTotalPrice.setText("Total price for your trip: " + String.format("%,.0f", (double) vd.getTotalPrice()) + " kr.");
    }

    public void setFlights() {
        if (vd.isFlight()) {
            fxFlightsTotalPrice.setVisible(true);
            fxFlightsPassPrice.setText("Price per passanger: " +
                    String.format("%,.0f", (double) vd.getTotalPassFlightPrice()) + " kr.");
            fxFlightsTotalPrice.setText("Total price for " + vd.getTotalCount() + " passangers: " +
                    String.format("%,.0f", (double) vd.getTotalFlightPrice()) + " kr.");
            setFlightBack(vd.getMyFlightBack());
            setFlightOut(vd.getMyFlightThere());
        }
        else {
            fxFlightsPassPrice.setText("No flight booking");
            fxFlightsTotalPrice.setVisible(false);
        }

    }

    public void setHotel() throws Exception {
        if (vd.isHotel()) {
            ArrayList<Room> rooms = vd.getMyRooms();
            fxHotelPrice.setVisible(true);
            fxHotelOverview.setText(rooms.size() + " rooms booked at "
                    + vd.getMyHotel().getName());
            fxHotelPrice.setText("Total price for " + vd.getVacationDuration() + " nights: " +
                    String.format("%,.0f", (double) vd.getHotelPrice()) + " kr.");
            loadHotelRoomCards();
        }
        else {
            fxHotelOverview.setText("No hotel booking");
            fxHotelPrice.setVisible(false);
        }
    }

    public void setDayTrips() throws IOException {
        if (vd.isDayTrip()) {
            fxDayTripsPrice.setVisible(true);
            fxDayTripsCount.setText(vd.getMyDayTrips().size() + " day trips booked");
            fxDayTripsPrice.setText("Total price of day trips: " +
                    String.format("%,.0f", (double) vd.getDayTripsPrice()) + " kr.");
            loadDTCards();
        }
        else {
            fxDayTripsCount.setText("No day trip booking");
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

    public void loadHotelRoomCards() throws Exception {

        ArrayList<HotelRoomCardController> listi = new ArrayList<HotelRoomCardController>();

        ArrayList<Room> roomList = vd.getMyRooms();

        for (Room room : roomList) {
            HotelRoomCardController hrcc = new HotelRoomCardController(room);
            listi.add(hrcc);
        }
        fxRoomsCont.getChildren().clear();
        fxRoomsCont.getChildren().addAll(listi);

    }

    public void loadDTCards() throws IOException {
        ArrayList<DayTripBookedCardController> booked = vd.getBookedDTList();

        fxDayTripsCont.getChildren().clear();
        fxDayTripsCont.getChildren().addAll(booked);

    }
}
