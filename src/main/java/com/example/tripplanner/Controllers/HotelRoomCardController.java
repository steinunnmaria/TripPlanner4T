package com.example.tripplanner.Controllers;

import com.example.tripplanner.HotelDataBase.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

public class HotelRoomCardController extends SplitPane {

    @FXML
    private Label fxRoomNumber, fxCapacity, fxNoBeds, fxPricePerNight;
    @FXML
    private ToggleButton fxBook;


    private Room room;
    private BookingProcessController bpc;

    public HotelRoomCardController(Room rm, BookingProcessController bookingProcessController) {
        bpc = bookingProcessController;;
        readCard();
        //Setja DayTrip hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
        this.room=rm;
        setLabels(rm);
    }

    public HotelRoomCardController(Room rm) {
        readCard();
        this.room = rm;
        setLabels(rm);
    }

    public void selectHandler(ActionEvent actionEvent) throws Exception {
        if (fxBook.isSelected()) {
            bpc.bookRoom(this.room);
            fxBook.setText("Unbook room");
            //fxBook.setStyle("-fx-background-color: red");
            fxBook.setStyle("-fx-background-color: radial-gradient(radius 180%, red, derive(red, -30%), derive(red, 30%))radial-gradient(radius 180%, red, derive(red, -30%), derive(red, 30%));");
        }
        else {
            bpc.unbookRoom(this.room);
            fxBook.setText("Book room");
            fxBook.setStyle("-fx-background-color: radial-gradient(radius 180%, LIGHTGREEN, derive(LIGHTGREEN, -30%), derive(LIGHTGREEN, 30%))radial-gradient(radius 180%, LIGHTGREEN, derive(LIGHTGREEN, -30%), derive(LIGHTGREEN, 30%));");
        }
        System.out.println("Valið herbergi");
        System.out.println(room.getRoomNum());
    }

    private void setLabels(Room rm) {
        fxRoomNumber.setText(rm.getRoomNum().toString());
        fxCapacity.setText(rm.getCapacity().toString());
        fxNoBeds.setText(rm.getNumBeds().toString());
        fxPricePerNight.setText(rm.getPrice() + " kr.");

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
