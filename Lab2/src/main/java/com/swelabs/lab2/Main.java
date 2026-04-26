package com.swelabs.lab2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main entry point for the Lab 2 JavaFX application.
 * Responsibilities:
 *   1. Read "user.txt" from the working directory and build a list of valid users (Lab 1 logic).
 *   2. Load the Login screen (login.fxml) and pass the user list to its controller.
 *   3. Ensure that closing any window terminates the entire application (requirement 2.4).
 */
public class Main extends Application {

    /*
     * JavaFX lifecycle start method.
     * primaryStage - the main application window provided by the JavaFX runtime
     * Throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        // --- 1. Load and validate users from user.txt (same logic as Lab 1) ---
        ArrayList<User> users = loadUsers("user.txt");

        // --- 2. Load the Login screen FXML ---
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/swelabs/lab2/login.fxml")
        );
        Parent loginRoot = loader.load();

        // Pass the validated user list to the LoginController
        LoginController loginController = loader.getController();
        loginController.setUsers(users);
        loginController.setStage(primaryStage);

        // --- 3. Configure and show the primary stage ---
        primaryStage.setTitle("GCM System — Login");
        primaryStage.setScene(new Scene(loginRoot, 420, 320));
        primaryStage.setResizable(false);

        // Requirement 2.4: pressing X on any window exits the entire application
        primaryStage.setOnCloseRequest(event -> Platform.exit());

        primaryStage.show();
    }

    /*
     * Reads the given file and returns an ArrayList of valid User objects.
     * Invalid lines are silently skipped (error printed to console only, as in Lab 1).
     * filename - path to the text file containing "username password" pairs
     * Returns an ArrayList of validated User instances
     */
    private ArrayList<User> loadUsers(String filename) {
        ArrayList<User> users = new ArrayList<>();
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue; // skip blank lines
                }

                // Split on one or more whitespace characters (handles multiple spaces)
                String[] parts = line.split("\\s+");

                if (parts.length >= 2) {
                    String username = parts[0];
                    String password = parts[1];
                    try {
                        users.add(new User(username, password));
                    } catch (Exception e) {
                        // Validation error — print to console, do not add to list
                        System.out.println("Skipping invalid user [" + username + "]: " + e.getMessage());
                    }
                } else if (parts.length == 1) {
                    // Single token: missing password — print error and skip
                    System.out.println("Skipping line with missing password: " + parts[0]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Warning: 'user.txt' not found. Starting with empty user list.");
        }

        return users;
    }

    /*
     * Standard Java entry point — launches the JavaFX application.
     * args - command-line arguments (not used)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
