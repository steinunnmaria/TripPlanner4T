package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.DayTrip;

import java.util.Comparator;

public class DayTripRatingComparator implements Comparator<DayTrip> {
    @Override
    public int compare(DayTrip o1, DayTrip o2) {
        return o1.getRating().compareTo(o2.getRating());
    }
}
