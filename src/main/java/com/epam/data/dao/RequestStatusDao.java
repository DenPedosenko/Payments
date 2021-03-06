package com.epam.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.data.model.RequestStatus;

public class RequestStatusDao {
	private static Logger logger = Logger.getLogger(RequestStatusDao.class);
	
	public static RequestStatus getRequestStatus(Connection connection, String language, int id) {
		String selectQuery = "SELECT * FROM request_statuses WHERE id=?";
		RequestStatus requestStatus = null;
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
            	requestStatus = new RequestStatus(resultSet.getInt("id"), resultSet.getString("name_" + language));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return requestStatus;
		
	}
}
