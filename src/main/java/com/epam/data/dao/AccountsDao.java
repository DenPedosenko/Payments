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
import com.epam.data.model.UserAccount;

public class AccountsDao {
	public static Logger logger = Logger.getLogger(AccountsDao.class);

	public static List<UserAccount> getUserAccounts(Connection connection, int id, String language) {
		List<UserAccount> accounts = new ArrayList<>();
		UserAccount account = null;
		try (Statement stmt = connection.createStatement();
				ResultSet resultSet = stmt
						.executeQuery("SELECT * FROM accounts ac\n" + "where ac.user_id = " + id + ";")) {
			while (resultSet.next()) {
				account = createAccount(connection, resultSet, language);
				accounts.add(account);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return accounts;
	}

	private static UserAccount createAccount(Connection connection, ResultSet resultSet, String language)
			throws SQLException {
		return new UserAccount(resultSet.getInt("id"), resultSet.getString("name_" + language), AccountStatusDao.getAccountStatus(connection, resultSet.getInt("account_status_id"), language),
				resultSet.getDouble("balance"));
	}

	public static UserAccount getUserAccountById(Connection connection, int account_id,  String language) {
		UserAccount userAccount = null;
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM ACCOUNTS\n" + "WHERE id =" + account_id + "")) {
			while (resultSet.next()) {
				userAccount = createAccount(connection, resultSet, language);

			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return userAccount;

	}
	
	public static int changeAccountStatus(Connection connection, int id, int statusId) {
        String changeStatusQuery = "UPDATE accounts SET account_status_id=? WHERE id=?;";
        try (PreparedStatement changeStatusStatement = connection.prepareStatement(changeStatusQuery)) {
            changeStatusStatement.setInt(1, statusId);
            changeStatusStatement.setInt(2, id);
            return changeStatusStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
	}
	
	public static int changeAccountBalance(Connection connection, int id, Double amount) {
        String changeAmountQuery = "UPDATE accounts SET balance=? WHERE id=?;";
        try (PreparedStatement changeAmountStatement = connection.prepareStatement(changeAmountQuery)) {
            changeAmountStatement.setDouble(1, amount);
            changeAmountStatement.setInt(2, id);
            return changeAmountStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
    } 

}
