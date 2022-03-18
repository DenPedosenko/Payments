package com.epam.domain.controlers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.data.dao.UserDao;
import com.epam.data.model.User;

public class Main {
	private static Connection connection = null;

	public static void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLogget)
			throws ServletException, IOException {
		req.setAttribute("language", language);
		if (!isLogget) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			List<User> users = UserDao.getUsers(connection);
			for (User user : users) {
				System.out.println(user);
			}

			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/index.jsp");
			view.forward(req, resp);
		}
	}

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		Main.connection = connection;
	}
}
