package com.example.tripplanner.DayTripDataBase;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DayTrip {
    private String dayTripId;
    private String name;
    private double price;
    private String description;
    private String location;
    private int localCode;
    private LocalDate date;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private int ageLimit;
    private String difficulty;
    private int capacity;
    private String oId;

    public DayTrip(
            String dayTripId,
            String name,
            double price,
            String description,
            String location,
            int localCode,
            LocalDate date,
            LocalDateTime timeStart,
            LocalDateTime timeEnd,
            int ageLimit,
            String difficulty,
            int capacity,
            String oId
    ) {
        this.dayTripId = dayTripId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.location = location;
        this.localCode = localCode;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.ageLimit = ageLimit;
        this.difficulty = difficulty;
        this.capacity = capacity;
        this.oId = oId;
    }

    public String getDayTripId() {
        return this.dayTripId;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalDateTime getTimeStart() {
        return this.timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return this.timeEnd;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public String getDescription() {
        return this.description;
    }

    public int getAgeLimit() {
        return this.ageLimit;
    }

    public double getPrice() {
        return this.price;
    }

    public String getOId() {
        return this.oId;
    }

    public String getLocation() {
        return this.location;
    }

    public int getLocalCode() {
        return this.localCode;
    }

    public int getCapacity() {
        return this.capacity;
    }
}
