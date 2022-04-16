package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.VacationDeal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingProcessController implements Initializable {
    @FXML
    private CheckBox fxIcelandair, fxErnir, fxXX;
    @FXML
    private Button fxDayTripConfirm, fxHotelConfirm, fxFlightsConfirm;
    @FXML
    private VBox fxFlightsDepCont, fxFlightsRetCont;

    @FXML
    private VBox fxHotelCont;
    @FXML
    private TabPane fxTabCont;
    @FXML
    private Tab fxDayTripsTab, fxHotelsTab, fxFlightsTab;
    @FXML
    private ComboBox<String> fxSortFlights, fxSortDT, fxSortHotels;
    @FXML
    private VBox fxDayTripsCont;
    @FXML
    private AnchorPane fxBookRoot;

    private VacationDeal vd;

    private FrontPageController fpg = new FrontPageController();


    private final String[] COMBOSORT = {"Price: Low to High", "Price: High to Low", "Ratings"};

    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();




    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            loadFlightCards();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        fxSortFlights.getItems().add(COMBOSORT[0]);
        fxSortFlights.getItems().add(COMBOSORT[1]);
        fxSortDT.getItems().addAll(COMBOSORT);
        fxSortHotels.getItems().addAll(COMBOSORT);

        configureCheckBox(fxIcelandair);
        configureCheckBox(fxErnir);
        configureCheckBox(fxXX);

    }

    private void configureCheckBox(CheckBox checkBox) {

        if (checkBox.isSelected()) {
            selectedCheckBoxes.add(checkBox);
        } else {
            unselectedCheckBoxes.add(checkBox);
        }

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                unselectedCheckBoxes.remove(checkBox);
                selectedCheckBoxes.add(checkBox);

                System.out.println(checkBox.getText());
            } else {
                selectedCheckBoxes.remove(checkBox);
                unselectedCheckBoxes.add(checkBox);

                System.out.println(checkBox.getText());
            }

        });

    }

    public void setVacationDeal(VacationDeal vd) {
        this.vd=vd;
    }


    public void dayTripConfirmHandler(ActionEvent actionEvent) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToSceneReview(actionEvent);
    }

    public void dayTripSkipHandler(ActionEvent actionEvent) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToSceneReview(actionEvent);
    }

    public void fxFilterFlights(ActionEvent actionEvent) {

    }

    public void fxFilterHotels(ActionEvent actionEvent) {
    }

    public void hotelConfirmHandler(ActionEvent actionEvent) throws IOException {
        try {
            loadDTCards();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        fxDayTripsTab.setDisable(false);
        fxTabCont.getSelectionModel().selectNext();
    }

    public void hotelSkipHandler(ActionEvent actionEvent) throws IOException {
        try {
            loadDTCards();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        fxDayTripsTab.setDisable(false);
        fxTabCont.getSelectionModel().selectNext();

    }


    public void loadDTCards() throws IOException {

        ArrayList<DayTripCardController> listi = new ArrayList<DayTripCardController>();
        DayTripCardController dt = new DayTripCardController("Fyrsti");
        DayTripCardController dtt = new DayTripCardController("Annar");
        DayTripCardController dta = new DayTripCardController("Fyrsti");
        DayTripCardController dtta = new DayTripCardController("Annar");
        DayTripCardController dtb = new DayTripCardController("Fyrsti");
        DayTripCardController dttb = new DayTripCardController("Annar");

        listi.add(dt);
        listi.add(dtt);
        listi.add(dta);
        listi.add(dtta);
        listi.add(dtb);
        listi.add(dttb);
        fxDayTripsCont.getChildren().addAll(listi);

    }

    public void loadHotelCards() throws IOException {

        ArrayList<HotelCardController> listi = new ArrayList<HotelCardController>();
        HotelCardController h = new HotelCardController("Hótel Selfoss");
        HotelCardController h1 = new HotelCardController("Hótel RVK");
        HotelCardController h2 = new HotelCardController("Radison");
        HotelCardController h3 = new HotelCardController("Stúdó");


        listi.add(h);
        listi.add(h1);
        listi.add(h2);
        listi.add(h3);

        fxHotelCont.getChildren().addAll(listi);

    }

    public void loadFlightCards() throws IOException {
        ArrayList<FlightCardController> listi = new ArrayList<FlightCardController>();
        FlightCardController f1 = new FlightCardController("Fyrsti");
        FlightCardController f2 = new FlightCardController("Annar");
        FlightCardController f3 = new FlightCardController("Þriðji");
        FlightCardController f4 = new FlightCardController("Fjórði");

        listi.add(f1);
        listi.add(f2);
        listi.add(f3);
        listi.add(f4);
        fxFlightsDepCont.getChildren().addAll(listi);

        ArrayList<FlightCardController> listi2 = new ArrayList<FlightCardController>();
        FlightCardController f11 = new FlightCardController("10");
        FlightCardController f22 = new FlightCardController("20");
        FlightCardController f33 = new FlightCardController("Icelandair");
        FlightCardController f44 = new FlightCardController("Ernir");

        listi2.add(f11);
        listi2.add(f22);
        listi2.add(f33);
        listi2.add(f44);
        fxFlightsRetCont.getChildren().addAll(listi2);

    }

    public void flightsConfirmHandler(ActionEvent actionEvent) throws IOException {
        try {
            loadHotelCards();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        fxHotelsTab.setDisable(false);
        fxTabCont.getSelectionModel().selectNext();

        System.out.println(vd.getAdultCount());
        System.out.println(vd.getDestFrom());
        System.out.println(vd.getDestTo());

    }

    public void flightsSkipHandler(ActionEvent actionEvent) {
        try {
            loadHotelCards();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        fxHotelsTab.setDisable(false);
        fxTabCont.getSelectionModel().selectNext();
    }


}
