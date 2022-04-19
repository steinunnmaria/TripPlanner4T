package com.example.tripplanner.FlightDataBase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private final Customer customer;

    public CustomerController(Customer customer) {
        this.customer = customer;
    }

    /**
     * Search flights within (and including) the date range of
     * `startDate` and `endDate`, from anywhere to anywhere, with
     * `passengerCount` free seats.
     *
     * Returns an empty list if no flights are found.
     *
     * @param departure       The departure aerodrome.
     * @param destination     The destination aerodrome.
     * @param passengerCount  The number of passengers to search flights from.
     * @return  A list of flights, within the date range.
     * @throws IllegalArgumentException  When the `endDate` is before `startDate`.
     */
    public List<List<Flight>> searchFlights(String departure, String destination, int passengerCount, int bufferDays) throws IllegalArgumentException {
        return searchFlights(null, null, departure, destination, passengerCount, bufferDays);
    }

    /**
     * Search flights within (and including) the date range of
     * `startDate` and `endDate`, from anywhere to anywhere, with
     * `passengerCount` free seats.
     *
     * Returns an empty list if no flights are found.
     *
     * @param startDate       The lower bounds of the date range.
     * @param endDate         The upper bounds of the date range.
     * @param passengerCount  The number of passengers to search flights from.
     * @return  A list of flights, within the date range.
     * @throws IllegalArgumentException  When the `endDate` is before `startDate`.
     */
    public List<List<Flight>> searchFlights(LocalDate startDate, LocalDate endDate, int passengerCount, int bufferDays) throws IllegalArgumentException {
        return searchFlights(startDate, endDate, "", "", passengerCount, bufferDays);
    }

    /**
     * Search flights within (and including) the date range of
     * `startDate` and `endDate`, from `departure` to `destination`,
     * with `passengerCount` free seats.
     *
     * Returns an empty list if no flights are found.
     *
     * @param startDate       The lower bounds of the date range.
     * @param endDate         The upper bounds of the date range.
     * @param departure       The departure aerodrome.
     * @param destination     The destination aerodrome.
     * @param passengerCount  The number of passengers to search flights from.
     * @return  A list of flights, within the date range.
     * @throws IllegalArgumentException  When the `endDate` is before `startDate`.
     */
    public List<List<Flight>> searchFlights(
            LocalDate startDate, LocalDate endDate,
            String departure, String destination, int passengerCount, int bufferDays) throws IllegalArgumentException {
        return FlightDB.searchFlights(startDate, endDate, departure, destination, passengerCount, bufferDays);
    }

    /**
     * Returns two lists, one of departure flights, and another of return flights.
     *
     * @param departureDate   The date of departure.
     * @param arrivalDate     The date of arrival.
     * @param departure       The departure location.
     * @param destination     The destination location.
     * @param passengerCount  The number of passengers.
     * @return  Two lists that, the first one containing the departure flights and second the return flights.
     */
    public List<List<Flight>> searchRoundTrips(
            LocalDate departureDate, LocalDate arrivalDate,
            String departure, String destination, int passengerCount) throws IllegalArgumentException {
        List<Flight> departureFlights = FlightDB.searchFlights(
                departureDate, departureDate, departure, destination, passengerCount, 1).get(0);
        List<Flight> returnFlights = FlightDB.searchFlights(
                arrivalDate, arrivalDate, destination, departure, passengerCount, 1).get(0);
        List<List<Flight>> flights = new ArrayList<>(2);
        flights.add(departureFlights);
        flights.add(returnFlights);

        return flights;
    }

    /**
     * Returns two lists, one of departure flights, and another of return flights, both lists
     * only contain flights with airlines from a supplied airlines list.
     *
     * @param departureDate   The date of departure.
     * @param arrivalDate     The date of arrival.
     * @param departure       The departure location.
     * @param destination     The destination location.
     * @param passengerCount  The number of passengers.
     * @param airlineNames    The airline names used to filter the returned list.
     * @return  Two lists that, the first one containing the departure flights and second the return flights.
     */
    public List<List<Flight>> searchRoundTripsByAirline(
            LocalDate departureDate, LocalDate arrivalDate,
            String departure, String destination, int passengerCount, List<String> airlineNames) throws IllegalArgumentException {
        List<List<Flight>> searchedFlights = searchRoundTrips(departureDate, arrivalDate, departure, destination, passengerCount);
        List<Flight> filteredDepartureFlights = new ArrayList<>();
        List<Flight> filteredReturnFlights = new ArrayList<>();

        for (Flight flight: searchedFlights.get(0)){
            if(airlineNames.contains(flight.getAirlineName())){
                filteredDepartureFlights.add(flight);
            }
        }
        for (Flight flight: searchedFlights.get(1)){
            if(airlineNames.contains(flight.getAirlineName())){
                filteredReturnFlights.add(flight);
            }
        }
        List<List<Flight>> flights = new ArrayList<>(2);
        flights.add(filteredDepartureFlights);
        flights.add(filteredReturnFlights);

        return flights;
    }

    /**
     * Makes the booking for `passengers` in the `flights`
     * @param passengers  The passengers to include in the booking.
     * @param flights     The flights to include in the booking.
     */
    public void makeBooking(List<Passenger> passengers, List<Flight> flights) {
        System.out.println("Making bookings for " + passengers.size() + " passengers into " + flights.size() + "flight(s).");
        Booking booking = new Booking(flights, passengers);

        BookingDB.insertBooking(booking, customer.getId());
        for (Flight f: flights) {
            f.addPassengers(passengers);
            FlightDB.occupySeats(f.howManyFreeSeats() - passengers.size(), f.getId());
        }
    }

    /**
     * Cancel the booking for the user.
     *
     * @param booking  The booking to cancel.
     */
    public void cancelBooking(Booking booking) {
        List<Passenger> passengers = booking.getPassengers();
        List<Flight> flights = booking.getFlights();
        for (Flight flight: flights){
            flight.removePassengers(passengers);
        }
        //BookingDB.delete(booking);
        //save deoccupied seats to flight data
    }

    /**
     * Change the `oldBooking` to the `newBooking` for the user.
     *
     * @param oldBooking  The booking to cancel.
     * @param newBooking  The booking to save.
     */
    public void modifyBooking(Booking oldBooking, Booking newBooking) {
        //BookingDB.delete(oldBooking);
        //BookingDB.insert(newBooking);
    }
}
