package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.DayTrip;
import com.example.tripplanner.Classes.VacationDeal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FinishCardController extends Pane {

    private BookingProcessController bpc;
    @FXML
    private TextField fxFirstName;
    private String name;

    private int totalPeople;



    public FinishCardController(String name, VacationDeal vd) {
        totalPeople = vd.getAdultCount() + vd.getAdultCount();

        readCard();
        this.name = name;
        fxFirstName.setText(name);

        //Setja DayTrip hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
        //this.dayTrip=dayTrip;
        //setLabels(dayTrip);

    }

    private void setLabels(DayTrip dt) {
        // sækja titil, dags, o.s.frv frá daytrip og setja í labels (setText)
    }


    private void readCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tripplanner/finish-card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
