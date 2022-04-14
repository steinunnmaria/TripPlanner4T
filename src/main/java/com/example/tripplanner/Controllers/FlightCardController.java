package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.Flight;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

import java.io.IOException;

public class FlightCardController extends SplitPane {
    @FXML
    private Label fxAirline;

    private Flight flight;



    public FlightCardController(String airline) {
        lesaVidburd();
        //Setja Flight hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
        //this.flight=flight;
        //setLabels(flight);


        fxAirline.setText(airline);

    }

    private void setLabels(Flight f) {
        // sækja titil, dags, o.s.frv frá flight og setja í labels (setText)
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
