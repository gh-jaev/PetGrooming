/*
 * DatabaseConnectivity.java
 * Handles database operations for the pet grooming application.
 */
package petgroom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnectivity {
    private static final String URL = "jdbc:mysql://localhost:3306/petgroom";
    private static final String USER = "root";
    private static final String PASSWORD = "@l03e1t3";
    private Connection connection;

    /**
     * Initializes the database connection.
     */
    public DatabaseConnectivity() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a new appointment to the database.
     * @param clientName Client's name
     * @param contact Client's contact information
     * @param petName Pet's name
     * @param petBreed Pet's breed
     * @param service Service type
     * @param sqlDate Appointment date
     * @return true if successful, false otherwise
     */
    public boolean saveAppointment(String clientName, String contact, String petName, String petBreed, String service, Date sqlDate) {
        String query = "INSERT INTO appointments (client_name, contact, date, pet_name, pet_breed, service) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, clientName);
            stmt.setString(2, contact);
            stmt.setDate(3, sqlDate);
            stmt.setString(4, petName);
            stmt.setString(5, petBreed);
            stmt.setString(6, service);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all appointments from the database.
     * @return List of appointment data as Object arrays
     */
    public List<Object[]> getAppointments() {
        List<Object[]> appointments = new ArrayList<>();
        String query = "SELECT client_name, contact, date, pet_name, pet_breed, service FROM appointments";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                appointments.add(new Object[]{
                    rs.getString("client_name"),
                    rs.getString("contact"),
                    rs.getDate("date"),
                    rs.getString("pet_name"),
                    rs.getString("pet_breed"),
                    rs.getString("service")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    /**
     * Saves a new payment to the database.
     * @param clientName Client's name
     * @param contact Client's contact information
     * @param totalPaid Amount paid
     * @param status Payment status (Paid or Downpayment)
     * @return true if successful, false otherwise
     */
    public boolean savePayment(String clientName, String contact, double totalPaid, String status) {
        String query = "INSERT INTO payments (client_name, contact, total_paid, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, clientName);
            stmt.setString(2, contact);
            stmt.setDouble(3, totalPaid);
            stmt.setString(4, status);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all payments from the database.
     * @return List of payment data as Object arrays
     */
    public List<Object[]> getPayments() {
        List<Object[]> payments = new ArrayList<>();
        String query = "SELECT client_name, contact, total_paid, status FROM payments";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                payments.add(new Object[]{
                    rs.getString("client_name"),
                    rs.getString("contact"),
                    rs.getDouble("total_paid"),
                    rs.getString("status")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    /**
     * Deletes an appointment from the database.
     * @param clientName Client's name
     * @param contact Client's contact information
     * @param sqlDate Appointment date
     * @return true if successful, false otherwise
     */
    public boolean deleteAppointment(String clientName, String contact, Date sqlDate) {
        String query = "DELETE FROM appointments WHERE client_name = ? AND contact = ? AND date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, clientName);
            stmt.setString(2, contact);
            stmt.setDate(3, sqlDate);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing appointment in the database.
     * @param oldClientName Original client's name
     * @param oldContact Original client's contact
     * @param oldDate Original appointment date
     * @param newClientName Updated client's name
     * @param newContact Updated client's contact
     * @param newPetName Updated pet's name
     * @param newPetBreed Updated pet's breed
     * @param newService Updated service type
     * @param newDate Updated appointment date
     * @return true if successful, false otherwise
     */
    public boolean updateAppointment(String oldClientName, String oldContact, Date oldDate, String newClientName, String newContact, String newPetName, String newPetBreed, String newService, Date newDate) {
        String query = "UPDATE appointments SET client_name = ?, contact = ?, date = ?, pet_name = ?, pet_breed = ?, service = ? WHERE client_name = ? AND contact = ? AND date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newClientName);
            stmt.setString(2, newContact);
            stmt.setDate(3, newDate);
            stmt.setString(4, newPetName);
            stmt.setString(5, newPetBreed);
            stmt.setString(6, newService);
            stmt.setString(7, oldClientName);
            stmt.setString(8, oldContact);
            stmt.setDate(9, oldDate);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a payment from the database.
     * @param clientName Client's name
     * @param contact Client's contact information
     * @param totalPaid Amount paid
     * @param status Payment status (Paid or Downpayment)
     * @return true if successful, false otherwise
     */
    public boolean deletePayment(String clientName, String contact, double totalPaid, String status) {
        String query = "DELETE FROM payments WHERE client_name = ? AND contact = ? AND total_paid = ? AND status = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, clientName);
            stmt.setString(2, contact);
            stmt.setDouble(3, totalPaid);
            stmt.setString(4, status);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Registers a new user in the database.
     * @param fullName User's full name
     * @param email User's email (unique)
     * @param password User's password
     * @return true if successful, false otherwise
     */
    public boolean registerUser(String fullName, String email, String password) {
        String query = "INSERT INTO users (full_name, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, password); // Note: In production, hash the password
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Validates user credentials against the database.
     * @param email User's email
     * @param password User's password
     * @return true if credentials are valid, false otherwise
     */
    public boolean validateUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password); // Note: In production, compare hashed passwords
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if a matching user is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}