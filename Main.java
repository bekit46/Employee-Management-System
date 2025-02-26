import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * Main class serves as the entry point for the Firm Management System application.
 * It handles user authentication, role-based employee actions, and provides an interactive menu-driven system.
 * The application supports login and termination options.
 */
public class Main {
    /**
     * Main method initializes the application components and starts the main loop for user interaction.
     * 
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Initialize the database facade and authentication objects
        Facade dbFacade = new Facade();
        Scanner scanner = new Scanner(System.in, "UTF-8");
        Authentication auth = new Authentication(dbFacade, scanner);
        boolean authenticated = false;
        Employee employee = null; // Stores the logged-in employee object
        boolean system = true;

        AsciiArt.firmManagement(scanner);

        // Main loop for login and authentication
        while (system) {
            int choice = 0;
            boolean validInput = false;

            // Ensure valid input for menu selection
            while (!validInput) {
                clearTerminal();
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║        FIRM MANAGEMENT SYSTEM          ║");
                System.out.println("╚════════════════════════════════════════╝");
                System.out.println("1. Login");
                System.out.println("2. Terminate");
                System.out.print("Enter your choice: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after the number
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Consume invalid input
                    pressEnterToContinue(scanner);
                }
            }

            switch (choice) {
                case 1:
                    boolean loginAttempt = true; // Inner loop to allow retrying login
                    while (loginAttempt) {
                        clearTerminal();
                        System.out.println("╔═══════════════════════════════╗");
                        System.out.println("║             Login             ║");
                        System.out.println("╚═══════════════════════════════╝");
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();

                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();

                        authenticated = auth.authenticate(scanner, username, password);

                        if (authenticated) {
                            // Fetch employee details from the database
                            ResultSet rs = dbFacade.getEmployeeByUsername(username);
                            try {
                                if (rs.next()) {
                                    // Retrieve employee details
                                    int employeeId = rs.getInt("employee_id");
                                    EmployeeRole role;
                                    String roleString = rs.getString("role").trim().toUpperCase(); // Ensure trimming and case-insensitivity
                                    role = EmployeeRole.valueOf(roleString);
                                    String name = rs.getString("name");
                                    String surname = rs.getString("surname");
                                    String phoneNo = rs.getString("phone_no");
                                    String dateOfBirth = rs.getString("dateofbirth");
                                    String dateOfStart = rs.getString("dateofstart");
                                    String email = rs.getString("email");
                                    password = rs.getString("password");

                                    // Determine the type of employee
                                    if (role == EmployeeRole.MANAGER) {
                                        employee = new Manager(employeeId, username, password, role, name, surname, phoneNo, dateOfBirth, dateOfStart, email);
                                    } else {
                                        employee = new StandardEmployee(employeeId, username, password, role, name, surname, phoneNo, dateOfBirth, dateOfStart, email);
                                    }
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                                System.out.println("Error fetching employee details. Please try again.");
                                pressEnterToContinue(scanner);
                                authenticated = false; // Reset authentication flag in case of errors
                            }

                            // Continue application flow with the authenticated employee object
                            if (employee != null) {
                                clearTerminal();
                                loginAttempt = false; // Exit login retry loop
                                if (employee instanceof Manager) {
                                    ((Manager) employee).displayMenu(scanner);
                                } else {
                                    ((StandardEmployee) employee).displayMenu(scanner);
                                }
                            }
                        } else {
                            loginAttempt = false;
                        }
                    }
                    break;

                case 2:
                    system = false; // Terminate the program
                    AsciiArt.goodBye("good bye!");
                    scanner.close();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    pressEnterToContinue(scanner);
            }
        }
    }

    /**
     * Pauses the system and waits for the user to press Enter to continue.
     *
     * @param scanner A Scanner object to read user input.
     */
    private static void pressEnterToContinue(Scanner scanner) {
        System.out.println("Press Enter to continue...");
        // Check if there's a leftover newline in the buffer
        if (scanner.hasNextLine() && scanner.nextLine().isEmpty()) {
            return; // Consume the leftover newline
        }
        scanner.nextLine(); // Wait for the user to press Enter
    }

    /**
     * Clears the terminal screen based on the operating system.
     * Supports both Windows and non-Windows environments.
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
}