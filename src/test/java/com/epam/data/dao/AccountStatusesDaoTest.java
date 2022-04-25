package com.epam.data.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.epam.data.model.AccountStatus;
import com.epam.utils.TestUtils;

public class AccountStatusesDaoTest {
	static Connection connection;
	static Logger logger = Logger.getLogger(UserDaoTest.class);

	@BeforeAll
	public static void setup() {
		BasicConfigurator.configure();
		connection = TestUtils.getTestConnection();
		try {
			TestUtils.beforeTests(connection);
		} catch (FileNotFoundException | SQLException e) {
			logger.info(e.getMessage());
		}
	}

	@Test
	public void shouldReturnUserAccountStatus() {
		AccountStatus actual = AccountStatusDao.getAccountStatus(connection, 1, "en");
		AccountStatus expected = new AccountStatus(1, "Active");
		assertEquals(expected, actual);
	}

	public Connection getConnection() {
		return connection;
	}

	@AfterAll
	public static void DropDatabase() {
		try {
			TestUtils.afterTests(connection);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

}
