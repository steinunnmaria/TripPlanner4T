package com.example.tripplanner.HotelDataBase;

import java.util.ArrayList;

public class ReservationController {

    private static ReservationController instance = null;
    private static HotelDataConnection connection = HotelDataConnection.getInstance();

    /**
     * Returns the instance of the ReservationController
     * @return the ReservationController instance
     */
    public static ReservationController getInstance() {
        if (instance == null) {
            instance = new ReservationController();
        }
        return instance;
    }

    /**
     * Checks if the room is available for the given dates
     * @return true if the room is available for the given date range
     */
    public boolean isAvailable(int hotelId, int roomId, String startDate, String endDate) throws Exception {
        Reservation mockReservation = new Reservation("", "", startDate, endDate, "", "", "", 0, roomId, hotelId);
        ArrayList<Reservation> reservations = connection.getRoomReservations(hotelId, roomId);
        for (Reservation res: reservations) {
            if (res.compareTo(mockReservation) != 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * Writes the reservation to the database if the reservation is valid
     */
    public void addReservation(Reservation res) throws Exception {
        if (isAvailable(res.getHotelId(), res.getRoomNum(), res.getStartDate(), res.getEndDate())) {
            Room room = connection.getRoomByIds(res.getHotelId(), res.getRoomNum());
            if (res.getNumCustomers() <= room.getCapacity())
                connection.createReservation(res);
            else throw new Exception("Room is not large enough for the number of customers");

        } else throw new Exception("Room is not available");
    }

    /**
     * Deletes the reservation from the database
     */
    public void removeReservations(String resID) throws Exception {
        connection.removeReservation(resID);
    }

    public ArrayList<Reservation> getAllReservations() throws Exception {
        return connection.getAllReservations();
    }

    public ArrayList<Reservation> getRoomReservations(Integer hotelID, Integer roomNum) throws Exception {
        return connection.getRoomReservations(hotelID, roomNum);
    }

}


