package com.epam.data.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.epam.data.model.Payment;

public class PaymentDao {
	
	public static List<Payment> getUserPayments(Connection connection, int userId, String language) {
		List<Payment> payments = new ArrayList<>();
		String queryString = "";
		
		return null;
	}
	
	

}
