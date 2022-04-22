package com.epam.data.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.epam.data.model.PaymentType;
import com.epam.utils.TestUtils;

public class PaymentsTypeDaoTest {
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
	public void shouldReturnPaymentType() {
		PaymentType actual = PaymentTypeDao.getPaymentTypeById(connection, 1, "en");
		PaymentType expected = new PaymentType(1, "Public utilities");
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnPaymentTypeByName() {
		PaymentType actual = PaymentTypeDao.getPaymentTypeByName(connection, "Public utilities", "en");
		PaymentType expected = new PaymentType(1, "Public utilities");
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnPaymentTypes() {
		List<PaymentType> actual = PaymentTypeDao.getTypes(connection, "en");
		List<PaymentType> expected = List.of(
				new PaymentType(1, "Public utilities"),
				new PaymentType(2, "Mobile communications"),
				new PaymentType(3, "Help for Ukrainians"),
				new PaymentType(4, "Internet"),
				new PaymentType(5, "Television"),
				new PaymentType(6, "Distribution"),
				new PaymentType(7, "Security service"),
				new PaymentType(8, "Kindergartens"),
				new PaymentType(9, "Transport"),
				new PaymentType(10, "Games")
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
