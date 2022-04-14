package com.epam.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.data.model.Payment;
import com.epam.data.model.PaymentStatus;
import com.epam.data.model.User;
import com.epam.utils.Utils;

public class PaymentDao {

	public static Logger logger = Logger.getLogger("PaymentsDao");

	public static Map<String, List<Payment>> getUserPayments(Connection connection, User user, String language) {
		Map<String, List<Payment>> payments = new HashMap<>();
		Payment payment = null;
		String getCardsQuery = "SELECT * FROM Payments WHERE user_id = ?;";

		try (PreparedStatement statement = connection.prepareStatement(getCardsQuery)) {
			statement.setInt(1, user.getId());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				payment = createPayment(connection, resultSet, user, language);
				putPayment(payments, payment);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return payments;
	}
	
	public static Map<String, List<Payment>> getUserPaymentsByStatus(Connection connection, User user, PaymentStatus status,  String language) {
		Map<String, List<Payment>> payments = new HashMap<>();
		Payment payment = null;
		String getCardsQuery = "SELECT * FROM Payments WHERE user_id = ? AND payment_status_id=?;";

		try (PreparedStatement statement = connection.prepareStatement(getCardsQuery)) {
			statement.setInt(1, user.getId());
			statement.setInt(2, status.getId());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				payment = createPayment(connection, resultSet, user, language);
				putPayment(payments, payment);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return payments;
	}

	private static void putPayment(Map<String, List<Payment>> payments, Payment payment) {
		String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(payment.getCreationDate());
		payments.put(date, getPaymentsByDate(payments, payment, date));
	}
	
	private static List<Payment> getPaymentsByDate(Map<String, List<Payment>> payments,Payment payment, String date){
		List<Payment> paymentsByDate = payments.get(date);
		if(paymentsByDate == null) {
			paymentsByDate = List.of(payment);
		} else {
			List<Payment> tempList = new ArrayList<>(paymentsByDate);
			tempList.add(payment);
			paymentsByDate = tempList;
		}
		return paymentsByDate;
	}

	public static Payment createPayment(Connection connection, ResultSet resultSet, User user, String language)
			throws SQLException {
		return new Payment(resultSet.getInt("id"),LocalDateTime.parse(resultSet.getString("creating_date"), Utils.getDateTimeFormater(language)),
				PaymentTypeDao.getPaymentTypeById(connection, resultSet.getInt("payment_type_id"), language),
				PaymentStatusDao.getPaymentStatusById(connection, resultSet.getInt("payment_status_id"), language),
				user, AccountsDao.getUserAccountById(connection, resultSet.getInt("account_id"), user, language),
				resultSet.getDouble("amount"));

	}
}
