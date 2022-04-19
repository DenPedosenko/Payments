<%@page import="com.epam.data.dao.AccountStatusDao"%>
<%@page import="com.epam.data.model.AccountStatus"%>
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
String operationStatus = request.getParameter("operationStatus")!= null?request.getParameter("operationStatus"):"";
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
		<% 
	switch(operationStatus){
	case "alreadyCreated":
		out.print("<h2 class=\"text-center text-danger\">"+bundle.getString("operations.already_created")+"</h2>");
		break;
	case "unblockedRequestSent":
		out.print("<h2 class=\"text-center text-success\">"+bundle.getString("operations.unblocked_request_sent")+"</h2>");
		break;
	} 
	%>
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
				+ "</div>\n <div class=\"vr\"></div>\n <div class=\"card-text ms-1\">" + card.getCvv() + "</div>\n</div>");
				if(card.getAccount().getAccountStatus().getId() == 1){
					out.print( " <button type=\"button\" href=\"#\" class=\"btn btn-primary me-3\" data-bs-toggle=\"modal\" data-bs-target=\"#addFundsModal"+card.getId()+"\">"+ bundle.getString("general.add_funds") + "</button>" 
						+ " <button href=\"#\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#blockCardModal"+card.getId()+"\">"+ bundle.getString("general.block") + "</button>" );
				} else {
					out.print("<button href=\"#\" class=\"btn btn-secondary\" data-bs-toggle=\"modal\" data-bs-target=\"#unblockCardModal"+card.getId()+"\">"+ bundle.getString("general.unblock") + "</button>" );						
				}
				
				out.print(" \n</div>" 
				+ "</div>\n" 
				+ " </div>\n"
				+	"<div class=\"modal fade\" id=\"addFundsModal"+card.getId()+"\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\r\n"
				+ "  <div class=\"modal-dialog\">\r\n"
				+ "   <form action=\"?accountId="+card.getAccount().getId()+"\" method=\"post\">\r\n"
				+ "    <div class=\"modal-content\">\r\n"
				+ "      <div class=\"modal-header\">\r\n"
				+ "        <h5 class=\"modal-title\" id=\"exampleModalLabel\">"+bundle.getString("general.add_funds")+"</h5>\r\n"
				+ "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n"
				+ "      </div>\r\n"
				+ "      <div class=\"modal-body\">\r\n"
				+ "			<div class=\"mb-3\">\r\n"
				+ "    			<label for=\"amount_funds\" class=\"form-label\">"+bundle.getString("general.add_funds_text")+"</label>\r\n"
				+ "  			<input type=\"number\" class=\"form-control\" step=\"0.01\" aria-label=\"Amount (to the nearest dollar)\" name=\"amount\" required>\r\n"
				+ "  		</div>"
				+ "      </div>\r\n"
				+ "      <div class=\"modal-footer\">\r\n"
				+ "        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">"+bundle.getString("button.cancel")+"</button>\r\n"
				+ "        <input type=\"submit\" class=\"btn btn-primary\"   name=\"continue\" value="+bundle.getString("button.continue")+">"
				+ "      </div>\r\n"
				+ "    </div>\r\n"
				+ "   </form>\r\n"
				+ "  </div>\r\n"
				+ "</div>"
				+	"<div class=\"modal fade\" id=\"blockCardModal"+card.getId()+"\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\r\n"
				+ "  <div class=\"modal-dialog\">\r\n"
				+ "   <form action=\"?accountId="+card.getAccount().getId()+"\" method=\"post\">\r\n"
				+ "    <div class=\"modal-content\">\r\n"
				+ "      <div class=\"modal-header\">\r\n"
				+ "        <h5 class=\"modal-title\" id=\"exampleModalLabel\">"+bundle.getString("cards.block_header")+"</h5>\r\n"
				+ "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n"
				+ "      </div>\r\n"
				+ "      <div class=\"modal-body\">\r\n"
				+ "        <p>"+bundle.getString("cards.block_text")+"</p>"
				+ "         <h4>"+card+"</h4>"
				+ "      </div>\r\n"
				+ "      <div class=\"modal-footer\">\r\n"
				+ "        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">"+bundle.getString("button.no")+"</button>\r\n"
				+ "        <input type=\"submit\" class=\"btn btn-primary\"   name=\"block\" value="+bundle.getString("button.yes")+">"
				+ "      </div>\r\n"
				+ "    </div>\r\n"
				+ "   </form>\r\n"
				+ "  </div>\r\n"
				+ "</div>"
				+	"<div class=\"modal fade\" id=\"unblockCardModal"+card.getId()+"\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\r\n"
				+ "  <div class=\"modal-dialog\">\r\n"
				+ "   <form action=\"?accountId="+card.getAccount().getId()+"\" method=\"post\">\r\n"
				+ "    <div class=\"modal-content\">\r\n"
				+ "      <div class=\"modal-header\">\r\n"
				+ "        <h5 class=\"modal-title\" id=\"exampleModalLabel\">"+bundle.getString("cards.unblock_header")+"</h5>\r\n"
				+ "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n"
				+ "      </div>\r\n"
				+ "      <div class=\"modal-body\">\r\n"
				+ "        <p>"+bundle.getString("cards.unblock_text")+"</p>"
				+ "      </div>\r\n"
				+ "      <div class=\"modal-footer\">\r\n"
				+ "        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">"+bundle.getString("button.no")+"</button>\r\n"
				+ "        <input type=\"submit\" class=\"btn btn-primary\"   name=\"unblock\" value="+bundle.getString("button.yes")+">"
				+ "      </div>\r\n"
				+ "    </div>\r\n"
				+ "   </form>\r\n"
				+ "  </div>\r\n"
				+ "</div>");
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
			if(account.getAccountStatus().getId() == 1){
				if(account.getId() == payment.getUserAccount().getId()){
					out.print("  <option value="+account.getId()+" selected>"+account.getName()+"</option>\r\n");
				} else {
					out.print("  <option value="+account.getId()+">"+account.getName()+"</option>\r\n");
				}	
			}	
		}
		out.print("</select>"
		+ "		</div>\r\n"
		+ "	<label for=\"basic-url\" class=\"form-label\">"+bundle.getString("payment.recipient")+"</label>\r\n"
		+ "	<div class=\"input-group mb-3\">\r\n"
		+"		<select class=\"form-select\" name=\"payment_type\" aria-label=\"Default select example\">\r\n"
		+ "  		<option value="+payment.getPaymentType().getId()+" selected>"+payment.getPaymentType().getName()+"</option>\r\n"					
		+"		</select>"
		+ "	</div>\r\n"
		+ "<div class=\"input-group mb-3\">\r\n"
		+ "  <span class=\"input-group-text\">$</span>\r\n"
		+ "  <input type=\"number\" class=\"form-control\" step=\"0.01\" value=\""+payment.getAmount()+"\" name=\"amount\" required>\r\n"
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
		out.print("</tbody></table></div>");
	}
	%>
</div>
</div>
