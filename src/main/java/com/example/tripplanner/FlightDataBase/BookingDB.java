package com.example.tripplanner.FlightDataBase;


import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDB extends DBConnection {
    /**
     * Insert passenger into `passengers` table.
     *
     * @param passenger   The passenger to insert into the database.
     * @param bookingId  The booking ID of booking this passenger is attached to.
     */
    private static void insertPassenger(Passenger passenger, int bookingId) {
        String sql = "INSERT INTO passengers(name, email, ssn, booking_id) VALUES(?,?,?,?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = connect();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getEmail());
            preparedStatement.setString(3, passenger.getSsn());
            preparedStatement.setInt(4, bookingId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * Insert booking into `bookings` table.
     *
     * @param flight_id  The ID of the flight this booking made.
     * @param user_id    The ID of the user that made the booking.
     */
    private static void insertBooking(int flight_id, int user_id) {
        String sql = "INSERT INTO bookings(flight_id, user_id) VALUES(?,?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = connect();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, flight_id);
            preparedStatement.setInt(2, user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * Converts a result set into a list of passengers.
     * If the result set is null, an empty list is returned.
     *
     * @param rs  The result to convert.
     * @return  A list of passenger objects.
     */
    private static List<Passenger> resultSetToPassengers(ResultSet rs) {
        List<Passenger> passengers = new ArrayList<>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    Passenger passenger = new Passenger(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("ssn"),
                        rs.getString("seat_nr")
                    );
                    passengers.add(passenger);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return passengers;
    }

    /**
     * Select the passengers with booking_id equal to `bookingID` from the
     * `passengers` table in the database.
     *
     * @param bookingID  The ID of the booking to get the passengers from.
     * @return  A list of passengers.
     */
    private static List<Passenger> selectPassengers(int bookingID) {
        String sql = "SELECT * FROM passengers WHERE booking_id = ?;";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Passenger> passengers = null;
        try {
            conn = connect();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, bookingID);
            resultSet = preparedStatement.executeQuery();
            passengers = resultSetToPassengers(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(conn);
        }
        return passengers;
    }

    /**
     * Select the bookings with user_id equal to `userID` from the `bookings`
     * table in the database.
     *
     * @param userID  The ID of the user to get the bookings from.
     * @return  A list of bookings.
     */
    private static List<Booking> selectBookings(int userID) {
        String sql = "SELECT * FROM bookings WHERE user_id = ?;";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Find the booking entries.
        try {
            conn = connect();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(conn);
        }

        // Find the passenger and flight entries and create bookings.
        List<Passenger> passengers = new ArrayList<>();
        List<Flight> flights = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();
        int currBookingID;
        try {
            while (resultSet.next()) {
                currBookingID = resultSet.getInt("id");
                passengers = selectPassengers(currBookingID);
                flights = FlightDB.selectFlightByID(currBookingID);
            }
        } catch (SQLException|NullPointerException e) { /* Ignore */ }

        return bookings;
    }

    /**
     * Inserts the booking into the database.
     *
     * @param booking  The booking to insert into the database.
     */
    public static void insertBooking(Booking booking, int userId) {
        String sqlInsertBookings = "INSERT INTO bookings(flight_id,user_id) VALUES(?,?)";
        Connection conn;
        PreparedStatement statement;
        List<Integer> bookingIds = new ArrayList<>();
        for (Flight f: booking.getFlights()) {
            try {
                conn = connect();
                statement = conn.prepareStatement(sqlInsertBookings);
                statement.setInt(1, f.getId());
                statement.setInt(2, userId);
                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int bookingId = rs.getInt(1);
                    bookingIds.add(bookingId);
                    System.out.println("Made booking with id: " + bookingId);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        String sqlInsertPassenger = "INSERT INTO passengers(name,email,ssn,booking_id) VALUES(?,?,?,?)";
        for (int bookingId: bookingIds) {
            for (Passenger p: booking.getPassengers()) {
                try {
                    conn = connect();
                    statement = conn.prepareStatement(sqlInsertPassenger);
                    statement.setString(1, p.getName());
                    statement.setString(2, p.getEmail());
                    statement.setString(3, p.getSsn());
                    statement.setInt(4, bookingId);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static List<Booking> getBookings(int userId) {
        return selectBookings(userId);
    }
}
