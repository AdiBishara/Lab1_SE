import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UsersApp {
    /*
     * Driver method verifying passwords and usernames from a file 
     * args - runtime arguments
     * Returns nothing
     */
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        File file = new File("user.txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+", 2);

                if (parts.length < 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                    System.err.println("Invalid line (missing username or password): " + line);
                    continue;
                }

                String username = parts[0];
                String password = parts[1].trim();

                try {
                    User user = new User(username, password);
                    users.add(user);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: user.txt file not found. Ensure it is placed in the project root directory.");
        }

        Collections.sort(users);
        for (User u : users) {
             System.out.println(u);
        }
    }
}
