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

import com.epam.data.model.PaymentStatus;
import com.epam.utils.TestUtils;

public class PaymentStatusesDaoTest {
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
	public void shouldReturnPaymentStatus() {
		PaymentStatus actual = PaymentStatusDao.getPaymentStatusById(connection, 1, "en");
		PaymentStatus expected = new PaymentStatus(1, "Prepeared");
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnPaymentStatusByName() {
		PaymentStatus actual = PaymentStatusDao.getPaymentStatusByName(connection, "Prepeared", "en");
		PaymentStatus expected = new PaymentStatus(1, "Prepeared");
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
