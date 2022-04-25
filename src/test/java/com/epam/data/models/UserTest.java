package com.epam.data.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.data.model.User;
import com.epam.data.model.UserStatus;
import com.epam.data.model.UserType;

public class UserTest {
	User user = null;
	
	@BeforeEach
	public void setup() {
		user = new User(1, "Gendalf", "Gray", "the_gratest_mag@example.com", "7777", new UserType(2, "Admin"),
				new UserStatus(1, "Active"));
	}

	@Test
	public void getIdTest() {
		assertEquals(1, user.getId());
	}
	
	@Test
	public void setIdTest() {
		user.setId(2);
		assertEquals(2, user.getId());
	}
	
	@Test
	public void getFirstNameTest() {
		assertEquals("Gendalf", user.getFirstName());
	}
	
	@Test
	public void setFirstNameTest() {
		user.setFirstName("NotGendalf");
		assertEquals("NotGendalf", user.getFirstName());
	}
	
	@Test
	public void getLastNameTest() {
		assertEquals("Gray", user.getLastName());
	}
	
	@Test
	public void setLastNameTest() {
		user.setLastName("White");
		assertEquals("White", user.getLastName());
	}
	
	@Test
	public void getEmailTest() {
		assertEquals("the_gratest_mag@example.com", user.getEmail());
	}
	
	@Test
	public void setEmailTest() {
		user.setEmail("gendalf@example.com");
		assertEquals("gendalf@example.com", user.getEmail());
	}
	
	
	
}
