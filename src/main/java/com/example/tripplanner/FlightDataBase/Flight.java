package com.example.tripplanner.FlightDataBase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private int id;
    private String airlineName;
    private LocalDate date;
    private LocalTime time;
    private double duration;
    private String departure;
    private String destination;
    private String flightNumber;
    private int flightStatus;
    private int price;
    private List<Passenger> passengers = new ArrayList<Passenger>();
    private int freeSeats;
    private int seatsOccupied;

    public Flight(String airlineName, LocalDate date, LocalTime time, double duration, String departure,
                  String destination, String flightNumber, int flightStatus, int price, int freeSeats) {
        this.airlineName = airlineName;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.departure = departure;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.flightStatus = flightStatus;
        this.price = price;
        this.freeSeats = freeSeats;
        this.id = -1;
    }

    public Flight(String airlineName, LocalDate date, LocalTime time, double duration, String departure,
                  String destination, String flightNumber, int flightStatus, int price, int freeSeats, int id) {
        this.airlineName = airlineName;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.departure = departure;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.flightStatus = flightStatus;
        this.price = price;
        this.freeSeats = freeSeats;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public double getDuration() {return duration;}

    public String getDeparture() {return departure;}

    public String getDestination() {return destination;}

    public String getFlightNumber() {
        return flightNumber;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public int getPrice() {
        return price;
    }

    public int getFlightStatus() {
        return flightStatus;
    }

    public int getStatus() {
        return flightStatus;
    }

    public void addPassengers(List<Passenger> newPassengers) {
        this.passengers.addAll(newPassengers);
        this.freeSeats -= newPassengers.size();
    }

    public void removePassengers(List<Passenger> delPassengers) {
        this.passengers.removeAll(delPassengers);
        this.freeSeats += delPassengers.size();
    }

    public int howManyFreeSeats() {
        return freeSeats;
    }

    /**
     * Calculate the end time using the flights start time and duration.
     *
     * @return  The end time of the flight.
     */
    public LocalTime calculateEndTime(){
        int durationInMinutes = (int) Math.round(duration*60);
        return time.plusMinutes(durationInMinutes);
    }
}
