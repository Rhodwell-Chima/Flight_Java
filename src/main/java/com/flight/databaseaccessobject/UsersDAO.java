package com.flight.databaseaccessobject;

import com.flight.connector.MySQLConnector;
import com.flight.dataclass.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    //COLOR SCHEMES
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";


    //________________________________________________________________________________________________PRINT METHODS:
    private void printFormat(List<User> userList) {
        // Formatting the String:
        String formattingString = "|" + ANSI_GREEN + " %-7s " + ANSI_RESET + "|" + ANSI_GREEN + " %-15s " + ANSI_RESET + "|" + ANSI_GREEN + " %-15s " + ANSI_RESET +
                "|" + ANSI_GREEN + " %-13s " + ANSI_RESET + "|" + ANSI_GREEN + " %-35s " + ANSI_RESET + "|" + ANSI_GREEN + " %-15s " + ANSI_RESET + "|" + ANSI_GREEN + " %-17s " + ANSI_RESET + "|\n";

        String formattingStringTableContent = "|" + ANSI_CYAN + " %-7s " + ANSI_RESET + "|" + ANSI_CYAN + " %-15s " + ANSI_RESET + "|" + ANSI_CYAN + " %-15s " + ANSI_RESET +
                "|" + ANSI_CYAN + " %-13s " + ANSI_RESET + "|" + ANSI_CYAN + " %-35s " + ANSI_RESET + "|" + ANSI_CYAN + " %-15s " + ANSI_RESET + "|" + ANSI_CYAN + " %-17s " + ANSI_RESET + "|\n";

        System.out.println("___________________________________________________________________________________________________________________________________________");
        System.out.printf(formattingString, "User ID", "First Name", "Last Name", "Phone Number", "Email", "Nationality", "Passport");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        for (User user : userList) {
            System.out.printf(formattingStringTableContent, user.getUserID(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getEmail(), user.getNationality(), user.getPassport());
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    //________________________________________________________________________________________________SELECT METHODS:
    // Select Everything From Users Table.
    public List<User> selectEverythingFromUsers() {
        String sqlQuery = "SELECT * FROM flight_java.users;";
        List<User> usersList = new ArrayList<>();

        try (Connection connection = MySQLConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("nationality"),
                        resultSet.getString("passport")
                );

                usersList.add(user);
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }

    // Select Everything From Users Table using given User ID.
    public List<User> selectEverythingFromUsers(int userID) {
        String sqlQuery = "SELECT * FROM users WHERE user_id = ?;";
        List<User> usersList = new ArrayList<>();
        try (Connection connection = MySQLConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("nationality"),
                        resultSet.getString("passport")
                );

                usersList.add(user);
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }

    // Select Everything From Users Table and Paginate.
    public List<User> selectEverythingFromUsersInRangeOf(int min, int offset) {
        String sqlQuery = "SELECT * FROM users LIMIT ? OFFSET ?;";
        List<User> usersList = new ArrayList<>();
        try (Connection connection = MySQLConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("nationality"),
                        resultSet.getString("passport")
                );

                usersList.add(user);
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }

    // Get the Total Number of Users.
    public int totalNumberOfUsers() {
        int totalUsers = 0;
        String sqlSelectQuery = "SELECT COUNT(*) AS totalNumberOfUsers FROM users";
        try (Connection connection = MySQLConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalUsers = resultSet.getInt("totalNumberOfUsers");
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalUsers;
    }

    // Logic for Logging In a User.
    public int login(String email, String password) {
        int userID = 0;
        String sqlQuery = "SELECT * FROM users WHERE email = ? AND password = ?;";
        try (Connection connection = MySQLConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userID = resultSet.getInt("user_id");
            }
            // Close the Connection after querying
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userID;
    }

    //________________________________________________________________________________________________INSERTION METHODS:
    // Create a new User.
    public void insertIntoUsersAll(User user) {
        String sqlQuery = "INSERT INTO users (first_name, last_name, phone_number, email, nationality, passport, password) VALUES (?,?,?,?,?,?,?)";

        try (Connection connection = MySQLConnector.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);


            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getNationality());
            preparedStatement.setString(6, user.getPassport());
            preparedStatement.setString(7, user.getPassword());

            // Execute the Query to INSERT the data INTO the Database TABLE
            preparedStatement.executeUpdate();

            // Close Connection
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //________________________________________________________________________________________________UPDATE METHODS:
    // Update User Contact Details (Phone Number & Email)
    public void updateIntoUser(int userID, String phoneNumber, String email) {
        String sqlUpdateQuery = "UPDATE users SET phone_number = ?, email = ? WHERE user_id = ?;";
        try (Connection connection = MySQLConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateQuery);
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, userID);

            // Execute the Query to UPDATE TABLE with new Values
            preparedStatement.executeUpdate();

            // Close Connection
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update User Particulars (First Name & Last Name)
    public void updateIntoUser(String firstName, String lastName, int userID) {
        String sqlUpdateQuery = "UPDATE users SET first_name = ?, last_name = ? WHERE user_id = ?;";
        try (Connection connection = MySQLConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, userID);

            // Execute the Query to UPDATE TABLE with new Values
            preparedStatement.executeUpdate();

            // Close Connection
            MySQLConnector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update All User Details.
    public void updateIntoUser(User user) {
        String sqlUpdateQuery = "UPDATE users SET first_name = ?, last_name = ?, phone_number = ?, email = ?, nationality = ?, passport = ? WHERE user_id = ?;";
        try (Connection connection = MySQLConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateQuery);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getNationality());
            preparedStatement.setString(6, user.getPassport());
            preparedStatement.setInt(7, user.getUserID());

            // Execute the Query to UPDATE TABLE with new Values
            preparedStatement.executeUpdate();

            // Close Connection
            MySQLConnector.closeConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //________________________________________________________________________________________________DATA TABULATION METHODS:
    public void printUsersAvailableInTableForm() {
        List<User> userList = this.selectEverythingFromUsers();
        this.printFormat(userList);
    }

    public void printUsersAvailableInTableForm(int id) {
        List<User> userList = this.selectEverythingFromUsers(id);
        this.printFormat(userList);
    }

    public void printUsersAvailableInTableForm(int min, int offset) {
        List<User> userList = this.selectEverythingFromUsersInRangeOf(min, offset);
        this.printFormat(userList);
    }
}
