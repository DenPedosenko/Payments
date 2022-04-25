package com.epam.utils;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;


public class TestUtils {	
    public static Logger logger = Logger.getLogger(TestUtils.class);

	
	public static Connection getTestConnection() {
		Connection connection = null;
		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=root");
			
		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e.getMessage());
		}
		
		return connection;
		
	}
	
	public static void beforeTests(Connection connection) throws FileNotFoundException, SQLException{
		 Statement statement = connection.createStatement();
	        String createDBQuery = "CREATE DATABASE IF NOT EXISTS paymentsTest";
	        statement.executeUpdate(createDBQuery);
	        String useDBQuery = "USE paymentsTest";
	        statement.execute(useDBQuery);
	        ScriptRunner sr = new ScriptRunner(connection);
	        Reader reader = new BufferedReader(new FileReader("createTables.sql"));
	        sr.runScript(reader);
	        reader = new BufferedReader(new FileReader("inserts.sql"));
	        sr.runScript(reader);
	}
	
	public static void  afterTests(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
        String createDBQuery = "DROP DATABASE paymentsTest";
        statement.execute(createDBQuery);
	}

}
