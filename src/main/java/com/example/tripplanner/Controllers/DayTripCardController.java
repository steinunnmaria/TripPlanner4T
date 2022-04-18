package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.DayTrip;
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
    private Button fxOpenTrip;

    private DayTrip dayTrip;
    private String title;

    private BookingProcessController bpc;



    public DayTripCardController(String title, BookingProcessController bookingProcessController) {
        bpc = bookingProcessController;
        this.title = title;
        readCard(title);
        //Setja DayTrip hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
        //this.dayTrip=dayTrip;
        //setLabels(dayTrip);


        fxTitle.setText(title);

    }

    private void setLabels(DayTrip dt) {
        // sækja titil, dags, o.s.frv frá daytrip og setja í labels (setText)
    }

    public DayTrip getDayTrip() {
        fxOpenTrip.setText("Close trip");
        fxOpenTrip.setStyle("Yellow");
        return(this.dayTrip);
    }

    public void selectHandler(ActionEvent actionEvent) {

        bpc.setDTPopUp(fxTitle.getText());

        System.out.println("Valin ferð");
        System.out.println(fxTitle.getText());

    }

    public String getTitle() {
        return title;
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
