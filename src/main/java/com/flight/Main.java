package com.flight;

import com.flight.views.UserView;

public class Main {
    public static void main(String[] args) {
        // Providing the User Interface for the user to interact with the application.
        UserView userView = new UserView();
        userView.userPresentationInteraction();
    }
}