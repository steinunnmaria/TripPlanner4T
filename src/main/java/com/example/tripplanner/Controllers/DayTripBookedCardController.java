package com.example.tripplanner.Controllers;

import com.example.tripplanner.DayTripDataBase.DayTrip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DayTripBookedCardController extends SplitPane {

    @FXML
    private Label fxDayTripName, fxDate, fxTickets, fxDifficulty, fxTotalPrice;
    @FXML
    private Label fxShowDifficulty, fxShowDifficultyReview, fxDifficultyReview;
    @FXML
    private ToggleButton fxUnBook;

    private BookingProcessController bpc;
    private DayTrip dt;
    private int tickets;
    private double price;

    public DayTripBookedCardController(DayTrip dt, BookingProcessController bookingProcessController, int tickets) {
        this.bpc = bookingProcessController;
        readCard();
        this.tickets = tickets;
        //Setja Hotel hlut sem parameter í fallið, upphafsstilla fyrir tilviksbreytu hér fyrir neðan
        this.dt=dt;
        this.price = dt.getPrice() * tickets;
        setLabels(dt);

    }


    private void setLabels(DayTrip dt) {
        // sækja titil, dags, o.s.frv frá hotel og setja í labels (setText)
        fxDayTripName.setText(dt.getName());
        fxDate.setText(dt.getDate().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)));
        fxTickets.setText(String.valueOf(this.tickets));
        fxDifficulty.setText(dt.getDifficulty());
        fxTotalPrice.setText(String.format("%,.0f", (double) this.price) + " kr.");
    }

    public void unBookHandler(ActionEvent actionEvent) throws Exception {
        this.bpc.unBookDayTrip(this.dt, this);
    }

    public void setAsReviewCard() {
        fxUnBook.setVisible(false);
        fxUnBook.setDisable(true);
        fxDifficultyReview.setText(dt.getDifficulty());
        fxDifficultyReview.setVisible(true);
        fxDifficulty.setVisible(false);
        fxShowDifficultyReview.setVisible(true);
        fxShowDifficulty.setVisible(false);
    }

    public double getPrice() {
        return this.price;
    }

    private void readCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tripplanner/dayTripBookedCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
