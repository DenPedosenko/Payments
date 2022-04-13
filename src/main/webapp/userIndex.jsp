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
			<a class="navbar-brand" href="<%out.print(request.getContextPath());%>"> <img src="./resources/img/logo.png"
				alt="logo" width="130" height="32" class="d-inline-block align-text-top">
			</a>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
						data-bs-toggle="dropdown" aria-expanded="false">${language}</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="?language=en">English</a></li>
							<li><a class="dropdown-item" href="?language=ua">Українська</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link active" aria-current="page" href="./"><fmt:message
								key="header.main" /></a></li>
					<li class="nav-item"><a class="nav-link" href="./payments"><fmt:message key="header.payments" /></a></li>
					<li class="nav-item"><a class="nav-link" href="./operations"><fmt:message key="header.operations" /></a></li>
					<li class="nav-item"><a class="nav-link" href="./accounts"><fmt:message key="header.accounts" /></a></li>
					<li class="nav-item"><a class="nav-link" href="./cards"><fmt:message key="header.cards" /></a></li>
				</ul>
			</div>
			<div class="btn-group">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="defaultDropdown" data-bs-toggle="dropdown"
					data-bs-auto-close="true" aria-expanded="false">
					<img src="./resources/img/defaultUserLogo.png" alt="userLogo">
				</button>
				<ul class="dropdown-menu dropdown-menu-end" aria-labelledby="defaultDropdown">
					<li><a class="dropdown-item" href="profile"><fmt:message key="general.profile" /></a></li>
					<li><a class="dropdown-item" href="logout"><fmt:message key="general.logout" /></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<h1>
		<fmt:message key="wigets.header" />
	</h1>
	<div class="row ms-2">
		<%
		List<Card> cards = (List<Card>) request.getAttribute("cards");
		for (Card card : cards) {
			out.print("<div class=\"col-sm-3\">\n" + "<div class=\"card\">\n" + "<div class=\"card-header\">\n"
			+ card.getAccount().getName() + "</div>\n" + "<div class=\"card-body\">\n" + " <h5 class=\"card-title\">\n"
			+ bundle.getString("cards.balance") + " " + card.getAccount().getBalance() + "</h5>\n"
			+ "<div class=\"card-text\"><h3>" + card.getCardNumber().replaceAll("(.{4})", "$0 ").trim()
			+ "</h3></div>\n" + "<div class=\"hstack mb-2\">\n<div class=\"card-text me-1\">" + card.getExpDate()
			+ "</div>\n <div class=\"vr\"></div>\n <div class=\"card-text ms-1\">" + card.getCvv() + "</div>\n</div>"
			+ "<a href=\"#\" class=\"stretched-link\"></a>" + " <a href=\"#\" class=\"btn btn-primary me-3\">"
			+ bundle.getString("general.add_funds") + "</a>" + " <a href=\"#\" class=\"btn btn-secondary\">"
			+ bundle.getString("general.block") + "</a>" + " \n</div>" + "</div>\n" + "  </div>\n" + "");
		}
		%>
	</div>
	<div class="mt-2">
		<h1>
			<fmt:message key="main.operations_header" />
		</h1>
	</div>
</div>
<div class="container mt-2">
	<div class="row">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">22.03.2022</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">1</th>
					<td>•••• 5635 (Visa Fishka)</td>
					<td>262083503747 (Універсальний безстроковий)</td>
					<td>Виконано</td>
					<td>6 806,00</td>
					<td><a href="#">Повторити</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
