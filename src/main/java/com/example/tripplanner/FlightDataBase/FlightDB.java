package com.example.tripplanner.FlightDataBase;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FlightDB extends DBConnection {
    /**
     * Converts a result set into a list of passengers.
     * If the result set is null, an empty list is returned.
     *
     * @param rs  The result to convert.
     * @return  A list of passenger objects.
     */
    private static List<Flight> resultSetToFlights(ResultSet rs) {
        List<Flight> flights = new ArrayList<>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    Flight flight = new Flight(
                            rs.getString("airline_name"),
                            LocalDate.parse(rs.getString("date_of_flight")),
                            LocalTime.parse(rs.getString("time_of_flight")),
                            rs.getDouble("duration"),
                            rs.getString("departure"),
                            rs.getString("destination"),
                            rs.getString("flight_number"),
                            rs.getInt("flight_status"),
                            rs.getInt("price"),
                            rs.getInt("free_seats"),
                            rs.getInt("id")
                    );
                    flights.add(flight);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return flights;
    }

    /**
     * Select the flights using the supplied connection and prepared statement.
     *
     * If none is found, an empty list is returned.
     *
     * @param conn  The SQL connection to use.
     * @param preparedStatement  The SQL prepared statement to execute.
     * @return  A list of flights.
     */
    private static List<Flight> selectFlights(Connection conn, PreparedStatement preparedStatement) {
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();
        try {
            resultSet = preparedStatement.executeQuery();
            flights = resultSetToFlights(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(conn);
        }
        return flights;
    }

    /**
     * Select the flights with id equal to `flightID` from the
     * `flights` table in the database.
     *
     * If none is found, an empty list is returned.
     *
     * @param flightID  The ID of the flight to get.
     * @return  A list of flights.
     */
    public static List<Flight> selectFlightByID(int flightID) {
        String sql = "SELECT * FROM flights WHERE id = ?;";
        List<Flight> flights = null;
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, flightID);
            flights = selectFlights(conn, preparedStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flights;
    }

    /**
     * Searches for flights in the database that match the given parameters.
     *
     * @param startDate       The lower date in the date range. This can only be null if the endDate is also
     *                        null. Otherwise, an IllegalArgumentException is thrown. If both dates are set to
     *                        null, the search is not limited to any date range.
     * @param endDate         The higher date in the date range. This can only be null if the startDate is also
     *                        null. Otherwise, an IllegalArgumentException is thrown. If both dates are set to
     *                        null, the search is not limited to any date range.
     * @param departure       The departure aerodrome. If this is set to null, all possible departures are used
     *                        in the search.
     * @param destination     The destination aerodrome. If this is set to null, all possible destinations are
     *                        used in the search.
     * @param passengerCount  The number of seats required to be free.
     * @param bufferDays      The number of days used to search around the supplied date range.
     * @return  A list of two lists of Flight objects, matching the given parameters.
     *          The first one contains flights that are within the given date range.
     *          The second one flights that are outside the given date range, but within the buffered date range.
     */
    public static List<List<Flight>> searchFlights(LocalDate startDate, LocalDate endDate, String departure,
                                             String destination, int passengerCount, int bufferDays) {
        if ((startDate == null && endDate != null) || (startDate != null && endDate == null)) {
            throw new IllegalArgumentException("Both start and end dates need to be null, not just one.");
        } else if (startDate != null && startDate.isAfter(endDate)) {  // both dates are then not null.
            throw new IllegalArgumentException("The end date is before the start date");
        }
        if (bufferDays < 1) {
            throw new IllegalArgumentException("The bufferDays need to be greater or equal to 1.");
        }

        String regularSql = "SELECT * FROM flights WHERE free_seats>=" + passengerCount;
        String bufferedSql = "SELECT * FROM flights WHERE free_seats>=" + passengerCount;
        if (startDate != null) {  // both dates are then not null.
            regularSql += " AND date_of_flight BETWEEN '" + startDate + "' AND '" + endDate + "'";

            // The BETWEEN statement is inclusive.
            LocalDate bufferedStart = startDate.minusDays(bufferDays);
            LocalDate bufferedEnd = startDate.plusDays(bufferDays);
            bufferedSql += " AND date_of_flight BETWEEN '" + bufferedStart + "' AND '" + startDate.minusDays(1) + "'";
            bufferedSql += " OR date_of_flight BETWEEN '" + endDate.plusDays(1) + "' AND '" + bufferedEnd + "'";
        }
        if (destination != null && !destination.equals("")) {
            regularSql += " AND destination='" + destination + "'";
            bufferedSql += " AND destination='" + destination + "'";
        }
        if (departure != null && !departure.equals("")) {
            regularSql += " AND departure='" + departure + "'";
            bufferedSql += " AND departure='" + departure + "'";
        }

        System.out.println(regularSql);
        System.out.println(bufferedSql);

        Connection conn;
        PreparedStatement regularStatement;
        PreparedStatement bufferedStatement;
        List<Flight> regularFlights = null;
        List<Flight> bufferedFlights = null;
        try {
            conn = connect();
            regularStatement = conn.prepareStatement(regularSql);
            regularFlights = selectFlights(conn, regularStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            conn = connect();
            bufferedStatement = conn.prepareStatement(bufferedSql);
            bufferedFlights = selectFlights(conn, bufferedStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        List<List<Flight>> flights = new ArrayList<>(2);
        flights.add(regularFlights);
        flights.add(bufferedFlights);

        return flights;
    }

    /**
     * Insert new flight into the database.
     *
     * @param flight  The flight object to insert into the database.
     */
    public static void insertFlight(Flight flight) {
        String sql = "INSERT INTO flights(" +
                "airline_name,date_of_flight,time_of_flight,duration," +
                "departure,destination,flight_number,flight_status," +
                "price,free_seats) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        Connection conn;
        PreparedStatement statement;

        try {
            conn = connect();
            statement = conn.prepareStatement(sql);
            statement.setString(1, flight.getAirlineName());
            statement.setString(2, flight.getDate().toString());
            statement.setString(3, flight.getTime().toString());
            statement.setDouble(4, flight.getDuration());
            statement.setString(5, flight.getDestination());
            statement.setString(6, flight.getDestination());
            statement.setString(7, flight.getFlightNumber());
            statement.setInt(8, flight.getFlightStatus());
            statement.setInt(9, flight.getPrice());
            statement.setInt(10, flight.howManyFreeSeats());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update existing flight in the database using a modified flight object.
     *
     * @param flight  The flight object used to update the database.
     */
    public static void updateFlight(Flight flight) {
        String sql = "UPDATE flights SET " +
                "airline_name=?, date_of_flight=?, time_of_flight=?, duration=?, departure=?," +
                "destination=?, flight_number=?, flight_status=?, price=?, free_seats=?, WHERE id=?";
        Connection conn;
        PreparedStatement statement;

        try {
            conn = connect();
            statement = conn.prepareStatement(sql);
            statement.setString(1, flight.getAirlineName());
            statement.setString(2, flight.getDate().toString());
            statement.setString(3, flight.getTime().toString());
            statement.setDouble(4, flight.getDuration());
            statement.setString(5, flight.getDestination());
            statement.setString(6, flight.getDestination());
            statement.setString(7, flight.getFlightNumber());
            statement.setInt(8, flight.getFlightStatus());
            statement.setInt(9, flight.getPrice());
            statement.setInt(10, flight.howManyFreeSeats());
            statement.setInt(11, flight.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Delete existing flight from the database.
     *
     * Note: You need to delete all the bookings that are referencing the flight before deleting it.
     *
     * @param flight  the flight object to delete from the database.
     */
    public static void deleteFlight(Flight flight) {
        String sql = "DELETE FROM flights WHERE id = ?";
        Connection conn;
        PreparedStatement statement;

        try {
            conn = connect();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, flight.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void occupySeats(int newSeatCount, int flightId) {
        String sql = "UPDATE flights SET free_seats=?, WHERE id=?";
        Connection conn;
        PreparedStatement statement;

        try {
            conn = connect();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, newSeatCount);
            statement.setInt(2, flightId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
