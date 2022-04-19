package com.example.tripplanner.Classes;

import com.example.tripplanner.HotelDataBase.Room;

import java.util.ArrayList;

public class RoomBooking {

    private ArrayList<Room> roomsBooked;

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
}
