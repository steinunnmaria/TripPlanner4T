package com.example.tripplanner.Comparators;

import com.example.tripplanner.FlightDataBase.Flight;

import java.util.Comparator;

public class FlightPriceComparator implements Comparator<Flight> {

    @Override
    public int compare(Flight o1, Flight o2) {
        double a = o1.getPrice();
        double b = o2.getPrice();
        return a > b ? +1 : a < b ? -1 : 0;
    }

}
