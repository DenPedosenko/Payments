<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.epam.data.model.Card"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
ResourceBundle bundle = ResourceBundle.getBundle("translate", new Locale((String) request.getAttribute("language")));
%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="translate" />
<%@ page contentType="text/html; charset=UTF-8"%>
<div class="container-fluid">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="<%out.print(request.getContextPath());%>"> <img
				src="./resources/img/logo.png" alt="logo" width="130" height="32"
				class="d-inline-block align-text-top">
			</a>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">${language}</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="?language=en">English</a></li>
							<li><a class="dropdown-item" href="?language=ua">Українська</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="./"><fmt:message key="header.main" /></a></li>
					<li class="nav-item"><a class="nav-link" href="users" class="users"><fmt:message
								key="profile.users" /></a></li>
					<li class="nav-item"><a class="nav-link" href="requests" class="users"><fmt:message
								key="profile.requests" /></a></li>
				</ul>
			</div>
			<div class="btn-group">
				<button class="btn btn-secondary dropdown-toggle" type="button"
					id="defaultDropdown" data-bs-toggle="dropdown"
					data-bs-auto-close="true" aria-expanded="false">
					<img src="./resources/img/defaultUserLogo.png" alt="userLogo">
				</button>
				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="defaultDropdown">
					<li><a class="dropdown-item" href="profile"><fmt:message
								key="general.profile" /></a></li>
					<li><a class="dropdown-item" href="logout"><fmt:message
								key="general.logout" /></a></li>
				</ul>
			</div>
		</div>
	</nav>
		<h1>
		<fmt:message key="admin.header" />
	</h1>
</div>