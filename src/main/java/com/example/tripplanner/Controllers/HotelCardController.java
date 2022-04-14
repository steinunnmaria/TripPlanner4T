package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.Hotel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

import java.io.IOException;

public class HotelCardController extends SplitPane {
    @FXML
    private Label fxName;

    private Hotel hotel;



    public HotelCardController(String name) {
        lesaVidburd();
        //Setja Hotel hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
        //this.hotel=hotel;
        //setLabels(hotel);


        fxName.setText(name);

    }

    private void setLabels(Hotel h) {
        // sækja titil, dags, o.s.frv frá hotel og setja í labels (setText)
    }



    private void lesaVidburd() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tripplanner/hotel-card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
