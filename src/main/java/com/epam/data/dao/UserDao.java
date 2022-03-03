package com.epam.data.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.data.model.User;

public class UserDao {
	public static List<User> getUsers(Connection connection) {
		List<User> books = new ArrayList<User>();
		try(Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * from users");

			while (rs.next()) {
				books.add(new User(rs.getString("email"), rs.getString("user_password")));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return books;
	}
}
