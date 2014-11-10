package com.diplab.timmy00274672.learn_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtility {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "dip-LAB";

    static Connection getConnection() throws ClassNotFoundException,
	    SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	// System.out.println("Connecting to database...");
	return DriverManager.getConnection(DB_URL, USER, PASS);

    }

    public static void prepareDatabase() throws SQLException,
	    ClassNotFoundException {
	Object data[][] =
		{
			{ 100, 18, "Zara", "Ali" },
			{ 101, 25, "Mahnaz", "Fatma" },
			{ 102, 35, "Zaid", "Khan" } };
	/*
	 * 100 | 18 | Zara | Ali | | 101 | 25 | Mahnaz | Fatma | | 102 | 35 |
	 * Zaid | Khan | | 103 | 30 | Sumit | Mittal |
	 */
	Connection connection = getConnection();
	connection.createStatement().executeUpdate(
		"DELETE FROM Employees WHERE 1");

	String sql = "INSERT INTO Employees VALUES (?, ?, ?, ?)";
	PreparedStatement stmt = connection.prepareStatement(sql);

	for (int i = 0; i < data.length; i++) {
	    stmt.setInt(1, (Integer) data[i][0]);
	    stmt.setInt(2, (Integer) data[i][1]);
	    stmt.setString(3, (String) data[i][2]);
	    stmt.setString(4, (String) data[i][3]);
	    stmt.executeUpdate();
	}

	stmt.close();
	connection.close();
    }

    public static void main(String[] args) throws ClassNotFoundException,
	    SQLException {
	prepareDatabase();
	System.out.println("END");
    }
}
