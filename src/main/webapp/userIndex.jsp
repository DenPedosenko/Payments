<%@page import="com.mysql.cj.conf.ConnectionUrlParser.Pair"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.epam.data.model.Card"%>
<%@page import="com.epam.data.model.Payment"%>
<%@page import="com.epam.data.model.UserAccount"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
ResourceBundle bundle = ResourceBundle.getBundle("translate", new Locale((String) request.getAttribute("language")));
%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="translate" />
<%@ page contentType="text/html; charset=UTF-8"%>
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
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="./"><fmt:message key="header.main" /></a></li>
					<li class="nav-item"><a class="nav-link" href="./payments"><fmt:message
								key="header.payments" /></a></li>
					<li class="nav-item"><a class="nav-link" href="./operations"><fmt:message
								key="header.operations" /></a></li>
					<li class="nav-item"><a class="nav-link" href="./accounts"><fmt:message
								key="header.accounts" /></a></li>
					<li class="nav-item"><a class="nav-link" href="./cards"><fmt:message
								key="header.cards" /></a></li>
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
		<fmt:message key="wigets.header" />
	</h1>
	<div class="container">
		<div class="row ms-2">
			<%
			List<Card> cards = (List<Card>) request.getAttribute("cards");
			for (Card card : cards) {
				out.print("<div class=\"col-sm-4\">\n" + "<div class=\"card\">\n" + "<div class=\"card-header\">\n"
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
	</div>
	<div class="mt-2">
		<h1>
			<fmt:message key="main.operations_header" />
		</h1>
	</div>
<div class="container mt-2">
	<%
	Map<String, List<Payment>> payments = (Map<String, List<Payment>>) request.getAttribute("payments");
	for (String key : payments.keySet()) {
		out.print("<div class=\"row\">" + "<table class=\"table table-hover\">" + "<thead><tr><th scope=\"col\">" + key
		+ "</th></tr></thead>" + "<tbody>");
		for (Payment payment:payments.get(key)){
		out.print("<tr><td>"+payment.getUserAccount().getName()+"</td>"
		+ "<td>"+payment.getPaymentType().getName()+"</td><td>"+payment.getPaymentStatus().getName()+"</td><td>"+payment.getAmount()+"</td>"
		+ "<td><a href=\"#\" data-bs-toggle=\"modal\" data-bs-target=\"#paymentModal"+payment.getId()+"\">Повторити</a></td></tr>\r\n"+
		"<div class=\"modal fade\" id=\"paymentModal"+payment.getId()+"\" tabindex=\"-1\" aria-labelledby=\"paymentModalLabel\" aria-hidden=\"true\">\r\n"
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
		List<UserAccount> accounts = (List<UserAccount>) request.getAttribute("accounts");
		for(UserAccount account: accounts){
			if(account.getId() == payment.getUserAccount().getId()){
				out.print("  <option value="+account.getId()+"\" selected>"+account.getName()+"</option>\r\n");
			} else {
				out.print("  <option value="+account.getId()+"\">"+account.getName()+"</option>\r\n");
			}		
		}
		out.print("</select>"
		+ "		</div>\r\n"
		+ "	<label for=\"basic-url\" class=\"form-label\">"+bundle.getString("payment.recipient")+"</label>\r\n"
		+ "	<div class=\"input-group mb-3\">\r\n"
		+"		<select class=\"form-select\" name=\"payment_type\" aria-label=\"Default select example\" disabled>\r\n"
		+ "  		<option value="+payment.getPaymentType().getId()+"\" selected>"+payment.getPaymentType().getName()+"</option>\r\n"					
		+"		</select>"
		+ "	</div>\r\n"
		+ "<div class=\"input-group mb-3\">\r\n"
		+ "  <span class=\"input-group-text\">$</span>\r\n"
		+ "  <input type=\"text\" class=\"form-control\" aria-label=\"Amount (to the nearest dollar)\" value="+payment.getAmount()+">\r\n"
		+ "  <span class=\"input-group-text\">.00</span>\r\n"
		+ "    </div>"
		+ "      </div>\r\n"
		+ "      <div class=\"modal-footer\">\r\n"
		+ "        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>\r\n"
		+ "        <input type=\"submit\" class=\"btn btn-primary\" data-bs-dismiss=\"modal\" name=\"continue\" value="+bundle.getString("button.continue")+">"
		+ "      </div>\r\n"
		+ "    </div>\r\n"
		+ "   </form>"
		+ "  </div>\r\n"
		+ "</div>");
		}
		out.print("</tbody></table></div>");
	}
	%>
</div>
</div>
