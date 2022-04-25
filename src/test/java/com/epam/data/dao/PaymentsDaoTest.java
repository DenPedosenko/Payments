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
import com.epam.data.model.Payment;
import com.epam.data.model.PaymentStatus;
import com.epam.data.model.PaymentType;
import com.epam.data.model.User;
import com.epam.data.model.UserAccount;
import com.epam.data.model.UserStatus;
import com.epam.data.model.UserType;
import com.epam.utils.TestUtils;
import com.epam.utils.Utils;

public class PaymentsDaoTest {
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
	public void shouldInsertPayment() {
		int actual = PaymentDao.insertPayment(connection, 3, 1, 1, 1, 100);
		int expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void shouldChangenPaymentStatus() {
		int actual = PaymentDao.changePaymentStatus(connection, 3, 1);
		int expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void shouldReturnPayments() {
		User testUser  = new User(3, "Frodo", "Bagins",
				"mister_frodo@example.com@example.com", "1111", new UserType(1, "User"), new UserStatus(1, "Active"));
		UserAccount testUserAccount = new UserAccount(1, "Payment", new AccountStatus(1, "Active"), 100.0);
		List<Payment> actual = PaymentDao.getUserPayments(connection, testUser,	"en").subList(0, 2);
		List<Payment> expected = List.of(
				new Payment(1, LocalDateTime.parse("2020-10-09 00:00:00", Utils.getDateTimeFormater("en")), new PaymentType(1, "Public utilities"), new PaymentStatus(1, "Prepeared"), testUser, testUserAccount,250.0),
				new Payment(2, LocalDateTime.parse("2020-10-09 12:00:00", Utils.getDateTimeFormater("en")), new PaymentType(1, "Public utilities"), new PaymentStatus(1, "Prepeared"), testUser, testUserAccount,250.0)
				);
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
