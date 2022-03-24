package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.DayTrip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

class DayTripControllerTest {

    private ArrayList<DayTrip> available;
    private DayTripController dummy = new DayTripController();

    String loc = "Stykkishólmur";
    LocalDate dep = LocalDate.now();
    LocalDate ret = dep.plusDays(2);
    int adults = 2;
    int children = 1;

    @BeforeEach
    void retrieveDayTrips() {
        available = dummy.retrieveDayTrips(loc, dep, ret, adults, children);
    }

    @Test
    void testOrderPrice() {
        Collections.sort(available, new DayTripPriceComparator());
        for(int i = 0; i < available.size()-1; i++) {
            assert(available.get(i).getPrice() <= available.get(i+1).getPrice());
        }
    }

    @Test
    void testOrderRating() {
        Collections.sort(available, new DayTripRatingComparator());
        for(int i = 0; i < available.size()-1; i++) {
            assert(available.get(i).getRating() <= available.get(i+1).getRating());
        }
    }

    /**
     * Hægt að nota ef við viljum kalla fram kvörtun viljandi, það er ekki víst að við viljum það
    @Test
    void testIllegal() {
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                DayTrip user = new DayTrip();
            }
        });
    }
    **/

    @Test
    void illegalLocation() {
        available = dummy.retrieveDayTrips("Hawaii", dep, ret, adults, children);
        assert(available.isEmpty());
    }

    @Test
    void timeTravel() {
        available = dummy.retrieveDayTrips(loc, ret, dep, adults, children);
        assert(available.isEmpty());
    }

    @Test
    void ghostShip() {
        available = dummy.retrieveDayTrips(loc, ret, dep, 0, 0);
        assert(available.isEmpty());
    }

    @Test
    void filterDifficulty() {
        int diff = 2;
        ArrayList<DayTrip> new_available = new ArrayList<>();
        for(int i = 0; i < available.size(); i++) {
            if( available.get(i).getDifficulty() >= diff) {
                new_available.add(available.get(i));
            }
        }
        for(int i = 0; i < new_available.size(); i++) {
            assert(new_available.get(i).getDifficulty() >= diff);
        }

    }

}
