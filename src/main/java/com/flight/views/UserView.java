package com.flight.views;

import com.flight.databaseaccessobject.FlightDAO;
import com.flight.databaseaccessobject.ReservationsDAO;
import com.flight.databaseaccessobject.UsersDAO;
import com.flight.dataclass.Reservation;
import com.flight.dataclass.User;
import java.util.Scanner;

public class UserView {
    private final String formatter = "%-50s";
    private int userID;

    //COLOR SCHEMES
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    //------------------------------------------------------------------------------------CREATE NEW USER
    public void signUpNewUser() {
        Scanner input = new Scanner(System.in);
        System.out.println(ANSI_PURPLE + "Please Enter Your Details to Sign Up." + ANSI_RESET);
        System.out.printf(formatter, "Enter your First Name: ");
        String firstName = input.nextLine();
        System.out.printf(formatter, "Enter your Last Name: ");
        String lastName = input.nextLine();
        System.out.printf(formatter, "Enter your Phone Number (e.g. +260987654321): ");
        String phoneNumber = input.nextLine();
        System.out.printf(formatter, "Enter your Email (e.g example@email.com): ");
        String email = input.nextLine();
        System.out.printf(formatter, "Enter your Nationality: ");
        String nationality = input.nextLine();
        System.out.printf(formatter, "Enter your Passport Number: ");
        String passport = input.nextLine();

        String password = "";
        while (password == "") {
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

        System.out.println(ANSI_GREEN + "Sign Up Successful!" + ANSI_RESET);
        this.userID = usersDAO.login(email, password);
        System.out.println(ANSI_GREEN + "Automatically Logged in." + ANSI_RESET);

    }

    //------------------------------------------------------------------------------------LOG USER IN
    public void logUserIn() {
        Scanner input = new Scanner(System.in);
        int numberOfAttempts = 0;

        while (this.userID == 0) {
            System.out.println(ANSI_PURPLE + "Please Enter Your Credentials to Log in." + ANSI_RESET);
            System.out.printf(formatter, "Enter your Email (e.g example@email.com): ");
            String email = input.nextLine();

            System.out.printf(formatter, "Enter your Password: ");
            String password = input.nextLine();

            UsersDAO usersDAO = new UsersDAO();
            this.userID = usersDAO.login(email, password);
            numberOfAttempts++;
            if (this.userID == 0 && numberOfAttempts < 3) {
                System.out.println(ANSI_YELLOW + "\nNumber of Attempts remaining: " + (3 - numberOfAttempts) + ANSI_RESET);
                System.out.println(ANSI_RED + "Incorrect Login credentials. Please try again...\n" + ANSI_RESET);
            } else if (this.userID > 0) {
                System.out.println(ANSI_GREEN + "\nLogged in Successfully.\n" + ANSI_RESET);
            } else {
                System.exit(0);
            }
        }


    }

    //------------------------------------------------------------------------------------UPDATE USER
    public void updateUserContactDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println(ANSI_PURPLE + "Update Contact Details" + ANSI_RESET);
        int userID;
        if (this.userID == 0) {
            System.out.printf(formatter, "Enter your User ID: ");
            userID = input.nextInt();
            input.nextLine(); // Consume the newline character
        } else {
            userID = this.userID;
        }
        System.out.printf(formatter, "Enter your Phone Number (e.g +260987654321): ");
        String phoneNumber = input.nextLine();

        System.out.printf(formatter, "Enter your Email (e.g example@email.com): ");
        String email = input.nextLine();

        UsersDAO usersDAO = new UsersDAO();
        usersDAO.updateIntoUser(userID, phoneNumber, email);
    }

    public void updateUserDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println(ANSI_PURPLE + "Update Particulars." + ANSI_RESET);
        int userID;
        if (this.userID == 0) {
            System.out.printf(formatter, "Enter your User ID: ");
            userID = input.nextInt();
            input.nextLine(); // Consume the newline character
        } else {
            userID = this.userID;
        }
        System.out.printf(formatter, "Enter your First Name: ");
        String firstName = input.nextLine();
        System.out.printf(formatter, "Enter your Last Name: ");
        String lastName = input.nextLine();

        UsersDAO usersDAO = new UsersDAO();
        usersDAO.updateIntoUser(firstName, lastName, userID);
    }

    public void updateAllUserDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println(ANSI_PURPLE + "Update Every User Details." + ANSI_RESET);
        int userID;
        if (this.userID == 0) {
            System.out.printf(formatter, "Enter your User ID: ");
            userID = input.nextInt();
            input.nextLine(); // Consume the newline character
        } else {
            userID = this.userID;
        }
        System.out.printf(formatter, "Enter your First Name: ");
        String firstName = input.nextLine();
        System.out.printf(formatter, "Enter your Last Name: ");
        String lastName = input.nextLine();
        System.out.printf(formatter, "Enter your Phone Number (e.g. +260987654321): ");
        String phoneNumber = input.nextLine();
        System.out.printf(formatter, "Enter your Email (e.g example@email.com): ");
        String email = input.nextLine();
        System.out.printf(formatter, "Enter your Nationality: ");
        String nationality = input.nextLine();
        System.out.printf(formatter, "Enter your Passport Number: ");
        String passport = input.nextLine();
        System.out.printf(formatter, "Enter your Password: ");
        String password = input.nextLine();


        User user = new User(userID, firstName, lastName, phoneNumber, email, nationality, passport, password);

        UsersDAO usersDAO = new UsersDAO();
        usersDAO.updateIntoUser(user);

        input.close();
    }

    //------------------------------------------------------------------------------------GET USER DETAILS
    public void getUserDetails() {
        if (this.userID != 0) {
            System.out.println(ANSI_PURPLE + "User Details." + ANSI_RESET);
            UsersDAO usersDAO = new UsersDAO();
            usersDAO.printUsersAvailableInTableForm(this.userID);
        } else {
            System.out.println("Something is Wrong.");
        }
    }


    //------------------------------------------------------------------------------------USER MAKE RESERVATIONS
    public void makeReservations() {
        Scanner input = new Scanner(System.in);
        int userID;
        if (this.userID == 0) {
            System.out.printf(formatter, "Enter your User ID: ");
            userID = input.nextInt();
            input.nextLine(); // Consume the newline character
        } else {
            userID = this.userID;
        }
        System.out.printf(formatter, "Enter the Number of seats to be Reserved: ");
        int seatNumber = input.nextInt();
        input.nextLine(); // Consume the newline character

        // The Status Defaults to Pending
        String status = "Pending";

        // Enter the Flight ID (Not to be mistaken for Flight Number.)
        System.out.printf(formatter, "Enter Flight ID: ");
        int flightID = input.nextInt();
        input.nextLine(); // Consume the newline character

        // Add the data for Processing.
        Reservation reservation = new Reservation(seatNumber, status, userID, flightID);
        ReservationsDAO reservationsDAO = new ReservationsDAO();
        reservationsDAO.insertIntoReservationsAll(reservation);
    }

    public void getAllMyReservations() {
        if (this.userID != 0) {
            Scanner input = new Scanner(System.in);
            int currentPageNumber = 0;
            int rowsPerPage = 4;
            ReservationsDAO reservationsDAO = new ReservationsDAO();
            int totalReservations = reservationsDAO.totalNumberOfReservations();
            int totalPages = (int) Math.ceil((double) totalReservations / rowsPerPage);

            while (true) {
                int offset = rowsPerPage * currentPageNumber;
                reservationsDAO.printUserReservationsAvailableInTableForm(rowsPerPage, offset, this.userID);

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
                } else if (choice.equals("p") && currentPageNumber > 0) {
                    currentPageNumber--;
                } else if (choice.equals("exit")) {
                    break;
                } else {
                    System.out.println("Something Wrong. Please Try again");
                }
            }
        } else {
            System.out.println("Something is Wrong.");
        }
    }

    public void updateReservation() {
        Scanner input = new Scanner(System.in);
        int userID;

        System.out.printf(formatter, "Enter the Reservation ID: ");
        int reservationID = input.nextInt();
        input.nextLine(); // Consume the newline character

        if (this.userID == 0) {
            System.out.printf(formatter, "Enter your User ID: ");
            userID = input.nextInt();
            input.nextLine(); // Consume the newline character
        } else {
            userID = this.userID;
        }

        System.out.printf(formatter, "Enter the Number of seats to be Reserved: ");
        int seatNumber = input.nextInt();
        input.nextLine(); // Consume the newline character

        // The Status Defaults to Pending
        String status = "Pending";

        // Enter the Flight ID (Not to be mistaken for Flight Number.)
        System.out.printf(formatter, "Enter Flight ID: ");
        int flightID = input.nextInt();
        input.nextLine(); // Consume the newline character

        // Add the data for Processing.
        Reservation reservation = new Reservation(reservationID, seatNumber, status, userID, flightID);
        ReservationsDAO reservationsDAO = new ReservationsDAO();
        reservationsDAO.updateIntoReservations(reservation);
    }

    //------------------------------------------------------------------------------------USER VIEW SECTIONS
    public void manageUserAccount() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions for Managing User Account: ");
            System.out.println("1. See Your Profile ");
            System.out.println("2. Update Your Contact Details ");
            System.out.println("3. Update Your Names");
            System.out.println("4. Update Everything");
            System.out.println("5. Back");


            int manage = input.nextInt();
            input.nextLine(); // Consume the newline character

            if (manage == 1) {
                this.getUserDetails();
            } else if (manage == 2) {
                this.updateUserContactDetails();
            } else if (manage == 3) {
                this.updateUserDetails();
            } else if (manage == 4) {
                this.updateAllUserDetails();
            } else if (manage == 5) {
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
            System.out.println("1. Make a Reservation");
            System.out.println("2. Update Your Reservations ");
            System.out.println("3. See Your Reservations ");
            System.out.println("4. Back");

            int manage = input.nextInt();
            input.nextLine(); // Consume the newline character

            if (manage == 1) {
                this.makeReservations();
            } else if (manage == 2) {
                this.updateReservation();
            } else if (manage == 3) {
                this.getAllMyReservations();
            } else if (manage == 4) {
                break;
            } else {
                System.out.println("Incorrect Input Value, please try again");
            }
        }
    }

    public void searchFlights() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Origin (or leave blank to skip): ");
        String origin = input.nextLine();
        System.out.println("Enter Destination (or leave blank to skip): ");
        String destination = input.nextLine();

        FlightDAO flightDAO = new FlightDAO();
        flightDAO.printSearchResultsInTableForm(origin, destination);
    }

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
            } else if (choice.equals("p") && currentPageNumber > 0) {
                currentPageNumber--;
            } else if (choice.equals("exit")) {
                break;
            } else {
                System.out.println("Something Wrong. Please Try again");
            }
        }
    }

    //------------------------------------------------------------------------------------FULL LOGIC
    public void userPresentationInteraction() {
        Scanner input = new Scanner(System.in);
        int loginOrSignUp = 0;

        while (loginOrSignUp != 1 && loginOrSignUp != 2) {
            System.out.println(ANSI_BLUE + "Welcome, Select Option:" + ANSI_RESET);
            System.out.println("1. Sign Up");
            System.out.println("2. Log in");
            loginOrSignUp = input.nextInt();
            input.nextLine(); // Consume the newline character
            if (loginOrSignUp == 2) {
                this.logUserIn();
            } else if (loginOrSignUp == 1) {
                this.signUpNewUser();
            } else {
                System.out.println(ANSI_RED + "Incorrect input. Please try again...." + ANSI_RESET);
            }
        }

        while (true) {
            System.out.println(ANSI_PURPLE + "Select one of the Option: " + ANSI_RESET);
            System.out.println("1. Manage Account.");
            System.out.println("2. Manage Reservations.");
            System.out.println("3. View All Flights.");
            System.out.println("4. Search for Flight.");
            System.out.println("5. exit.");

            int firstPageDecider = input.nextInt();
            input.nextLine(); // Consume the newline character

            if (firstPageDecider == 1) {
                this.manageUserAccount();
            } else if (firstPageDecider == 2) {
                this.manageReservations();
            } else if (firstPageDecider == 3) {
                this.viewAllFlights();
            } else if (firstPageDecider == 4) {
                this.searchFlights();
            } else if (firstPageDecider == 5) {
                System.exit(0);
            } else {
                System.out.println(ANSI_RED + "Incorrect Input Value, please try again" + ANSI_RESET);
            }
        }

    }

}
