package com.swelabs.lab2;

/**
 * Represents a system user with a validated email username and password.
 * Validation rules are identical to Lab 1:
 *   - Username: valid email format, max 50 characters
 *   - Password: 8-12 characters, must contain a letter, a digit, and a special character
 */
public class User implements Comparable<User> {

    private String username;
    private String password;

    /*
     * Instantiates a new User after validating both credentials.
     * username - the email string to be processed
     * password - candidate password string matching validation rules
     * Throws Exception if either field fails validation
     */
    public User(String username, String password) throws Exception {
        setUsername(username);
        setPassword(password);
    }

    /*
     * Validates the format and length of the email username.
     * username - the candidate username string
     * Throws Exception if the username is too long or not a valid email format
     */
    private void setUsername(String username) throws Exception {
        if (username.length() > 50) {
            throw new Exception("Username is too long, try something shorter");
        }

        String regex = "^[a-zA-Z0-9\\-\\+\\%_]+@[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,}$";
        if (!username.matches(regex)) {
            throw new Exception("Please enter a valid Email as username");
        }

        this.username = username;
    }

    /*
     * Confirms the password meets length and character-composition criteria.
     * password - the candidate password string
     * Throws Exception if the password is too short, too long, or missing required character types
     */
    private void setPassword(String password) throws Exception {
        if (password.length() < 8) {
            throw new Exception("Your password is too short, add more characters");
        }
        if (password.length() > 12) {
            throw new Exception("Your password is too long, try a shorter one");
        }

        // Must contain at least one letter, one digit, and one special character
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%\\^&*()_+])[a-zA-Z\\d!@#$%\\^&*()_+]+$";
        if (!password.matches(regex)) {
            throw new Exception("Please enter a valid password");
        }

        this.password = password;
    }

    /*
     * Retrieves the validated username.
     * Returns the username string
     */
    public String getUsername() {
        return username;
    }

    /*
     * Retrieves the validated password.
     * Returns the password string
     */
    public String getPassword() {
        return password;
    }

    /*
     * Compares two Users lexicographically by username.
     * other - the User to compare against
     * Returns a negative, zero, or positive integer
     */
    @Override
    public int compareTo(User other) {
        return this.username.compareTo(other.username);
    }

    /*
     * Returns a space-separated representation: "username password".
     */
    @Override
    public String toString() {
        return username + " " + password;
    }
}
