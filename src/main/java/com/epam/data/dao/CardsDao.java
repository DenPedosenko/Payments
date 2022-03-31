package com.epam.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.data.model.Card;
import com.epam.data.model.UserAccount;

public class CardsDao {
	public static Logger logger = Logger.getLogger("CardsDao");

	public static List<Card> getAccountsCards(Connection connection, List<UserAccount> accounts) {
		Card card = null;
		List<Card> cards = new ArrayList<>();
		String getCardsQuery = "SELECT * FROM CARDS WHERE account_id = ?;";

		try (PreparedStatement statement = connection.prepareStatement(getCardsQuery);) {
			for (UserAccount account : accounts) {
				statement.setInt(1, account.getId());
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					card = createCard(resultSet, account);
					cards.add(card);
				}
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}

		return cards;
	}

	private static Card createCard(ResultSet resultSet, UserAccount account) throws SQLException {
		return new Card(resultSet.getInt("id"), resultSet.getString("card_number"), resultSet.getString("exp_date"),
				resultSet.getString("cvv"), account);
	}
}
