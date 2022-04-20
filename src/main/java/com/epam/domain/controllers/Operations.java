package com.epam.domain.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.data.dao.PaymentDao;
import com.epam.data.dao.UserDao;
import com.epam.data.model.Payment;
import com.epam.data.model.User;

public class Operations implements GetController, PostController {
	public static Logger logger = Logger.getLogger(Payments.class);
	private Connection connection;
	private static Payments instance;
	private User user;
	private List<Payment> operations;

	public Operations(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void post(HttpServletRequest req, HttpServletResponse resp, String language)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLogget)
			throws ServletException, IOException {
		req.setAttribute("language", language);
		if (!isLogget) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			int user_id = (int) req.getSession().getAttribute("user_id");
			initData(language, user_id);
			setAttributes(req);
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/operations.jsp");
			view.forward(req, resp);
		}
	}
	
	private static String getAscending(HttpServletRequest request) {
        String ascending = request.getParameter("ascending");
        if (ascending == null) {
            ascending = "true";
        }
        return ascending;
    }

    private static String getOrderBy(HttpServletRequest request) {
        String orderBy = request.getParameter("orderBy");
        if (orderBy == null) {
            orderBy = "id";
        }
        return orderBy;
    }

	private void setAttributes(HttpServletRequest req) {
		String ascending = getAscending(req);
        String orderBy = getOrderBy(req);

        req.setAttribute("ascending", ascending);
        req.setAttribute("orderBy", orderBy);
        
        String currentPage = "1";
        if (req.getParameter("page") != null) {
            currentPage = req.getParameter("page");
        }
        req.setAttribute("page", currentPage);
        orderOperations(orderBy, ascending);
        req.setAttribute("operations", operations);

	}

	private void orderOperations(String orderBy, String ascending) {
		logger.debug(ascending + " " +orderBy );
		if (orderBy.equals("id")) {
            if (ascending.equals("true")) {
                operations.sort(Comparator.comparing(Payment::getId));
            } else {
            	operations.sort((payment, p1) -> Integer.compare(p1.getId(), payment.getId()));
            }
        }
        if (orderBy.equals("date")) {
            if (ascending.equals("true")) {
            	operations.sort(Comparator.comparing(Payment::getCreationDate));
            } else {
            	operations.sort((payment, p1) -> p1.getCreationDate().compareTo(payment.getCreationDate()));
            }
        }
        if (orderBy.equals("paymentStatus")) {
            if (ascending.equals("true")) {
                operations.sort((payment, p1) -> Integer.compare(payment.getPaymentStatus().getId(), p1.getPaymentStatus().getId()));
            } else {
            	operations.sort((payment, p1) -> Integer.compare(p1.getPaymentStatus().getId(), payment.getPaymentStatus().getId()));
            }
        }
		
	}

	private void initData(String language, int user_id) {
		user = UserDao.getUser(connection, user_id, language);
		operations = PaymentDao.getUserPayments(connection, user, language);

	}

	public static GetController getInstance(Connection connection) {
		if (instance == null) {
			return new Operations(connection);
		}
		return null;
	}

}
