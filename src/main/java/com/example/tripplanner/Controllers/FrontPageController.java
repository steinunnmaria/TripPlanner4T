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
    private ComboBox<String> fxFromDest, fxToDest;
    @FXML
    private DatePicker fxReturnDate, fxDepartureDate;
    @FXML
    private RadioButton fxOneWay, fxReturn;
    //Redda að má ekki skrifa strengi - Steinunn Breyting
    @FXML
    private Label fxChildrenCount, fxAdultCount;


    private final int MAXPEOPLE = 9;
    private int totalAdult;
    private int totalChildren;

    private ArrayList<String> location = new ArrayList<>();

    private VacationDeal vd;

    private final String[] LOCATIONS1 = {"Ísafjörður", "Bolungarvík", "Reykjarfjörður"};
    private final String[] LOCATIONS2 = {"Stykkishólmur", "Ólafsvík", "Arnarstapi"};
    private final String[] LOCATIONS3 = {"Reykjavík", "Keflavík", "Selfoss"};
    private final String[] LOCATIONS4 = {"Vestmannaeyjar"};
    private final String[] LOCATIONS5 = {"Egilsstaðir", "Seyðisfjörður", "Neskaupsstaður", "Fjarðabyggð"};
    private final String[] LOCATIONS6 = {"Akureyri", "Húsavík", "Reykjahlíð"};
    private final String[] LOC = {"Ísafjörður", "Stykkishólmur", "Reykjavík", "Vestmannaeyjar"
            , "Egilsstaðir", "Akureyri"};


    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalAdult = Integer.parseInt(fxAdultCount.getText());
        totalChildren = Integer.parseInt(fxChildrenCount.getText());

        fxDepartureDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
        location.addAll(List.of(LOC));
        fxFromDest.getItems().addAll(location);
        fxToDest.getItems().addAll(location);
        fxToDest.setDisable(true);
        fxReturnDate.setDisable(true);

        fxConfirm.setDisable(true);

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
        //LocalDate depDate = fxDepartureDate.getValue();
        fxReturnDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate depDate = fxDepartureDate.getValue();

                setDisable(empty || date.compareTo(depDate) < 0 );
            }
        });
        fxReturnDate.setDisable(false);

        if (isFilledOut()) {
            fxConfirm.setDisable(false);
        }
    }

    public boolean isFilledOut() {
        if (fxReturn.isSelected()) {
            return fxReturnDate.getValue() != null && fxDepartureDate.getValue() != null
                    && fxFromDest.getValue() != null && fxToDest.getValue() != null;
        }
        else {
            return fxDepartureDate.getValue() != null
                    && fxFromDest.getValue() != null && fxToDest.getValue() != null;
        }
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

        SceneController sc = new SceneController();
        sc.switchToSceneBooking(actionEvent, vd);

    }

    public void fromDestHandler(ActionEvent actionEvent) {
        if (isFilledOut()) {
            fxConfirm.setDisable(false);
        }
        fxToDest.setDisable(false);
        String from = fxFromDest.getValue();
        fxToDest.getItems().remove(from);
    }

    public void toDestHandler(ActionEvent actionEvent) {
        if (isFilledOut()) {
            fxConfirm.setDisable(false);
        }
    }
}
