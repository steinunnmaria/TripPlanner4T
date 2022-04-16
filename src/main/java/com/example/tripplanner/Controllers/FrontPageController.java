package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.VacationDeal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FrontPageController implements Initializable {
    @FXML
    private Button fxConfirm;
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
    //Redda að má ekki skrifa strengi - Steinunn Breyting
    @FXML
    private Label fxChildrenCount;
    @FXML
    private Label fxAdultCount;

    private final int MAXPEOPLE = 9;
    private int totalAdult;
    private int totalChildren;

    private ArrayList<String> location = new ArrayList<>();
    private final String[] loc = {"Reykjavík", "Stykkishólmur", "Akureyri", "Egilsstaðir"};
    private VacationDeal vd;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalAdult = Integer.parseInt(fxAdultCount.getText());
        totalChildren = Integer.parseInt(fxChildrenCount.getText());

        //fxFromDest.setItems(FXCollections.observableArrayList("Dog", "Cat", "Bird"));

        fxDepartureDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
        location.addAll(List.of(loc));
        fxFromDest.getItems().addAll(location);
        fxToDest.getItems().addAll(location);

        //fxConfirm.setDisable(true);

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
        if (isFilledOut()) {
            fxConfirm.setDisable(false);
        }
    }

    public void oneWayHandler(ActionEvent actionEvent) {
        fxReturn.setSelected(false);
        fxReturnDate.setDisable(true);
        if (isFilledOut()) {
            fxConfirm.setDisable(false);
        }
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

        if (isFilledOut()) {
            fxConfirm.setDisable(false);
        }
    }

    public boolean isFilledOut() {
        if (fxReturn.isSelected()) {
            return fxReturnDate.getValue() != null && fxDepartureDate.getValue() != null
                    && fxFromDest.getValue() != null && fxToDest.getValue() != null;
        }
        else if (fxOneWay.isSelected()) {
            return fxDepartureDate.getValue() != null
                    && fxFromDest.getValue() != null && fxToDest.getValue() != null;
        }
        return false;
    }

    public void returnDateHandler(ActionEvent actionEvent) {
        if (isFilledOut()) {
            fxConfirm.setDisable(false);
        }
    }

    public VacationDeal getDetails() {
        return vd;
    }

    public void searchHandler(ActionEvent actionEvent) throws IOException {
        String fromDest = fxFromDest.getValue();
        String toDest = fxToDest.getValue();
        LocalDate fromDate = fxDepartureDate.getValue();
        LocalDate returnDate = fxReturnDate.getValue();
        vd = new VacationDeal(fromDest, toDest, fromDate, returnDate, totalAdult, totalChildren);

        //Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        //bpc.setVacationDeal(vd);
        SceneController sc = new SceneController();
        sc.switchToSceneBooking(actionEvent, vd);

    }
}
