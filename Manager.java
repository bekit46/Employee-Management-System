import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
/**
 * The Manager class extends Employee and provides functionalities 
 * specific to managing employees, including profile management, hiring, 
 * and firing operations. It interacts with the database via a facade.
 */
public class Manager extends Employee {
    /**
     * The database facade used to interact with the database for employee-related operations.
     */
    protected Facade dbFacade;
    /**
     * Constructs a Manager object with the given details.
     *
     * @param employeeId The unique ID of the manager.
     * @param username The username for login.
     * @param password The password for login.
     * @param role The role of the manager.
     * @param name The first name of the manager.
     * @param surname The last name of the manager.
     * @param phoneNo The phone number of the manager.
     * @param dateOfBirth The date of birth in the format "yyyy-MM-dd".
     * @param dateOfStart The starting date of employment in the format "yyyy-MM-dd".
     * @param email The email address of the manager.
     */
    public Manager(int employeeId, String username, String password, EmployeeRole role,
                   String name, String surname, String phoneNo,
                   String dateOfBirth, String dateOfStart, String email) {
        super(employeeId, username, password, role, name, surname, phoneNo, dateOfBirth, dateOfStart, email);
        this.dbFacade = new Facade(); // Initialize the dbFacade object here
    }
    /**
     * Updates the profile of the current manager or another employee.
     *
     * @param obj The Employee object to update. If null, updates the manager's profile.
     * @param header The field to update (e.g., "phoneNo", "password").
     * @param val The new value for the specified field.
     */
    @Override
    public void updateProfile(Employee obj,String header, String val)
    {
        if(obj==null){
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
        else{
            switch(header){
                case "username":
                    obj.setUsername(val);
                    break;
                case "name":
                    obj.setName(val);
                    break;
                case "surname":
                    obj.setSurname(val);
                    break;
                case "dateOfBirth":
                    obj.setDateOfBirth(val);
                    break;
                case "dateOfStart":
                    obj.setDateOfStart(val);
                    break;
                case "role":
                    if(val=="ENGINEER"){
                        obj.setRole(EmployeeRole.ENGINEER);
                    }
                    else if(val=="TECHNICIAN"){
                        obj.setRole(EmployeeRole.TECHNICIAN);
                    }
                    else if(val == "INTERN"){
                        obj.setRole(EmployeeRole.INTERN);
                    }
                    else if(val=="MANAGER"){
                        obj.setRole(EmployeeRole.MANAGER);
                    }
            }
        }
    }
    /**
     * Updates the profile of the current manager or another employee in the database.
     *
     * @param obj The Employee object to update. If null, updates the manager's profile.
     * @param scanner A Scanner object for user input.
     */
    @Override
    public void updateDB(Employee obj,Scanner scanner) {
        String query = "UPDATE employees SET username = ?, password = ?, role = ?, name = ?, surname = ?, " +
                    "phone_no = ?, dateofbirth = ?, dateofstart = ?, email = ? WHERE employee_id = ?";
            
        int rowsUpdated=0;
        if(obj==null){
            rowsUpdated = dbFacade.executeUpdate(query, this.username, this.password, this.role.toString(), this.name, 
                                                this.surname, this.phoneNo, this.dateOfBirth, this.dateOfStart, 
                                                this.email, this.employeeId);
        }
        else{
            rowsUpdated = dbFacade.executeUpdate(query, obj.getUsername(), obj.getPassword(), obj.getRole(), obj.getName(), 
                                                obj.getSurname(), obj.getPhoneNo(), obj.getDateOfBirth(), obj.getDateOfStart(), 
                                                obj.getEmail(), obj.getEmployeeId());
        }
        if (rowsUpdated > 0) {
            clearTerminal();
            AsciiArt.standardPrint("updated!","\u001B[32m",0);
            scanner.nextLine();
            pressEnterToContinue(scanner);
        } else {
            clearTerminal();
            AsciiArt.standardPrint("failed","\u001B[31m",0);
            scanner.nextLine();
            pressEnterToContinue(scanner);
        }
    }
    /**
     * Displays the profile of the current manager in a formatted layout.
     */
    @Override
    public void displayProfile() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                  Employee Profile                    ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║ Employee ID: " + padRight(String.valueOf(employeeId), 40) + "║");
        System.out.println("║ Name: " + padRight(name + " " + surname, 47) + "║");
        System.out.println("║ Username: " + padRight(username, 43) + "║");
        System.out.println("║ Role: " + padRight(role.name(), 47) + "║");
        System.out.println("║ Phone: " + padRight(phoneNo, 46) + "║");
        System.out.println("║ Email: " + padRight(email, 46) + "║");
        System.out.println("║ Date of Birth: " + padRight(dateOfBirth, 38) + "║");
        System.out.println("║ Starting Date: " + padRight(dateOfStart, 38) + "║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
    /**
     * Displays the profile of an employee by their username.
     *
     * @param username The username of the employee to search for.
     * @param scanner A Scanner object for user input.
     */
    public void displayEmployeeByUsername(String username,Scanner scanner) {
        clearTerminal();
        ResultSet rs = null;
    
        try {
            rs = dbFacade.getEmployeeByUsername(username); // Assume this returns a ResultSet
            if (rs != null && rs.next()) { // Check if the ResultSet has data
                System.out.println("╔══════════════════════════════════════════════════════╗");
                System.out.println("║                   Employee Profile                   ║");
                System.out.println("╠══════════════════════════════════════════════════════╣");
                System.out.println("║ Employee ID: " + padRight(String.valueOf(rs.getInt("employee_id")), 40) + "║");
                System.out.println("║ Name: " + padRight(rs.getString("name") + " " + rs.getString("surname"), 47) + "║");
                System.out.println("║ Username: " + padRight(rs.getString("username"), 43) + "║");
                System.out.println("║ Role: " + padRight(rs.getString("role"), 47) + "║");
                System.out.println("║ Phone: " + padRight(rs.getString("phone_no"), 46) + "║");
                System.out.println("║ Email: " + padRight(rs.getString("email"), 46) + "║");
                System.out.println("║ Date of Birth: " + padRight(rs.getString("dateofbirth"), 38) + "║");
                System.out.println("║ Starting Date: " + padRight(rs.getString("dateofstart"), 38) + "║");
                System.out.println("╚══════════════════════════════════════════════════════╝");
            } else {
                System.out.println("╔══════════════════════════════════════════════════════╗");
                System.out.println("║                    User Not Found!                   ║");
                System.out.println("╚══════════════════════════════════════════════════════╝");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while retrieving the user profile.");
            pressEnterToContinue(scanner);
        } finally {
            // Close the ResultSet if it was opened
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    

    /**
     * Displays all employees in the organization.
     * 
     * @param scanner A Scanner object for user input.
     */
    public void displayAllEmployees(Scanner scanner) 
    {
        clearTerminal();
        try {
            ResultSet rs = dbFacade.getAllEmployees();
    
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                           All Employees                                                                              ║");
            System.out.println("╠════════╦════════════════════╦══════════════╦════════════════════╦════════════════════╦════════════════╦══════════════╦══════════════╦════════════════════════════════╣");
    
            System.out.printf("║ %-6s ║ %-18s ║ %-12s ║ %-18s ║ %-18s ║ %-14s ║ %-12s ║ %-12s ║ %-30s ║%n", 
                    "ID", "Username", "Role", "Name", "Surname", "Phone Number", "Birth Date", "Start Date", "Email");
            System.out.println("╠════════╬════════════════════╬══════════════╬════════════════════╬════════════════════╬════════════════╬══════════════╬══════════════╬════════════════════════════════╣");
    
            while (rs.next()) {
                System.out.printf("║ %-6d ║ %-18s ║ %-12s ║ %-18s ║ %-18s ║ %-14s ║ %-12s ║ %-12s ║ %-30s ║%n",
                rs.getInt("employee_id"),
                rs.getString("username"),
                rs.getString("role"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("phone_no"),
                rs.getString("dateofbirth"),
                rs.getString("dateofstart"),
                rs.getString("email"));
            }
            rs.close();
    
            // Print bottom border
            System.out.println("╚════════╩════════════════════╩══════════════╩════════════════════╩════════════════════╩════════════════╩══════════════╩══════════════╩════════════════════════════════╝");
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while trying to display employees");
            pressEnterToContinue(scanner);
        }
    }
    /**
     * Displays all employees filtered by their role.
     *
     * @param role The role to filter employees by.
     * @param scanner A Scanner object for user input.
     */
    public void displayAllEmployeesByRole(EmployeeRole role,Scanner scanner) {
        clearTerminal();
        try {
            ResultSet rs = dbFacade.getEmployeesByRole(role.toString());
    
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println(  "║                                                                  All Employees (" + padRight(role.toString()+")", 35) + "                                                  ║");
            System.out.println("╠════════╦════════════════════╦══════════════╦════════════════════╦════════════════════╦════════════════╦══════════════╦══════════════╦════════════════════════════════╣");
    
            System.out.printf("║ %-6s ║ %-18s ║ %-12s ║ %-18s ║ %-18s ║ %-14s ║ %-12s ║ %-12s ║ %-30s ║%n", 
                    "ID", "Username", "Role", "Name", "Surname", "Phone Number", "Birth Date", "Start Date", "Email");
            System.out.println("╠════════╬════════════════════╬══════════════╬════════════════════╬════════════════════╬════════════════╬══════════════╬══════════════╬════════════════════════════════╣");
    
            while (rs.next()) {
                System.out.printf("║ %-6d ║ %-18s ║ %-12s ║ %-18s ║ %-18s ║ %-14s ║ %-12s ║ %-12s ║ %-30s ║%n",
                        rs.getInt("employee_id"),
                        rs.getString("username"),
                        rs.getString("role"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("phone_no"),
                        rs.getString("dateofbirth"),
                        rs.getString("dateofstart"),
                        rs.getString("email"));
            }
            rs.close();
    
            // Print bottom border
            System.out.println("╚════════╩════════════════════╩══════════════╩════════════════════╩════════════════════╩════════════════╩══════════════╩══════════════╩════════════════════════════════╝");
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while trying to display employees");
            pressEnterToContinue(scanner);
        }
    }
    
    /**
     * Hires a new employee by adding them to the database.
     *
     * @param username The username of the new employee.
     * @param role The role of the new employee.
     * @param name The first name of the new employee.
     * @param surname The last name of the new employee.
     * @param phone The phone number of the new employee.
     * @param dob The date of birth of the new employee in the format "yyyy-MM-dd".
     * @param startDate The start date of employment in the format "yyyy-MM-dd".
     * @param email The email address of the new employee.
     * @param scanner A Scanner object for user input.
     */
    public void hireEmployee(String username, String role,String name,String surname,String phone,String dob,String startDate,String email,Scanner scanner) {
        
        // Add employee to the database (employee_id is auto-generated)
        boolean success = dbFacade.addEmployee(username, "password123", role, name, surname, phone, dob, startDate, email);

        if (success) {
            clearTerminal();
            AsciiArt.hired(name);
        } else {
            clearTerminal();
            AsciiArt.standardPrint("failed","\u001B[31m",0);
            pressEnterToContinue(scanner);
        }
    }

    /**
     * Fires an employee by their ID.
     *
     * @param scanner A Scanner object for user input.
     * @param id The ID of the employee to be fired.
     */
    public void fireEmployee(Scanner scanner, int id) {
        if(id == this.employeeId)
        {
            System.out.println("'" + id + "'" + " is your Employee ID. You can not fire yourself.");
            return;
        }
        try {
            // Fetch the employee data based on the ID entered
            ResultSet rs = dbFacade.getEmployeeById(id);
    
            // Check if a valid employee record was found
            if (rs != null && rs.next()) {
                String firedName = rs.getString("name");
                boolean success = dbFacade.deleteEmployee(id);
                if (success) {
                    AsciiArt.burn(firedName);
                    System.out.println(firedName+" is fired successfully.");
                }

            } else {
                System.out.println("Failed to fire employee. Please ensure the ID is correct.");
            }
        } catch (SQLException e) {
            System.out.println("Error accessing employee data: " + e.getMessage());
        }        
    }
    /**
     * Displays the main menu for the manager with various management options.
     *
     * @param scanner A Scanner object for user input.
     */
    @Override
    public void displayMenu(Scanner scanner)
    {
        boolean logout = false;

        while (!logout) {

            int choice = 0;
            // Ensure that the input is a valid integer
            boolean validInput = false;
            while (!validInput) {
                clearTerminal();
                System.out.println("╔════════════════════════════════════════════════╗");
                System.out.println("║                    MAIN Menu                   ║");
                System.out.println("╠═══════════════════════╦════════════════════════╣");
                System.out.println("║ " + padRight(this.getName() + " " + this.getSurname(), 22) + "║" + padLeft(this.getRole(), 23) + " ║");
                System.out.println("╚═══════════════════════╩════════════════════════╝");
                System.out.println("1. Display Own Profile");
                System.out.println("2. Update Own Profile");
                System.out.println("3. Display All Employees");
                System.out.println("4. Display All Employees Filtering by Role");
                System.out.println("5. Display Employee by User Name");
                System.out.println("6. Update Employee");
                System.out.println("7. Hire Employee");
                System.out.println("8. Fire Employee");
                System.out.println("9. Algorithms");
                System.out.println("10. Logout");
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
                    clearTerminal();
                    this.displayProfile();
                    scanner.nextLine();
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
                            System.out.println("╔══════════════════════════════════════════════════════╗");
                            System.out.println("║                 Profile Update Menu                  ║");
                            System.out.println("╚══════════════════════════════════════════════════════╝");
                            this.displayProfile();
                            System.out.println("1. Update Phone Number");
                            System.out.println("2. Update E-Mail");
                            System.out.println("3. Update Password");
                            System.out.println("4. Quit without Saving");
                            System.out.println("5. Save Changes and Quit");
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
                                this.updateProfile(null, "phoneNo", getValidPhoneNumber(scanner));
                                pressEnterToContinue(scanner);
                                break;
                            case 2:
                                this.updateProfile(null, "email", getValidEmail(scanner));
                                pressEnterToContinue(scanner);
                                break;
                            case 3:
                                this.updateProfile(null, "password", getValidPassword(scanner));
                                pressEnterToContinue(scanner);
                                break;
                            case 4:
                                exitSubmenu = true; // Exit the submenu
                                break;
                            case 5:
                                this.updateDB(null,scanner);
                                exitSubmenu = true; // Exit the submenu
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                scanner.nextLine();
                                pressEnterToContinue(scanner);
                        }
                    }
                    break;
                case 3:
                    this.displayAllEmployees(scanner);
                    scanner.nextLine();
                    pressEnterToContinue(scanner);
                    break;
                case 4:
                    exitSubmenu = false; // Control flag for the submenu
                    while (!exitSubmenu) {
                        int subChoice = 0;
                        boolean subValidInput = false;
                
                        // Ensure that the input is a valid integer
                        while (!subValidInput) {
                            clearTerminal();
                            System.out.println("╔════════════════════════════════════════════════════╗");
                            System.out.println("║          Display Filtered Employees Menu           ║");
                            System.out.println("╚════════════════════════════════════════════════════╝");
                            System.out.println("1. Managers");
                            System.out.println("2. Engineers");
                            System.out.println("3. Technicians");
                            System.out.println("4. Interns");
                            System.out.println("5. Exit");
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
                                this.displayAllEmployeesByRole(EmployeeRole.MANAGER,scanner);
                                scanner.nextLine();
                                pressEnterToContinue(scanner);
                                break;
                            case 2:
                                this.displayAllEmployeesByRole(EmployeeRole.ENGINEER,scanner);
                                scanner.nextLine();
                                pressEnterToContinue(scanner);
                                break;
                            case 3:
                                this.displayAllEmployeesByRole(EmployeeRole.TECHNICIAN,scanner);
                                scanner.nextLine();
                                pressEnterToContinue(scanner);
                                break;
                            case 4:
                                this.displayAllEmployeesByRole(EmployeeRole.INTERN,scanner);
                                scanner.nextLine();
                                pressEnterToContinue(scanner);
                                break;
                            case 5:
                                exitSubmenu = true; // Exit the submenu
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                scanner.nextLine();
                                pressEnterToContinue(scanner);
                        }
                    }
                    break;
                case 5:
                    clearTerminal();
                    System.out.println("╔══════════════════════════════════════════════════════╗");
                    System.out.println("║          Searching Employee by Username Menu         ║");
                    System.out.println("╚══════════════════════════════════════════════════════╝");
                    System.out.println("Via this menu you can serach an employee by Username");
                    pressEnterToContinue(scanner);
                    this.displayEmployeeByUsername(getValidUsername(scanner, true),scanner);
                    pressEnterToContinue(scanner);
                    break;
                case 6:
                    exitSubmenu = false;
                    Employee obj = null; // Declare the Employee object outside the loop so it can be reused
                    EmployeeRole role = null;
                    
                    // Ask for the employee ID once
                    ResultSet rs = null;
                    clearTerminal();
                    System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
                    System.out.println("║                   Employee Information Update Menu                ║");
                    System.out.println("╚═══════════════════════════════════════════════════════════════════╝");
                    System.out.println("Via this menu you can update non-profile informations of an employee.");
                    pressEnterToContinue(scanner);
                    int employeeId = getValidId(scanner);  // Get the valid employee ID once at the beginning
                    if(employeeId == this.employeeId)
                    {
                        System.out.println("'" + employeeId + "'" + " is your Employee ID. You can not update your non-profile informations.");
                        scanner.nextLine();
                        pressEnterToContinue(scanner);
                        break;
                    }
                    if(employeeId == 0)
                        break;
                    
                    try {
                        // Fetch the employee data based on the ID entered
                        rs = dbFacade.getEmployeeById(employeeId);
                
                        // Check if a valid employee record was found
                        if (rs != null && rs.next()) {
                            String roleString = rs.getString("role").trim().toUpperCase();
                            role = EmployeeRole.valueOf(roleString);
                            if (role == EmployeeRole.MANAGER) {
                                obj = new Manager(rs.getInt("employee_id"), rs.getString("username"), rs.getString("password"), role, rs.getString("name"), rs.getString("surname"), rs.getString("phone_no"), rs.getString("dateofbirth"), rs.getString("dateofstart"), rs.getString("email"));
                            } else {
                                obj = new StandardEmployee(rs.getInt("employee_id"), rs.getString("username"), rs.getString("password"), role, rs.getString("name"), rs.getString("surname"), rs.getString("phone_no"), rs.getString("dateofbirth"), rs.getString("dateofstart"), rs.getString("email"));
                            }
                        } else {
                            System.out.println("Failed to fire employee. Please ensure the ID is correct.");
                            scanner.nextLine();
                            pressEnterToContinue(scanner);
                            break; // Exit the case if the employee is not found
                        }
                    } catch (SQLException e) {
                        System.out.println("Error accessing employee data: " + e.getMessage());
                        pressEnterToContinue(scanner);
                        break; // Exit the case if there's an error
                    }
                
                    // Now we enter the loop where the user can make changes to the employee's data
                    while (!exitSubmenu) {
                        int subChoice = 0;
                        boolean subValidInput = false;
                
                        // Ensure that the input is a valid integer for the submenu
                        while (!subValidInput) {
                            clearTerminal();
                            System.out.println("╔══════════════════════════════════════════════════════╗");
                            System.out.println("║           Employee Information Update Menu           ║");
                            System.out.println("╚══════════════════════════════════════════════════════╝");
                            obj.displayProfile();
                            System.out.println("1. Update User Name");
                            System.out.println("2. Update Name");
                            System.out.println("3. Update Surname");
                            System.out.println("4. Update Role");
                            System.out.println("5. Update Birth Date");
                            System.out.println("6. Update Hire Date");
                            System.out.println("7. Quit without Saving");
                            System.out.println("8. Save Changes and Quit");
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
                
                        // Handle menu choices
                        switch (subChoice) {
                            case 1:
                                updateProfile(obj, "username", getValidUsername(scanner, false));
                                pressEnterToContinue(scanner);
                                break;
                            case 2:
                                updateProfile(obj, "name", getValidName(scanner));
                                pressEnterToContinue(scanner);
                                break;
                            case 3:
                                updateProfile(obj, "surname", getValidSurname(scanner));
                                pressEnterToContinue(scanner);
                                break;
                            case 4:
                                updateProfile(obj, "role", getValidRole(scanner));
                                pressEnterToContinue(scanner);
                                break;
                            case 5:
                                updateProfile(obj, "dateOfBirth", getValidDate(scanner, "Birth", "birthDate", null));
                                pressEnterToContinue(scanner);
                                break;
                            case 6:
                                updateProfile(obj, "dateOfStart", getValidDate(scanner, "Starting", "jobStartDate", LocalDate.parse(obj.dateOfBirth)));
                                pressEnterToContinue(scanner);
                                break;
                            case 7:
                                exitSubmenu = true; // Exit the submenu
                                break;
                            case 8:
                                updateDB(obj,scanner);  // Save the changes to the database
                                exitSubmenu = true; // Exit the submenu
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                scanner.nextLine();
                                pressEnterToContinue(scanner);
                        }
                    }
                    break;                
                
                case 7:
                    clearTerminal();
                    System.out.println("╔═══════════════════════════════════════════════════════════════════════════════╗");
                    System.out.println("║                               Hire Employee Menu                              ║");
                    System.out.println("╚═══════════════════════════════════════════════════════════════════════════════╝");
                    System.out.println("Via this menu you can hire a new employee by filling all neccessary informations.");
                    pressEnterToContinue(scanner);
                    String usernameH = getValidUsername(scanner, false);
                    String nameH = getValidName(scanner);
                    String surnameH = getValidSurname(scanner);
                    String roleH = getValidRole(scanner);
                    String birthH = getValidDate(scanner,"Birth", "birthDate", null);
                    String startH = getValidDate(scanner,"Starting", "jobStartDate", LocalDate.parse(birthH));
                    this.hireEmployee(usernameH, roleH, nameH, surnameH, "", birthH, startH, "",scanner);
                    break;
                case 8:
                    clearTerminal();
                    System.out.println("╔═════════════════════════════════════════════════════╗");
                    System.out.println("║                   Fire Employee Menu                ║");
                    System.out.println("╚═════════════════════════════════════════════════════╝");
                    System.out.println("Via this menu you can fire an employee by employee ID.");
                    pressEnterToContinue(scanner);
                    int id = getValidId(scanner); 
                    if(id != 0){
                        this.fireEmployee(scanner, id);
                        scanner.nextLine();
                        pressEnterToContinue(scanner);
                    }
                    break;
                case 9:
                    Algorithm algo = new Algorithm(getValidArraySize(scanner));
                    algo.algorithmsTimeSpends();
                    pressEnterToContinue(scanner);
                    break;
                case 10:
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    scanner.nextLine();
                    pressEnterToContinue(scanner);
            }
        }
    }
}