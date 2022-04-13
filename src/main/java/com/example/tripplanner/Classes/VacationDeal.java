package com.example.tripplanner.Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class VacationDeal {

    private String destFrom;
    private String destTo;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int vacationDuration;
    private boolean isFlight;
    private boolean isHotel;
    private boolean isDayTrip;
    private boolean isReturnTrip;
    private Customers customers;
    private Flight myFlightThere;
    private Flight myFlightBack;
    private Hotel myHotel;
    private int adultCount;
    private int childCount;
    private ArrayList<DayTrip> myDayTrips;
    private int totalPrice; /* double? */

    public VacationDeal(String tripName, LocalDate date, LocalDateTime timeStart, LocalDateTime timeEnd, int difficulty, String description, int ageLimit, Integer price, com.example.tripplanner.Classes.Operator operator, String location, int capacity, Integer rating) {

    }

}
