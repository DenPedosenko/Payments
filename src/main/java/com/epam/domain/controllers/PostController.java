package com.epam.domain.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PostController {
	public void post(HttpServletRequest request, HttpServletResponse response, String language) throws IOException, ServletException;
}
