package com.flight.dataclass;

import java.util.Date;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String nationality;
    private String passport;
    private String password;
    private Date createdAt;
    private Date updatedAt;

    // CONSTRUCTOR OF THE CLASS:
    public User(int userID,
                String firstName,
                String lastName,
                String phoneNumber,
                String email,
                String nationality,
                String passport,
                String password,
                Date createdAt,
                Date updatedAt) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nationality = nationality;
        this.passport = passport;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User(int userID,
                String firstName,
                String lastName,
                String phoneNumber,
                String email,
                String nationality,
                String passport,
                String password) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nationality = nationality;
        this.passport = passport;
        this.password = password;
    }

    public User(String firstName,
                String lastName,
                String phoneNumber,
                String email,
                String nationality,
                String passport,
                String password) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nationality = nationality;
        this.passport = passport;
        this.password = password;
    }


    public User(int userID,
                String firstName,
                String lastName,
                String phoneNumber,
                String email,
                String nationality,
                String passport) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nationality = nationality;
        this.passport = passport;
    }

    public User(String firstName,
                String lastName,
                String phoneNumber,
                String email,
                String nationality,
                String passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nationality = nationality;
        this.passport = passport;
    }


    // Getter Methods
    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public String getPassword() {
        return password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    // Setter Methods
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Other Methods

}
