package com.epam.domain.controlers;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.data.dao.UserDao;
import com.epam.data.dao.UserStatusDao;
import com.epam.data.dao.UserTypeDao;
import com.epam.data.model.User;
import com.epam.utils.Utils;

public class Registration {
	private static Logger logger = Logger.getLogger("Registration");

	private static HashMap<String, String> statuses = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("active_en", "Active");
			put("active_ru", "Активный");
			put("blocked_en", "Blocked");
			put("blocked_ru", "Заблокированный");
		}
	};
	private static HashMap<String, String> types = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("user_en", "User");
			put("user_ru", "Пользователь");
			put("admin_en", "Admin");
			put("admin_ru", "Админиcтратор");
		}
	};

	private static Connection connection = null;

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		Registration.connection = connection;
	}

	public static void get(HttpServletRequest req, HttpServletResponse resp, String language)
			throws ServletException, IOException {
		req.setAttribute("language", language);
		RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/registration.jsp");
		view.forward(req, resp);
	}

	public static void post(HttpServletRequest req, HttpServletResponse resp, String language) throws IOException {
		String first_name = req.getParameter("first_name");
		String last_name = req.getParameter("last_name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		User user = new User(1, first_name, last_name, email, password,
				UserTypeDao.getTypeByName(connection, types.get("user_" + language), language),
				UserStatusDao.getStatus(connection, statuses.get("active_" + language), language));
		int rows = UserDao.registerUser(connection, user);
		if (rows == 0) {
			logger.debug("Sorry, an error occurred!");
			
		} else {
			logger.debug("User successfuly added!");
			Utils.logIn(user, req, language);
			resp.sendRedirect(req.getContextPath());
		}

	}

}
