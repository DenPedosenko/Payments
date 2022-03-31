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

	public static List<User> getUsers(Connection connection, String language) {
		User user = null;
		List<User> users = new ArrayList<User>();
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * from users")) {
			while (rs.next()) {
				user = createUser(connection, rs, language);
				users.add(user);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public static int registerUser(Connection connection, User user) {
		String insertQuery = "INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id)\n"
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		try (PreparedStatement insertUserStatement = connection.prepareStatement(insertQuery)) {
			insertUserStatement.setString(1, user.getFirstName());
			insertUserStatement.setString(2, user.getLastName());
			insertUserStatement.setString(3, user.getEmail());
			insertUserStatement.setString(4, user.getPassword());
			insertUserStatement.setInt(5, user.getUserType().getId());
			insertUserStatement.setInt(6, user.getUserStatus().getId());
			return insertUserStatement.executeUpdate();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return 0;
	}

	public static User loginUser(Connection connection, String email, String password, String language) {

		String query = "SELECT * FROM USERS u\n" + "WHERE email=? AND user_password=?;";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return createUser(connection, resultSet, language);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	private static User createUser(Connection connection, ResultSet resultSet, String language) throws SQLException {
		return new User(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_name"),
				resultSet.getString("email"), resultSet.getString("user_password"),
				UserTypeDao.getTypeById(connection, resultSet.getInt("user_type_id"), language),
				UserStatusDao.getStatusById(connection, resultSet.getInt("user_status_id"), language));
	}

	public static User getUser(Connection connection, int id, String language) {
		User user = null;
		try (Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * from users where id =" + id + "")) {
			while (rs.next()) {
				user = createUser(connection, rs, language);
			}
			rs.close();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return user;
	}
}
