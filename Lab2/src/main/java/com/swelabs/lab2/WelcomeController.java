package com.swelabs.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for the Welcome screen (welcome.fxml).
 * Displays a personalised greeting after successful authentication.
 *
 * Responsibility:
 *   - Receive the authenticated User from LoginController
 *   - Update the welcome label with the user's username (requirement 2.3)
 */
public class WelcomeController {

    /** Label that shows the personalised welcome message. */
    @FXML
    private Label welcomeLabel;

    /*
     * Called by LoginController after the FXML is loaded.
     * Sets the welcome label text using the authenticated user's username.
     * user - the User object that successfully logged in
     */
    public void setUser(User user) {
        welcomeLabel.setText("Welcome, " + user.getUsername() + "!\nYou are now logged in to the GCM system.");
    }
}
