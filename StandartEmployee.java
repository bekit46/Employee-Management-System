import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StandardEmployee extends Employee {
    private Facade dbFacade;

    public StandardEmployee(int employeeId, String username, String password, EmployeeRole role, 
                            String name, String surname, String phoneNo, 
                            String dateOfBirth, String dateOfStart, String email) {
        super(employeeId, username, password, role, name, surname, phoneNo, dateOfBirth, dateOfStart, email);
        this.dbFacade = new Facade(); // Initialize the dbFacade object here
    }

    @Override
    public void displayProfile() {
        System.out.println("==============================");
        System.out.println("            Profile           ");
        System.out.println("==============================");
        System.out.println("Manager ID: " + employeeId);
        System.out.println("Name: " + name + " " + surname);
        System.out.println("Username: " + username);
        System.out.println("Role: " + role.name());
        System.out.println("Phone: " + phoneNo);
        System.out.println("Email: " + email);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Start Date: " + dateOfStart);
    }

    @Override
    public void updateProfile(Employee obj,String header,String val){
        switch(header){
            case "phoneNo":
                this.phoneNo = val; 
                break;
            case "password":
                this.password = val;
                break;
            case "email":
                this.email = val;
                break;
        }
    }

    @Override
    public void updateDB(Employee obj) {

        String query = "UPDATE employees SET password = ?, phone_no = ?, email = ? WHERE employee_id = ?";
        int rowsUpdated = dbFacade.executeUpdate(query, this.password, this.phoneNo, this.email, this.employeeId);

        if (rowsUpdated > 0) {
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("Failed to update profile in the database.");
        }
    }

    @Override
    public void displayMenu()
    {
        Scanner scanner = new Scanner(System.in);
        boolean logout = false;

        while (!logout) {
            
            int choice = 0;
            // Ensure that the input is a valid integer
            boolean validInput = false;
            while (!validInput) {
                clearTerminal();
                System.out.println("=== Standard Employee Menu ===");
                System.out.println("1. Display Profile");
                System.out.println("2. Update Profile");
                System.out.println("3. Logout");
                System.out.print("Enter your choice: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine(); // Consume the newline character from nextInt()
                    clearTerminal(); 
                }
            }

            switch (choice) {
                case 1:
                    // Display profile logic
                    clearTerminal();
                    this.displayProfile();
                    pressEnterToContinue(scanner);
                    break;
                case 2:
                boolean exitSubmenu = false; // Control flag for the submenu
                while (!exitSubmenu) {
                    int subChoice = 0;
                    boolean subValidInput = false;
            
                    // Ensure that the input is a valid integer
                    while (!subValidInput) {
                        clearTerminal();
                        System.out.println("=== Profile Update Menu ===");
                        System.out.println("1. Update Phone Number");
                        System.out.println("2. Update E-Mail");
                        System.out.println("3. Update Password");
                        System.out.println("4. Save Changes and Quit");
                        System.out.print("Enter your choice: ");
                        
                        if (scanner.hasNextInt()) {
                            subChoice = scanner.nextInt();
                            subValidInput = true;
                        } else {
                            System.out.println("Invalid input. Please enter a valid number.");
                            scanner.nextLine(); // Clear invalid input
                            System.out.println("Press Enter to continue...");
                            scanner.nextLine(); // Pause for user
                        }
                    }
            
                    // Handle the submenu choices
                    switch (subChoice) {
                        case 1:
                            this.updateProfile(null, "phoneNo", getValidPhoneNumber());
                            pressEnterToContinue(scanner);
                            break;
                        case 2:
                            this.updateProfile(null, "email", getValidEmail());
                            pressEnterToContinue(scanner);
                            break;
                        case 3:
                            this.updateProfile(null, "password", getValidPassword());
                            pressEnterToContinue(scanner);
                            break;
                        case 4:
                            this.updateDB(null);
                            pressEnterToContinue(scanner);
                            exitSubmenu = true; // Exit the submenu
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            pressEnterToContinue(scanner);
                        }
                    }
                    break;
                case 3:
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    pressEnterToContinue(scanner);
            }
        }
    }

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

    private static void pressEnterToContinue(Scanner scanner) {
        System.out.println("Press Enter to continue...");
        scanner.nextLine(); // Consume the newline character from nextInt()
        scanner.nextLine(); // Wait for the user to press Enter
    }
}
