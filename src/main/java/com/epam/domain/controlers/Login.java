package com.epam.domain.controlers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.data.dao.UserDao;
import com.epam.data.model.User;

public class Login {
	private static Connection connection = null;

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		Login.connection = connection;
	}

	public static void get(HttpServletRequest req, HttpServletResponse resp, String language)
			throws ServletException, IOException {
		req.setAttribute("language", language);
		req.setAttribute("error", true);
		RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/login.jsp");
		view.forward(req, resp);

	}

	public static void post(HttpServletRequest request, HttpServletResponse response, String language)
			throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = UserDao.loginUser(connection, email, password, language);
		if (user != null) {
			logIn(user, request, language);
			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("error", true);
			response.sendRedirect(request.getContextPath()+"/login");
		}
		
	}

	private static void logIn(User user, HttpServletRequest request, String language) {
		HttpSession session = request.getSession();
		session.setAttribute("loggedIn", true);
		session.setAttribute("id", user.getId());
		session.setAttribute("type", user.getUserType().getId());
		session.setAttribute("status", user.getUserStatus().getId());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("userName", user.getUserName());
	}
}
