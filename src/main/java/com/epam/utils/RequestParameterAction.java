package com.epam.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface RequestParameterAction{
	public void invoke(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
