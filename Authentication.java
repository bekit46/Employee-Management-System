import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * Handles user authentication and registration processes.
 */
public class Authentication {
    private Facade dbFacade;

    /**
     * Constructs an Authentication object.
     *
     * @param dbFacade the database facade for database operations
     * @param scanner  the scanner for user input
     */
    public Authentication(Facade dbFacade, Scanner scanner) {
        this.dbFacade = dbFacade;
    }
    /**
     * Authenticates a user based on the provided credentials.
     *
     * @param scanner  the scanner for user input
     * @param username the username of the user
     * @param password the password of the user
     * @return true if authentication is successful, false otherwise
     */
    public boolean authenticate(Scanner scanner, String username, String password) {
        clearTerminal();
    
        String query = "SELECT password, role, name FROM employees WHERE username = ?";
        try (Connection connection = dbFacade.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
    
            // Check if the user exists
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                String name = rs.getString("name");
                // First, check if the entered password matches the stored password
                if (storedPassword.equals(password)) {
                    // If it's the default password ("password123"), prompt for password change
                    if (storedPassword.equals("password123")) {
                        clearTerminal(); // Clear terminal before prompting for password change
                        return promptPasswordChange(scanner, username,name);
                    } else {
                        // Login is successful if the entered password is correct
                        clearTerminal(); // Clear terminal before showing success message
                        AsciiArt.welcomeBack(false, name);
                        pressEnterToContinue(scanner); // Wait for user confirmation
                        return true;
                    }
                } else {
                    // If the password is incorrect, clear terminal and show error message
                    clearTerminal();
                    System.out.println("Invalid username or password. Please try again.");
                    pressEnterToContinue(scanner); // Wait for user confirmation
                    return false;
                }
            } else {
                // If the username doesn't exist in the database
                clearTerminal();
                System.out.println("User doesn't exist!");
                pressEnterToContinue(scanner); // Wait for user confirmation
                return false;
            }
        } catch (SQLException e) {
            clearTerminal(); // Clear terminal before showing the exception stack trace
            System.out.println("An error occurred while accessing the database. Please try again later.");
            pressEnterToContinue(scanner); // Wait for user confirmation
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Prompts the user to change their password if it's the default password.
     *
     * @param scanner  the scanner for user input
     * @param username the username of the user
     * @param name the first name of the user
     * @return true if the password is successfully changed, false otherwise
     */
    private boolean promptPasswordChange(Scanner scanner, String username,String name) {
        String newPassword;
        while (true) {
            clearTerminal();
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.println("║            First Login Password Update Tab           ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
            System.out.println("Your default password is still active. Please change it.");
            System.out.print("Enter a new password (at least 8 characters and different from 'password123'): ");
            newPassword = scanner.nextLine(); // Read the new password input
  
            if (newPassword.length() >= 8 && !newPassword.equals("password123")) {
                // Update the password in the database
                if (updatePasswordInDatabase(username, newPassword)) {
                    clearTerminal();
                    AsciiArt.welcomeBack(true, name);
                    System.out.println("Password successfully changed.\n");
                    pressEnterToContinue(scanner); // Wait for user confirmation
                    return true;
                } else {
                    clearTerminal();
                    System.out.println("Failed to update password. Please try again.");
                    pressEnterToContinue(scanner); // Wait for user confirmation
                }
            } else {
                clearTerminal();
                System.out.println("╔══════════════════════════════════════════════════════╗");
                System.out.println("║            First Login Password Update Tab           ║");
                System.out.println("╚══════════════════════════════════════════════════════╝");
                System.out.println("Password must be at least 8 characters long and different from 'password123'.");
                pressEnterToContinue(scanner); // Wait for user confirmation
            }
        }
    }
    /**
     * Updates the user's password in the database.
     *
     * @param username    the username of the user
     * @param newPassword the new password to set
     * @return true if the password is successfully updated, false otherwise
     */
    private boolean updatePasswordInDatabase(String username, String newPassword) {
        String query = "UPDATE employees SET password = ? WHERE username = ?";
        try (Connection connection = dbFacade.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Clears the terminal screen.
     */
    public static void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Waits for the user to press Enter to proceed.
     *
     * @param scanner the scanner for user input
     */
    private static void pressEnterToContinue(Scanner scanner) {
        System.out.println("Press Enter to continue...");
        // Check if there's a leftover newline in the buffer
        if (scanner.hasNextLine() && scanner.nextLine().isEmpty()) {
            return; // Consume the leftover newline
        }
        scanner.nextLine(); // Wait for the user to press Enter
    }
}
