package com.epam.domain.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
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
import com.epam.data.dao.PaymentStatusDao;
import com.epam.data.dao.PaymentTypeDao;
import com.epam.data.dao.RequestsDao;
import com.epam.data.dao.UserDao;
import com.epam.data.dao.UserTypeDao;
import com.epam.data.model.Card;
import com.epam.data.model.Payment;
import com.epam.data.model.PaymentStatus;
import com.epam.data.model.PaymentType;
import com.epam.data.model.Request;
import com.epam.data.model.User;
import com.epam.data.model.UserAccount;
import com.epam.data.model.UserType;

public class Main implements GetController {
	private  Connection connection;
	private static Main instance;
	private User user;
	private List<Request> requests;
	private List<PaymentType> paymentTypes;
	private List<UserAccount> accounts;
	private List<Card> cards;
	private Map<String, List<Payment>> payments;
	private boolean isAdmin = false;
		
	private static HashMap<String, String> statuses = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("sent_en", "Sent");
			put("sent_ua", "Відправлений");
		}
	};
	
	public static Logger logger = Logger.getLogger(Main.class);

	public void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLogget)
			throws ServletException, IOException {
		req.setAttribute("language", language);
		if (!isLogget) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			int user_id = (int) req.getSession().getAttribute("user_id");
			initData(language, user_id);
			setAttributes(req);
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/index.jsp");
			view.forward(req, resp);
		}
	}

	private void setAttributes(HttpServletRequest req) {
		if(isAdmin) {
			req.setAttribute("requests",requests);
		} else {
			req.setAttribute("payment_types", paymentTypes);
			req.setAttribute("cards", cards);
			req.setAttribute("accounts", accounts);
			req.setAttribute("payments", payments);
		}

	}

	private void initData(String language, int user_id) {
		user = UserDao.getUser(connection, user_id, language);
		if(isAdmin = user.getUserType().equals(UserTypeDao.getTypeById(connection, 2, language))) {
			requests = RequestsDao.getActiveRequests(connection, language);
			logger.debug(requests);	
		} else {
			paymentTypes = PaymentTypeDao.getTypes(connection, language);
			accounts = AccountsDao.getUserAccounts(connection, user, language);
			cards = CardsDao.getAccountsCards(connection, accounts);
			PaymentStatus status = PaymentStatusDao.getPaymentStatusByName(connection, statuses.get("sent_"+language), language);
			payments = PaymentDao.getUserPaymentsByStatus(connection, user, status, language);
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
