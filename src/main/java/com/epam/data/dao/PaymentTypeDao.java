package com.epam.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.data.model.PaymentType;

public class PaymentTypeDao {
	private static Logger logger = Logger.getLogger(PaymentTypeDao.class);

    public static PaymentType getPaymentTypeByName(Connection connection, String type, String language) {
        String selectQuery = "SELECT * FROM payment_types WHERE name_" + language + "=?";
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
        String selectQuery = "SELECT * FROM payment_types WHERE id=?";
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

	public static List<PaymentType> getTypes(Connection connection, String language) {
		String selectQuery = "SELECT * FROM payment_types";
		List<PaymentType> types = new ArrayList<>();
        PaymentType paymentType = null;
        try (Statement selectStatement = connection.createStatement()) {
            ResultSet resultSet = selectStatement.executeQuery(selectQuery);
            if (resultSet.next()) {
                paymentType = new PaymentType(resultSet.getInt("id"), resultSet.getString("name_" + language));
                types.add(paymentType);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return types;
	}
}
