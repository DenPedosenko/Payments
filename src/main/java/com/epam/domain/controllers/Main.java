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

public class Main implements GetController, PostController {
	private Connection connection;
	private static Main instance;
	private User user;
	private List<Request> requests;
	private List<PaymentType> paymentTypes;
	private List<UserAccount> accounts;
	private List<Card> cards;
	private Map<String, List<Payment>> payments;
	private boolean isAdmin = false;
	public static Logger logger = Logger.getLogger(Main.class);

	private static HashMap<String, String> statuses = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("sent_en", "Sent");
			put("sent_ua", "Відправлений");
		}
	};

	public void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLogget)
			throws ServletException, IOException {
		req.setAttribute("language", language);
		if (!isLogget) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			if(req.getParameter("dismissRequest") != null) {
				int requestId = Integer.parseInt(req.getParameter("dismissRequest"));
				RequestsDao.changeRequsetStatus(connection, requestId, 2);
			}
			if(req.getParameter("acceptRequest") != null) {
				int requestId = Integer.parseInt(req.getParameter("acceptRequest"));
				Request request = RequestsDao.getRequestById(connection, language, requestId);
				AccountsDao.changeAccountStatus(connection, request.getAccount().getId(), 1);
				RequestsDao.changeRequsetStatus(connection, requestId, 2);
			}
			int user_id = (int) req.getSession().getAttribute("user_id");
			initData(language, user_id);
			setAttributes(req);
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/index.jsp");
			view.forward(req, resp);
		}
	}

	public void setAttributes(HttpServletRequest req) {
		if (isAdmin) {
			req.setAttribute("requests", requests);
		} else {
			req.setAttribute("payment_types", paymentTypes);
			req.setAttribute("cards", cards);
			req.setAttribute("accounts", accounts);
			req.setAttribute("payments", payments);
		}

	}

	public void initData(String language, int user_id) {
		user = UserDao.getUser(connection, user_id, language);
		if (isAdmin = user.getUserType().equals(UserTypeDao.getTypeById(connection, 2, language))) {
			requests = RequestsDao.getActiveRequests(connection, language);
		} else {
			paymentTypes = PaymentTypeDao.getTypes(connection, language);
			accounts = AccountsDao.getUserAccounts(connection, user.getId(), language);
			cards = CardsDao.getAccountsCards(connection, accounts);
			PaymentStatus status = PaymentStatusDao.getPaymentStatusByName(connection, statuses.get("sent_" + language),
					language);
			payments = PaymentDao.getUserPaymentsByStatus(connection, user, status, language);
		}
	}


	private Main(Connection connection) {
		this.connection = connection;
	}

	public static Main getInstance(Connection connection) {
		if (instance == null) {
			instance = new Main(connection);
		}
		return instance;
	}

	@Override
	public void post(HttpServletRequest req, HttpServletResponse resp, String language)
			throws IOException, ServletException {
		int accountId = Integer.parseInt(req.getParameter("accountId"));
		int user_id = (int) req.getSession().getAttribute("user_id");
		initData(language, user_id);
		if (req.getParameter("block") != null) {
			AccountsDao.changeAccountStatus(connection, accountId, 2);
			resp.sendRedirect(req.getContextPath());
		} 
		if (req.getParameter("continue") != null) {	
			double amount = Double.parseDouble(req.getParameter("amount"));
			UserAccount account = AccountsDao.getUserAccountById(connection, accountId, language);
			AccountsDao.changeAccountBalance(connection, accountId,account.getBalance() + amount);
			resp.sendRedirect(req.getContextPath());
		} 
		if(req.getParameter("unblock") != null) {
			if(RequestsDao.createNewRequest(connection, user.getId(),  accountId, 1) == 0) {
				resp.sendRedirect(req.getContextPath() + "?operationStatus=alreadyCreated");
			}else {
				resp.sendRedirect(req.getContextPath() + "?operationStatus=unblockedRequestSent");
			}
		}
	}
}
