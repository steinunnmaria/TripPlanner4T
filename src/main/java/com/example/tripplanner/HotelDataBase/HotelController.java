package com.example.tripplanner.HotelDataBase;

import java.time.LocalDate;
import java.util.ArrayList;

public class HotelController {

    private static HotelController instance = null;
    private HotelDataConnection connection = HotelDataConnection.getInstance();

    /**
     * Returns the instance of the HotelController
     * @return the HotelController instance
     */
    public static HotelController getInstance() {
        if (instance == null) {
            instance = new HotelController();
        }
        return instance;
    }

    /**
     * @param location: the location in which we want to search for hotels
     * @param numCustomers: the number of customers that we want to accomodate, i.e. the
     * minimum amount of accommodation we need to have
     * @param from: the start date of the interval in which we want to search for hotels
     * @param to: the end date of the interval in which we want to search for hotels
     * @return an ArrayList of Hotels which fit these criteria
     * @throws Exception
     */
    public ArrayList<Hotel> getAllAvailableHotels(Integer location, Integer numCustomers, LocalDate from, LocalDate to) throws Exception {
        ReservationController reservController = ReservationController.getInstance();
        String fromString = from.toString();
        String toString = to.toString();
        ArrayList<Hotel> hotels = connection.getHotelsInArea(location);
        ArrayList<Hotel> availableHotels = new ArrayList<Hotel>();
        for (Hotel hotel : hotels) {
            ArrayList<Room> rooms = connection.getRoomsByHotelId(hotel.getId());
            Integer count = 0;
            for (Room room : rooms) {
                if (reservController.isAvailable(room.getHotelId(), room.getRoomNum(), fromString, toString)) {
                    count += room.getCapacity();
                }
            }
            if (count >= numCustomers) {
                availableHotels.add(hotel);
            }
        }
        return availableHotels;
    }

    /**
     * @param hotels: the ArrayList of Hotels we are filtering
     * @param constrs: an array of Strings which correspond to boolean criteria for filtering
     * @return the ArrayList of Hotels after filtering
     * @throws Exception
     */
    public ArrayList<Hotel> filterByInfo(ArrayList<Hotel> hotels, String[] constrs) throws Exception {
        ArrayList<Hotel> availableHotels = new ArrayList<Hotel>();
        for (Hotel hotel : hotels) {
            Boolean check = true;
            for (String s: constrs) {
                if(s.contains("gym") && !hotel.getHotelInfo().getGym()){
                    check = false;
                }
                if(s.contains("wifi") && !hotel.getHotelInfo().getWifi()){
                    check = false;
                }
                if(s.contains("breakfast") && !hotel.getHotelInfo().getRestaurant()){
                    check = false;
                }
            }
            if(check){
                availableHotels.add(hotel);
            }
        }
        return availableHotels;
    }

    /**
     * @param hotels: the ArrayList of Hotels we are filtering
     * @param constrs: an array of Integers containing possible starRatings which the hotels must correspond to
     * @return the ArrayList of Hotels after filtering
     * @throws Exception
     */
    private ArrayList<Hotel> filterByStars(ArrayList<Hotel> hotels, Integer[] constrs) throws Exception {
        ArrayList<Hotel> availableHotels = new ArrayList<Hotel>();
        for (Hotel hotel : hotels) {
            Boolean check = false;
            for (Integer n: constrs) {
                if(n == hotel.getHotelInfo().getStarRating()){
                    check = true;
                }
            }
            if(check){
                availableHotels.add(hotel);
            }
        }
        return availableHotels;
    }

    /**
     * Method that runs filterByStars and filterByInfo on the ArrayList of Hotels
     * @param hotels: the ArrayList of Hotels we are filtering
     * @param stars: an array of Integers containing possible starRatings which the hotels must correspond to
     * @param info: an array of Strings which correspond to boolean criteria for filtering
     * @return the ArrayList of Hotels after filtering
     * @throws Exception
     */
    public ArrayList<Hotel> filterHotels(ArrayList<Hotel> hotels, Integer[] stars, String[] info) throws Exception {
        return filterByInfo(filterByStars(hotels, stars), info);
    }

    public ArrayList<Hotel> getAllHotels() throws Exception {
        return connection.getAllHotels();
    }

    public Hotel getHotelByID(Integer ID) throws Exception {
        return connection.getHotelById(ID);
    }

    public Hotel getHotelIdByName(String name) throws Exception {
        return connection.getHotelByName(name);
    }

    public ArrayList<Hotel> getHotelsByStarRating(Integer starRating) throws Exception {
        return connection.getHotelsByStarRating(starRating);
    }

    public ArrayList<Hotel> getHotelsInArea(Integer area) throws Exception {
        return connection.getHotelsInArea(area);
    }

    public ArrayList<Hotel> getHotelsInTown(String town) throws Exception {
        return connection.getHotelsInTown(town);
    }

    public ArrayList<Room> getRoomsByHotelId(Integer hotelId) throws Exception {
        return connection.getRoomsByHotelId(hotelId);
    }

    public Room getRoomsByIds(Integer hotelId, Integer roomNum) throws Exception {
        return connection.getRoomByIds(hotelId, roomNum);
    }

    public ArrayList<Room> sortAllRoomsByPrice() throws Exception {
        return connection.sortAllRoomsByPrice();
    }

    public ArrayList<Room> getAvailableRoomsByHotelId(Integer hotelId, LocalDate from, LocalDate to) throws Exception {
        ReservationController reservController = ReservationController.getInstance();
        String fromString = from.toString();
        String toString = to.toString();
        ArrayList<Room> rooms = connection.getRoomsByHotelId(hotelId);
        ArrayList<Room> availableRooms = new ArrayList<Room>();
        for (Room room : rooms) {
            if (reservController.isAvailable(room.getHotelId(), room.getRoomNum(), fromString, toString)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

}

