package com.epam.domain.controlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout {
	public static void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn")) {
			session.setAttribute("loggedIn", false);
		}
		response.sendRedirect(request.getContextPath());
	}

}
