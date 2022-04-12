package com.epam.data.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.data.model.User;
import com.epam.data.model.UserAccount;

public class AccountsDao {
	public static Logger logger = Logger.getLogger("AccountsDao");

	public static List<UserAccount> getUserAccounts(Connection connection, User user, String language) {
		List<UserAccount> accounts = new ArrayList<>();
		UserAccount account = null;
		try (Statement stmt = connection.createStatement();
				ResultSet resultSet = stmt
						.executeQuery("SELECT * FROM accounts ac\n" + "where ac.user_id = " + user.getId() + ";")) {
			while (resultSet.next()) {
				account = createAccount(connection, resultSet, language, user);
				accounts.add(account);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return accounts;

	}

	private static UserAccount createAccount(Connection connection, ResultSet resultSet, String language, User user)
			throws SQLException {
		return new UserAccount(resultSet.getInt("id"), resultSet.getString("name_" + language), user,
				AccountStatusDao.getAccountStatus(connection, resultSet.getInt("account_status_id"), language),
				resultSet.getDouble("balance"));
	}

	public static UserAccount getUserAccountById(Connection connection, int account_id, User user, String language) {
		UserAccount userAccount = null;
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM ACCOUNTS\n" + "WHERE id =" + account_id + "")) {
			while (resultSet.next()) {
				userAccount = createAccount(connection, resultSet, language, user);

			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return userAccount;

	}

}
