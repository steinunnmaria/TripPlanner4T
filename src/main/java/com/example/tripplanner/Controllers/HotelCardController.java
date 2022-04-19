package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.VacationDeal;
import com.example.tripplanner.HotelDataBase.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

import java.io.IOException;

public class HotelCardController extends SplitPane {
    @FXML
    private Label fxName;
    @FXML
    private Label fxInDate;
    @FXML
    private Label fxOutDate;
    @FXML
    private Label fxLocation;
    @FXML
    private Label fxCapacity;
    @FXML
    private Label fxHotelID;


    private Hotel hotel;
    private VacationDeal vd;
    private BookingProcessController bpc;



    public HotelCardController(Hotel h, BookingProcessController bookingProcessController, VacationDeal vd) {
        this.bpc = bookingProcessController;
        this.vd = vd;
        readCard();
        //Setja Hotel hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
        this.hotel=h;
        setLabels(hotel);

    }

    public void selectHandler(ActionEvent actionEvent) throws Exception {
        bpc.loadHotelRoomCards(this.hotel);

        System.out.println("Valið hótel");
        System.out.println(fxName.getText());
    }

    private void setLabels(Hotel h) {
        // sækja titil, dags, o.s.frv frá hotel og setja í labels (setText)
        fxName.setText(h.getName());
        fxCapacity.setText("Has capacity for at least "+vd.getTotaCount());
        fxInDate.setText(this.vd.getDateFrom().toString());
        fxOutDate.setText(this.vd.getDateTo().toString());
        fxLocation.setText(h.getTown());
    }



    private void readCard() {
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
