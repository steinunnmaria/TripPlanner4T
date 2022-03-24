package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.DayTrip;

import java.util.Comparator;

public class DayTripPriceComparator implements Comparator<DayTrip> {
        @Override
        public int compare(DayTrip o1, DayTrip o2) {
            return o1.getPrice().compareTo(o2.getPrice());
        }
}

