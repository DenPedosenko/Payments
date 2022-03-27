package com.epam.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.data.model.UserStatus;

public class UserStatusDao {

	private static Logger logger = Logger.getLogger("UserStatusDao");

    public static UserStatus getStatus(Connection connection, String status, String language) {
        String getUserStatusQuery = "SELECT * " +
                "FROM user_statuses us\n" +
                "WHERE us.name_" + language + "=?;";
        try (PreparedStatement getUserStatusStatement = connection.prepareStatement(getUserStatusQuery)) {
            getUserStatusStatement.setString(1, status);
            ResultSet resultSet = getUserStatusStatement.executeQuery();
            if (resultSet.next()) {
            	System.out.println(resultSet.getString("name_"+language));
                return new UserStatus(resultSet.getInt("id"), resultSet.getString("name_" + language));
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return null;
    }
    
    public static UserStatus getStatusById(Connection connection, int id, String language) {
        String getUserStatusQuery = "SELECT * " +
                "FROM user_statuses us\n" +
                "WHERE us.id=?;";
        try (PreparedStatement getUserStatusStatement = connection.prepareStatement(getUserStatusQuery)) {
            getUserStatusStatement.setInt(1, id);
            ResultSet resultSet = getUserStatusStatement.executeQuery();
            if (resultSet.next()) {
                return new UserStatus(resultSet.getInt("id"), resultSet.getString("name_" + language));
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return null;
    }
}
