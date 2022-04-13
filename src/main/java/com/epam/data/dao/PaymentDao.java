package com.epam.data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.data.model.Payment;
import com.epam.data.model.User;

public class PaymentDao {

	public static Logger logger = Logger.getLogger("PaymentsDao");

	public static Map<String, Payment> getUserPayments(Connection connection, User user, String language) {
		Map<String, Payment> payments = new HashMap<>();
		Payment payment = null;
		String getCardsQuery = "SELECT * FROM Payments WHERE user_id = ?;";

		try (PreparedStatement statement = connection.prepareStatement(getCardsQuery)) {
			statement.setInt(1, user.getId());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				payment = createPayment(connection, resultSet, user, language);
				payments.put(payment.getCreationDate().toString(), payment);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return payments;
	}

	public static Payment createPayment(Connection connection, ResultSet resultSet, User user, String language)
			throws SQLException {
		return new Payment(resultSet.getInt("id"), Date.valueOf(resultSet.getString("creating_date")),
				PaymentTypeDao.getPaymentTypeById(connection, resultSet.getInt("payment_type_id"), language),
				PaymentStatusDao.getPaymentStatusById(connection, resultSet.getInt("payment_status_id"), language),
				user, AccountsDao.getUserAccountById(connection, resultSet.getInt("account_id"), user, language),
				resultSet.getDouble("amount"));

	}
}
