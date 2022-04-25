package com.epam.domain.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.data.dao.AccountsDao;
import com.epam.data.dao.PaymentDao;
import com.epam.data.dao.PaymentStatusDao;
import com.epam.data.dao.UserDao;
import com.epam.data.model.Payment;
import com.epam.data.model.User;

public class Operations implements GetController {
	public static Logger logger = Logger.getLogger(Operations.class);
	private Connection connection;
	private static Operations instance;
	private User user;
	private List<Payment> operations;

	private static HashMap<String, String> statuses = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("prepeared_en", "Prepeared");
			put("prepeared_ua", "Підготовлений");
			put("sent_en", "Sent");
			put("sent_ua", "Відправлений");
		}
	};
	
	private Operations(Connection connection) {
		this.connection = connection;
	}


	@Override
	public void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLogget)
			throws ServletException, IOException {
		req.setAttribute("language", language);
		if (!isLogget) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			int user_id = (int) req.getSession().getAttribute("user_id");
	        if(req.getParameter("pay")!=null) {
	        	int paymentId = Integer.parseInt(req.getParameter("pay"));
	        	Payment payment = operations.stream().filter(v -> v.getId() == paymentId).findAny().orElse(null);
				if (payment != null && payment.getUserAccount().getBalance() - payment.getAmount() >= 0) {
					AccountsDao.changeAccountBalance(connection, payment.getUserAccount().getId(), payment.getUserAccount().getBalance() - payment.getAmount());
					PaymentDao.changePaymentStatus(connection, paymentId, PaymentStatusDao
							.getPaymentStatusByName(connection, statuses.get("sent_" + language), language).getId());
					resp.sendRedirect(req.getContextPath() + "/operations?&operationStatus=success");
				} else {
					resp.sendRedirect(req.getContextPath() + "/operations?operationStatus=error");
				}
	        } else {
				initData(language, user_id);
				setAttributes(req);
				RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/operations.jsp");
				view.forward(req, resp);
	        }
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
            orderBy = "paymentStatus";
        }
        return orderBy;
    }

	private void setAttributes(HttpServletRequest req) {
		String ascending = getAscending(req);
        String orderBy = getOrderBy(req);

        req.setAttribute("ascending", ascending);
        req.setAttribute("orderBy", orderBy);
        req.setAttribute("size", operations.size());
        int currentPage = 1;
        if (req.getParameter("page") != null) {
            currentPage = Integer.parseInt(req.getParameter("page"));
        }
        req.setAttribute("page", currentPage);
        orderOperations(orderBy, ascending, operations);
        req.setAttribute("operations", operations.subList(getOperationSubListStartIndex(currentPage), getOperationsSubListEndIndex(operations.size(), currentPage)));
	}
	
	private int getOperationSubListStartIndex(int currentPage) {
		return (currentPage - 1) * 10;
	}
	private int getOperationsSubListEndIndex(int size, int currentPage) {
	     return Math.min(getOperationSubListStartIndex(currentPage) + 10, size);
	}

	public void orderOperations(String orderBy, String ascending, List<Payment> operations) {
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

	public static Operations getInstance(Connection connection) {
		if (instance == null) {
			instance =  new Operations(connection);
		}
		return instance;
	}

}
