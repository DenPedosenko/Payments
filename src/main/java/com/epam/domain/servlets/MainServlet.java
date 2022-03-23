package com.epam.domain.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.data.dao.PaymentsDB;
import com.epam.domain.controlers.Login;
import com.epam.domain.controlers.Main;
import com.epam.domain.controlers.Registration;

@WebServlet(value = "/", name = "mainPage")
public class MainServlet extends HttpServlet {
	private Connection connection = PaymentsDB.getConnection();
	private static final long serialVersionUID = 2684944235775031753L;

	private boolean isLoggetIn(HttpServletRequest req) {
		return req.getSession(false) != null && req.getSession().getAttribute("loggedIn") != null
				&& (Boolean) req.getSession().getAttribute("loggedIn");
	}

	private static String getLanguage(HttpServletRequest request, HttpServletResponse response) {
		String language = request.getParameter("language");
		if (request.getCookies() != null && language == null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("language")) {
					language = cookie.getValue();
					break;
				}
			}
		}
		if (language == null) {
			language = "ru";
		}
		Cookie languageCookie = new Cookie("language", language);
		response.addCookie(languageCookie);
		return languageCookie.getValue();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		boolean isLogget = isLoggetIn(req);
		String language = getLanguage(req, resp);
		switch (path) {
		case "/":
			Main.setConnection(connection);
			Main.get(req, resp, language, isLogget);
			System.out.println(connection);
			break;
		case "/registration":
			Registration.setConnection(connection);
			Registration.get(req, resp, language);
			System.out.println(connection);
			break;
		case "/login":
			Login.setConnection(connection);
			Login.get(req, resp, language);
			System.out.println("Main get" +connection);
			break;	
		default:
			resp.getWriter().print("resdsdas");
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		String language = getLanguage(req, resp);
		switch (path) {
		case "/":
			Main.setConnection(connection);
			System.out.println(connection);
			break;
		case "/registration":
			Registration.setConnection(connection);
			Registration.post(req, resp, language);
			System.out.println(connection);
			break;
		case "/login":
			Login.setConnection(connection);
			Login.post(req, resp, language);
			System.out.println("Main post"+connection);
			break;
		default:
			resp.getWriter().print("resd");
			break;
		}

	}

	@Override
	public void destroy() {
		System.out.println("destory#called");
		// TODO Auto-generated method stub
		super.destroy();
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
