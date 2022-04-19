package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.VacationDeal;
import com.example.tripplanner.DayTripDataBase.DayTrip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

public class DayTripBookedCardController extends SplitPane {

    @FXML
    private Label fxDayTripName, fxLocation, fxTickets, fxDifficulty, fxTotalPrice;
    @FXML
    private ToggleButton fxUnBook;

    private BookingProcessController bpc;
    private VacationDeal vd;
    private DayTrip dt;
    private int tickets;
    private int price;

    public DayTripBookedCardController(DayTrip dt, BookingProcessController bookingProcessController, int tickets) {
        this.bpc = bookingProcessController;
        readCard();
        this.tickets = tickets;
        //Setja Hotel hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
        this.dt=dt;
        this.price = (int) dt.getPrice() * tickets;
        setLabels(dt);

    }


    private void setLabels(DayTrip dt) {
        // sækja titil, dags, o.s.frv frá hotel og setja í labels (setText)
        fxDayTripName.setText(dt.getName());
        fxLocation.setText("Tel. " + dt.getLocation());
        fxTickets.setText(String.valueOf(this.tickets));
        fxDifficulty.setText(dt.getDifficulty());
        fxTotalPrice.setText(this.price + " kr.");
    }

    public void unBookHandler(ActionEvent actionEvent) throws Exception {
        this.bpc.unBookDayTrip(this.dt, this);
    }

    public int getPrice() {
        return this.price;
    }

    private void readCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tripplanner/dayTripBookedCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
