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
    private ComboBox<String> fxComboboxTest;
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
    private TextField fxChildrenCount;
    @FXML
    private TextField fxAdultCount;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        fxComboboxTest.setItems(FXCollections.observableArrayList("Dog", "Cat", "Bird"));
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
        int c = Integer.parseInt(fxAdultCount.getText());
        if (c > 1) {
            c--;
            fxAdultCount.setText(String.valueOf(c));
        }
    }

    public void adultsPlusHandler(ActionEvent actionEvent) {
        int c = Integer.parseInt(fxAdultCount.getText());
        c++;
        fxAdultCount.setText(String.valueOf(c));
    }

    public void childrenMinusHandler(ActionEvent actionEvent) {
        int c = Integer.parseInt(fxChildrenCount.getText());
        if (c > 0) {
            c--;
            fxChildrenCount.setText(String.valueOf(c));
        }
    }

    public void childrenPlusHandler(ActionEvent actionEvent) {
        int c = Integer.parseInt(fxChildrenCount.getText());
        c++;
        fxChildrenCount.setText(String.valueOf(c));
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

        // RUGL fxReturnDate.setDisable(depDate.compareTo(LocalDate.now()) < 0);
    }
}
