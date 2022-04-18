package com.example.tripplanner.Controllers;

import com.example.tripplanner.DayTripDataBase.DayTrip;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DayTripPopUpController extends Pane {
        @FXML
        private Label fxName;
        @FXML
        private Label fxDescription;
        @FXML
        private Button fxBook;

        private DayTrip dayTrip;



        public DayTripPopUpController(DayTrip dt) {
            lesaVidburd("Ný dayTrip");
            //Setja DayTrip hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
            //this.dayTrip=dayTrip;
            //setLabels(dayTrip);


            fxName.setText("Halló!");

        }

        private void setLabels(DayTrip dt) {
            // sækja titil, dags, o.s.frv frá daytrip og setja í labels (setText)
        }



        private void lesaVidburd(String title) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tripplanner/daytrip-popup.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();

            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
