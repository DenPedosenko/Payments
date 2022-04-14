package com.epam.domain.servlets;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.data.dao.PaymentsDB;

import com.epam.domain.controllers.ServletGetController;
import com.epam.domain.controllers.ServletPostController;

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
			language = "ua";
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
		ServletGetController.findRoute(path).getController(connection).get(req, resp, language, isLogget);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		String language = getLanguage(req, resp);
		ServletPostController.findRoute(path).getController(connection).post(req, resp, language);
	}

	@Override
	public void init() throws ServletException {
		BasicConfigurator.configure();
		super.init();
	}

	@Override
	public void destroy() {
		System.out.println("destory#called");
		super.destroy();
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
