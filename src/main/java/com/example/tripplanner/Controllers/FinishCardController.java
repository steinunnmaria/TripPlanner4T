package com.example.tripplanner.Controllers;

import com.example.tripplanner.DayTripDataBase.DayTrip;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FinishCardController extends Pane {

    private BookingProcessController bpc;
    @FXML
    private TextField fxName;
    @FXML
    private TextField fxSsnNo;
    private String name;

    private int totalPeople;



    public FinishCardController() {


        readCard();

        //Setja DayTrip hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
        //this.dayTrip=dayTrip;
        //setLabels(dayTrip);

        fxSsnNo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    fxSsnNo.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


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
