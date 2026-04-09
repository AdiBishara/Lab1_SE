import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UsersApp {
    /*
     * Main entry point that parses the file and prints valid sorted users
     * args - Command line arguments passed to the program
     * Returns void
     */
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();

        // Reading the user.txt file from the root
        File file = new File("user.txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }

                // Every line separated by one or more spaces
                String[] parts = line.split("\\s+");
                
                if (parts.length >= 2) {
                    String username = parts[0];
                    String password = parts[1];

                    try {
                        User user = new User(username, password);
                        users.add(user); // added successfully if conditions met
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (parts.length == 1) {
                    // Scenario where user might provide username without separated password
                    try {
                        User user = new User(parts[0], ""); 
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: user.txt file not found. Ensure it is placed in the project root directory.");
        }

        // At the end, sort and print the ArrayList
        Collections.sort(users);
        for (User u : users) {
             System.out.println(u);
        }
    }
}
