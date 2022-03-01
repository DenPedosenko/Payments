package com.epam.domain.controlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends Controler {

	@Override
	public void get(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.getWriter().print("login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void post(HttpServletRequest req, HttpServletResponse resp) {
		String first_name = req.getParameter("first_name");
		String password = req.getParameter("password");

		try {
			resp.getWriter().print(first_name + " " + password);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
