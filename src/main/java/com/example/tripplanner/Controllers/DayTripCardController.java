package com.example.tripplanner.Controllers;

import com.example.tripplanner.DayTripDataBase.DayTrip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

import java.io.IOException;

public class DayTripCardController extends SplitPane {
    @FXML
    private Label fxTitle;
    @FXML
    private Label fxDate;
    @FXML
    private Label fxDiff;
    @FXML
    private Label fxCapacity;
    @FXML
    private Label fxPrice;
    @FXML
    private Button fxOpenTrip;
    @FXML
    private Label fxDuration;


    private DayTrip dayTrip;

    private BookingProcessController bpc;



    public DayTripCardController(DayTrip dt, BookingProcessController bookingProcessController) {
        bpc = bookingProcessController;;
        readCard("LOL");
        //Setja DayTrip hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
        this.dayTrip=dt;
        setLabels(dayTrip);

    }

    private void setLabels(DayTrip dayTrip) {
        // sækja titil, dags, o.s.frv frá daytrip og setja í labels (setText)
        fxTitle.setText(dayTrip.getName());
        fxDate.setText(dayTrip.getDate().toString());
        fxCapacity.setText(String.valueOf(dayTrip.getCapacity()) + " spots open");
        fxDiff.setText(dayTrip.getDifficulty());
        fxPrice.setText(String.valueOf(dayTrip.getPrice())+" kr.");
        fxDuration.setText(dayTrip.getTimeStart() + " - " + dayTrip.getTimeEnd());

    }

    public DayTrip getDayTrip() {
        fxOpenTrip.setText("Close trip");
        fxOpenTrip.setStyle("Yellow");
        return(this.dayTrip);
    }

    public void selectHandler(ActionEvent actionEvent) {

        bpc.setDTPopUp(this.dayTrip);

        System.out.println("Valin ferð");
        System.out.println(fxTitle.getText());

    }

    private void readCard(String title) {
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
