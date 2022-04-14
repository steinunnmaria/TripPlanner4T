package com.example.tripplanner.Classes;

public class CreditCard {

    private String cardNumber;
    private String cardHolder;
    private String month;
    private String year;
    private String cvv;

    public CreditCard(String num,String name,String mm,String yy,String cvv) {
        this.cardHolder = name;
        this.cardNumber = num;
        this.month = mm;
        this.year = yy;
        this.cvv = cvv;
    }
}
