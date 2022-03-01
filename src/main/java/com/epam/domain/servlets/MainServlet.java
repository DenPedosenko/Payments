package com.epam.domain.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.data.dao.PaymentsDB;
import com.epam.domain.controlers.Main;
import com.epam.domain.controlers.Registration;

@WebServlet(value = "/", name = "mainPage")
public class MainServlet extends HttpServlet {
	private Connection connection = PaymentsDB.getConnection();
	private static final long serialVersionUID = 2684944235775031753L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/":
			Main main = new Main();
			main.setConnection(connection);
			main.get(req, resp);
			System.out.println(main + " " + connection);
			break;
		case "/login":
			Registration registration = new Registration();
			registration.setConnection(connection);
			registration.get(req, resp);
			System.out.println(registration + " " + connection);
			break;
		default:
			resp.getWriter().print("resdsdas");
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/":
			Main main = new Main();
			main.setConnection(connection);
			main.post(req, resp);
			System.out.println(main + " " + connection);
			break;
		case "/login":
			Registration registration = new Registration();
			registration.setConnection(connection);
			registration.post(req, resp);
			System.out.println(registration + " " + connection);
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
