package com.epam.data.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.io.IOException;
import java.sql.Connection;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.data.dao.UserDaoTest;
import com.epam.domain.controllers.Login;
import com.epam.utils.TestUtils;

@ExtendWith(MockitoExtension.class)
public class LoginTest {
	static Connection connection;
	static Logger logger = Logger.getLogger(UserDaoTest.class);
	Login login = Login.getIntsance(connection);
    HttpServletRequest request = mock(HttpServletRequest.class);       
    HttpServletResponse response = mock(HttpServletResponse.class);  
	RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    
    @BeforeAll
	public static void setup() {
		BasicConfigurator.configure();
		connection = TestUtils.getTestConnection();
	}


	@Test
	public void shouldSendRedirect() throws IOException, ServletException  {
		login.get(request, response, "en", true);
		verify(response, atLeastOnce()).sendRedirect(any());
	}
	
	@Test
	public void shouldDoGet() throws ServletException, IOException {
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		login.get(request, response, "en", false);
		verify(dispatcher, atLeastOnce()).forward(any(), any());
	}
	
		
	@Test
	public void shouldDoPost() throws ServletException, IOException {
		when(request.getParameter("password")).thenReturn("1111");
		when(request.getParameter("email")).thenReturn("mister_frodo@example.com");
		login.post(request, response, "en");
		verify(response, atLeastOnce()).sendRedirect(anyString());
	}


}
