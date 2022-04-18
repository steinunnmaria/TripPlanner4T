package com.example.tripplanner.Comparators;

import com.example.tripplanner.DayTripDataBase.DayTrip;

import java.util.Comparator;

public class DayTripPriceComparator implements Comparator<DayTrip> {
    @Override
    public int compare(DayTrip o1, DayTrip o2) {
        double a = o1.getPrice();
        double b = o2.getPrice();
        return a > b ? +1 : a < b ? -1 : 0;
    }
}

