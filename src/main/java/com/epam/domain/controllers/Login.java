package com.epam.domain.controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.data.dao.UserDao;
import com.epam.data.model.User;
import com.epam.utils.Utils;

public class Login implements GetController, PostController {
	private  Connection connection = null;
	private static Login instance = null;

	private Login(Connection connection) {
		this.connection = connection;
	}

	public  Connection getConnection() {
		return connection;
	}

	public  void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLoggetIn)
			throws ServletException, IOException {
		if (!isLoggetIn) {

			req.setAttribute("language", language);
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/login.jsp");
			view.forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath());

		}
	}

	public void post(HttpServletRequest request, HttpServletResponse response, String language)
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
	
	public static Login getIntsance(Connection connection) {
		if(instance == null) {
			instance = new Login(connection);
		}
		return instance;
	}
}
