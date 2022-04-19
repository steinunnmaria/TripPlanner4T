package com.example.tripplanner.Controllers;

import com.example.tripplanner.HotelDataBase.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

import java.io.IOException;

public class HotelRoomCardController extends SplitPane {

    @FXML
    private Label fxRoomNumber;
    private Room room;
    private BookingProcessController bpc;

    public HotelRoomCardController(Room rm, BookingProcessController bookingProcessController) {
        bpc = bookingProcessController;;
        readCard();
        //Setja DayTrip hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
        this.room=rm;
        setLabels(rm);

    }

    private void setLabels(Room rm) {
        fxRoomNumber.setText(rm.getRoomNum().toString());
        // sækja titil, dags, o.s.frv frá daytrip og setja í labels (setText)
        //fxTitle.setText(dayTrip.getName());
        //fxDate.setText(dayTrip.getDate().toString());
        //fxCapacity.setText(String.valueOf(dayTrip.getCapacity()) + " spots open");
        //fxDiff.setText(dayTrip.getDifficulty());
        //fxPrice.setText(String.valueOf(dayTrip.getPrice())+" kr.");
        //fxDuration.setText(dayTrip.getTimeStart() + " - " + dayTrip.getTimeEnd());

    }

    public void selectHandler(ActionEvent actionEvent) {

        //bpc.setDTPopUp(this.dayTrip);

        System.out.println("Valin ferð");
        //System.out.println(fxTitle.getText());

    }

    private void readCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tripplanner/hotel-room-card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
