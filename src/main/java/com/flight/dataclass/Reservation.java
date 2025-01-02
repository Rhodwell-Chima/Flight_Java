package com.flight.dataclass;

public class Reservation {
    private int reservationID;
    private int seatNumber;
    private String status;
    private int userID;
    private int FlightID;


    private String fullName;
    private String phoneNumber;
    private String email;
    private String nationality;
    private String passport;


    private int flightNumber;
    private String origin;
    private String destination;

    private User user;
    private Flight flight;


    public Reservation(int reservationID, int seatNumber, String status, int userID, int FlightID) {
        this.reservationID = reservationID;
        this.seatNumber = seatNumber;
        this.status = status;
        this.userID = userID;
        this.FlightID = FlightID;
    }

    public Reservation(int seatNumber, String status, int userID, int FlightID) {
        this.seatNumber = seatNumber;
        this.status = status;
        this.userID = userID;
        this.FlightID = FlightID;
    }


    public Reservation(int reservationID, int seatNumber, String status, User user, Flight flight) {
        this.reservationID = reservationID;
        this.seatNumber = seatNumber;
        this.status = status;
        this.user = user;
        this.flight = flight;
    }

    public Reservation(int userID,
                       String fullName,
                       String phoneNumber,
                       String email,
                       String nationality,
                       String passport,
                       int seatNumber,
                       String status,
                       int flightNumber,
                       String origin,
                       String destination) {
        this.userID = userID;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nationality = nationality;
        this.passport = passport;
        this.seatNumber = seatNumber;
        this.status = status;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;

    }

    // Getter Methods
    public int getReservationID() {
        return reservationID;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getStatus() {
        return status;
    }

    public int getUserID() {
        return userID;
    }

    public int getFlightID() {
        return FlightID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPassport() {
        return passport;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public User getUser() {
        return user;
    }

    public Flight getFlight() {
        return flight;
    }

    // Setter Methods

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserID(int userId) {
        this.userID = userId;
    }

    public void setFlightID(int flightID) {
        FlightID = flightID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    // Other Methods
}
