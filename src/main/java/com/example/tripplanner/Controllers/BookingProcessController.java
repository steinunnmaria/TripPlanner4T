package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.RoomBooking;
import com.example.tripplanner.Classes.VacationDeal;
import com.example.tripplanner.DayTripDataBase.DayTrip;
import com.example.tripplanner.DayTripDataBase.DayTripController;
import com.example.tripplanner.FlightDataBase.Customer;
import com.example.tripplanner.FlightDataBase.CustomerController;
import com.example.tripplanner.FlightDataBase.Flight;
import com.example.tripplanner.HotelDataBase.Hotel;
import com.example.tripplanner.HotelDataBase.HotelController;
import com.example.tripplanner.HotelDataBase.Room;
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
import java.util.List;
import java.util.ResourceBundle;

public class BookingProcessController implements Initializable {

    @FXML
    private Label fxPopHotelName, fxPopHotelNights, fxPopHotelDateFrom, fxPopHotelDateTo;
    @FXML
    private Label fxPopDTAdCnt, fxPopDTChCnt, fxPopDTName, fxPopDTAgeLimit, fxPopDTDescr,
            fxPopDTDate, fxPopDTTime, fxPopDTPrice;
    @FXML
    private Label fxMyFlightNo1, fxMyFlightDate1, fxMyAirline1, fxMyFlightDept1, fxMyFlightArr1,
            fxMyFlightPass1, fxMyFlightFrom1, fxMyFlightTo1, fxMyFlightPrice1;
    @FXML
    private Label fxMyFlightNo2, fxMyFlightDate2, fxMyAirline2, fxMyFlightDept2, fxMyFlightArr2,
            fxMyFlightPass2, fxMyFlightFrom2, fxMyFlightTo2, fxMyFlightPrice2;
    @FXML
    private Label fxHotelBookedName, fxHotelBookedDates, fxHotelBookedRooms, fxHotelBookedGuests, fxHotelBookedPriceTotal;
    @FXML
    private SplitPane fxMyFlight1, fxMyFlight2;
    @FXML
    private Label fxFlightTotalPrice, fxNoDayTrips;
    @FXML
    private Button fxDayTripConfirm, fxHotelConfirm, fxFlightsConfirm, fxNextDay, fxPrevDay, fxJumpButton;
    @FXML
    private DatePicker fxPickADay;
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
    private Hotel chosenHotel;
    private final String[] COMBOSORT = {"Price: Low to High", "Price: High to Low", "Ratings"};
    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();
    private FXMLLoader loader;
    private int totalPeople;
    private int totalAdult;
    private int totalChildren;
    private double flightOutCost;
    private double flightBackCost = 0;
    private LocalDate chosenDayForDayTrip;
    private ArrayList<Hotel> hotelListi;
    private HotelController hotelController;
    private Flight myFlightOut;
    private Flight myFlightBack;
    private RoomBooking bookedRooms;
    private boolean datePickerOpened;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*try {
            loadFlightCards();
        }
        catch (IOException e) {
            e.printStackTrace();
        }*/
        fxSortFlights.getItems().add(COMBOSORT[0]);
        fxSortFlights.getItems().add(COMBOSORT[1]);
        fxSortDT.getItems().addAll(COMBOSORT);
        fxSortHotels.getItems().addAll(COMBOSORT);

        fxDayTripPopup.setVisible(false);
        fxHotelPopUp.setVisible(false);
        fxBookedHotel.setVisible(false);
        fxMyFlight1.setVisible(false);
        fxMyFlight2.setVisible(false);

    }


    public void setVacationDeal(VacationDeal vd) {
        this.vd=vd;
        totalPeople = vd.getTotaCount();
        totalAdult = vd.getAdultCount();
        totalChildren = vd.getChildCount();
        this.chosenDayForDayTrip = vd.getDateFrom();

        fxPickADay.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate depDate = vd.getDateFrom();
                LocalDate retDate = vd.getDateTo();

                setDisable(empty || date.compareTo(depDate) < 0 || date.compareTo(retDate) > 0 );
            }
        });
        fxPrevDay.setDisable(true);
    }

    public void setThisLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public void setHotelPopUp(Hotel h) throws Exception {
        //Taka inn hotel hlut, upphafsstilla hann sem chosenHotel
        fxPopHotelName.setText(h.getName());
        fxPopHotelDateFrom.setText(vd.getDateFrom().toString() + " - " + vd.getDateTo().toString());

        fxPopHotelNights.setText(totalPeople + " guests for " + vd.getVacationDuration() + " nights");
        ArrayList<Room> rooms = hotelController.getAvailableRoomsByHotelId(h.getId(), vd.getDateFrom(), vd.getDateTo());
        System.out.println(rooms.size());
        fxHotelPopUp.setVisible(true);
        this.chosenHotel = h;
    }

    public void setDTPopUp(DayTrip dt) {
        //Taka inn dayTrip hlut, upphafsstilla hann sem chosenDayTrip
        //Ef ýtt er á confirm, ætti að fjarlægja þessa daytrip af listanum?
        fxPopDTName.setText(dt.getName());
        fxPopDTAgeLimit.setText("Age limit: "+dt.getAgeLimit()+" years");
        fxPopDTDate.setText(dt.getDate().toString());
        fxPopDTTime.setText(dt.getTimeStart().toString()+" - "+dt.getTimeEnd().toString());
        fxPopDTDescr.setText(dt.getDescription());
        fxPopDTPrice.setText(String.valueOf(dt.getPrice()));
        fxDayTripPopup.setVisible(true);
    }

    public void setOutFlightPopUp(Flight fl) {
        //Taka inn Flight hlut, upphafsstilla hann sem chosenFlightOut
        fxMyFlightNo1.setText(fl.getFlightNumber());
        fxMyFlightDate1.setText(fl.getDate().toString());
        fxMyAirline1.setText(fl.getAirlineName());
        fxMyFlightDept1.setText(fl.getDeparture());
        fxMyFlightArr1.setText(fl.getDestination());
        fxMyFlightPass1.setText(vd.getTotaCount() + " tickets");
        fxMyFlightFrom1.setText(fl.getTime().toString());
        fxMyFlightTo1.setText(String.valueOf(fl.getDuration()));
        fxMyFlightPrice1.setText(fl.getPrice()+" kr.");
        this.flightOutCost = fl.getPrice();
        fxMyFlight1.setVisible(true);
        fxFlightTotalPrice.setText(this.flightOutCost+this.flightBackCost+" kr.");
        this.myFlightOut = fl;
    }

    public void setBackFlightPopUp(Flight fl) {
        //Taka inn Flight hlut, upphafsstilla hann sem chosenFlightOut
        fxMyFlightNo2.setText(fl.getFlightNumber());
        fxMyFlightDate2.setText(fl.getDate().toString());
        fxMyAirline2.setText(fl.getAirlineName());
        fxMyFlightDept2.setText(fl.getDeparture());
        fxMyFlightArr2.setText(fl.getDestination());
        fxMyFlightPass2.setText(vd.getTotaCount() + " tickets");
        fxMyFlightFrom2.setText(fl.getTime().toString());
        fxMyFlightTo2.setText(String.valueOf(fl.getDuration()));
        fxMyFlightPrice2.setText(fl.getPrice()+" kr.");
        this.flightBackCost = fl.getPrice();
        fxMyFlight2.setVisible(true);
        fxFlightTotalPrice.setText(this.flightOutCost+this.flightBackCost+" kr.");
        this.myFlightBack = fl;
    }

    public void loadFlightCards() throws IOException {
        ArrayList<FlightCardController> listi = new ArrayList<FlightCardController>();

        CustomerController cc = new CustomerController(new Customer("", "", new ArrayList<>(), 1));
        List<List<Flight>> allFlights = cc.searchRoundTrips(vd.getDateFrom(), vd.getDateTo(), vd.getDestFrom(), vd.getDestTo(), totalPeople);
        List<Flight> depFlights = allFlights.get(0);
        List<Flight> retFlights = allFlights.get(1);
        for (Flight f : depFlights) {
            FlightCardController fcc = new FlightCardController(f, loader.getController(), true);
            listi.add(fcc);
        }
        fxFlightsDepCont.getChildren().addAll(listi);

        ArrayList<FlightCardController> listi2 = new ArrayList<FlightCardController>();

        for (Flight f : retFlights) {
            FlightCardController fcc = new FlightCardController(f, loader.getController(), false);
            listi2.add(fcc);
        }
        fxFlightsRetCont.getChildren().addAll(listi2);

    }

    public void loadHotelCards() throws Exception {

        ArrayList<HotelCardController> listi = new ArrayList<HotelCardController>();

        this.hotelController = HotelController.getInstance();
        this.hotelListi = hotelController.getAllAvailableHotels(2, totalPeople, vd.getDateFrom(), vd.getDateTo());


        for (Hotel h : hotelListi) {
            HotelCardController hcc = new HotelCardController(h, loader.getController(), this.vd);
            listi.add(hcc);
        }


        fxHotelCont.getChildren().addAll(listi);

    }

    public void loadDTCards() throws IOException {
        fxNoDayTrips.setDisable(true);
        ArrayList<DayTripCardController> listi = new ArrayList<DayTripCardController>();
        ArrayList<DayTrip> listiafDT = new ArrayList<DayTrip>();
        Hashtable<String, Object> params = new Hashtable<>();
        LocalDate[] fylki = {this.chosenDayForDayTrip, this.chosenDayForDayTrip};
        params.put("date", fylki);
        params.put("localCode", 2);
        listiafDT = DayTripController.getDayTrips(params);
        if(listiafDT.size() == 0) {
            fxNoDayTrips.setDisable(false);
        }
        System.out.println(listiafDT.size());
        for (DayTrip dt : listiafDT) {
            DayTripCardController dtc = new DayTripCardController(dt, loader.getController());
            listi.add(dtc);
        }
        fxDayTripsCont.getChildren().clear();
        fxDayTripsCont.getChildren().addAll(listi);

    }

    public void nextDayHandler(ActionEvent actionEvent) throws IOException {
        this.chosenDayForDayTrip = this.chosenDayForDayTrip.plusDays(1);
        fxPrevDay.setDisable(false);
        if(this.chosenDayForDayTrip.compareTo(vd.getDateTo()) >= 0) {
            fxNextDay.setDisable(true);
        }
        loadDTCards();
    }

    public void prevDayHandler(ActionEvent actionEvent) throws IOException {
        this.chosenDayForDayTrip = this.chosenDayForDayTrip.minusDays(1);
        if(this.chosenDayForDayTrip.compareTo(vd.getDateFrom()) <= 0) {
            fxPrevDay.setDisable(true);
        }
        loadDTCards();
    }

    public void jumpToDateHandler(ActionEvent actionEvent) throws IOException {
        if(this.datePickerOpened) {
            this.chosenDayForDayTrip = fxPickADay.getValue();
            fxPrevDay.setDisable(false);
            if(this.chosenDayForDayTrip.compareTo(vd.getDateTo()) >= 0) {
                fxNextDay.setDisable(true);
            }
            if(this.chosenDayForDayTrip.compareTo(vd.getDateFrom()) <= 0) {
                fxPrevDay.setDisable(true);
            }
            loadDTCards();
        } else {
            fxJumpButton.setText("Pick one ->");
        }
    }
    public void pickDayHandler(ActionEvent actionEvent) throws IOException {
        this.datePickerOpened = true;
        fxJumpButton.setText("Jump to:");
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
        vd.setMyFlightThere(this.myFlightOut);
        vd.setMyFlightBack(this.myFlightBack);

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
        if (total < totalPeople) {
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
        fxHotelBookedName.setText(chosenHotel.getName());
        fxHotelBookedDates.setText(vd.getDateFrom() + " - " + vd.getDateTo());
        //fxHotelBookedRooms.setText(this.bookedRooms.print()); hægt að nota bráðum...
        fxHotelBookedGuests.setText(vd.getTotaCount() + " persons");
        fxBookedHotel.setVisible(true);
        fxHotelPopUp.setVisible(false);
    }

    public void prufuHandler(ActionEvent actionEvent) throws IOException {
        loadFlightCards();
    }

    public VacationDeal getVD() {
        return this.vd;
    }

    public int getTotalPeople() {
        return this.totalPeople;
    }
}
