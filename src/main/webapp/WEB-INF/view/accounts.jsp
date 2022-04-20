<%@page import="com.epam.data.model.Card"%>
<%@page import="com.epam.data.model.UserAccount"%>
<%@page import="com.epam.data.model.PaymentType"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="translate" />
<%
ResourceBundle bundle = ResourceBundle.getBundle("translate", new Locale((String) request.getAttribute("language")));
String language = (String) request.getAttribute("language");
String ascending = (String) request.getAttribute("ascending");
String orderBy = (String) request.getAttribute("orderBy");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<title>Payments</title>
</head>
<body>
	<div class="container-fluid">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
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
					<li class="nav-item"><a class="nav-link " aria-current="page"
						href="./"><fmt:message key="header.main" /></a></li>
					<li class="nav-item"><a class="nav-link" href="./payments"><fmt:message
								key="header.payments" /></a></li>
					<li class="nav-item"><a class="nav-link"
						href="./operations"><fmt:message key="header.operations" /></a></li>
					<li class="nav-item"><a class="nav-link active" href="./accounts"><fmt:message
								key="header.accounts" /></a></li>
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
		</nav>
		<h1>
			<fmt:message key="header.operations" />
		</h1>
		<div class="container">
			<table class="table table-sm align-middle mt-2">
				<thead>
					<tr>
						<th scope="col" role="button" onclick="document.location = '<%out.print(request.getContextPath());%>/accounts?language=<% out.print(language); %>&orderBy=id&ascending=<%= orderBy.equals("name")||orderBy.equals("amount")?"true":ascending.equals("true")?"false":"true" %>';">
						<fmt:message key="accounts.number"/>
						<c:choose>
								<c:when test="${orderBy == 'id' && ascending=='true'}">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-arrow-up" viewBox="0 0 16 16">
  										<path fill-rule="evenodd"
											d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5z" />
									</svg>
								</c:when>
								<c:when test="${orderBy == 'id' && ascending=='false'}">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down" viewBox="0 0 16 16">
  										<path fill-rule="evenodd" d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1z"/>
									</svg>
								</c:when>
							</c:choose>
							</th>
						<th scope="col" role="button" onclick="document.location = '<%out.print(request.getContextPath());%>/accounts?language=<% out.print(language); %>&orderBy=name&ascending=<%= orderBy.equals("id")||orderBy.equals("amount")?"true":ascending.equals("true")?"false":"true" %>';">
						<fmt:message key="accounts.name"/>
						<c:choose>
								<c:when test="${orderBy == 'name' && ascending=='true'}">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-arrow-up" viewBox="0 0 16 16">
  										<path fill-rule="evenodd"
											d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5z" />
									</svg>
								</c:when>
								<c:when test="${orderBy == 'name' && ascending=='false'}">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down" viewBox="0 0 16 16">
  										<path fill-rule="evenodd" d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1z"/>
									</svg>
								</c:when>
							</c:choose>
						</th>
						<th scope="col"><fmt:message key="accounts.status"/></th>
						<th scope="col" role="button" onclick="document.location = '<%out.print(request.getContextPath());%>/accounts?language=<% out.print(language); %>&orderBy=amount&ascending=<%= orderBy.equals("id")||orderBy.equals("name")?"true":ascending.equals("true")?"false":"true" %>';">
						<fmt:message key="accounts.balance"/>
						<c:choose>
								<c:when test="${orderBy == 'amount' && ascending=='true'}">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-arrow-up" viewBox="0 0 16 16">
  										<path fill-rule="evenodd"
											d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5z" />
									</svg>
								</c:when>
								<c:when test="${orderBy == 'amount' && ascending=='false'}">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down" viewBox="0 0 16 16">
  										<path fill-rule="evenodd" d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1z"/>
									</svg>
								</c:when>
							</c:choose>
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="account" items="${accounts}">
					<tr>
						<th scope="row">${account.getId()}</th>
						<td>${account.getName()}</td>
						<td>${account.getAccountStatus().getName()}</td>
						<td>${account.getBalance()}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>