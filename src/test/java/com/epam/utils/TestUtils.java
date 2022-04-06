package com.epam.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class TestUtils {	
    public static Logger logger = Logger.getLogger("TestUtils");

	
	public static Connection getTestConnection() {
		Connection connection = null;
		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payments?user=root&password=root");
			
		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e.getMessage());
		}
		
		return connection;
		
	}
	
	public static void beforeTests() {
		
	}
	
	public static void  afterTests() {
		
	}

}
