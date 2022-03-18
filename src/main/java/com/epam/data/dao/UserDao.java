package com.epam.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.data.model.User;

public class UserDao {	
    public static Logger logger = Logger.getLogger("UserDao");
    
	public static List<User> getUsers(Connection connection) {
		User user = null;
		List<User> users = new ArrayList<User>();
		try(Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * from users");
			while (rs.next()) {
				user = new User();
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setId(rs.getInt("id"));
				users.add(user);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public static int registerUser(Connection connection, User user) {
		 String insertQuery = "INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id)\n" +
	                "VALUES (?, ?, ?, ?, ?, ?, ?);";
	        try (PreparedStatement insertUserStatement = connection.prepareStatement(insertQuery)) {
	            insertUserStatement.setString(1, user.getFirstName());
	            insertUserStatement.setString(2, user.getLastName());
	            insertUserStatement.setString(3, user.getEmail());
	            insertUserStatement.setString(4, user.getPassword());
	            insertUserStatement.setInt(5, user.getUserType().getId());
	            insertUserStatement.setInt(6, user.getUserStatus().getId());
	            insertUserStatement.setNull(7, java.sql.Types.INTEGER);
	            return insertUserStatement.executeUpdate();
	        } catch (SQLException e) {
	            logger.info(e.getMessage());
	        }
	        return 0;
	}
}
