package com.example.tripplanner.FlightDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Shizmix to maintain a test db.
    //public static String db = "flight_bookings_integration.db";

    static Connection connect() {
        String DB_PATH = "src/main/java/com/example/tripplanner/FlightDataBase/flight_bookings_integration.db";
        String connectionUrl = "jdbc:sqlite:" + DB_PATH;
        //conn = DriverManager.getConnection(connectionUrl);
        //String url = "jdbc:sqlite:" + DBConnection.class.getClassLoader().getResource("db/" + db);
        System.out.println("Connecting to DB: " + connectionUrl);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
