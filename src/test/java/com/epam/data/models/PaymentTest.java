package com.epam.data.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.data.model.AccountStatus;
import com.epam.data.model.Payment;
import com.epam.data.model.PaymentStatus;
import com.epam.data.model.PaymentType;
import com.epam.data.model.User;
import com.epam.data.model.UserAccount;
import com.epam.data.model.UserStatus;
import com.epam.data.model.UserType;
import com.epam.utils.Utils;

public class PaymentTest {
	Payment payment = null;
	
	@BeforeEach
	public void setup() {
		User testUser  = new User(3, "Frodo", "Bagins",
				"mister_frodo@example.com@example.com", "1111", new UserType(1, "User"), new UserStatus(1, "Active"));
		UserAccount testUserAccount = new UserAccount(1, "Payment", new AccountStatus(1, "Active"), 100.0);
		payment = new Payment(1, LocalDateTime.parse("2020-10-09 00:00:00", Utils.getDateTimeFormater("en")), new PaymentType(1, "Public utilities"), new PaymentStatus(1, "Prepeared"), testUser, testUserAccount,250.0);
	}

	@Test
	public void getIdTest() {
		assertEquals(1, payment.getId());
	}
	
	@Test
	public void setIdTest() {
		payment.setId(2);
		assertEquals(2, payment.getId());
	}
		
}
