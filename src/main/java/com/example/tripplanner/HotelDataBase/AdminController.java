package com.example.tripplanner.HotelDataBase;

// Extra methods which are only Admin methods in name
public class AdminController {

    private static AdminController instance = null;
    private HotelDataConnection connection = HotelDataConnection.getInstance();

    /**
     * Returns the instance of the AdminController
     * @return the AdminController instance
     */
    public static AdminController getInstance() {
        if (instance == null) {
            instance = new AdminController();
        }
        return instance;
    }

    public void createHotel(Hotel hotel) throws Exception {
        connection.createHotel(hotel);
    }

    public void removeHotel(Integer hotelId) throws Exception {
        connection.removeHotel(hotelId);
    }

    public void createRoom(Room room) throws Exception {
        connection.createRoom(room);
    }

    public void removeHotel(Integer hotelId, Integer roomNum) throws Exception {
        connection.removeRoom(hotelId, roomNum);
    }
}
