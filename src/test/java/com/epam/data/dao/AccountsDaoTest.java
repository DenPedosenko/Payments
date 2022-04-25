package com.epam.data.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.data.model.AccountStatus;
import com.epam.data.model.UserAccount;
import com.epam.utils.TestUtils;

public class AccountsDaoTest {
	static Connection connection;
	static Logger logger = Logger.getLogger(UserDaoTest.class);

	@BeforeAll
	public static void setup() {
		BasicConfigurator.configure();
		connection = TestUtils.getTestConnection();
	}

	@BeforeEach
	public void configureDatabase() {
		try {
			TestUtils.beforeTests(connection);
		} catch (FileNotFoundException | SQLException e) {
			logger.info(e.getMessage());
		}
	}

	@Test
	public void shouldReturnUserAccounById() {
		UserAccount actual = AccountsDao.getUserAccountById(connection, 1, "en");
		UserAccount expected = new UserAccount(1, "Payment", new AccountStatus(1, "Active"), 100.0);
		assertEquals(expected, actual);
	}

	@Test
	public void shouldReturnUsersAccounts() {
		List<UserAccount> actual = AccountsDao.getUserAccounts(connection, 3, "en");
		List<UserAccount> expected = List.of(new UserAccount(1, "Payment", new AccountStatus(1, "Active"), 100.0),
				new UserAccount(2, "Bonus", new AccountStatus(1, "Active"), 100.0));
		assertEquals(actual, expected);
	}

	@Test
	public void shouldChangeAccountBalance() {
		int actual = AccountsDao.changeAccountBalance(connection, 1, 200.0);
		assertEquals(actual, 1);
	}

	public Connection getConnection() {
		return connection;
	}

	@AfterEach
	public void DropDatabase() {
		try {
			TestUtils.afterTests(connection);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

}
