package com.diplab.timmy00274672.learn_jdbc;

//Import required packages
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class BatchDemo {

    public static void main(String[] args) {
	Connection conn = null;
	PreparedStatement stmt = null;
	try {
	    // Register JDBC driver
	    Class.forName("com.mysql.jdbc.Driver");

	    // Open a connection
	    System.out.println("Connecting to database...");
	    DatabaseUtility.prepareDatabase();
	    conn = DatabaseUtility.getConnection();

	    System.out.println(conn.getMetaData().supportsBatchUpdates());
	    
	    // Create SQL statement
	    String SQL =
		    "INSERT INTO Employees(id,first,last,age) "
			    + "VALUES(?, ?, ?, ?)";

	    // Create preparedStatemen
	    System.out.println("Creating statement...");
	    stmt = conn.prepareStatement(SQL);

	    // Set auto-commit to false
	    conn.setAutoCommit(false);

	    // First, let us select all the records and display them.
	    printRows(stmt);

	    // Set the variables
	    stmt.setInt(1, 400);
	    stmt.setString(2, "Pappu");
	    stmt.setString(3, "Singh");
	    stmt.setInt(4, 33);
	    // Add it to the batch
	    stmt.addBatch();

	    // Set the variables
	    stmt.setInt(1, 401);
	    stmt.setString(2, "Pawan");
	    stmt.setString(3, "Singh");
	    stmt.setInt(4, 31);
	    // Add it to the batch
	    stmt.addBatch();

	    // Create an int[] to hold returned values
	    int[] count = stmt.executeBatch();
	    System.out.println(Arrays.toString(count));
	    // Explicitly commit statements to apply changes
	    conn.commit();

	    // Again, let us select all the records and display them.
	    printRows(stmt);

	    // Clean-up environment
	    stmt.close();
	    conn.close();
	} catch (SQLException se) {
	    // Handle errors for JDBC
	    se.printStackTrace();
	} catch (Exception e) {
	    // Handle errors for Class.forName
	    e.printStackTrace();
	} finally {
	    // finally block used to close resources
	    try {
		if (stmt != null)
		    stmt.close();
	    } catch (SQLException se2) {
	    }// nothing we can do
	    try {
		if (conn != null)
		    conn.close();
	    } catch (SQLException se) {
		se.printStackTrace();
	    }// end finally try
	}// end try
	System.out.println("Goodbye!");
    }// end main

    public static void printRows(Statement stmt) throws SQLException {
	System.out.println("Displaying available rows...");
	// Let us select all the records and display them.
	String sql = "SELECT id, first, last, age FROM Employees";
	ResultSet rs = stmt.executeQuery(sql);

	while (rs.next()) {
	    // Retrieve by column name
	    int id = rs.getInt("id");
	    int age = rs.getInt("age");
	    String first = rs.getString("first");
	    String last = rs.getString("last");

	    // Display values
	    System.out.print("ID: " + id);
	    System.out.print(", Age: " + age);
	    System.out.print(", First: " + first);
	    System.out.println(", Last: " + last);
	}
	System.out.println();
	rs.close();
    }// end printRows()
}// end JDBCExample