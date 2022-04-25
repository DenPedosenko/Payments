package com.epam.domain.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.data.dao.AccountsDao;
import com.epam.data.dao.UserDao;
import com.epam.data.model.User;
import com.epam.data.model.UserAccount;

public class Accounts implements GetController {
	public static Logger logger = Logger.getLogger(Accounts.class);
	private Connection connection;
	private static Accounts instance;
	private User user;
	private List<UserAccount> accounts;

	private Accounts(Connection connnection) {
		this.connection = connnection;
	}

	public Connection getConnection() {
		return connection;
	}

	public static Accounts getInstance(Connection connnection) {
		if(instance == null) {
			instance = new Accounts(connnection);
		}
		return instance;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
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
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/accounts.jsp");
			view.forward(req, resp);
		}
	}

	private static String getAscending(HttpServletRequest request) {
        String ascending = request.getParameter("ascending");
        if (ascending == null) {
            ascending = "true";
        }
        return ascending;
    }

    private static String getOrderBy(HttpServletRequest request) {
        String orderBy = request.getParameter("orderBy");
        if (orderBy == null) {
            orderBy = "id";
        }
        return orderBy;
    }

	private void setAttributes(HttpServletRequest req) {
		String ascending = getAscending(req);
        String orderBy = getOrderBy(req);

        req.setAttribute("ascending", ascending);
        req.setAttribute("orderBy", orderBy);
        req.setAttribute("size", accounts.size());
        int currentPage = 1;
        if (req.getParameter("page") != null) {
            currentPage = Integer.parseInt(req.getParameter("page"));
        }
        req.setAttribute("page", currentPage);
        orderAccounts(orderBy, ascending, accounts);
        req.setAttribute("accounts", accounts);
	}

	private void initData(String language, int user_id) {
		user = UserDao.getUser(connection, user_id, language);
		accounts = AccountsDao.getUserAccounts(connection, user.getId(), language);
	}
	
	private void orderAccounts(String orderBy, String ascending, List<UserAccount> operations) {
		logger.debug(ascending + " " +orderBy );
		if (orderBy.equals("id")) {
            if (ascending.equals("true")) {
                operations.sort(Comparator.comparing(UserAccount::getId));
            } else {
            	operations.sort((payment, p1) -> Integer.compare(p1.getId(), payment.getId()));
            }
        }
        if (orderBy.equals("name")) {
            if (ascending.equals("true")) {
            	operations.sort(Comparator.comparing(UserAccount::getName));
            } else {
            	operations.sort((account, a1) -> a1.getName().compareTo(account.getName()));
            }
        }
        if (orderBy.equals("amount")) {
            if (ascending.equals("true")) {
                operations.sort((account, a1) -> Double.compare(account.getBalance(), a1.getBalance()));
            } else {
            	operations.sort((account, a1) -> Double.compare(a1.getBalance(), account.getBalance()));
            }
        }
	}	
}