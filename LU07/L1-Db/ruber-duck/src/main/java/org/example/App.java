package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class App {

    // Adjust these based on your Docker setup
    private static final String URL = "jdbc:mysql://localhost:3306/duck_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS rubber_ducks (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100),
                cost DECIMAL(10, 2),
                type ENUM('CLASSIC', 'SUPERHERO', 'GLOW_IN_DARK', 'PROGRAMMER')
            )
            """;

        String insertSQL = "INSERT INTO rubber_ducks (name, cost, type) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {

            //Create the table
            statement.execute(createTableSQL);
            System.out.println("Table 'rubber_ducks' is ready.");

            //Insert data using a PreparedStatement (Prevents SQL Injection)
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, "Classic Yellow");
                pstmt.setDouble(2, 4.99);
                pstmt.setString(3, "CLASSIC");
                pstmt.executeUpdate();

                pstmt.setString(1, "Debug Wizard");
                pstmt.setDouble(2, 12.50);
                pstmt.setString(3, "PROGRAMMER");
                pstmt.executeUpdate();

                System.out.println("Duck data inserted successfully!");
            }

            // Querying data
            var resultSet = statement.executeQuery("SELECT * FROM rubber_ducks");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double cost = resultSet.getDouble("cost");
                String type = resultSet.getString("type");
                System.out.printf("ID: %d, Name: %s, Cost: %.2f, Type: %s%n", id, name, cost, type);
            }

            //Clean up the table after use
            // Uncomment the line below to drop the table after testing
            cleanUp();

            //Disconnect from DB
            conn.close();

        } catch (Exception e) {
           System.err.println("Unable to connect to the database OR execute SQL: " + e.getMessage());
        }   
    }

    public static void cleanUp() {
        String dropTableSQL = "DROP TABLE IF EXISTS rubber_ducks";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = conn.createStatement()) {
            statement.execute(dropTableSQL);
            System.out.println("Table 'rubber_ducks' has been dropped.");
        } catch (Exception e) {
            System.err.println("Error during cleanup: " + e.getMessage());
        }
    }
}       

    

