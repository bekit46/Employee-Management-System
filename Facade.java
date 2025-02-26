import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * This class provides a facade to interact with the employee database.
 * It encapsulates the database connection and CRUD operations for employee data.
 */
public class Facade {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/EmployeeDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "ubkt1234";

    /**
     * Establishes a connection to the database.
     * 
     * @return A Connection object representing the connection to the database.
     * @throws SQLException If there is an error while connecting to the database.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    /**
     * Executes a SELECT query on the database and returns the resulting ResultSet.
     * 
     * @param query  The SQL query to execute.
     * @param params The parameters to be set in the PreparedStatement.
     * @return A ResultSet containing the results of the query, or null if an error occurs.
     */
    public ResultSet executeQuery(String query, Object... params) {
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            setParameters(stmt, params);
            return stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Executes an UPDATE, INSERT, or DELETE query and returns the number of affected rows.
     * 
     * @param query  The SQL query to execute.
     * @param params The parameters to be set in the PreparedStatement.
     * @return The number of rows affected by the query.
     */
    public int executeUpdate(String query, Object... params) {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            setParameters(stmt, params);
            return stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Sets the parameters for a PreparedStatement.
     * 
     * @param stmt   The PreparedStatement object.
     * @param params The parameters to be set in the PreparedStatement.
     * @throws SQLException If there is an error setting the parameters.
     */
    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

    /**
     * Fetches all employees from the database.
     * 
     * @return A ResultSet containing all employee records.
     */
    public ResultSet getAllEmployees() {
        String query = "SELECT * FROM employees";
        return executeQuery(query);
    }

    /**
     * Fetches employees by role from the database.
     * 
     * @param role The role to filter employees by.
     * @return A ResultSet containing the employees with the specified role.
     */
    public ResultSet getEmployeesByRole(String role) {
        String query = "SELECT * FROM employees WHERE role = ?";
        return executeQuery(query, role);
    }

    /**
     * Fetches a single employee by username.
     * 
     * @param username The username of the employee.
     * @return A ResultSet containing the employee with the specified username.
     */
    public ResultSet getEmployeeByUsername(String username) {
        String query = "SELECT * FROM employees WHERE username = ?";
        return executeQuery(query, username);
    }

    /**
     * Fetches a single employee by ID.
     * 
     * @param employeeId The ID of the employee.
     * @return A ResultSet containing the employee with the specified ID.
     */
    public ResultSet getEmployeeById(int employeeId) {
        String query = "SELECT * FROM employees WHERE employee_id = ?";
        return executeQuery(query, employeeId);
    }

    /**
     * Deletes an employee by ID.
     * 
     * @param employeeId The ID of the employee to delete.
     * @return true if the employee was successfully deleted, false otherwise.
     */
    public boolean deleteEmployee(int employeeId) {
        String query = "DELETE FROM employees WHERE employee_id = ?";
        return executeUpdate(query, employeeId) > 0;
    }

    /**
     * Updates an employee's password in the database.
     * 
     * @param username    The username of the employee whose password is to be updated.
     * @param newPassword The new password.
     * @return true if the password was successfully updated, false otherwise.
     */
    public boolean updatePassword(String username, String newPassword) {
        String query = "UPDATE employees SET password = ? WHERE username = ?";
        return executeUpdate(query, newPassword, username) > 0;
    }

    /**
     * Inserts a new employee into the database.
     * 
     * @param username   The username of the new employee.
     * @param password   The password of the new employee.
     * @param role       The role of the new employee.
     * @param name       The name of the new employee.
     * @param surname    The surname of the new employee.
     * @param phoneNo    The phone number of the new employee.
     * @param dateOfBirth The date of birth of the new employee.
     * @param dateOfStart The start date of the new employee.
     * @param email      The email of the new employee.
     * @return true if the employee was successfully added, false otherwise.
     */
    public boolean addEmployee(String username, String password, String role, 
                                String name, String surname, String phoneNo, 
                                String dateOfBirth, String dateOfStart, String email) {
        String query = "INSERT INTO employees (username, password, role, name, surname, phone_no, " +
                       "dateofbirth, dateofstart, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return executeUpdate(query, username, "password123", role.toString(), name, surname, phoneNo, dateOfBirth, dateOfStart, email) > 0;
    }

    /**
     * Updates an employee's profile, including phone number and email.
     * 
     * @param employeeId The ID of the employee whose profile is to be updated.
     * @param phoneNo    The new phone number.
     * @param email      The new email address.
     * @return true if the profile was successfully updated, false otherwise.
     */
    public boolean updateProfile(int employeeId, String phoneNo, String email) {
        String query = "UPDATE employees SET phone_no = ?, email = ? WHERE employee_id = ?";
        return executeUpdate(query, phoneNo, email, employeeId) > 0;
    }

    /**
     * Fetches the profile details of a specific employee.
     * 
     * @param employeeId The ID of the employee whose profile is to be fetched.
     * @return A ResultSet containing the profile details of the employee.
     */
    public ResultSet getEmployeeProfile(int employeeId) {
        String query = "SELECT * FROM employees WHERE employee_id = ?";
        return executeQuery(query, employeeId);
    }
}