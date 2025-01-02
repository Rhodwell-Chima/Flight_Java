package com.flight.dataclass;

import java.sql.Time;
import java.util.Date;

public class Flight {
    private int flightID;
    private int flightNumber;
    private String origin;
    private String destination;
    private Time departureTime;
    private Time arrivalTime;
    private int availableSeats;
    private int totalSeats;
    private Date createdAt;
    private Date updateAt;

    public Flight(int flightID,
                  int flightNumber,
                  String origin,
                  String destination,
                  Time departureTime,
                  Time arrivalTime,
                  int availableSeats,
                  int totalSeats,
                  Date createdAt,
                  Date updateAt) {
        this.flightID = flightID;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
        this.totalSeats = totalSeats;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }
    public Flight(int flightNumber,
                  String origin,
                  String destination,
                  Time departureTime,
                  Time arrivalTime,
                  int availableSeats,
                  int totalSeats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
        this.totalSeats = totalSeats;
    }

    //The following are the Getter Methods
    public int getFlightID() {
        return flightID;
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

    public Time getDepartureTime() {
        return departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    //The following are the Setter Methods

    public void setFlightID(int flightID) {
        this.flightID = flightID;
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

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    // Other Methods

}
