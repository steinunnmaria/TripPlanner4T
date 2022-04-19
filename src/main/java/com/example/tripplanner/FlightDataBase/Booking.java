package com.example.tripplanner.FlightDataBase;

import java.util.List;

public class Booking {
    private int id;
    private List<Flight> flights;
    private List<Passenger> passengers;

    public Booking(List<Flight> flights, List<Passenger> passengers) {
        this.flights = flights;
        this.passengers = passengers;
    }

    public Booking(List<Flight> flights, List<Passenger> passengers, int id) {
        this.flights = flights;
        this.passengers = passengers;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}
