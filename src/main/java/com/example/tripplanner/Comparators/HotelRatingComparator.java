package com.example.tripplanner.Comparators;

import com.example.tripplanner.Classes.Hotel;

import java.util.Comparator;

public class HotelRatingComparator implements Comparator<Hotel> {
    @Override
    public int compare(Hotel o1, Hotel o2) {
        double a = o1.getRating();
        double b = o2.getRating();
        return a > b ? +1 : a < b ? -1 : 0;
    }
}
