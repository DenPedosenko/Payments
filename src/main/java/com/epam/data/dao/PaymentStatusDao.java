package com.epam.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.data.model.PaymentStatus;

public class PaymentStatusDao {
	private static Logger logger = Logger.getLogger(PaymentStatusDao.class);
		
    public static PaymentStatus getPaymentStatusByName(Connection connection, String status, String language) {
        String selectQuery = "SELECT * FROM payment_statuses WHERE name_" + language + "=?";
        PaymentStatus paymentStatus = null;
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setString(1, status);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                paymentStatus = new PaymentStatus(resultSet.getInt("id"), resultSet.getString("name_" + language));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return paymentStatus;
    }
    
    public static PaymentStatus getPaymentStatusById(Connection connection, int id, String language) {
        String selectQuery = "SELECT * FROM payment_statuses WHERE id=?";
        PaymentStatus paymentStatus = null;
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                paymentStatus = new PaymentStatus(resultSet.getInt("id"), resultSet.getString("name_" + language));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return paymentStatus;
    }
}
