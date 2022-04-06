package com.epam.domain.controlers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.data.dao.UserDao;
import com.epam.data.model.User;
import com.epam.utils.Utils;

public class Login {
	private static Connection connection = null;

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		Login.connection = connection;
	}

	public static void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLoggetIn)
			throws ServletException, IOException {
		if (!isLoggetIn) {

			req.setAttribute("language", language);
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/login.jsp");
			view.forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath());

		}
	}

	public static void post(HttpServletRequest request, HttpServletResponse response, String language)
			throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(email + password);
		User user = UserDao.loginUser(connection, email, password, language);
		if (user != null) {
			Utils.logIn(user, request, language);
			response.sendRedirect(request.getContextPath());
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}

	}
}
