package com.example.tripplanner.HotelDataBase;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class HotelDataConnection {
    private Connection conn = null;
    // private final DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static HotelDataConnection instance = null;

    public HotelDataConnection() {
        try {
            String DB_PATH = "src/main/java/com/example/tripplanner/HotelDataBase/HotelData.db";
            String connectionUrl = "jdbc:sqlite:" + DB_PATH;
            conn = DriverManager.getConnection(connectionUrl);
            initializeDatabaseFromScript();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
    }

    public static HotelDataConnection getInstance(){
        if(instance == null){
            instance = new HotelDataConnection();
        }
        return instance;
    }

    private void getConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            String DB_PATH = "src/main/java/com/example/tripplanner/HotelDataBase/HotelData.db";
            String connectionUrl = "jdbc:sqlite:" + DB_PATH;
            conn = DriverManager.getConnection(connectionUrl);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    private void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    // From oracle docs
    private void initializeDatabaseFromScript() throws Exception {
        InputStream scriptStream = null;
        // ApplicationDirectory returns the private read-write sandbox area
        // of the mobile device's file system that this application can access.
        // This is where the database is created
        String dbName = "HotelData.db";

        // Verify whether or not the database exists.
        // If it does, then it has already been initialized
        // and no further actions are required
        File dbFile = new File(dbName);
        if (dbFile.exists())
            return;

        String current = System.getProperty("user.dir");
        // Since the SQL script has been packaged as a resource within
        // the application, the getResourceAsStream method is used
        scriptStream = Thread.currentThread().getContextClassLoader().
                getResourceAsStream("META-INF/initialize.sql");
        BufferedReader scriptReader = new BufferedReader(new FileReader(current + "database.sql"));
        String nextLine;
        StringBuffer nextStatement = new StringBuffer();

        // The while loop iterates over all the lines in the SQL script,
        // assembling them into valid SQL statements and executing them as
        // a terminating semicolon is encountered
        Statement stmt = conn.createStatement();
        while ((nextLine = scriptReader.readLine()) != null) {
            // Skipping blank lines, comments, and COMMIT statements
            if (nextLine.startsWith("REM") ||
                    nextLine.startsWith("COMMIT") ||
                    nextLine.length() < 1)
                continue;
            nextStatement.append(nextLine);
            if (nextLine.endsWith(";")) {
                stmt.execute(nextStatement.toString());
                nextStatement = new StringBuffer();
            }
        }
        scriptReader.close();
    }

    /**
     * Reads result set from SQL query and adds to Arraylist of Hotels
     * @param rs result set from SQL query
     * @return ArrayList<Hotel> Arraylist of hotels in result set
     * @throws Exception
     */
    private ArrayList<Hotel> readHotels(ResultSet rs) throws Exception {
        ArrayList<Hotel> res = new ArrayList<Hotel>();
        while (rs.next()) {
            res.add(new Hotel(rs.getInt("id"),rs.getString("name"),rs.getInt("region"),rs.getString("town"),rs.getString("image"),new Info(rs.getInt("starRating"),rs.getInt("priceRating"),rs.getBoolean("gym"),rs.getBoolean("spa"),rs.getBoolean("wifi"),rs.getBoolean("bar"),rs.getBoolean("restaurant"))));
        }
        rs.close();
        closeConnection();
        return res;
    }

     /**
     * Reads result set from SQL query and adds to Arraylist of Reservations
     * @param rs result set from SQL query
     * @return ArrayList<Reservation> Arraylist of reservations in result set
     * @throws Exception
     */
    private ArrayList<Reservation> readReservations(ResultSet rs) throws Exception {
        ArrayList<Reservation> res = new ArrayList<Reservation>();
        while (rs.next()) {
            res.add(new Reservation(rs.getString("reservationId"),rs.getString("created"),rs.getString("startDate"),rs.getString("endDate"),
            rs.getString("cName"),rs.getString("cEmail"),rs.getString("cPhone"),rs.getInt("numCustomers"),rs.getInt("hotelId"),rs.getInt("roomNum")));
        }
        rs.close();
        closeConnection();
        return res;
    }

    /**
     * Reads result set from SQL query and adds to Arraylist of Rooms
     * @param rs result set from SQL query
     * @return ArrayList<Room> Arraylist of rooms in result set
     * @throws Exception
     */
    private ArrayList<Room> readRooms(ResultSet rs) throws Exception {
        ArrayList<Room> res = new ArrayList<Room>();
        while (rs.next()) {
            res.add(new Room(rs.getInt("roomNum"),rs.getInt("hotelId"),rs.getInt("price"),rs.getInt("type"),rs.getInt("numBeds"),rs.getInt("capacity")));
        }
        rs.close();
        closeConnection();
        return res;
    }

    /**
     * Get all hotels in database
     * @return ArrayList<Hotel>
     * @throws Exception
     */
    public ArrayList<Hotel> getAllHotels() throws Exception {
        getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM HOTELS");
        return readHotels(rs);
    }

    /**
     * Returns hotel with given id
     * @param id ID of hotel in database
     * @return Hotel
     * @throws Exception
     */
    public Hotel getHotelById(Integer id) throws Exception {
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM HOTELS WHERE id = ?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        Hotel res = new Hotel(rs.getInt("id"),rs.getString("name"),rs.getInt("region"),rs.getString("town"),rs.getString("image"),new Info(rs.getInt("starRating"),rs.getInt("priceRating"),rs.getBoolean("gym"),rs.getBoolean("spa"),rs.getBoolean("wifi"),rs.getBoolean("bar"),rs.getBoolean("restaurant")));
        rs.close();
        closeConnection();
        return res;
    }

    /**
     * Returns hotel with given name
     * @param name name of hotel in database
     * @return Hotel
     * @throws Exception
     */
    public Hotel getHotelByName(String name) throws Exception{
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM HOTELS WHERE name = ?");
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        Hotel res = new Hotel(rs.getInt("id"),rs.getString("name"),rs.getInt("region"),rs.getString("town"),rs.getString("image"),new Info(rs.getInt("starRating"),rs.getInt("priceRating"),rs.getBoolean("gym"),rs.getBoolean("spa"),rs.getBoolean("wifi"),rs.getBoolean("bar"),rs.getBoolean("restaurant")));
        rs.close();
        closeConnection();
        return res;
    }

    /**
     * Returns hotels with given star rating
     * @param starRating Integer from 1-5
     * @return ArrayList<Hotel>
     * @throws Exception
     */
    public ArrayList<Hotel> getHotelsByStarRating(Integer starRating) throws Exception {
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM HOTELS WHERE starRating = ?");
        pstmt.setInt(1, starRating);
        ResultSet rs = pstmt.executeQuery();
        return readHotels(rs);
    }

    /**
     * Returns hotels in given area
     * @param areacode Integer from 1-6
     * @return ArrayList<Hotel>
     * @throws Exception
     */
    public ArrayList<Hotel> getHotelsInArea(Integer areaCode) throws Exception {
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM HOTELS WHERE region = ?");
        pstmt.setInt(1, areaCode);
        ResultSet rs = pstmt.executeQuery();
        return readHotels(rs);
    }

    /**
     * Returns hotels in given town name
     * @param townName name of town
     * @return ArrayList<Hotel>
     * @throws Exception
     */
    public ArrayList<Hotel> getHotelsInTown(String townName) throws Exception {
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM HOTELS WHERE town = ?");
        pstmt.setString(1, townName);
        ResultSet rs = pstmt.executeQuery();
        return readHotels(rs);
    }

    /**
     * Creates hotel in database from hotel object
     * @param hotel Hotel object
     * @throws Exception
     */
    public void createHotel(Hotel hotel) throws Exception{
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO HOTELS VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
        pstmt.setInt(1,hotel.getId());
        pstmt.setString(2,hotel.getName());
        pstmt.setInt(3,hotel.getRegion());
        pstmt.setString(4,hotel.getTown());
        pstmt.setString(5,hotel.getImage());
        Info info = hotel.getHotelInfo();
        pstmt.setInt(6,info.getStarRating());
        pstmt.setInt(7,info.getPriceRating());
        pstmt.setBoolean(8,info.getGym());
        pstmt.setBoolean(9,info.getSpa());
        pstmt.setBoolean(10,info.getWifi());
        pstmt.setBoolean(11,info.getBar());
        pstmt.setBoolean(12,info.getRestaurant());
        pstmt.executeUpdate();
        closeConnection();
    }

    /**
     * Delete Hotel from database
     * @param hotelId Hotel ID of the hotel to be deleted
     * @throws Exception
     */
    public void removeHotel(Integer hotelId) throws Exception{
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
            "DELETE FROM HOTELS WHERE hotelId = ?");
        pstmt.setInt(1, hotelId);
        pstmt.executeUpdate();

        pstmt = conn.prepareStatement(
            "DELETE FROM ROOMS WHERE hotelId = ?");
        pstmt.setInt(1, hotelId);
        pstmt.executeUpdate();
        closeConnection();
    }

    /**
     * Creates room in database from room object
     * @param room Room object
     * @throws Exception
     */
    public void createRoom(Room room) throws Exception{
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ROOMS VALUES(?,?,?,?,?,?)");
        pstmt.setInt(1,room.getRoomNum());
        pstmt.setInt(2,room.getHotelId());
        pstmt.setInt(3,room.getPrice());
        pstmt.setInt(4,room.getType());
        pstmt.setInt(5,room.getNumBeds());
        pstmt.setInt(6,room.getCapacity());
        pstmt.executeUpdate();
        closeConnection();
    }

    /**
     * Delete room from database
     * @param hotelId the id of the Hotel which contains the room
     * @param roomNum the room number of the room to be deleted
     * @throws Exception
     */
    public void removeRoom(Integer hotelId, Integer roomNum) throws Exception{
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
            "DELETE FROM HOTELS WHERE hotelId = ? AND roomNum = ?");
        pstmt.setInt(1, hotelId);
        pstmt.setInt(2, roomNum);
        pstmt.executeUpdate();
        closeConnection();
    }


    /**
     * Get all rooms at a specific hotel
     * @param hotelId id of hotel
     * @return ArrayList<Room>
     * @throws Exception
     */
    public ArrayList<Room> getRoomsByHotelId(Integer hotelId) throws Exception {
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ROOMS WHERE hotelId = ?");
        pstmt.setInt(1, hotelId);
        ResultSet rs = pstmt.executeQuery();
        return readRooms(rs);
    }

    /**
     * Get specific hotel room
     * @param hotelId id of hotel
     * @param roomNum room number
     * @return Room
     * @throws Exception
     */
    public Room getRoomByIds(Integer hotelId, Integer roomNum) throws Exception {
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ROOMS WHERE hotelId = ? and roomNum = ?");
        pstmt.setInt(1, hotelId);
        pstmt.setInt(2, roomNum);
        ResultSet rs = pstmt.executeQuery();
        Room res = new Room(rs.getInt("roomNum"),rs.getInt("hotelId"),rs.getInt("price"),rs.getInt("type"),rs.getInt("numBeds"),rs.getInt("capacity"));
        rs.close();
        closeConnection();
        return res;
    }

    /**
     * Get all rooms sorted by price
     * @return the sorted list of rooms
     * @throws Exception
     */
    public ArrayList<Room> sortAllRoomsByPrice() throws Exception {
        getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ROOMS ORDER BY price DESC");
        return readRooms(rs);
    }

    /**
     * Get all rooms sorted by star rating
     * @return the sorted list of rooms
     * @throws Exception
     */
    public ArrayList<Room> sortAllRoomsByStars() throws Exception {
        getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM HOTELS JOIN ROOMS WHERE id = hotelId ORDER BY starRating DESC");
        return readRooms(rs);
    }


    /**
     * Create reservation in database from Reservation object
     * @param resv Reservation object
     * @throws Exception
     */
    public void createReservation(Reservation resv) throws Exception{
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
            "INSERT INTO RESERVATIONS(?,?,?,?,?,?,?,?,?,?)");
        pstmt.setString(1,resv.getReservationId());
        pstmt.setString(2,resv.getCreated());
        pstmt.setString(3,resv.getStartDate());
        pstmt.setString(4,resv.getEndDate());
        pstmt.setString(5,resv.getCustomerName());
        pstmt.setString(6,resv.getCustomerEmail());
        pstmt.setString(7,resv.getCustomerPhone());
        pstmt.setInt(8,resv.getNumCustomers());
        pstmt.setInt(9,resv.getHotelId());
        pstmt.setInt(10,resv.getRoomNum());
        pstmt.executeUpdate();
        closeConnection();
    }


    /**
     * Delete reservation from database
     * @param resID Reservation ID of reservation to delete
     * @throws Exception
     */
    public void removeReservation(String resID) throws Exception{
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
            "DELETE FROM RESERVATIONS WHERE reservationId = ?");
        pstmt.setString(1, resID);
        pstmt.executeUpdate();
        closeConnection();
    }

    /**
     * Get all reservations in database
     * @return ArrayList<Reservation>
     * @throws Exception
     */
    public ArrayList<Reservation> getAllReservations() throws Exception{
        getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM RESERVATIONS");
        return readReservations(rs);
    }

    /**
     * Get all reservations for specific hotel room
     * @param hotelID ID of hotel
     * @param roomNum room number of hotel room
     * @return ArrayList<Reservation>
     * @throws Exception
     */
    public ArrayList<Reservation> getRoomReservations(Integer hotelID, Integer roomNum) throws Exception{
        getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
            "SELECT * FROM RESERVATIONS WHERE (hotelId = ? AND roomNum = ?)"
        );
        pstmt.setInt(1, hotelID);
        pstmt.setInt(2, roomNum);
        ResultSet rs = pstmt.executeQuery();
        return readReservations(rs);
    }

}
