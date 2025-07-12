#PetGrooming - Pet Grooming Management System
PetGrooming is a user-friendly desktop application designed to streamline the management of pet grooming appointments and payments for pet care businesses. Built with Java Swing for an intuitive GUI and MySQL for robust data management, this application provides a comprehensive solution for scheduling, tracking, and processing pet grooming services.
Features

User Authentication: Secure user registration and login system to manage access.
Appointment Management:
Create, update, and delete grooming appointments with details such as client name, contact, pet name, breed, service type, and date.
Interactive calendar interface for selecting appointment dates.


Payment Processing:
Handle full payments or downpayments with automatic change calculation.
View and delete payment records with a clear summary of transactions.


Service Consistency: Supports multiple grooming services (e.g., Full Groom, Trim, Bath, Vaccination) with predefined pricing for accurate billing.
Responsive UI: Built with Java Swing for a clean and intuitive user experience, featuring tables for viewing appointments and payments.
Database Integration: Uses MySQL to persistently store user, appointment, and payment data, ensuring reliability and scalability.

Tech Stack

Language: Java
GUI Framework: Java Swing
Database: MySQL (JDBC for connectivity)
Libraries: JCalendar for date selection
IDE: Developed with NetBeans for streamlined GUI design
Dependencies: MySQL Connector/J for database operations

Getting Started
Prerequisites

Java Development Kit (JDK) 8 or higher
MySQL Server 5.7 or higher
MySQL Connector/J (JDBC driver)
NetBeans IDE (optional, for easier project setup)

Installation

Clone the Repository:git clone https://github.com/your-username/petgrooming.git


Set Up the Database:
Create a MySQL database named petgroom.
Execute the following SQL to create required tables:CREATE TABLE users (
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE appointments (
    client_name VARCHAR(255) NOT NULL,
    contact VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    pet_name VARCHAR(255) NOT NULL,
    pet_breed VARCHAR(255) NOT NULL,
    service VARCHAR(255) NOT NULL
);

CREATE TABLE payments (
    client_name VARCHAR(255) NOT NULL,
    contact VARCHAR(255) NOT NULL,
    total_paid DOUBLE NOT NULL,
    status VARCHAR(50) NOT NULL
);


Update the database connection settings in DatabaseConnectivity.java if your MySQL credentials differ (default: root, @l03e1t3).


Build and Run:
Open the project in NetBeans or your preferred IDE.
Ensure the MySQL Connector/J JAR is included in the project’s library.
Run Main.java to launch the application.



Usage

Register/Login: Start by registering a new user or logging in with existing credentials.
Manage Appointments: Use the main dashboard to create, edit, or delete appointments, selecting services like Full Groom (₱600), Trim Groom (₱300), Full Bath (₱200), or Vaccination (₱1000).
Process Payments: Select an appointment, choose a service, and enter the cash amount to process full or partial payments. View payment history in the Payments section.
Navigate: Use the intuitive interface to switch between appointment management and payment tracking.

Future Improvements

Implement password hashing (e.g., BCrypt) for enhanced security.
Add connection pooling for better database performance.
Introduce input validation for contact numbers and email formats.
Enhance the UI with modern themes or additional visual feedback.
Add reporting features for appointment and payment analytics.

Contributing
Contributions are welcome! Please fork the repository, create a new branch, and submit a pull request with your changes. Ensure your code follows the existing style and includes appropriate tests.
License
This project is licensed under the MIT License - see the LICENSE file for details.
Contact
For questions or feedback, please open an issue on GitHub or contact the repository owner.

PetGrooming - Simplifying pet care management, one appointment at a time.
