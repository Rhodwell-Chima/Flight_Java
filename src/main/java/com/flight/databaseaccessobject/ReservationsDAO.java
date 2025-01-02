package com.flight.databaseaccessobject;

import com.flight.connector.MySQLConnector;
import com.flight.dataclass.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationsDAO {

    //COLOR SCHEMES
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";


    //_______________________________________________________________________________________________________PRINT METHODS:
    private void printFormat(List<Reservation> reservationList) {
        String formattingString = "|" + ANSI_GREEN + " %-5s " + ANSI_RESET + "|" + ANSI_GREEN + " %-20s " + ANSI_RESET + "|" + ANSI_GREEN + " %-13s " + ANSI_RESET +
                "|" + ANSI_GREEN + " %-15s " + ANSI_RESET + "|" + ANSI_GREEN + " %-14s " + ANSI_RESET + "|" + ANSI_GREEN + " %-14s " + ANSI_RESET + "|" + ANSI_GREEN + " %-9s " + ANSI_RESET +
                "|" + ANSI_GREEN + " %-15s " + ANSI_RESET + "|" + ANSI_GREEN + " %-13s " + ANSI_RESET + "|\n";

        String formattingStringTableContent = "|" + ANSI_CYAN + " %-5s " + ANSI_RESET + "|" + ANSI_CYAN + " %-20s " + ANSI_RESET + "|" + ANSI_CYAN + " %-13s " + ANSI_RESET +
                "|" + ANSI_CYAN + " %-15s " + ANSI_RESET + "|" + ANSI_CYAN + " %-14s " + ANSI_RESET + "|" + ANSI_CYAN + " %-14s " + ANSI_RESET + "|" + ANSI_CYAN + " %-9s " + ANSI_RESET +
                "|" + ANSI_CYAN + " %-15s " + ANSI_RESET + "|" + ANSI_CYAN + " %-13s " + ANSI_RESET + "|\n";

        System.out.println("__________________________________________________________________________________________________________________________________________________");
        System.out.printf(formattingString, "ID", "Full Name", "Phone Number", "Nationality", "Passport", "Reserved Seats", "Status", "Origin", "Destination");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Reservation reservation : reservationList) {
            System.out.printf(formattingStringTableContent, reservation.getReservationID(), reservation.getFullName(), reservation.getPhoneNumber(), reservation.getNationality(), reservation.getPassport(), reservation.getSeatNumber(), reservation.getStatus(), reservation.getOrigin(), reservation.getDestination());
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    //_______________________________________________________________________________________________________SELECT METHODS:
    // Selects Everything From the Reservation Table.
    public List<Reservation> selectEverythingFromReservations() {
        String sqlQuery = "SELECT * FROM reservations, users, flights WHERE reservations.user_id=users.user_id AND reservations.flight_id = flight_java.flights.flight_id;";
        List<Reservation> reservationList = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservations.user_id"),
                        fullName,
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("nationality"),
                        resultSet.getString("passport"),
                        resultSet.getInt("seat_number"),
                        resultSet.getString("status"),
                        resultSet.getInt("flight_number"),
                        resultSet.getString("origin"),
                        resultSet.getString("destination")
                );
                reservation.setReservationID(resultSet.getInt("reservation_id"));

                reservationList.add(reservation);
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationList;
    }

    // Selects Everything From the Reservation Table with the given Reservation ID.
    public List<Reservation> selectEverythingFromReservations(int reservationID) {
        String sqlQuery = "SELECT * FROM reservations, users, flights WHERE reservation_id = ?;";
        List<Reservation> reservationList = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, reservationID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservations.user_id"),
                        fullName,
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("nationality"),
                        resultSet.getString("passport"),
                        resultSet.getInt("seat_number"),
                        resultSet.getString("status"),
                        resultSet.getInt("flight_number"),
                        resultSet.getString("origin"),
                        resultSet.getString("destination")
                );
                reservation.setReservationID(resultSet.getInt("reservation_id"));

                reservationList.add(reservation);
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationList;
    }

    // Selects Everything From the Reservation Table with the given User ID & Flight ID.
    public List<Reservation> selectEverythingFromReservations(int userID, int flightID) {
        String sqlQuery = "SELECT * FROM reservations, users, flights WHERE reservations.user_id = ? AND reservations.flight_id = ?;";
        List<Reservation> reservationList = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, flightID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservations.user_id"),
                        fullName,
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("nationality"),
                        resultSet.getString("passport"),
                        resultSet.getInt("seat_number"),
                        resultSet.getString("status"),
                        resultSet.getInt("flight_number"),
                        resultSet.getString("origin"),
                        resultSet.getString("destination")
                );
                reservation.setReservationID(resultSet.getInt("reservation_id"));

                reservationList.add(reservation);
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationList;
    }

    // Selects Everything From the Reservation Table with the given User ID.
    public List<Reservation> selectAllUserReservations(int userID) {
        String sqlQuery = "SELECT * FROM reservations " +
                "JOIN users ON reservations.user_id = users.user_id " +
                "JOIN flights ON reservations.flight_id = flights.flight_id " +
                "WHERE reservations.user_id = ?;";
        List<Reservation> reservationList = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservations.user_id"),
                        fullName,
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("nationality"),
                        resultSet.getString("passport"),
                        resultSet.getInt("seat_number"),
                        resultSet.getString("status"),
                        resultSet.getInt("flight_number"),
                        resultSet.getString("origin"),
                        resultSet.getString("destination")
                );
                reservation.setReservationID(resultSet.getInt("reservation_id"));

                reservationList.add(reservation);
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationList;
    }

    // Selects Everything From the Reservation Table and Paginate.
    public List<Reservation> selectEverythingFromReservationsInRangeOf(int min, int offset) {
        String sqlQuery = "SELECT * FROM reservations, users, flights WHERE reservations.user_id=users.user_id AND reservations.flight_id = flight_java.flights.flight_id LIMIT ? OFFSET ?;";
        List<Reservation> reservationList = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservations.user_id"),
                        fullName,
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("nationality"),
                        resultSet.getString("passport"),
                        resultSet.getInt("seat_number"),
                        resultSet.getString("status"),
                        resultSet.getInt("flight_number"),
                        resultSet.getString("origin"),
                        resultSet.getString("destination")
                );
                reservation.setReservationID(resultSet.getInt("reservation_id"));

                reservationList.add(reservation);
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationList;
    }

    // Selects Everything From the Reservation Table with given User ID and Paginate.
    public List<Reservation> selectEverythingFromReservationsWithUserIdInRangeOf(int min, int offset, int userID) {
        String sqlQuery = "SELECT * FROM reservations, users, flights WHERE reservations.user_id = ? AND reservations.user_id=users.user_id AND reservations.flight_id = flight_java.flights.flight_id LIMIT ? OFFSET ?;";
        List<Reservation> reservationList = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, min);
            preparedStatement.setInt(3, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservations.user_id"),
                        fullName,
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("nationality"),
                        resultSet.getString("passport"),
                        resultSet.getInt("seat_number"),
                        resultSet.getString("status"),
                        resultSet.getInt("flight_number"),
                        resultSet.getString("origin"),
                        resultSet.getString("destination")
                );
                reservation.setReservationID(resultSet.getInt("reservation_id"));

                reservationList.add(reservation);
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationList;
    }

    // Get the Reservation ID.
    public int getReservationIDFromTuple(Reservation reservation) {
        String sqlQuery = "SELECT * FROM reservations, users, flights WHERE reservations.user_id = ? AND reservations.flight_id = ?;";
        int reservationID = 0;
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, reservation.getUserID());
            preparedStatement.setInt(2, reservation.getFlightID());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                reservationID = resultSet.getInt("reservation_id");
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationID;

    }

    // Get the total number of Reservations
    public int totalNumberOfReservations() {
        int totalReservations = 0;
        String sqlQuery = "SELECT COUNT(*) AS totalNumberOfReservations FROM reservations, users, flights WHERE reservations.user_id=users.user_id AND reservations.flight_id = flight_java.flights.flight_id;";
        List<Reservation> reservationList = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalReservations = resultSet.getInt("totalNumberOfReservations");
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalReservations;
    }

    //_______________________________________________________________________________________________________INSERTION METHODS:
    // Create a new Reservation
    public void insertIntoReservationsAll(Reservation reservation) {
        String sqlQuery = "INSERT INTO reservations (seat_number, status, user_id, flight_id) VALUES (?,?,?,?)";
        try {
            // Establish Connection.
            Connection connection = MySQLConnector.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, reservation.getSeatNumber());
            preparedStatement.setString(2, reservation.getStatus());
            preparedStatement.setInt(3, reservation.getUserID());
            preparedStatement.setInt(4, reservation.getFlightID());

            // Execute the Query to INSERT the data INTO the Database TABLE
            preparedStatement.executeUpdate();

            // Close Connection
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //_______________________________________________________________________________________________________UPDATE METHODS:
    // Update Reservation Status
    public void updateIntoReservations(int reservationID, String status) {
        String sqlUpdateQuery = "UPDATE reservations SET status = ? WHERE reservation_id = ?;";
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateQuery);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, reservationID);

            // Execute the Query to UPDATE TABLE with new Values
            preparedStatement.executeUpdate();

            // Close Connection
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update the Number of Seats Reserved.
    public void updateIntoReservations(int reservationID, int seatNumber) {
        String sqlUpdateQuery = "UPDATE reservations SET seat_number = ? WHERE reservation_id = ?;";
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateQuery);
            preparedStatement.setInt(1, seatNumber);
            preparedStatement.setInt(2, reservationID);

            // Execute the Query to UPDATE TABLE with new Values
            preparedStatement.executeUpdate();

            // Close Connection
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update all Reservation Details.
    public void updateIntoReservations(Reservation reservation) {
        String sqlUpdateQuery = "UPDATE reservations SET seat_number= ?, status = ?, user_id = ?, flight_id = ? WHERE reservation_id = ?;";
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateQuery);

            preparedStatement.setInt(1, reservation.getSeatNumber());
            preparedStatement.setString(2, reservation.getStatus());
            preparedStatement.setInt(3, reservation.getUserID());
            preparedStatement.setInt(4, reservation.getFlightID());
            preparedStatement.setInt(5, reservation.getReservationID());

            // Execute the Query to UPDATE TABLE with new Values
            preparedStatement.executeUpdate();

            // Close Connection
            MySQLConnector.closeConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //_______________________________________________________________________________________________________DELETION METHODS:
    // Remove a Reservation Using its ID
    public void removeReservation(int reservationID) {
        String sqlUpdateQuery = "DELETE FROM reservations WHERE reservation_id = ?;";
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateQuery);

            preparedStatement.setInt(1, reservationID);
            // Execute the Query to UPDATE TABLE with new Values
            preparedStatement.executeUpdate();
            // Close Connection
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //_______________________________________________________________________________________________________DATA TABULATION METHODS:
    public void printReservationsAvailableInTableForm() {
        List<Reservation> reservationList = this.selectEverythingFromReservations();
        this.printFormat(reservationList);
    }

    public void printReservationsAvailableInTableForm(int id) {
        List<Reservation> reservationList = this.selectEverythingFromReservations(id);
        this.printFormat(reservationList);
    }

    public void printReservationsAvailableInTableForm(int min, int max) {
        List<Reservation> reservationList = this.selectEverythingFromReservationsInRangeOf(min, max);
        this.printFormat(reservationList);
    }

    public void printUserReservationsAvailableInTableForm(int userID) {
        List<Reservation> reservationList = this.selectAllUserReservations(userID);
        this.printFormat(reservationList);
    }

    public void printUserReservationsAvailableInTableForm(int min, int offset, int userID) {
        List<Reservation> reservationList = this.selectEverythingFromReservationsWithUserIdInRangeOf(min, offset, userID);
        this.printFormat(reservationList);
    }
}
