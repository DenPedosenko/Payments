package com.epam.data.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.epam.data.model.User;
import com.epam.data.model.UserStatus;
import com.epam.data.model.UserType;
import com.epam.utils.TestUtils;

public class UserDaoTest {
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
	public void shouldAnsweredWithTrue() {
		User userActual = UserDao.getUser(connection, 1, "en");
		User testUser = new User(1, "Gendalf", "Gray", "the_gratest_mag@example.com", "7777", new UserType(2, "Admin"),
				new UserStatus(1, "Active"));
		testUser.setAccounts(Collections.emptyList());
		assertEquals(userActual, testUser);
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
