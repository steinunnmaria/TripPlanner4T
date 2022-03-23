package com.example.tripplanner.Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DayTrip {

    /* 4D:
    public class DayTrip {
*/
    private String tripName;
    private LocalDate date;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private int difficulty;
    private String description;
    private int ageLimit;
    private double price;
    private Operator Operator;
    private String location;
    private int capacity;

    public DayTrip(String tripName, LocalDate date, LocalDateTime timeStart, LocalDateTime timeEnd, int difficulty, String description, int ageLimit, double price, com.example.tripplanner.Classes.Operator operator, String location, int capacity) {
        this.tripName = tripName;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.difficulty = difficulty;
        this.description = description;
        this.ageLimit = ageLimit;
        this.price = price;
        Operator = operator;
        this.location = location;
        this.capacity = capacity;
    }
}
