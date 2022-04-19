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
import com.epam.utils.Utils;

public class RequestsDao {
	private static Logger logger = Logger.getLogger(RequestsDao.class);

	public static List<Request> getActiveRequests(Connection connection, String language) {
		List<Request> requests = new ArrayList<>();
		Request request = null;
		String selectQuery = "SELECT * FROM payments.requests p\r\n"
				+ "JOIN payments.request_statuses rs on p.request_status_id = rs.id\r\n" + "WHERE rs.name_" + language
				+ " =?";
		try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
			selectStatement.setString(1, language.equals("en") ? "Active" : "Активний");
			ResultSet resultSet = selectStatement.executeQuery();
			while (resultSet.next()) {
				request = createRequest(resultSet, connection, language);
				requests.add(request);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return requests;
	}
	
	public static Request getRequestById(Connection connection, String language, int id) {
		String selectQuery = "SELECT * FROM requests WHERE id=?";
		Request request = null;
		try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
			selectStatement.setInt(1, id);
			ResultSet resultSet = selectStatement.executeQuery();
			while (resultSet.next()) {
				request = createRequest(resultSet, connection, language);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return request;
	}

	public static List<Request> getRequests(Connection connection, String language) {
		List<Request> requests = new ArrayList<>();
		Request request = null;
		String selectQuery = "SELECT * FROM payments.requests";
		try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
			ResultSet resultSet = selectStatement.executeQuery();
			while (resultSet.next()) {
				request = createRequest(resultSet, connection, language);
				requests.add(request);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return requests;

	}

	private static Request createRequest(ResultSet resultSet, Connection connection, String language)
			throws SQLException {
		User user = UserDao.getUser(connection, resultSet.getInt("user_id"), language);
		return new Request(resultSet.getInt("id"), user,
				AccountsDao.getUserAccountById(connection, resultSet.getInt("account_id"), user, language),
				LocalDateTime.parse(resultSet.getString("creating_date"), Utils.getDateTimeFormater(language)));

	}
	
	public static int createNewRequest(Connection connection, int userId, int accountId, int requestStatusId) {
		if (checkIfRequestAlreadyExists(connection, userId, accountId)) {
			logger.debug(0);
			return 0;
		}
		String insertQuery = "INSERT INTO requests(user_id, account_id, creating_date,  request_status_id)\n"
				+ "VALUES (?, ?, NOW(), ?);";
		try (PreparedStatement insertUserStatement = connection.prepareStatement(insertQuery)) {
			insertUserStatement.setInt(1, userId);
			insertUserStatement.setInt(2, accountId);
			insertUserStatement.setInt(3, requestStatusId);
			return insertUserStatement.executeUpdate();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return 0;
	}
	
	public static boolean checkIfRequestAlreadyExists(Connection connection, int userId, int accountId) {
		String selectQuery = "SELECT * FROM requests where user_id=? and account_id=? and request_status_id=1;";
		try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
			selectStatement.setInt(1, userId);
			selectStatement.setInt(2, accountId);
			ResultSet resultSet = selectStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	
	public static int changeRequsetStatus(Connection connection, int id, int statusId) {
        String changeStatusQuery = "UPDATE requests SET request_status_id=? WHERE id=?;";
        try (PreparedStatement changeStatusStatement = connection.prepareStatement(changeStatusQuery)) {
            changeStatusStatement.setInt(1, statusId);
            changeStatusStatement.setInt(2, id);
            return changeStatusStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
	}

}
