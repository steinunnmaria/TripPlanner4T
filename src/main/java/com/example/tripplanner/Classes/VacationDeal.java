package com.example.tripplanner.Classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class VacationDeal {

    private final String destFrom;
    private final String destTo;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    private final long vacationDuration;
    private boolean isFlight;
    private boolean isHotel;
    private boolean isDayTrip;
    private boolean isReturnTrip;
    private Customers customers;
    private Flight myFlightThere;
    private Flight myFlightBack;
    private Hotel myHotel;
    private final int adultCount;
    private final int childCount;
    private ArrayList<DayTrip> myDayTrips;
    private int totalPrice; /* double? */

    public VacationDeal(String destFrom, String destTo, LocalDate dateFrom, LocalDate dateTo, int adultCount, int childCount) {
        this.destFrom = destFrom;
        this.destTo = destTo;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.vacationDuration = ChronoUnit.DAYS.between(dateFrom, dateTo);
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

    public int getAdultCount() {
        return adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public ArrayList<DayTrip> getMyDayTrips() {
        return myDayTrips;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    /* Setters */

    public void setFlight(boolean flight) {
        isFlight = flight;
    }

    public void setHotel(boolean hotel) {
        isHotel = hotel;
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

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setContactDetails(Customers fromView) {
        this.customers = fromView;
    }

    public void setCardDetails(CreditCard cardFromView) {
        this.customers.setCreditCard(cardFromView);
    }

}
