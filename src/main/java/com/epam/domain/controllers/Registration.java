package com.epam.domain.controllers;

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

public class Registration implements GetController, PostController {
	private static Registration instance;
	
	private static Logger logger = Logger.getLogger(Registration.class);

	private static HashMap<String, String> statuses = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("active_en", "Active");
			put("active_ua", "Активный");
			put("blocked_en", "Blocked");
			put("blocked_ua", "Заблокированный");
		}
	};
	private static HashMap<String, String> types = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("user_en", "User");
			put("user_ua", "Пользователь");
			put("admin_en", "Admin");
			put("admin_ua", "Админиcтратор");
		}
	};

	private  Connection connection = null;

	public  Connection getConnection() {
		return connection;
	}

	public  void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLoggenIn)
			throws ServletException, IOException {
		if (!isLoggenIn) {
			req.setAttribute("language", language);
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/registration.jsp");
			view.forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath());
		}
	}

	public void post(HttpServletRequest req, HttpServletResponse resp, String language) throws IOException {
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

	public static Registration getInstance(Connection connection) {
		if( instance == null) {
			instance = new Registration(connection);
		}
		return instance;
	}
	
	private Registration(Connection connection) {
		this.connection = connection;
	}

}
