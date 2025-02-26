import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Abstract base class representing an Employee.
 * Provides common attributes, validation methods, and abstract methods
 * for subclasses to implement specific functionality.
 */
public abstract class Employee {
    // Protected attributes accessible by subclasses
    /**
     * The unique ID of the employee.
     */
    protected int employeeId;

    /**
     * The username for the employee's login credentials.
     */
    protected String username;

    /**
     * The password for the employee's login credentials.
     */
    protected String password;

    /**
     * The role of the employee in the organization.
     */
    protected EmployeeRole role;

    /**
     * The first name of the employee.
     */
    protected String name;

    /**
     * The last name (surname) of the employee.
     */
    protected String surname;

    /**
     * The phone number of the employee.
     */
    protected String phoneNo;

    /**
     * The date of birth of the employee in the format "yyyy-MM-dd".
     */
    protected String dateOfBirth;

    /**
     * The starting date of the employee's employment in the format "yyyy-MM-dd".
     */
    protected String dateOfStart;

    /**
     * The email address of the employee.
     */
    protected String email;
    /**
     * Constructor to initialize all attributes of an Employee.
     * @param employeeId The employee ID.
     * @param username The username for login.
     * @param password The password for login.
     * @param role The role of the employee.
     * @param name The first name of the employee.
     * @param surname The last name of the employee.
     * @param phoneNo The phone number of the employee.
     * @param dateOfBirth The date of birth in format "yyyy-MM-dd".
     * @param dateOfStart The starting date of employment in format "yyyy-MM-dd".
     * @param email The email address of the employee.
     */
    public Employee(int employeeId, String username, String password, EmployeeRole role,
                    String name, String surname, String phoneNo,
                    String dateOfBirth, String dateOfStart, String email) {
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.phoneNo = phoneNo;
        this.dateOfBirth = dateOfBirth;
        this.dateOfStart = dateOfStart;
        this.email = email;
    }

    // Abstract methods to be implemented by subclasses
    /**
     * Abstract method to display the profile of the employee.
     * To be implemented by subclasses.
     */    
    public abstract void displayProfile();
    /**
     * Abstract method to update the database with employee details.
     * @param obj The Employee object containing updated details.
     * @param scanner A Scanner object for user input.
     */
    public abstract void updateDB(Employee obj,Scanner scanner);
    /**
     * Abstract method to update a specific profile field.
     * @param obj The Employee object being updated.
     * @param header The profile field to update.
     * @param val The new value for the field.
     */
    public abstract void updateProfile(Employee obj,String header, String val);
    /**
     * Abstract method to display a menu for the employee.
     * @param scanner A Scanner object for user input.
     */
    public abstract void displayMenu(Scanner scanner);
    /**
     * Validates a phone number based on predefined patterns.
     * @param scanner A Scanner object for user input.
     * @return A valid phone number string.
     */
    public static String getValidPhoneNumber(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Clear buffer
        }
        String phoneNumber;
        // Define a regex pattern for a valid phone number
        String phoneRegex = "^(\\+\\d{1,3})?\\d{10}$"; // Supports optional country code (+1, +44, etc.) and 10-digit numbers
        boolean isValid = false;

        do {
            clearTerminal();
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║         Phone Number Validation Tab            ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("Enter a valid phone number (e.g., +1234567890 or 1234567890): ");
            phoneNumber = scanner.nextLine();

            if (phoneNumber.matches(phoneRegex)) {
                isValid = true;
            } else {
                System.out.println("Invalid phone number. Please try again.");
                pressEnterToContinue(scanner);
            }
        } while (!isValid);
        System.out.println("Phone number is valid");
        return phoneNumber;
    }
    /**
     * Validates an email address based on a regex pattern.
     * @param scanner A Scanner object for user input.
     * @return A valid email address string.
     */
    public static String getValidEmail(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Clear buffer
        }
        String email;
        // Define a regex pattern for a valid email address
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        boolean isValid = false;

        do {
            clearTerminal();
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║         Email Address Validation Tab           ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("Enter a valid email address (example@domain.com): ");
            email = scanner.nextLine().trim();

            if (email.matches(emailRegex)) {
                isValid = true;
            } else {
                System.out.println("Invalid email address. Please try again.");
                System.out.println("Press Enter to continue...");
                scanner.nextLine(); // Wait for user to press Enter
            }
        } while (!isValid);

        System.out.println("Email address is valid.");
        return email;
    }
    /**
     * Validates a date input against the format "yyyy-MM-dd".
     * @param scanner A Scanner object for user input.
     * @param title The title of the validation screen.
     * @return A valid date string in the specified format.
     */
    public static String getValidDate(Scanner scanner, String title, String dateType, LocalDate birthDate) {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Clear buffer
        }
        String dateInput;
        LocalDate validDate = null;
    
        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean isValid = false;
        LocalDate today = LocalDate.now();
    
        do {
            clearTerminal();
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║" + padLeft(title, 17) + " Date Validation Tab" + "           ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("Enter a valid date (format: YYYY-MM-DD): ");
            dateInput = scanner.nextLine().trim();
    
            try {
                validDate = LocalDate.parse(dateInput, formatter);
    
                // Additional validation logic
                if ("birthDate".equalsIgnoreCase(dateType)) {
                    if (validDate.isAfter(today.minusYears(18)) || validDate.isBefore(today.minusYears(100))) {
                        System.out.println("Invalid birth date. The person must be between 18 and 100 years old.");
                        pressEnterToContinue(scanner);
                        continue;
                    }
                } else if ("jobStartDate".equalsIgnoreCase(dateType)) {
                    if (birthDate != null && validDate.isBefore(birthDate.plusYears(18))) {
                        System.out.println("Invalid job start date. It must be at least 18 years after the birth date.");
                        pressEnterToContinue(scanner);
                        continue;
                    }
                    if (validDate.isAfter(today)) {
                        System.out.println("Invalid job start date. It cannot be in the future.");
                        pressEnterToContinue(scanner);
                        continue;
                    }
                }
    
                isValid = true; // If parsing and validation succeed
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please try again.");
                pressEnterToContinue(scanner);
            }
        } while (!isValid);
    
        System.out.println("Date is valid.");
        return validDate.format(formatter); // Return the date in the specified format
    }
    
    /**
     * Validates a password with a minimum length requirement.
     * @param scanner A Scanner object for user input.
     * @return A valid password string.
     */
    public static String getValidPassword(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Clear buffer
        }
        String password;
        boolean isValid = false;
        do {
            clearTerminal();
            System.out.println("╔════════════════════════════════════╗");
            System.out.println("║       Password Validation Tab      ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.print("Enter a valid password (at least 8 characters): ");
            password = scanner.nextLine().trim();

            if (password.length() >= 8 && !password.equals("password123")) {
                isValid = true;
            } else {
                System.out.println("Invalid password. Password must be at least 8 characters long and different from deafult password.");
                System.out.println("Press Enter to continue...");
                scanner.nextLine(); // Wait for user to press Enter
            }
        } while (!isValid);

        System.out.println("Password is valid.");
        return password;
    }
    /**
     * Validates a username for length and uniqueness in the database.
     * @param scanner A Scanner object for user input.
     * @param safe Boolean to allow overriding uniqueness checks.
     * @return A valid username string.
     */
    public static String getValidUsername(Scanner scanner, boolean safe) {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Clear buffer
        }
        String username;
        boolean isValid = false;
        // Assuming dbFacade is the object that provides database interactions
        Facade dbFacade = new Facade();
    
        do {
            clearTerminal();
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║          Username Validation Tab           ║");
            System.out.println("╚════════════════════════════════════════════╝");
    
            System.out.print("Enter a valid username (maximum 16 characters): ");
            username = scanner.nextLine().trim();
    
            // Check if the username length is valid
            if (username.length() > 16 || username.length() < 1) {
                System.out.println("Invalid username. Username must be at most 16 characters long.");
                System.out.println("Press Enter to continue...");
                scanner.nextLine(); // Wait for user to press Enter
            } else {
                // Check if the username already exists in the database
                ResultSet rs = dbFacade.getEmployeeByUsername(username);
                try {
                    if (rs.next() && !safe) {
                        // Username already exists
                        System.out.println("Username already taken. Please choose a different username.");
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine(); // Wait for user to press Enter
                    } else {
                        // Username is valid and not taken
                        isValid = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("An error occurred while checking the username.");
                }
            }
        } while (!isValid);
    
        System.out.println("Username is valid.");
        return username;
    }    
    /**
     * Validates a name with alphabetic characters only.
     * @param scanner A Scanner object for user input.
     * @return A valid name string.
     */
    public static String getValidName(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Clear buffer
        }
        String name;
        boolean isValid = false;
        do {
            clearTerminal();
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║             Name Validation Tab            ║");
            System.out.println("╚════════════════════════════════════════════╝");

            System.out.print("Enter a valid name (only alphabetic characters, max 16 characters): ");
            name = scanner.nextLine().trim();
    
            // Regular expression that includes Turkish characters
            if (name.length() <= 16 && name.length() > 1 && name.matches("[A-Za-zçÇıığĞöÖşŞüÜ]+")) {
                isValid = true;
            } else {
                System.out.println("Invalid name. Name must be at most 16 characters long and contain only alphabetic characters.");
                System.out.println("Press Enter to continue...");
                scanner.nextLine(); // Wait for user to press Enter
            }
        } while (!isValid);
    
        System.out.println("Name is valid.");
        return name;
    }    
    /**
     * Validates a surname with alphabetic characters only.
     * @param scanner A Scanner object for user input.
     * @return A valid surname string.
     */
    public static String getValidSurname(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Clear buffer
        }
        String surname;
        boolean isValid = false;
        do {
            clearTerminal();
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                        Surname Validation Tab                         ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            System.out.print(  "Enter a valid surname (only alphabetic characters, maximum 16 characters): ");
            surname = scanner.nextLine().trim();

            if (surname.length() <= 16 && surname.length() > 1 && surname.matches("[A-Za-zçÇıığĞöÖşŞüÜ]+")) {
                isValid = true;
            } else {
                System.out.println("Invalid Surname. Surname must be at most 16 characters long and contain only alphabetic characters.");
                System.out.println("Press Enter to continue...");
                scanner.nextLine(); // Wait for user to press Enter
            }
        } while (!isValid);

        System.out.println("Surname is valid.");
        return surname;
    }
    /**
     * Validates a role based on predefined options.
     * @param scanner A Scanner object for user input.
     * @return A valid role string.
     */
    public static String getValidRole(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Clear buffer
        }
        String role;
        boolean isValid = false;
        do {
            clearTerminal();
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║            Role Validation Tab             ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("1 = Manager\n" +
                             "2 = Engineer\n" +
                             "3 = Technician\n" +
                             "4 = Intern\n" +
                             "Select a role for the employee: ");
            role = scanner.nextLine().trim().toLowerCase();

            if (role.equals("1") || role.equals("2") || role.equals("3") || role.equals("4")) {
                System.out.println("Role is valid");
                isValid = true;
            } else {
                System.out.println("Invalid selection, please select '1, 2, 3 or 4'");
                System.out.println("Press Enter to continue...");
                scanner.nextLine(); // Wait for user to press Enter
            }
        } while (!isValid);
        if(role.equals("1"))
            return "MANAGER";

        else if(role.equals("2"))
            return "ENGINEER";
        else if(role.equals("3"))
            return "TECHNICIAN";

        else if(role.equals("4"))
            return "INTERN";
        System.out.println("Role is valid.");
        return role;
    }
    /**
     * Validates an employee ID for numeric input.
     * @param scanner A Scanner object for user input.
     * @return A valid employee ID.
     */
    public static int getValidId(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Clear buffer
        }
        int id = -1;
        boolean validId = false;
        do {
            clearTerminal();
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║            Employee ID Validation Tab            ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
    
            System.out.println("Enter the ID of the employee (or enter '0' to exit):");
    
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                if (id == 0) {
                    return id;
                } else {
                    validId = true;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                pressEnterToContinue(scanner);
            }
        } while (!validId);
        return id;
    }
    /**
     * Validates an Array size for numeric input an in a renge of 1000 to 10000.
     * @param scanner A Scanner object for user input.
     * @return A valid array size.
     */
    public int getValidArraySize(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); // Clear buffer
        }
        int size = -1;
        boolean validInput = false;
        do {
            clearTerminal();
            System.out.println("╔═════════════════════════════════════════════════════╗");
            System.out.println("║              Array Size Validation Tab              ║");
            System.out.println("╚═════════════════════════════════════════════════════╝");
    
            System.out.println("Enter the size of the array (between 1000 and 10,000): ");
    
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                if (size >= 1000 && size <= 10000) {
                    validInput = true;
                } else {
                    System.out.println("Size should be between 1000 and 10,000. Please try again.");
                    scanner.nextLine(); // Clear the buffer
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            pressEnterToContinue(scanner);
        } while (!validInput);
    
        return size;
    }

    // Getters and Setters for encapsulation
    public int getEmployeeId() {
        return employeeId;
    }

    public String getPassword(){
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role.name(); // Assuming EmployeeRole is an Enum
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfStart() {
        return dateOfStart;
    }

    public String getEmail() {
        return email;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setRole(EmployeeRole role) {
        this.role = role;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public void setDateOfStart(String dateOfStart) {
        this.dateOfStart = dateOfStart;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Clears the terminal for a clean user interface.
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
     * Prompts the user to press Enter to continue.
     * @param scanner A Scanner object for user input.
     */
    public static void pressEnterToContinue(Scanner scanner) {
        System.out.println("Press Enter to continue...");
        // Check if there's a leftover newline in the buffer
        if (scanner.hasNextLine() && scanner.nextLine().isEmpty()) {
            return; // Consume the leftover newline
        }
        scanner.nextLine(); // Wait for the user to press Enter
    }
    /**
     * Pads a string to the right for alignment purposes.
     * @param str The string to pad.
     * @param length The desired total length of the string.
     * @return The padded string.
     */
    public static String padRight(String str, int length) {
        return String.format("%-" + length + "s", str);
    }
    /**
     * Pads a string to the left for alignment purposes.
     * @param str The string to pad.
     * @param length The desired total length of the string.
     * @return The padded string.
     */    
    public static String padLeft(String str, int length) {
        return String.format("%" + length + "s", str);
    }
}