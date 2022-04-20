package com.epam.domain.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.data.dao.AccountsDao;
import com.epam.data.dao.CardsDao;
import com.epam.data.dao.PaymentDao;
import com.epam.data.dao.PaymentStatusDao;
import com.epam.data.dao.PaymentTypeDao;
import com.epam.data.dao.UserDao;
import com.epam.data.model.Card;
import com.epam.data.model.PaymentType;
import com.epam.data.model.User;
import com.epam.data.model.UserAccount;

public class Payments implements GetController, PostController {

	public static Logger logger = Logger.getLogger(Payments.class);
	private Connection connection;
	private static Payments instance;
	private User user;
	private List<PaymentType> paymentTypes;
	private List<Card> cards;
	private List<UserAccount> accounts;

	private static HashMap<String, String> statuses = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("prepeared_en", "Prepeared");
			put("prepeared_ua", "Підготовлений");
			put("sent_en", "Sent");
			put("sent_ua", "Відправлений");
		}
	};
	
	private Payments(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public static Payments getInstance(Connection connection) {
		if (instance == null) {
			instance = new Payments(connection);
		}
		return instance;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void post(HttpServletRequest req, HttpServletResponse resp, String language)
			throws IOException, ServletException {
		int user_id = (int) req.getSession().getAttribute("user_id");
		initData(language, user_id);
		setAttributes(req);
		int paymetTypeId = Integer.parseInt(req.getParameter("payment_type"));
		double amount = Double.parseDouble(req.getParameter("amount"));
		int accountId = Integer.parseInt(req.getParameter("account"));
		if (req.getParameter("save") != null) {
			PaymentDao.insertPayment(connection, user.getId(), accountId, PaymentStatusDao
					.getPaymentStatusByName(connection, statuses.get("prepeared_" + language), language).getId(),
					paymetTypeId, amount);
			resp.sendRedirect(req.getContextPath() + "/payments?operationStatus=save");
		} else if (req.getParameter("continue") != null) {
			UserAccount currentAccount = accounts.stream().filter(v -> v.getId() == accountId).findAny().orElse(null);
			if (currentAccount != null && currentAccount.getBalance() - amount >= 0) {
				AccountsDao.changeAccountBalance(connection, accountId, currentAccount.getBalance() - amount);
				PaymentDao.insertPayment(
						connection, user.getId(), accountId, PaymentStatusDao
								.getPaymentStatusByName(connection, statuses.get("sent_" + language), language).getId(),
						paymetTypeId, amount);
				resp.sendRedirect(req.getContextPath() + "/payments?operationStatus=success");
			} else {
				resp.sendRedirect(req.getContextPath() + "/payments?operationStatus=error");
			}
		}
	}

	@Override
	public void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLogget)
			throws ServletException, IOException {
		req.setAttribute("language", language);
		if (!isLogget) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			int user_id = (int) req.getSession().getAttribute("user_id");
			initData(language, user_id);
			setAttributes(req);
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/payments.jsp");
			view.forward(req, resp);
		}
	}

	private void setAttributes(HttpServletRequest req) {
		req.setAttribute("payment_types", paymentTypes);
		req.setAttribute("cards", cards);
		req.setAttribute("accounts", accounts);

	}

	private void initData(String language, int user_id) {
		user = UserDao.getUser(connection, user_id, language);
		paymentTypes = PaymentTypeDao.getTypes(connection, language);
		accounts = AccountsDao.getUserAccounts(connection, user, language);
		cards = CardsDao.getAccountsCards(connection, accounts);
	}

}
