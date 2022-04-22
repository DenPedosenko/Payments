
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

import com.epam.data.dao.AccountsDao;
import com.epam.data.dao.RequestsDao;
import com.epam.data.dao.UserDao;
import com.epam.data.model.Payment;
import com.epam.data.model.Request;
import com.epam.data.model.User;

public class Requests implements GetController {
	public static Logger logger = Logger.getLogger(Accounts.class);
	private Connection connection;
	private static Requests instance;
	private User user;
	private List<Request> requests;

	private Requests(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLogget)
			throws ServletException, IOException {
		req.setAttribute("language", language);
		int user_id = (int) req.getSession().getAttribute("user_id");
		user = UserDao.getUser(connection, user_id, language);
		if (!isLogget) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			if (user.getUserType().getId() == 1) {
				resp.sendRedirect(req.getContextPath());
			} else {
				if(req.getParameter("dismissRequest") != null) {
					int requestId = Integer.parseInt(req.getParameter("dismissRequest"));
					RequestsDao.changeRequsetStatus(connection, requestId, 2);
				}
				if(req.getParameter("acceptRequest") != null) {
					int requestId = Integer.parseInt(req.getParameter("acceptRequest"));
					Request request = RequestsDao.getRequestById(connection, language, requestId);
					AccountsDao.changeAccountStatus(connection, request.getAccount().getId(), 1);
					RequestsDao.changeRequsetStatus(connection, requestId, 2);
				}
				initData(language);
				setAttributes(req);
				RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/requests.jsp");
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
			orderBy = "requestStatus";
		}
		return orderBy;
	}

	private void setAttributes(HttpServletRequest req) {
		String ascending = getAscending(req);
        String orderBy = getOrderBy(req);

        req.setAttribute("ascending", ascending);
        req.setAttribute("orderBy", orderBy);
        req.setAttribute("size", requests.size());
        int currentPage = 1;
        if (req.getParameter("page") != null) {
            currentPage = Integer.parseInt(req.getParameter("page"));
        }
        req.setAttribute("page", currentPage);
        orderRequests(orderBy, ascending, requests);
        req.setAttribute("requests", requests.subList(getOperationSubListStartIndex(currentPage), getOperationsSubListEndIndex(requests.size(), currentPage)));
	}
	
	private int getOperationSubListStartIndex(int currentPage) {
		return (currentPage - 1) * 10;
	}
	private int getOperationsSubListEndIndex(int size, int currentPage) {
	     return Math.min(getOperationSubListStartIndex(currentPage) + 10, size);
	}

	private void orderRequests(String orderBy, String ascending, List<Request> requests) {
		logger.debug(ascending + " " +orderBy );
		if (orderBy.equals("id")) {
            if (ascending.equals("true")) {
            	requests.sort(Comparator.comparing(Request::getId));
            } else {
            	requests.sort((payment, p1) -> Integer.compare(p1.getId(), payment.getId()));
            }
        }
        if (orderBy.equals("date")) {
            if (ascending.equals("true")) {
            	requests.sort(Comparator.comparing(Request::getDate));
            } else {
            	requests.sort((request, r1) -> r1.getDate().compareTo(request.getDate()));
            }
        }
        if (orderBy.equals("requestStatus")) {
            if (ascending.equals("true")) {
            	requests.sort((request, r1) -> Integer.compare(request.getStatus().getId(), r1.getStatus().getId()));
            } else {
            	requests.sort((request, r1) -> Integer.compare(r1.getStatus().getId(), request.getStatus().getId()));
            }
        }
	}

	private void initData(String language) {
		requests = RequestsDao.getRequests(connection, language);
	}

	public static Requests getInstance(Connection connection) {
		if (instance == null) {
			instance = new Requests(connection);
		}
		return instance;
	}

}
