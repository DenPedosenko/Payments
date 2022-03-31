package com.epam.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.data.model.User;

public class Utils {
	public static void logIn(User user, HttpServletRequest request, String language) {
		HttpSession session = request.getSession();
		session.setAttribute("loggedIn", true);
		session.setAttribute("user_id", user.getId());
		session.setAttribute("type", user.getUserType().getId());
		session.setAttribute("status", user.getUserStatus().getId());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("userName", user.getUserName());
	}
}
