package com.flight.databaseaccessobject;

import com.flight.connector.MySQLConnector;
import com.flight.dataclass.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {

    //COLOR SCHEMES
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    //________________________________________________________________________________________________PRINT METHODS:
    private void printFormat(List<Flight> flightList) {
        // Formatting the String:
        String formattingString = "|" + ANSI_GREEN + " %-10s " + ANSI_RESET + "|" + ANSI_GREEN + " %-13s " + ANSI_RESET + "|" + ANSI_GREEN + " %-20s " + ANSI_RESET +
                "|" + ANSI_GREEN + " %-20s " + ANSI_RESET + "|" + ANSI_GREEN + " %-14s " + ANSI_RESET + "|" + ANSI_GREEN + " %-12s " + ANSI_RESET + "|" + ANSI_GREEN + " %-15s " + ANSI_RESET + "|" + ANSI_GREEN + " %-11s " + ANSI_RESET + "|\n";

        String formattingStringTableContent = "|" + ANSI_CYAN + " %-10s " + ANSI_RESET + "|" + ANSI_CYAN + " %-13s " + ANSI_RESET + "|" + ANSI_CYAN + " %-20s " + ANSI_RESET +
                "|" + ANSI_CYAN + " %-20s " + ANSI_RESET + "|" + ANSI_CYAN + " %-14s " + ANSI_RESET + "|" + ANSI_CYAN + " %-12s " + ANSI_RESET + "|" + ANSI_CYAN + " %-15s " + ANSI_RESET + "|" + ANSI_CYAN + " %-11s " + ANSI_RESET + "|\n";

        System.out.println("____________________________________________________________________________________________________________________________________________");
        System.out.printf(formattingString, "Flight ID", "Flight Number", "Origin", "Destination", "Departure Time", "Arrival Time", "Available Seats", "Total Seats");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        for (Flight flight : flightList) {
            System.out.printf(formattingStringTableContent, flight.getFlightID(), flight.getFlightNumber(), flight.getOrigin(), flight.getDestination(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getAvailableSeats(), flight.getTotalSeats());
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    //________________________________________________________________________________________________SELECTION METHODS
    // Select Everything From Flights Table
    public List<Flight> selectEverythingFromFlights() {
        String sqlQuery = "SELECT * FROM flights;";
        List<Flight> flights = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Flight flight = new Flight(
                        resultSet.getInt("flight_id"),
                        resultSet.getInt("flight_number"),
                        resultSet.getString("origin"),
                        resultSet.getString("destination"),
                        resultSet.getTime("departure_time"),
                        resultSet.getTime("arrival_time"),
                        resultSet.getInt("available_seats"),
                        resultSet.getInt("total_seats"),
                        resultSet.getDate("created_at"),
                        resultSet.getDate("update_at")
                );

                flights.add(flight);
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    // Select Everything From Flights Table using Given Flight ID.
    public List<Flight> selectEverythingFromFlights(int flightID) {
        String sqlQuery = "SELECT * FROM flights WHERE flight_id = ?;";
        List<Flight> flights = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, flightID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Flight flight = new Flight(
                        resultSet.getInt("flight_id"),
                        resultSet.getInt("flight_number"),
                        resultSet.getString("origin"),
                        resultSet.getString("destination"),
                        resultSet.getTime("departure_time"),
                        resultSet.getTime("arrival_time"),
                        resultSet.getInt("available_seats"),
                        resultSet.getInt("total_seats"),
                        resultSet.getDate("created_at"),
                        resultSet.getDate("update_at")
                );

                flights.add(flight);
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    // Select Everything From Flights Table and Paginate.
    public List<Flight> selectEverythingFromFlightsInRangeOf(int min, int offset) {
        String sqlQuery = "SELECT * FROM flights ORDER BY created_at DESC LIMIT ? OFFSET ?;";
        List<Flight> flights = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Flight flight = new Flight(
                        resultSet.getInt("flight_id"),
                        resultSet.getInt("flight_number"),
                        resultSet.getString("origin"),
                        resultSet.getString("destination"),
                        resultSet.getTime("departure_time"),
                        resultSet.getTime("arrival_time"),
                        resultSet.getInt("available_seats"),
                        resultSet.getInt("total_seats"),
                        resultSet.getDate("created_at"),
                        resultSet.getDate("update_at")
                );

                flights.add(flight);
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    // Search the Flights based on Origin and Destination.
    public List<Flight> searchFlights(String origin, String destination) {
        String sqlQuery = "SELECT * FROM flights WHERE origin LIKE ? AND destination LIKE ?;";
        List<Flight> flights = new ArrayList<>();
        try (Connection connection = MySQLConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, "%" + origin + "%");
            preparedStatement.setString(2, "%" + destination + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Flight flight = new Flight(
                        resultSet.getInt("flight_id"),
                        resultSet.getInt("flight_number"),
                        resultSet.getString("origin"),
                        resultSet.getString("destination"),
                        resultSet.getTime("departure_time"),
                        resultSet.getTime("arrival_time"),
                        resultSet.getInt("available_seats"),
                        resultSet.getInt("total_seats"),
                        resultSet.getDate("created_at"),
                        resultSet.getDate("update_at")
                );
                flights.add(flight);
            }
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    // Get the total number of Flights.
    public int totalNumberOfFlights() {
        int totalFlight = 0;
        String sqlSelectQuery = "SELECT COUNT(*) AS totalNumberOfFlights FROM flights";
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalFlight = resultSet.getInt("totalNumberOfFlights");
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalFlight;
    }

    //________________________________________________________________________________________________INSERTION METHODS:
    // Create New Flight
    public void insertIntoFlightsAll(Flight flight) {
        String sqlQuery = "INSERT INTO flights (flight_number, origin, destination, departure_time, arrival_time, available_seats, total_seats) " +
                "VALUES (?,?,?,?,?,?,?)";

        try {
            // Establish Connection.
            Connection connection = MySQLConnector.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, flight.getFlightNumber());
            preparedStatement.setString(2, flight.getOrigin());
            preparedStatement.setString(3, flight.getDestination());
            preparedStatement.setTime(4, flight.getDepartureTime());
            preparedStatement.setTime(5, flight.getArrivalTime());
            preparedStatement.setInt(6, flight.getAvailableSeats());
            preparedStatement.setInt(7, flight.getTotalSeats());

            // Execute the Query to INSERT the data INTO the Database TABLE
            preparedStatement.executeUpdate();

            // Close Connection
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //________________________________________________________________________________________________UPDATE METHODS:
    // Update Flights Departure Time and Arrival Time
    public void updateIntoFlights(int flightID, Time departureTime, Time arrivalTime) {
        String sqlUpdateQuery = "UPDATE flights SET departure_time = ?, arrival_time = ? WHERE flight_id = ?;";
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateQuery);
            preparedStatement.setTime(1, departureTime);
            preparedStatement.setTime(2, arrivalTime);
            preparedStatement.setInt(3, flightID);

            // Execute the Query to UPDATE TABLE with new Values
            preparedStatement.executeUpdate();

            // Close Connection
            MySQLConnector.closeConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update Flights Departure Time and Arrival Time by Entering Time as String
    public void updateIntoFlights(String departureTime, String arrivalTime, int flightID) {
        String sqlUpdateQuery = "UPDATE flights SET departure_time = ?, arrival_time = ? WHERE flight_id = ?;";
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateQuery);
            preparedStatement.setTime(1, Time.valueOf(departureTime));
            preparedStatement.setTime(2, Time.valueOf(arrivalTime));
            preparedStatement.setInt(3, flightID);

            // Execute the Query to UPDATE TABLE with new Values
            preparedStatement.executeUpdate();

            // Close Connection
            MySQLConnector.closeConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update Flights Origin and Destination
    public void updateIntoFlights(int flightID, String origin, String destination) {
        String sqlUpdateQuery = "UPDATE flights SET origin = ?, destination = ? WHERE flight_id = ?;";
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateQuery);
            preparedStatement.setString(1, origin);
            preparedStatement.setString(2, destination);
            preparedStatement.setInt(3, flightID);

            // Execute the Query to UPDATE TABLE with new Values
            preparedStatement.executeUpdate();

            // Close Connection
            MySQLConnector.closeConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //________________________________________________________________________________________________DATA TABULATION METHODS:
    public void printFlightsAvailableInTableForm() {
        List<Flight> flightList = this.selectEverythingFromFlights();
        this.printFormat(flightList);
    }

    public void printSearchResultsInTableForm(String origin, String destination) {
        List<Flight> flights = this.searchFlights(origin, destination);
        this.printFormat(flights);
    }

    public void printFlightsAvailableInTableForm(int id) {
        List<Flight> flightList = this.selectEverythingFromFlights(id);
        this.printFormat(flightList);
    }

    public void printFlightsAvailableInTableForm(int min, int offset) {
        List<Flight> flightList = this.selectEverythingFromFlightsInRangeOf(min, offset);
        this.printFormat(flightList);
    }


}
