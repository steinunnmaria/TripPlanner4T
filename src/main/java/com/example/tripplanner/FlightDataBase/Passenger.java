package com.example.tripplanner.FlightDataBase;

public class Passenger {
    private String name;
    private String email;
    private String ssn;

    public Passenger(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Passenger(String name, String ssn, String email) {
        this.name = name;
        this.ssn = ssn;
        this.email = email;
    }

    public Passenger(String firstName, String lastName, String ssn, String email) {
        this.name = firstName + " " + lastName;
        this.ssn = ssn;
        this.email = email;
    }

    public String getSsn() {
        return ssn;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
