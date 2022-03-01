package com.epam.data.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PaymentsDB {
	private static Connection connection;

	private PaymentsDB() {}

	public static Connection getConnection() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/TestDB");
			connection = ds.getConnection();
		} catch (SQLException | NamingException e) {
			System.out.println("Can't get class. No driver found");
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Can't close connection");
			e.printStackTrace();
			return;
		}
	}

}
