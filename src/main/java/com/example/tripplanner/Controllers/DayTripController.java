package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.DayTrip;
import javafx.scene.control.SplitPane;

import java.time.LocalDate;
import java.util.ArrayList;

public class DayTripController extends SplitPane {



    /* 4D:
    public class DayTrip {

  private String tripName;
  private LocalDate date;
  private LocalDateTime timeStart;
  private LocalDateTime timeEnd;
  private int difficulty;
  private String description;
  private int ageLimit;
  private double price;
  private Operator Operator;
  private String location;
  private int capacity;
     */

    /* Búa til smið sem annað hvort les inn úr skrá eða "generate'ar"
    gögn eftir formúlu (ekki handahófskennt, því við þurfum að geta yfirfarið þau.
     */


    public ArrayList<DayTrip> retrieveDayTrips(String location, LocalDate departureDate, LocalDate returnDate, int adultCount, int childrenCount){

        return null;
    }


}
