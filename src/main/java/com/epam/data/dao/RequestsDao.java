package com.epam.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.data.model.Request;
import com.epam.data.model.User;
import com.epam.data.model.UserAccount;

public class RequestsDao {
	private static Logger logger = Logger.getLogger(RequestsDao.class);
	
	public static List<Request> getActiveRequests(Connection connection, String language) {
		List<Request> requests = new ArrayList<>();
		Request request = null;
		String selectQuery = "SELECT * FROM payments.requests p\r\n"
				+ "JOIN payments.request_statuses rs on p.request_status_id = rs.id\r\n"
				+ "WHERE rs.name_"+language+" =?";
		try(PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
			selectStatement.setString(1, "Активний");
			ResultSet resultSet = selectStatement.executeQuery();
			while (resultSet.next()) {
				request = createRequest(resultSet, connection, language);
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		return null;
		
	}

	private static Request createRequest(ResultSet resultSet, Connection connection, String language) throws SQLException {
		User user = UserDao.getUser(connection, resultSet.getInt("user_id"), language);
		return 	new Request(resultSet.getInt("id"), user, AccountsDao.getUserAccounts(connection, user, language),LocalDateTime.parse(resultSet.getString("creating_date")));
	}

}
