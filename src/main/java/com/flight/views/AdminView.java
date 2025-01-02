package com.flight.views;

import com.flight.databaseaccessobject.FlightDAO;
import com.flight.databaseaccessobject.ReservationsDAO;
import com.flight.databaseaccessobject.UsersDAO;
import com.flight.dataclass.Flight;
import com.flight.dataclass.Reservation;
import com.flight.dataclass.User;

import java.sql.Time;
import java.util.Scanner;

public class AdminView {
    private final String formatter = "%-50s";

    //------------------------------------------------------------------------------------MANAGE USERS
    public void viewUserDetailsPaginated() {
        Scanner input = new Scanner(System.in);
        int currentPageNumber = 0;
        int rowsPerPage = 4;
        UsersDAO usersDAO = new UsersDAO();
        int totalNumberOfUsers = usersDAO.totalNumberOfUsers();
        int totalPages = (int) Math.ceil((double) totalNumberOfUsers / rowsPerPage);

        while (true) {
            int offset = rowsPerPage * currentPageNumber;
            usersDAO.printUsersAvailableInTableForm(rowsPerPage, offset);
            if (offset != 0) {
                System.out.println("p. Previous");
            }

            if (currentPageNumber < (totalPages - 1)) {
                System.out.println("n. Next");
            }
            System.out.println("exit. To Exit");

            String choice = input.nextLine();
            if (choice.equals("n") && currentPageNumber < (totalPages - 1)) {
                currentPageNumber++;
                offset = rowsPerPage * currentPageNumber;
            } else if (choice.equals("p") && currentPageNumber > 0) {
                currentPageNumber--;
                offset = rowsPerPage * currentPageNumber;
            } else if (choice.equals("exit")) {
                break;
            } else {
                System.out.println("Something Wrong. Please Try again");
            }
        }
    }

    public void updateUserParticulars() {
        Scanner input = new Scanner(System.in);
        UsersDAO usersDAO = new UsersDAO();
        System.out.printf(formatter, "Enter User ID to Update: ");
        int userID = input.nextInt();
        input.nextLine(); // Consume the newline character
        System.out.printf(formatter, "Enter new First Name: ");
        String firstName = input.nextLine();
        System.out.printf(formatter, "Enter new Last Name: ");
        String lastName = input.nextLine();
        usersDAO.updateIntoUser(firstName, lastName, userID);
    }

    public void createNewUser() {
        Scanner input = new Scanner(System.in);
        System.out.printf(formatter, "Enter First Name: ");
        String firstName = input.nextLine();
        System.out.printf(formatter, "Enter Last Name: ");
        String lastName = input.nextLine();
        System.out.printf(formatter, "Enter Phone Number (e.g. +260987654321): ");
        String phoneNumber = input.nextLine();
        System.out.printf(formatter, "Enter Email (e.g example@email.com): ");
        String email = input.nextLine();
        System.out.printf(formatter, "Enter Nationality: ");
        String nationality = input.nextLine();
        System.out.printf(formatter, "Enter Passport Number: ");
        String passport = input.nextLine();

        String password = "";
        while (password.isEmpty()) {
            System.out.printf(formatter, "Enter your Password: ");
            String password1 = input.nextLine();
            System.out.printf(formatter, "Enter your Password Again to Confirm: ");
            String password2 = input.nextLine();

            if (password1.equals(password2)) {
                password = password1;
            } else {
                System.out.println("\nPasswords entered did not match, Please Try again");
            }

        }
        User user = new User(firstName, lastName, phoneNumber, email, nationality, passport, password);

        UsersDAO usersDAO = new UsersDAO();
        usersDAO.insertIntoUsersAll(user);

        System.out.println("User Created Successfully!");
    }

    //------------------------------------------------------------------------------------MANAGE RESERVATIONS
    public void viewAllReservationsPaginated() {
        Scanner input = new Scanner(System.in);
        int currentPageNumber = 0;
        int rowsPerPage = 4;
        ReservationsDAO reservationsDAO = new ReservationsDAO();
        int totalReservations = reservationsDAO.totalNumberOfReservations();
        int totalPages = (int) Math.ceil((double) totalReservations / rowsPerPage);

        while (true) {
            int offset = rowsPerPage * currentPageNumber;
            reservationsDAO.printReservationsAvailableInTableForm(rowsPerPage, offset);
            System.out.println("Range is " + rowsPerPage + ", " + offset);
            if (offset != 0) {
                System.out.println("p. Previous");
            }

            if (currentPageNumber < (totalPages - 1)) {
                System.out.println("n. Next");
            }
            System.out.println("exit. To Exit");

            String choice = input.nextLine();
            if (choice.equals("n") && currentPageNumber < (totalPages - 1)) {
                currentPageNumber++;
                offset = rowsPerPage * currentPageNumber;
            } else if (choice.equals("p") && currentPageNumber > 0) {
                currentPageNumber--;
                offset = rowsPerPage * currentPageNumber;
            } else if (choice.equals("exit")) {
                break;
            } else {
                System.out.println("Something Wrong. Please Try again");
            }
        }
    }

    public void updateReservationsDetails() {
        Scanner input = new Scanner(System.in);
        ReservationsDAO reservationsDAO = new ReservationsDAO();

        System.out.printf(formatter, "Enter Reservation ID to Update: ");
        int reservationID = input.nextInt();
        input.nextLine(); // Consume the newline character
        System.out.printf(formatter, "Enter new Number of Seats: ");
        int seatNumber = input.nextInt();
        input.nextLine(); // Consume the newline character

        System.out.printf(formatter, "Enter the reservation Status");
        System.out.println("\nOptions\n----------------------");
        System.out.println("1. pending");
        System.out.println("2. approved");
        System.out.println("3. declined");
        int selector = input.nextInt();
        boolean decidor = true;
        String status;
        //Defaults to "Pending"
        status = "pending";
        while (decidor) {
            if (selector == 1) {
                decidor = false;
            } else if (selector == 2) {
                status = "approved";
                decidor = false;
            } else if (selector == 3) {
                status = "declined";
                decidor = false;
            } else {
                System.out.println("Something is Wrong, Please Try again...\n");
            }
        }
        System.out.printf(formatter, "Enter new User ID: ");
        int userID = input.nextInt();
        input.nextLine(); // Consume the newline character
        System.out.printf(formatter, "Enter new Flight ID: ");
        int flightID = input.nextInt();
        input.nextLine(); // Consume the newline character
        Reservation reservation = new Reservation(reservationID, seatNumber, status, userID, flightID);
        reservationsDAO.updateIntoReservations(reservation);
    }

    public void createReservation() {
        Scanner input = new Scanner(System.in);
        int userID;
        System.out.printf(formatter, "Enter your User ID: ");
        userID = input.nextInt();
        input.nextLine(); // Consume the newline character
        System.out.printf(formatter, "Enter the Number of seats to be Reserved: ");
        int seatNumber = input.nextInt();
        input.nextLine(); // Consume the newline character

        System.out.printf(formatter, "Enter the reservation Status\n");
        System.out.println("1. pending");
        System.out.println("2. approved");
        System.out.println("3. declined");
        int selector = input.nextInt();
        boolean decidor = true;
        String status;
        //Defaults to "Pending"
        status = "pending";
        while (decidor) {
            if (selector == 1) {
                decidor = false;
            } else if (selector == 2) {
                status = "approved";
                decidor = false;
            } else if (selector == 3) {
                status = "declined";
                decidor = false;
            } else {
                System.out.println("Something is Wrong, Please Try again...\n");
            }
        }
        // Enter the Flight ID (Not to be mistaken for Flight Number.)
        System.out.printf(formatter, "Enter Flight ID: ");
        int flightID = input.nextInt();
        input.nextLine(); // Consume the newline character

        // Add the data for Processing.
        Reservation reservation = new Reservation(seatNumber, status, userID, flightID);
        ReservationsDAO reservationsDAO = new ReservationsDAO();
        reservationsDAO.insertIntoReservationsAll(reservation);
    }

    //------------------------------------------------------------------------------------MANAGE FLIGHTS
    public void viewAllFlights() {
        Scanner input = new Scanner(System.in);
        int currentPageNumber = 0;
        int rowsPerPage = 4;
        FlightDAO flightDAO = new FlightDAO();
        int totalNumberOfFlights = flightDAO.totalNumberOfFlights();
        int totalPages = (int) Math.ceil((double) totalNumberOfFlights / rowsPerPage);

        while (true) {
            int offset = rowsPerPage * currentPageNumber;
            flightDAO.printFlightsAvailableInTableForm(rowsPerPage, offset);
            if (offset != 0) {
                System.out.println("p. Previous");
            }

            if (currentPageNumber < (totalPages - 1)) {
                System.out.println("n. Next");
            }
            System.out.println("exit. To Exit");

            String choice = input.nextLine();
            if (choice.equals("n") && currentPageNumber < (totalPages - 1)) {
                currentPageNumber++;
                offset = rowsPerPage * currentPageNumber;
            } else if (choice.equals("p") && currentPageNumber > 0) {
                currentPageNumber--;
                offset = rowsPerPage * currentPageNumber;
            } else if (choice.equals("exit")) {
                break;
            } else {
                System.out.println("Something Wrong. Please Try again");
            }
        }
    }

    public void updateFlightRoutes() {
        Scanner input = new Scanner(System.in);
        FlightDAO flightDAO = new FlightDAO();

        System.out.printf(formatter, "Enter Flight ID to Update: ");
        int flightID = input.nextInt();
        input.nextLine(); // Consume the newline character

        System.out.printf(formatter, "Enter your Origin: ");
        String origin = input.nextLine();

        System.out.printf(formatter, "Enter your Destination: ");
        String destination = input.nextLine();

        flightDAO.updateIntoFlights(flightID, origin, destination);
    }

    public void updateFlightTimes() {
        Scanner input = new Scanner(System.in);
        FlightDAO flightDAO = new FlightDAO();

        System.out.printf(formatter, "Enter Flight ID to Update: ");
        int flightID = input.nextInt();
        input.nextLine(); // Consume the newline character
        String departureTime;
        System.out.printf(formatter, "Enter the departure Time (e.g 08:00:00): ");
        departureTime = input.nextLine();

        String arrivalTime;
        System.out.printf(formatter, "Enter the Arrival Time (e.g 08:00:00): ");
        arrivalTime = input.nextLine();
//        while (true){
//            System.out.printf(formatter, "Enter the departure Time (e.g 08:00:00): ");
//            departureTime = input.nextLine();
//
//            System.out.printf(formatter, "Enter the Arrival Time (e.g 08:00:00): ");
//            arrivalTime = input.nextLine();
//            Time time1 =  Time.valueOf(departureTime);
//            Time time2 =  Time.valueOf(arrivalTime);
//
//            if (time1.before(time2)){
//                break;
//            } else if (time1.after(time2)) {
//                System.out.println("Arrival Time can not be before Departure Time, Please Try Again...");
//            } else {
//                System.out.println("Something is Wrong, please try again");
//            }
//        }

        flightDAO.updateIntoFlights(departureTime, arrivalTime, flightID);
    }

    public void createFlight() {
        Scanner input = new Scanner(System.in);
        FlightDAO flightDAO = new FlightDAO();

        System.out.printf(formatter, "Enter Flight Number: ");
        int flightNumber = input.nextInt();
        input.nextLine(); // Consume the newline character

        System.out.printf(formatter, "Enter the Origin: ");
        String origin = input.nextLine();

        System.out.printf(formatter, "Enter the Destination: ");
        String destination = input.nextLine();

        System.out.printf(formatter, "Enter the departure Time (e.g 08:00:00): ");
        String departureTime = input.nextLine();


        System.out.printf(formatter, "Enter the Arrival Time (e.g 08:00:00): ");
        String arrivalTime = input.nextLine();

        System.out.printf(formatter, "Enter Available Seats: ");
        int availableSeats = input.nextInt();
        input.nextLine(); // Consume the newline character

        System.out.printf(formatter, "Enter Total Number of Seats: ");
        int totalSeats = input.nextInt();
        input.nextLine(); // Consume the newline character

        Flight flight = new Flight(flightNumber, origin, destination, Time.valueOf(departureTime), Time.valueOf(arrivalTime), availableSeats, totalSeats);
        flightDAO.insertIntoFlightsAll(flight);
    }

    //------------------------------------------------------------------------------------ADMIN VIEW SECTION

    public void manageFlights() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions for Managing Flights: ");
            System.out.println("1. View All Flights");
            System.out.println("2. Add New Flight");
            System.out.println("3. Update Flight Routes");
            System.out.println("4. Update Flight Time Co 0rdinates");
            System.out.println("5. Back");

            int manage = input.nextInt();
            input.nextLine(); // Consume the newline character

            if (manage == 1) {
                this.viewAllFlights();
            } else if (manage == 2) {
                this.createFlight();
            } else if (manage == 3) {
                this.updateFlightRoutes();
            } else if (manage == 4) {
                this.updateFlightTimes();
            } else if (manage == 5) {
                break;
            } else {
                System.out.println("Incorrect Input Value, please try again");
            }
        }
    }

    public void manageUsers() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions for Managing Users: ");
            System.out.println("1. View All Users");
            System.out.println("2. Update your Particular Details");
            System.out.println("3. Create New User");
            System.out.println("4. Back");

            int manage = input.nextInt();
            input.nextLine(); // Consume the newline character

            if (manage == 1) {
                this.viewUserDetailsPaginated();
            } else if (manage == 2) {
                this.updateUserParticulars();
            } else if (manage == 3) {
                this.createNewUser();
            } else if (manage == 4) {
                break;
            } else {
                System.out.println("Incorrect Input Value, please try again");
            }
        }
    }

    public void manageReservations() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions for Managing Reservations: ");
            System.out.println("1. View All Reservations");
            System.out.println("2. Update Reservation");
            System.out.println("3. Create Reservation");
            System.out.println("4. Back");

            int manage = input.nextInt();
            input.nextLine(); // Consume the newline character

            if (manage == 1) {
                this.viewAllReservationsPaginated();
            } else if (manage == 2) {
                this.updateReservationsDetails();
            } else if (manage == 3) {
                this.createReservation();
            } else if (manage == 4) {
                break;
            } else {
                System.out.println("Incorrect Input Value, please try again");
            }
        }
    }

    //------------------------------------------------------------------------------------ADMIN INTERACTION
    public void adminPresentationInteraction() {
        Scanner input = new Scanner(System.in);
        int firstPageDecider = 0;
        while (true) {
            System.out.println("Select one of the Option: ");
            System.out.println("1. Manage Users.");
            System.out.println("2. Manage Reservations.");
            System.out.println("3. Manage Flights.");
            System.out.println("4. Exit.");

            firstPageDecider = input.nextInt();
            input.nextLine(); // Consume the newline character

            if (firstPageDecider == 1) {
                this.manageUsers();
            } else if (firstPageDecider == 2) {
                this.manageReservations();
            } else if (firstPageDecider == 3) {
                this.manageFlights();
            } else if (firstPageDecider == 4) {
                System.exit(0);
            } else {
                System.out.println("Incorrect Input Value, please try again");
            }
        }
    }
}