package com.example.tripplanner.Controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FrontPageController implements Initializable {
    @FXML
    private ComboBox<String> fxFromDest;
    @FXML
    private ComboBox<String> fxToDest;
    @FXML
    private DatePicker fxReturnDate;
    @FXML
    private DatePicker fxDepartureDate;
    @FXML
    private RadioButton fxOneWay;
    @FXML
    private RadioButton fxReturn;
    //Redda að má ekki skrifa strengi
    @FXML
    private Label fxChildrenCount;
    @FXML
    private Label fxAdultCount;
    private final int MAXPEOPLE = 9;
    private int totalAdult;
    private int totalChildren;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalAdult = Integer.parseInt(fxAdultCount.getText());
        totalChildren = Integer.parseInt(fxChildrenCount.getText());

        fxFromDest.setItems(FXCollections.observableArrayList("Dog", "Cat", "Bird"));
        //fxComboboxTest.setItems();
        fxDepartureDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

    }

    public void adultsMinusHandler(ActionEvent actionEvent) {
        if (totalAdult > 1) {
            totalAdult--;
            fxAdultCount.setText(String.valueOf(totalAdult));
        }
    }

    public void adultsPlusHandler(ActionEvent actionEvent) {
        if ((totalAdult+totalChildren) < MAXPEOPLE) {
            totalAdult++;
        }
        fxAdultCount.setText(String.valueOf(totalAdult));
    }

    public void childrenMinusHandler(ActionEvent actionEvent) {
        if (totalChildren > 0) {
            totalChildren--;
        }
        fxChildrenCount.setText(String.valueOf(totalChildren));
    }

    public void childrenPlusHandler(ActionEvent actionEvent) {
        if ((totalAdult+totalChildren) < MAXPEOPLE) {
            totalChildren++;
        }
        fxChildrenCount.setText(String.valueOf(totalChildren));
    }


    public void returnHandler(ActionEvent actionEvent) {
        fxOneWay.setSelected(false);
        fxReturnDate.setDisable(false);
    }

    public void oneWayHandler(ActionEvent actionEvent) {
        fxReturn.setSelected(false);
        fxReturnDate.setDisable(true);
    }

    public void departureDateHandler(ActionEvent actionEvent) {
        LocalDate depDate = fxDepartureDate.getValue();
        fxReturnDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate depDate = fxDepartureDate.getValue();

                setDisable(empty || date.compareTo(depDate) < 0 );
            }
        });
    }

    public void returnDateHandler(ActionEvent actionEvent) {
    }

    public void searchHandler(ActionEvent actionEvent) {
    }
}
