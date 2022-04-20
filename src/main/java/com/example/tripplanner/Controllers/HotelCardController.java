package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.VacationDeal;
import com.example.tripplanner.HotelDataBase.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
    private Label fxHotelID;
    @FXML
    private ImageView fx1Star, fx2Star, fx3Star, fx4Star, fx5Star;



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

        fxInDate.setText("Check-in: " + this.vd.getDateFrom().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)));
        fxOutDate.setText("Check-out: " + this.vd.getDateTo().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)));
        fxLocation.setText(h.getTown());

        int stars = h.getHotelInfo().getStarRating();
        showStars(stars);

    }

    private void showStars(int stars) {
        ImageView[] imgs = {fx1Star, fx2Star, fx3Star, fx4Star, fx5Star};
        for (int i = 0;i < imgs.length ;i++ ) {
            imgs[i].setVisible(false);
        }
        for (int i = 0; i < stars; i++) {
            imgs[i].setVisible(true);
        }

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
