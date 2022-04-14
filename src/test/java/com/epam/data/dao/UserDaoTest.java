package com.epam.data.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.epam.data.model.User;
import com.epam.data.model.UserStatus;
import com.epam.data.model.UserType;
import com.epam.utils.TestUtils;

public class UserDaoTest {
	 static Connection connection;

	@BeforeAll
	public static void setup() {
		connection = TestUtils.getTestConnection();
	}

	@Test
	public void shouldAnsweredWithTrue() {
		User userActual = UserDao.getUser(connection, 1, "en");
		User testUser = new User(1, "Gendalf", "Gray", "the_gratest_mag@example.com", "7777", new UserType(2, "Admin"),
				new UserStatus(1, "Active"));
		assertEquals(userActual, testUser);
	}

	public Connection getConnection() {
		return connection;
	}

}
