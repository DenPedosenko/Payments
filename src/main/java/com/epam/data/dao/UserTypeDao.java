package com.epam.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.data.model.UserType;

public class UserTypeDao {
	private static Logger logger = Logger.getLogger("UserTypeDao");

    public static UserType getTypeByName(Connection connection, String type, String language) {
        String getUserStatusQuery = "SELECT * " +
                "FROM user_types ut\n" +
                "WHERE ut.name_" + language + "=?;";
        try (PreparedStatement getUserStatusStatement = connection.prepareStatement(getUserStatusQuery)) {
            getUserStatusStatement.setString(1, type);
            ResultSet resultSet = getUserStatusStatement.executeQuery();
            if (resultSet.next()) {
                return new UserType(resultSet.getInt("id"), resultSet.getString("name_" + language));
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return null;
    }
    
    public static UserType getTypeById(Connection connection, int id, String language) {
        String getUserStatusQuery = "SELECT * " +
                "FROM user_types ut\n" +
                "WHERE ut.id=?;";
        try (PreparedStatement getUserStatusStatement = connection.prepareStatement(getUserStatusQuery)) {
            getUserStatusStatement.setInt(1, id);
            ResultSet resultSet = getUserStatusStatement.executeQuery();
            if (resultSet.next()) {
                return new UserType(resultSet.getInt("id"), resultSet.getString("name_" + language));
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return null;
    }
}
