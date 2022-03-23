package com.epam.domain.controlers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration {
	private static Connection connection = null;

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		Registration.connection = connection;
	}

	public static void get(HttpServletRequest req, HttpServletResponse resp, String language) throws ServletException, IOException {
		req.setAttribute("language", language);
		RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/registration.jsp");
		view.forward(req, resp);
	}

	public static void post(HttpServletRequest req, HttpServletResponse resp, String language) {
		String first_name = req.getParameter("first_name");
		String password = req.getParameter("password");

		try {
			resp.getWriter().print(first_name + " " + password);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
