package com.swelabs.lab2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Controller for the Login screen (login.fxml).
 * Handles user input validation and screen transition to the Welcome screen.
 *
 * Responsibilities:
 *   - Receive the validated user list and the primary Stage from Main.java
 *   - Respond to the Login button click (requirement 2.2 / 2.3)
 *   - Show an inline error label on failed login (requirement 2.3)
 *   - Load welcome.fxml and swap the scene on successful login (requirement 2.3)
 */
public class LoginController {

    /* ---- FXML-injected UI components (IDs must match login.fxml) ---- */

    /** Text field where the user types their username (email). */
    @FXML
    private TextField usernameField;

    /** Password field where the user types their password (input is masked). */
    @FXML
    private PasswordField passwordField;

    /** Label shown in red when login fails; hidden initially. */
    @FXML
    private Label errorLabel;

    /* ---- Data / state passed in from Main ---- */

    /** List of valid users loaded from user.txt. */
    private ArrayList<User> users;

    /** Reference to the primary stage, used to swap scenes. */
    private Stage stage;

    /*
     * Called by Main.java to inject the validated user list before the screen is shown.
     * users - the ArrayList of valid User objects read from user.txt
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /*
     * Called by Main.java to inject the primary stage reference.
     * stage - the JavaFX Stage that owns this scene
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /*
     * Event handler wired to the Login button in login.fxml.
     * Reads the username and password fields, searches the user list,
     * and either transitions to the Welcome screen or shows an error.
     */
    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Guard: empty fields
        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password.");
            return;
        }

        // Search for a matching user in the validated list
        User matchedUser = findUser(username, password);

        if (matchedUser != null) {
            // Requirement 2.3: valid user → open Welcome screen
            openWelcomeScreen(matchedUser);
        } else {
            // Requirement 2.3: no match → show inline error
            showError("Invalid username or password. Please try again.");
        }
    }

    /*
     * Searches the user list for a user whose username AND password both match.
     * username - the string entered in the username field
     * password - the string entered in the password field
     * Returns the matching User, or null if none is found
     */
    private User findUser(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    /*
     * Loads welcome.fxml, passes the matched user to its controller,
     * and replaces the current scene on the primary stage.
     * matchedUser - the authenticated User object
     */
    private void openWelcomeScreen(User matchedUser) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/swelabs/lab2/welcome.fxml")
            );
            Parent welcomeRoot = loader.load();

            // Pass the authenticated user to the WelcomeController
            WelcomeController welcomeController = loader.getController();
            welcomeController.setUser(matchedUser);

            // Swap the scene on the existing stage (requirement 2.3 / assignment diagram)
            stage.setTitle("GCM System — Welcome");
            stage.setScene(new Scene(welcomeRoot, 420, 320));

            // Requirement 2.4: X on the welcome screen also exits the application
            stage.setOnCloseRequest(event -> Platform.exit());

        } catch (Exception e) {
            showError("Error loading welcome screen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*
     * Displays an error message in the errorLabel and makes it visible.
     * message - the human-readable error string to display
     */
    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
}
