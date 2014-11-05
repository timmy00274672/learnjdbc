package com.diplab.timmy00274672.learn_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "dip-LAB";

    public static void main(String[] args) {
	Connection conn = null;
	Statement stmt = null;
	try {
	    // STEP 2: Register JDBC driver
	    // STEP 3: Open a connection
	    System.out.println("Connecting to database...");
	    conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    stmt = conn.createStatement();
	    String sql;
	    sql = "SELECT id, project_id, start_page, status FROM wikis";
	    ResultSet rs = stmt.executeQuery(sql);
	    // STEP 5: Extract data from result set
	    while (rs.next()) {
		// Retrieve by column name
		int id = rs.getInt("id");
		int project_id = rs.getInt("project_id");
		String start_page = rs.getString("start_page");

		System.out.format("Id = %d, project_id = %d, Start_page = %s\n", id,project_id,start_page);
	    }
	    // STEP 6: Clean-up environment
	    rs.close();
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
}
