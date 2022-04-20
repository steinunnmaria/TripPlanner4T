package com.example.tripplanner.Classes;

import com.example.tripplanner.Controllers.DayTripBookedCardController;
import com.example.tripplanner.DayTripDataBase.DayTrip;
import com.example.tripplanner.FlightDataBase.Flight;
import com.example.tripplanner.HotelDataBase.Hotel;
import com.example.tripplanner.HotelDataBase.Room;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class VacationDeal {

    private final String destFrom;
    private final String destTo;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    private final long vacationDuration;
    private boolean isFlight = true;
    private boolean isHotel = true;
    private boolean isDayTrip = true;
    private boolean isReturnTrip;
    private Customers customers;
    private Flight myFlightThere;
    private Flight myFlightBack;
    private Hotel myHotel;
    private final int adultCount;
    private final int childCount;
    private final int totalPeople;
    private final Integer localCode;
    private ArrayList<DayTrip> myDayTrips;
    private ArrayList<Room> myRooms;
    private int totalPrice; /* double? */
    private ArrayList<DayTripBookedCardController> bookedDTList;

    public VacationDeal(String destFrom, String destTo, LocalDate dateFrom, LocalDate dateTo, int adultCount, int childCount, Integer localCode) {
        this.destFrom = destFrom;
        this.destTo = destTo;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.vacationDuration = ChronoUnit.DAYS.between(dateFrom, dateTo);
        this.totalPeople = this.getAdultCount() + this.getChildCount();
        this.localCode = localCode;
    }

    /* Getters */
    public Integer getPrice() {
        return this.totalPrice;
    }

    public String getDestFrom() {
        return this.destFrom;
    }

    public String getDestTo() {
        return destTo;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public long getVacationDuration() {
        return vacationDuration;
    }

    public int getHotelPrice() {
        int price = 0;
        for (Room room : myRooms) {
            price += (room.getPrice()*vacationDuration);
        }
        return price;
    }

    public int getDayTripsPrice() {
        int price = 0;
        for (DayTripBookedCardController dtbcc : bookedDTList) {
            price += dtbcc.getPrice();
        }
        return price;
    }

    public int getTotalPassFlightPrice() {
        return myFlightThere.getPrice() + myFlightBack.getPrice();
    }

    public int getTotalFlightPrice() {
        return getTotalPassFlightPrice()*totalPeople;
    }

    public boolean isFlight() {
        return isFlight;
    }

    public boolean isHotel() {
        return isHotel;
    }

    public boolean isDayTrip() {
        return isDayTrip;
    }

    public boolean isReturnTrip() {
        return isReturnTrip;
    }

    public Customers getCustomers() {
        return customers;
    }

    public Flight getMyFlightThere() {
        return myFlightThere;
    }

    public Flight getMyFlightBack() {
        return myFlightBack;
    }

    public Hotel getMyHotel() {
        return myHotel;
    }

    public ArrayList<Room> getMyRooms() {
        return myRooms;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public ArrayList<DayTrip> getMyDayTrips() {
        return myDayTrips;
    }

    public Integer getLocalCode() {
        return localCode;
    }

    /* Setters */

    public void setFlight(boolean flight) {
        isFlight = flight;
    }

    public void setHotel(boolean hotel) {
        isHotel = hotel;
    }

    public void setMyRooms(ArrayList<Room> myRooms) {
        this.myRooms = myRooms;
    }

    public void setDayTrip(boolean dayTrip) {
        isDayTrip = dayTrip;
    }

    public void setReturnTrip(boolean returnTrip) {
        isReturnTrip = returnTrip;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public void setMyFlightThere(Flight myFlightThere) {
        this.myFlightThere = myFlightThere;
    }

    public void setMyFlightBack(Flight myFlightBack) {
        this.myFlightBack = myFlightBack;
    }

    public void setMyHotel(Hotel myHotel) {
        this.myHotel = myHotel;
    }

    public void setMyDayTrips(ArrayList<DayTrip> myDayTrips) {
        this.myDayTrips = myDayTrips;
    }

    public int getTotalPrice() {
        int price = 0;
        if (isFlight()) {
            price+=getTotalFlightPrice();
        }
        if (isHotel()) {
            price+=getHotelPrice();
        }
        if (isDayTrip()) {
            price+=getDayTripsPrice();
        }
        totalPrice=price;
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setContactDetails(Customers fromView) {
        this.customers = fromView;
    }

    public void setCardDetails(CreditCard cardFromView) {
        this.customers.setCreditCard(cardFromView);
    }

    public int getTotalCount() { return this.totalPeople; }

    public void setBookedDTList(ArrayList<DayTripBookedCardController> bookedDTList) {
        this.bookedDTList = bookedDTList;
    }

    public ArrayList<DayTripBookedCardController> getBookedDTList() {
        return bookedDTList;
    }
}
