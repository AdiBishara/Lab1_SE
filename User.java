public class User implements Comparable<User> {
    private String username;
    private String password;

    /*
     * Constructor that validates and creates a new User
     * username - The user's email address
     * password - The user's secret password
     * Returns a new User instance
     */
    public User(String username, String password) throws Exception {
        setUsername(username);
        setPassword(password);
    }

    /*
     * Validates the format and length of the username
     * username - The email string to validate and set
     * Returns void
     */
    private void setUsername(String username) throws Exception {
        if (username.length() > 50) {
            throw new Exception("Username is too long, try something shorter");
        }

        // Allowed characters based on assignment details:
        // Part 1: characters, ints, and the special keys - + % _
        // Part 2: separated by @, can contain characters, ints and special keys - .
        // Part 3: separated by ., at least 2 characters
        String regex = "^[a-zA-Z0-9\\-\\+\\%_]+@[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,}$";
        if (!username.matches(regex)) {
            throw new Exception("Please enter a valid Email as username");
        }
        
        this.username = username;
    }

    /*
     * Validates the length and character contents of the password
     * password - The string password to validate and set
     * Returns void
     */
    private void setPassword(String password) throws Exception {
        if (password.length() < 8) {
            throw new Exception("Your password is too short, add more characters");
        }
        if (password.length() > 12) {
            throw new Exception("Your password is too long, try a shorter one");
        }

        // Must contain one char, one int and one special char
        boolean hasChar = false;
        boolean hasInt = false;
        boolean hasSpecial = false;
        String allowedSpecials = "!@#$%^&*()_+";

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasChar = true;
            } else if (Character.isDigit(c)) {
                hasInt = true;
            } else if (allowedSpecials.indexOf(c) != -1) {
                hasSpecial = true;
            } else {
                throw new Exception("Please enter a valid password");
            }
        }

        if (!hasChar || !hasInt || !hasSpecial) {
            throw new Exception("Please enter a valid password");
        }

        this.password = password;
    }

    /*
     * Gets the username of the user
     * parameters: none
     * Returns the username as a string
     */
    public String getUsername() {
        return username;
    }

    /*
     * Gets the password of the user
     * parameters: none
     * Returns the password as a string
     */
    public String getPassword() {
        return password;
    }

    // Sorting by username alphabetically 
    /*
     * Compares this user with another based on their username alphabetically
     * other - The other User object to compare against
     * Returns negative if this username is less, zero if equal, positive if greater
     */
    @Override
    public int compareTo(User other) {
        return this.username.compareTo(other.username);
    }

    /*
     * Formats the user as a space-separated string of username and password
     * parameters: none
     * Returns the formatted string representation of the user
     */
    @Override
    public String toString() {
        return username + " " + password;
    }
}
