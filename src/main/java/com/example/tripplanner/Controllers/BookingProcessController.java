package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.VacationDeal;
import com.example.tripplanner.DayTripDataBase.DayTrip;
import com.example.tripplanner.DayTripDataBase.DayTripController;
import com.example.tripplanner.HotelDataBase.Hotel;
import com.example.tripplanner.HotelDataBase.HotelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class BookingProcessController implements Initializable {

    @FXML
    private Label fxPopHotelName, fxPopHotelNights, fxPopHotelDateFrom, fxPopHotelDateTo;
    @FXML
    private Label fxPopDTAdCnt, fxPopDTChCnt, fxPopDTName;
    @FXML
    private Button fxDayTripConfirm, fxHotelConfirm, fxFlightsConfirm;
    @FXML
    private VBox fxFlightsDepCont, fxFlightsRetCont, fxHotelCont, fxDayTripsCont;
    @FXML
    private Pane fxDayTripPopup, fxHotelPopUp, fxBookedHotel;
    @FXML
    private TabPane fxTabCont;
    @FXML
    private Tab fxDayTripsTab, fxHotelsTab, fxFlightsTab;
    @FXML
    private ComboBox<String> fxSortFlights, fxSortDT, fxSortHotels;

    private VacationDeal vd;
    private DayTrip chosenDayTrip;
    private final String[] COMBOSORT = {"Price: Low to High", "Price: High to Low", "Ratings"};
    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();
    private FXMLLoader loader;
    private int totalPeople;
    private int totalAdult;
    private int totalChildren;



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

        fxDayTripPopup.setVisible(false);
        fxHotelPopUp.setVisible(false);
        fxBookedHotel.setVisible(false);

    }


    public void setVacationDeal(VacationDeal vd) {
        this.vd=vd;
        totalPeople = vd.getAdultCount() + vd.getChildCount();
        totalAdult = vd.getAdultCount();
        totalChildren = vd.getChildCount();
    }

    public void setThisLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public void setHotelPopUp(String title) {
        //Taka inn hotel hlut, upphafsstilla hann sem chosenHotel
        fxPopHotelName.setText(title);
        fxPopHotelDateFrom.setText(vd.getDateFrom().toString() + " - " + vd.getDateTo().toString());

        fxPopHotelNights.setText(totalPeople + " guests for " + vd.getVacationDuration() + " nights");
        fxHotelPopUp.setVisible(true);
    }

    public void setDTPopUp(String title) {
        //Taka inn dayTrip hlut, upphafsstilla hann sem chosenDayTrip
        //Ef ýtt er á confirm, ætti að fjarlægja þessa daytrip af listanum?
        fxPopDTName.setText(title);
        //fxPopDTAdCnt.setText(String.valueOf(totalAdult));
        //fxPopDTChCnt.setText(String.valueOf(totalChildren));
        fxDayTripPopup.setVisible(true);
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

    public void loadHotelCards() throws Exception {

        ArrayList<HotelCardController> listi = new ArrayList<HotelCardController>();

        HotelController hotelController = HotelController.getInstance();
        ArrayList<Hotel> hotelListi = hotelController.getAllAvailableHotels(2, totalPeople, vd.getDateFrom(), vd.getDateTo());


        for (Hotel h : hotelListi) {
            HotelCardController hcc = new HotelCardController(h, loader.getController());
            listi.add(hcc);
        }


        fxHotelCont.getChildren().addAll(listi);

    }

    public void loadDTCards() throws IOException {
        ArrayList<DayTripCardController> listi = new ArrayList<DayTripCardController>();
        ArrayList<DayTrip> listiafDT = new ArrayList<DayTrip>();
        Hashtable<String, Object> params = new Hashtable<>();
        LocalDate[] fylki = {vd.getDateFrom(), vd.getDateTo()};
        params.put("date", fylki);
        params.put("localCode", 2);
        listiafDT = DayTripController.getDayTrips(params);

        for (DayTrip dt : listiafDT) {
            DayTripCardController dtc = new DayTripCardController(dt, loader.getController());
            listi.add(dtc);
        }
        fxDayTripsCont.getChildren().addAll(listi);

    }

    public void flightsConfirmHandler(ActionEvent actionEvent) throws IOException {
        resetCheckBoxes();
        try {
            loadHotelCards();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fxHotelsTab.setDisable(false);
        fxTabCont.getSelectionModel().selectNext();

        System.out.println(vd.getAdultCount());
        System.out.println(vd.getDestFrom());
        System.out.println(vd.getDestTo());

    }

    public void flightsSkipHandler(ActionEvent actionEvent) {
        resetCheckBoxes();
        try {
            loadHotelCards();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fxHotelsTab.setDisable(false);
        fxTabCont.getSelectionModel().selectNext();

    }

    public void resetCheckBoxes() {
        for (CheckBox c : selectedCheckBoxes) {
            c.setSelected(false);
        }
        selectedCheckBoxes.clear();
        unselectedCheckBoxes.clear();
    }

    public void hotelConfirmHandler(ActionEvent actionEvent) throws IOException {
        resetCheckBoxes();
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
        resetCheckBoxes();
        try {
            loadDTCards();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        fxDayTripsTab.setDisable(false);
        fxTabCont.getSelectionModel().selectNext();

    }

    public void dayTripConfirmHandler(ActionEvent actionEvent) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToSceneReview(actionEvent, vd);

    }

    public void dayTripSkipHandler(ActionEvent actionEvent) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToSceneReview(actionEvent, vd);
    }

    /**
     * Handler to decrement count of adults in daytrip. Makes sure not to decrement below 1.
     * @param actionEvent Button click event
     */
    public void popDTAdMinusHandler(ActionEvent actionEvent) {
        int total = Integer.parseInt(fxPopDTAdCnt.getText());
        if (total > 1) {
            total--;
            fxPopDTAdCnt.setText(String.valueOf(total));
        }
    }

    /**
     * Handler to decrement count of children in daytrip. Makes sure not to decrement below 0.
     * @param actionEvent Button click event
     */
    public void popDTChMinusHandler(ActionEvent actionEvent) {
        int total = Integer.parseInt(fxPopDTChCnt.getText());
        if (total > 0) {
            total--;
            fxPopDTChCnt.setText(String.valueOf(total));
        }
    }

    /**
     * Handler to add count of adults to daytrip. Makes sure not to add more than
     * originally marked on front page.
     * @param actionEvent Button click event
     */
    public void popDTAdPlusHandler(ActionEvent actionEvent) {
        int total = Integer.parseInt(fxPopDTAdCnt.getText());
        if (total < totalAdult) {
            total++;
            fxPopDTAdCnt.setText(String.valueOf(total));
        }
    }

    /**
     * Handler to add count of children to daytrip. Makes sure not to add more than
     * originally marked on front page.
     * @param actionEvent Button click event
     */
    public void popDTChPlusHandler(ActionEvent actionEvent) {
        int total = Integer.parseInt(fxPopDTChCnt.getText());
        if (total < totalChildren) {
            total++;
            fxPopDTChCnt.setText(String.valueOf(total));
        }
    }

    /**
     * Method to handle Checkbox filtering. Adds checkboxes to ObservableSets depending on
     * wether they are selected or unselected.
     * @param actionEvent event of selecting or unselecting checkbox
     */
    public void filterHandler(ActionEvent actionEvent) {
        CheckBox check = (CheckBox) actionEvent.getSource();

        if (check.isSelected()) {
            selectedCheckBoxes.add(check);
            unselectedCheckBoxes.remove(check);
            //System.out.println(check.getText());
        }
        else {
            selectedCheckBoxes.remove(check);
            unselectedCheckBoxes.add(check);
            //System.out.println(check.getText());
        }
        String[] checked = new String[selectedCheckBoxes.size()];
        int i = 0;

        for (CheckBox c : selectedCheckBoxes) {
            checked[i] = c.getText();
            i++;
        }

        // Hvernig er ef allt er afcheckað? Fáum við ekki örugglega gamla listann til baka?


        // Kalla á getDayTrips með strengjafylkinu sem parameter til að filtera, undir lyklinum
        // difficulty
        // Kalla á filteringsföll hjá hinum teymunum með strengjafylkinu

    }

    public void fxBookDayTrip(ActionEvent actionEvent) {
        fxDayTripPopup.setVisible(false);
        //Setja uppl úr popupinu í bókun / vacation deal hlutinn
        //Fjarlægja valið og bókað daytrip af listanum?
    }

    public void tabChangeHandler(Event event) {
        resetCheckBoxes();
    }

    public void hotelBookHandler(ActionEvent actionEvent) {
        fxBookedHotel.setVisible(true);
        fxHotelPopUp.setVisible(false);
    }
}
