package com.example.tripplanner.FlightDataBase;

import java.util.List;

public class Customer extends User {
    private List<Booking> bookings;

    public Customer(String name, String email, List<Booking> bookings) {
        super(name, email, false, false);
        this.bookings = bookings;
    }

    public Customer(String name, String email, List<Booking> bookings, int id) {
        super(name, email, false, false, id);
        this.bookings = bookings;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}
