package com.example.tripplanner.Classes;

import com.example.tripplanner.HotelDataBase.Room;

import java.util.ArrayList;

public class RoomBooking {

    public RoomBooking(long vd) {
        this.vacationDuration = vd;
    }

    private ArrayList<Room> roomsBooked;
    private long vacationDuration;

    public void addRoom(Room r) {
        roomsBooked.add(r);
    }

    public String print() {
        String s = "| ";
        for (Room r: roomsBooked) {
            String c = r.getRoomNum() + "("+r.getCapacity()+") | ";
            //Ógeðslegt og eitthvað sem aldrei ætti að gera í Java
            s = s+c;
        }
        return(s);
    }

    public double getPriceTotal() {
        double total = 0;
        for (Room r: roomsBooked) {
            total += r.getPrice()*this.vacationDuration;
        }
        return(total);
    }
}
