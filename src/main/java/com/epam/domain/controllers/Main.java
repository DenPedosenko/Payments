package com.epam.domain.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.data.dao.AccountsDao;
import com.epam.data.dao.CardsDao;
import com.epam.data.dao.PaymentDao;
import com.epam.data.dao.UserDao;
import com.epam.data.model.Card;
import com.epam.data.model.Payment;
import com.epam.data.model.User;
import com.epam.data.model.UserAccount;

public class Main implements GetController {
	private  Connection connection = null;
	private static Main instance = null;
	
	public static Logger logger = Logger.getLogger(Main.class);

	public void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLogget)
			throws ServletException, IOException {
		req.setAttribute("language", language);
		if (!isLogget) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			int user_id = (int) req.getSession().getAttribute("user_id");
			User user = UserDao.getUser(connection, user_id, language);
			List<UserAccount> accounts = AccountsDao.getUserAccounts(connection, user, language);
			List<Card> cards = CardsDao.getAccountsCards(connection, accounts);
			Map<String, List<Payment>> payments = PaymentDao.getUserPayments(connection, user, language);
			req.setAttribute("cards", cards);
			req.setAttribute("payments", payments);
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/index.jsp");
			view.forward(req, resp);
		}
	}

	public  Connection getConnection() {
		return connection;
	}

	private Main(Connection connection) {
		this.connection = connection;
	}

	public static Main getInstance(Connection connection) {
		if(instance == null) {
			instance = new Main(connection);
		}
		return instance;
	}
	public  void setConnection(Connection connection) {
		this.connection = connection;
	}
}
