package com.example.tripplanner.Controllers;

import com.example.tripplanner.FlightDataBase.Flight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class FlightCardController extends SplitPane {

    @FXML
    private Label fxFlightPrice;
    @FXML
    private Label fxFlightDate;
    @FXML
    private Label fxFlightNo;
    @FXML
    private Label fxFlightDuration;
    @FXML
    private Label fxFromDest;
    @FXML
    private Label fxToDest;

    private Flight flight;
    private boolean outTrip;
    private BookingProcessController bpc;




    public FlightCardController(Flight f, BookingProcessController bookingProcessController, boolean outTrip) {
        lesaVidburd();
        //Setja Flight hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
        this.flight=f;
        this.bpc = bookingProcessController;
        setLabels(flight);
        this.outTrip = outTrip;

    }

    public void selectHandler(ActionEvent actionEvent) {
        if(outTrip) {
            this.bpc.setOutFlightPopUp(this.flight);
        } else {
            this.bpc.setBackFlightPopUp(this.flight);
        }
    }

    private void setLabels(Flight fl) {
        // sækja titil, dags, o.s.frv frá flight og setja í labels (setText)
        fxFlightNo.setText(fl.getAirlineName() + " " + fl.getFlightNumber());
        fxFlightPrice.setText(String.format("%,.0f", (double) fl.getPrice()) + " kr. / person");
        fxFlightDate.setText(fl.getDate().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)));
        fxFlightDuration.setText(String.valueOf(fl.getDuration()) + " klst.");
        fxFromDest.setText(fl.getDeparture());
        fxToDest.setText(fl.getDestination());
    }



    private void lesaVidburd() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tripplanner/flight-card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
