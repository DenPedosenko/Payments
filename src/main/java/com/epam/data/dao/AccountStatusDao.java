package com.epam.data.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.data.model.AccountStatus;

public class AccountStatusDao {
	public static Logger logger = Logger.getLogger(AccountStatusDao.class);

	public static AccountStatus getAccountStatus(Connection connection, int account_id, String language) {

		AccountStatus accountStatus = null;
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM ACCOUNT_STATUS\n" + "WHERE id =" + account_id + "")) {
			while (resultSet.next()) {
				accountStatus = createAccountStatus(resultSet, language);

			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return accountStatus;
	}

	private static AccountStatus createAccountStatus(ResultSet resultSet, String language) throws SQLException {
		return new AccountStatus(resultSet.getInt("id"), resultSet.getString("name_" + language));
	}
}
