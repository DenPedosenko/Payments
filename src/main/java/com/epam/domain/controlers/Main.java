package com.epam.domain.controlers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.data.dao.UserDao;
import com.epam.data.model.User;

public class Main extends Controler{

	@Override
	public void get(HttpServletRequest req, HttpServletResponse resp) {
		List<User> users = UserDao.getUsers(connection);
		for (User user : users) {
			System.out.println(user);
		}
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/view/index.jsp");
        try {
			requestDispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void post(HttpServletRequest req, HttpServletResponse resp) {
	
	}
}
