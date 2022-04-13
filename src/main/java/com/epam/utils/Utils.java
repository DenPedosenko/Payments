package com.epam.utils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

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
	
	public static DateTimeFormatter getDateTimeFormater(String language) {
		DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder().parseCaseInsensitive().parseLenient().appendPattern("yyyy-MM-dd HH:mm:ss");
		return language == "en"?builder.toFormatter(Locale.ENGLISH):builder.toFormatter();
	}
}
