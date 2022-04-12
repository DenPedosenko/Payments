package com.epam.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.data.model.PaymentType;

public class PaymentTypeDao {
	private static Logger logger = Logger.getLogger("PaymentTypeDao");

    public static PaymentType getPaymentTypeByName(Connection connection, String type, String language) {
        String selectQuery = "SELECT * FROM payment_type WHERE name_" + language + "=?";
        PaymentType paymentType = null;
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setString(1, type);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                paymentType = new PaymentType(resultSet.getInt("id"), resultSet.getString("name_" + language));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return paymentType;
    }
    
    public static PaymentType getPaymentTypeById(Connection connection, int id, String language) {
        String selectQuery = "SELECT * FROM payment_type WHERE id=?";
        PaymentType paymentType = null;
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                paymentType = new PaymentType(resultSet.getInt("id"), resultSet.getString("name_" + language));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return paymentType;
    }
}
