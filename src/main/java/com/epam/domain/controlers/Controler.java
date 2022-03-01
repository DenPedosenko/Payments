package com.epam.domain.controlers;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 abstract public class Controler {
	protected Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public abstract void get(HttpServletRequest req, HttpServletResponse resp);
	public abstract void post(HttpServletRequest req, HttpServletResponse resp);

}
