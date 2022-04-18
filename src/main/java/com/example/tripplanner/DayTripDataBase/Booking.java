package com.example.tripplanner.DayTripDataBase;

import java.time.LocalDate;

public class Booking {
    private String bookingId;
    private String clientSSN;
    private String clientEmail;
    private String clientPhoneNumber;
    private int clientCount;
    private LocalDate date;
    private boolean isPaid;
    private String dayTripId;

    public Booking(
            String bookingId,
            String clientSSN,
            String clientEmail,
            String clientPhoneNumber,
            int clientCount,
            LocalDate date,
            boolean isPaid,
            String dayTripId
    ) {
        this.bookingId = bookingId;
        this.clientSSN = clientSSN;
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientCount = clientCount;
        this.date = date;
        this.isPaid = isPaid;
        this.dayTripId = dayTripId;
    }

    public String getBookingId() {
        return this.bookingId;
    }

    public String getClientSSN() {
        return this.clientSSN;
    }

    public String getDayTripId() {
        return this.dayTripId;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public boolean isPaid() {
        return this.isPaid;
    }

    public String getClientEmail() {
        return this.clientEmail;
    }

    public String getClientPhoneNumber() {
        return this.clientPhoneNumber;
    }

    public int getClientCount() {
        return this.clientCount;
    }
}
