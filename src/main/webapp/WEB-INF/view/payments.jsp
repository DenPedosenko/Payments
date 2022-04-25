<%@page import="com.epam.data.model.Card"%>
<%@page import="com.epam.data.model.UserAccount"%>
<%@page import="com.epam.data.model.PaymentType"%>
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
String operationStatus = request.getParameter("operationStatus")!= null?request.getParameter("operationStatus"):"";
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
					<li class="nav-item"><a class="nav-link "
						aria-current="page" href="./"><fmt:message key="header.main" /></a></li>
					<li class="nav-item"><a class="nav-link active" href="./payments"><fmt:message
								key="header.payments" /></a></li>
					<li class="nav-item"><a class="nav-link" href="./operations"><fmt:message
								key="header.operations" /></a></li>
					<li class="nav-item"><a class="nav-link" href="./accounts"><fmt:message
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
		<fmt:message key="header.payments" />
	</h1>
	<div class="container">
	
	<% 
	switch(operationStatus){
	case "success":
		out.print("<h2 class=\"text-center text-success\">Operation succeeded</h2>");
		break;
	case "save":
		out.print("<h2 class=\"text-center text-success\">Operation saved, you can continue in \"Operations\"</h2>");
		break;
	case "error":
		out.print("<h2 class=\"text-center text-danger\">You do not have enough funds in your account</h2>");
		break;	
	} 
	%>
		<div class="row ms-2">
			<%
			List<PaymentType> payments = (List<PaymentType>) request.getAttribute("payment_types");
			for (PaymentType payment : payments) {
				out.print("<div class=\"card m-3\" style=\"width: 12rem;\">\r\n"
				+ "  <img src=\"./resources/img/"+payment.getId()+".png\" class=\"card-img-top\" alt=\"payment_logo\">\r\n"
				+ "  <div class=\"card-body\">\r\n"
				+ "    <p class=\"card-text text-center\">"+payment.getName()+"</p>\r\n"
				+"     <a href=\"#\" class=\"stretched-link\" data-bs-toggle=\"modal\" data-bs-target=\"#paymentModal"+payment.getId()+"\"></a>"
				+ "  </div>\r\n"
				+ "</div>"
				+"<div class=\"modal fade\" id=\"paymentModal"+payment.getId()+"\" tabindex=\"-1\" aria-labelledby=\"paymentModalLabel\" aria-hidden=\"true\">\r\n"
				+ "  <div class = \"modal-dialog\">"
				+ "   <form action=\"payments\" method=\"post\">\r\n"
				+ "    <div class=\"modal-content\" action=\"payments\" method=\"post\">\r\n"
				+ "      <div class=\"modal-header\">\r\n"
				+ "        <h5 class=\"modal-title\" id=\"exampleModalLabel\">"+bundle.getString("header.payment")+"</h5>\r\n"
				+ "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n"
				+ "      </div>\r\n"
				+ "      <div class=\"modal-body\">\r\n"
				+ "			<label for=\"basic-url\" class=\"form-label\">"+bundle.getString("payment.sender")+"</label>\r\n"
				+ "			<div class=\"input-group mb-3\">\r\n"
				+ "			<select class=\"form-select\" name=\"account\" aria-label=\"Default select example\">\r\n");
				List<Card> cards = (List<Card>) request.getAttribute("cards");
				for(Card card: cards){
					if(card.getAccount().getAccountStatus().getId() == 1) {
						out.print("  <option value="+card.getId()+">"+card+"</option>\r\n");	
					}	
				}
				out.print("</select>"
				+ "		</div>\r\n"
				+ "	<label for=\"basic-url\" class=\"form-label\">"+bundle.getString("payment.recipient")+"</label>\r\n"
				+ "	<div class=\"input-group mb-3\">\r\n"
				+"		<select class=\"form-select\" name=\"payment_type\" aria-label=\"Default select example\">\r\n"
				+ "  		<option value="+payment.getId()+" selected>"+payment.getName()+"</option>\r\n"					
				+"		</select>"
				+ "	</div>\r\n"
				+ "<div class=\"input-group mb-3\">\r\n"
				+ "  <span class=\"input-group-text\">$</span>\r\n"
				+ "  <input type=\"number\" class=\"form-control\" step=\"0.01\" aria-label=\"Amount (to the nearest dollar)\" name=\"amount\" required>\r\n"
				+ "    </div>"
				+ "      </div>\r\n"
				+ "      <div class=\"modal-footer\">\r\n"
				+ "        <input type=\"submit\" class=\"btn btn-secondary\"  name=\"save\" value="+bundle.getString("button.save")+">"
				+ "        <input type=\"submit\" class=\"btn btn-primary\"   name=\"continue\" value="+bundle.getString("button.continue")+">"
				+ "      </div>\r\n"
				+ "    </div>\r\n"
				+ "   </form>"
				+ "  </div>\r\n"
				+ "</div>");
			}
			%>
		</div>
	</div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>