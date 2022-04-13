package com.epam.domain.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements GetController {

	@Override
	public void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLoggetIn)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn")) {
			session.setAttribute("loggedIn", false);
		}
		resp.sendRedirect(req.getContextPath());
		
	}
	
}
