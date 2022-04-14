package com.example.tripplanner.Classes;

public class Customers {

    private String contactTelNo;
    private String contactEmail;
    private String contactAddress;
    private String contactCity;
    private CreditCard contactCardDetails;
    private final int adultCount;
    private final int childCount;

    public Customers(String email, String telNo, String address, String city, int adults, int children) {
        this.contactTelNo = telNo;
        this.contactEmail = email;
        this.contactAddress = address;
        this.contactCity = city;
        this.adultCount = adults;
        this.childCount = children;
    }

    /* Getters */

    public void setCreditCard(CreditCard cardDetails) {
        this.contactCardDetails = cardDetails;
    }
}
