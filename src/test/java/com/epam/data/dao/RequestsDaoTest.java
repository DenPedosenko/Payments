package com.epam.data.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.epam.data.model.AccountStatus;
import com.epam.data.model.Request;
import com.epam.data.model.RequestStatus;
import com.epam.data.model.User;
import com.epam.data.model.UserAccount;
import com.epam.data.model.UserStatus;
import com.epam.data.model.UserType;
import com.epam.utils.TestUtils;
import com.epam.utils.Utils;

public class RequestsDaoTest {
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
	public void shouldInsertRequest() {
		int actual = RequestsDao.createNewRequest(connection, 3, 1, 1);
		int expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void shouldChangenRequestStatus() {
		int actual = RequestsDao.changeRequsetStatus(connection, 1, 1);
		int expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void shouldReturnRequest() {
		User testUser  = new User(4, "Sauron", "Maia",
				"the_dark_lord@example.com", "6666", new UserType(1, "User"), new UserStatus(2, "Blocked"));
		UserAccount testUserAccount = new UserAccount(3, "Payment", new AccountStatus(2, "Blocked"), 100.0);
		UserAccount testUserAccount2 = new UserAccount(4, "Bonus", new AccountStatus(2, "Blocked"), 100.0);
		List<UserAccount> userAccounts= List.of(testUserAccount, testUserAccount2);
		testUser.setAccounts(userAccounts);
		Request actual = RequestsDao.getRequestById(connection, "en", 1);
		Request expected = 	new Request(1,  testUser, testUserAccount2,  LocalDateTime.parse("2020-10-09 00:00:00", Utils.getDateTimeFormater("en")), new RequestStatus(1, "Active"));
				;
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
