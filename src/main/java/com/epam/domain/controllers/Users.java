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
import com.epam.utils.RequestParameterAction;

public class Users implements GetController {
	public static Logger logger = Logger.getLogger(Accounts.class);
	private Connection connection;
	private static Users instance;
	private User user;
	private List<User> users;

	private Users(Connection connection) {
		this.connection = connection;
	}

	private List<RequestParameterAction> actions = List.of((req, resp) -> {
		String param = req.getParameter("blockAccount");
		if (param != null) {
			AccountsDao.changeAccountStatus(connection, Integer.parseInt(param), 2);
		}
	}, (req, resp) -> {
		String param = req.getParameter("unlockAccount");
		if (param != null) {
			AccountsDao.changeAccountStatus(connection, Integer.parseInt(param), 1);
		}
	}, (req, resp) -> {
		String param = req.getParameter("blockUser");
		if (param != null) {
			UserDao.changeUserStatus(connection, Integer.parseInt(param), 2);
		}
	}, (req, resp) -> {
		String param = req.getParameter("unlockUser");
		if (param != null) {
			UserDao.changeUserStatus(connection, Integer.parseInt(param), 1);
		}
	});

	@Override
	public void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLogget)
			throws ServletException, IOException {
		req.setAttribute("language", language);
		if (!isLogget) {

			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			int user_id = (int) req.getSession().getAttribute("user_id");
			user = UserDao.getUser(connection, user_id, language);
			if (user.getUserType().getId() == 1) {
				resp.sendRedirect(req.getContextPath());
			} else {
				for (RequestParameterAction action : actions) {
					action.invoke(req, resp);
				}
				initData(language);
				setAttributes(req);
				RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/users.jsp");
				view.forward(req, resp);
			}
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
		req.setAttribute("size", users.size());
		int currentPage = 1;
		if (req.getParameter("page") != null) {
			currentPage = Integer.parseInt(req.getParameter("page"));
		}
		req.setAttribute("page", currentPage);
		orderUsers(orderBy, ascending, users);
		req.setAttribute("users", users.subList(getOperationSubListStartIndex(currentPage),
				getOperationsSubListEndIndex(users.size(), currentPage)));
	}

	private int getOperationSubListStartIndex(int currentPage) {
		return (currentPage - 1) * 3;
	}

	private int getOperationsSubListEndIndex(int size, int currentPage) {
		return Math.min(getOperationSubListStartIndex(currentPage) + 3, size);
	}

	private void orderUsers(String orderBy, String ascending, List<User> users) {
		if (orderBy.equals("id")) {
			if (ascending.equals("true")) {
				users.sort(Comparator.comparing(User::getId));
			} else {
				users.sort((user, u1) -> Integer.compare(u1.getId(), user.getId()));
			}
		}
		if (orderBy.equals("name")) {
			if (ascending.equals("true")) {
				users.sort(Comparator.comparing(User::getUserName));
			} else {
				users.sort((user, u1) -> u1.getUserName().compareTo(user.getUserName()));
			}
		}
		if (orderBy.equals("userStatus")) {
			if (ascending.equals("true")) {
				users.sort((user, u1) -> Integer.compare(user.getUserStatus().getId(), u1.getUserStatus().getId()));
			} else {
				users.sort((user, u1) -> Integer.compare(u1.getUserStatus().getId(), user.getUserStatus().getId()));
			}
		}
	}

	private void initData(String language) {
		users = UserDao.getUsers(connection, language);
	}

	public static Users getInstance(Connection connection) {
		if (instance == null) {
			instance = new Users(connection);
		}
		return instance;
	}

}
