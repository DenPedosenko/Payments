package com.epam.domain.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GetController {
	public void get(HttpServletRequest req, HttpServletResponse resp, String language, boolean isLoggetIn) throws ServletException, IOException;
}
