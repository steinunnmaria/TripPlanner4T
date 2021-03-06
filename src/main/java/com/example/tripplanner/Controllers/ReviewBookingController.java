package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.VacationDeal;
import com.example.tripplanner.FlightDataBase.Flight;
import com.example.tripplanner.HotelDataBase.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class ReviewBookingController {
    @FXML
    private Tab fxHotelTab, fxDtTab;
    @FXML
    private SplitPane fxMyFlight1, fxMyFlight2;
    @FXML
    private VBox fxVBox1, fxVBox2;
    @FXML
    private VBox fxDayTripsCont, fxRoomsCont;
    @FXML
    private Label fxHotelOverview, fxHotelPrice, fxFlightsPassPrice, fxFlightsTotalPrice,
            fxDayTripsCount, fxDayTripsPrice, fxTotalPrice, fxNoFlights;
    @FXML
    private Label fxOutDate, fxBackDate, fxOutFlightNo, fxBackFlightNo, fxOutAirline, fxBackAirline,
            fxOutDepTime, fxOutArrTime, fxBackDepTime, fxBackArrTime, fxOutTickets, fxBackTickets,
            fxOutPrice, fxBackPrice, fxOutDepLoc, fxOutArrLoc, fxBackDepLoc, fxBackArrLoc;
    private VacationDeal vd;

    public void startOverHandler(ActionEvent actionEvent) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToSceneFront(actionEvent);
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
            fxNoFlights.setVisible(false);
            fxOutTickets.setText(vd.getTotalCount() + " tickets");
            fxBackTickets.setText(vd.getTotalCount() + " tickets");
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
            fxVBox1.setVisible(false);
            fxVBox2.setVisible(false);
            fxMyFlight1.setVisible(false);
            fxMyFlight2.setVisible(false);
            fxNoFlights.setVisible(true);
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
            fxHotelTab.setDisable(true);
            fxHotelOverview.setText("No hotel booking");
            fxHotelPrice.setVisible(false);
        }
    }

    public void setDayTrips() throws IOException {
        if (vd.isDayTrip()) {

            fxDayTripsPrice.setVisible(true);
            fxDayTripsCount.setText(vd.getBookedDTList().size() + " day trips booked");
            fxDayTripsPrice.setText("Total price of day trips: " +
                    String.format("%,.0f", (double) vd.getDayTripsPrice()) + " kr.");
            loadDTCards();
        }
        else {
            fxDtTab.setDisable(true);
            fxDayTripsCount.setText("No day trip booking");
            fxDayTripsPrice.setVisible(false);
        }
    }

    public void setFlightOut(Flight f) {
        fxOutDate.setText(f.getDate().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)));
        fxOutDepLoc.setText(f.getDeparture());
        fxOutArrLoc.setText(f.getDestination());
        fxOutAirline.setText(f.getAirlineName());
        fxOutFlightNo.setText(f.getFlightNumber());
        fxOutDepTime.setText(f.getTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        fxOutArrTime.setText(f.getTime().plusHours((long) f.getDuration()).format(DateTimeFormatter.ofPattern("HH:mm")));
        fxOutPrice.setText(String.format("%,.0f", (double) f.getPrice()*vd.getTotalCount()) + " kr.");
    }
    public void setFlightBack(Flight f) {
        fxBackDate.setText(f.getDate().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)));
        fxBackDepLoc.setText(f.getDeparture());
        fxBackArrLoc.setText(f.getDestination());
        fxBackAirline.setText(f.getAirlineName());
        fxBackFlightNo.setText(f.getFlightNumber());
        fxBackDepTime.setText(f.getTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        fxBackArrTime.setText(f.getTime().plusHours((long) f.getDuration()).format(DateTimeFormatter.ofPattern("HH:mm")));
        fxBackPrice.setText(String.format("%,.0f", (double) f.getPrice()*vd.getTotalCount()) + " kr.");
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
            hrcc.setToReviewCard(vd.getDateFrom(), vd.getDateTo());
            listi.add(hrcc);
        }
        fxRoomsCont.getChildren().clear();
        fxRoomsCont.getChildren().addAll(listi);

    }

    public void loadDTCards() throws IOException {
        ArrayList<DayTripBookedCardController> booked = vd.getBookedDTList();

        for(DayTripBookedCardController dt : booked) {
            dt.setAsReviewCard();
        }
        fxDayTripsCont.getChildren().clear();
        fxDayTripsCont.getChildren().addAll(booked);

    }
}
