package com.example.tripplanner.FlightDataBase;

public class User {
    private int id;
    private String name;
    private String email;
    private boolean isAirline;
    private boolean isAdmin;

    public User(String name, String email, boolean isAirline, boolean isAdmin) {
        this.name = name;
        this.email = email;
        this.isAirline = isAirline;
        this.isAdmin = isAdmin;
    }

    public User(String name, String email, boolean isAirline, boolean isAdmin, int id) {
        this.name = name;
        this.email = email;
        this.isAirline = isAirline;
        this.isAdmin = isAdmin;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isAirline() {
        return isAirline;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
