package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.DayTrip;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

import java.io.IOException;

public class DayTripCardController extends SplitPane {
    @FXML
    private Label fxTitle;

    private DayTrip dayTrip;



    public DayTripCardController(String title) {
        lesaVidburd(title);
        //Setja DayTrip hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
        //this.dayTrip=dayTrip;
        //setLabels(dayTrip);


        fxTitle.setText(title);

    }

    private void setLabels(DayTrip dt) {
        // sækja titil, dags, o.s.frv frá daytrip og setja í labels (setText)
    }



    private void lesaVidburd(String title) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tripplanner/day-trip-card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
