<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.epam.data.model.Card"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="translate" />
<%
ResourceBundle bundle = ResourceBundle.getBundle("translate", new Locale((String) request.getAttribute("language")));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payments</title>
<jsp:include page="../../includeStyle.jsp" />
</head>
<body>
	<jsp:include page="../../header.jsp" />
	<div class="container_main">
		<div class="contentWrapper">
			<div class="main_menu">
				<ul class="menu">
					<li class="active dashboard"><a href="./" class="active"> <fmt:message key="header.main" />
					</a></li>
					<li class="payments"><a href="./payments"> <fmt:message key="header.payments" />
					</a></li>
					<li class="transactions"><a href="./transactions"> <fmt:message key="header.operations" />
					</a></li>
					<li class="accounts"><a href="./accounts"> <fmt:message key="header.accounts" />
					</a></li>
					<li class="cards"><a href="./cards"> <fmt:message key="header.cards" />
					</a></li>
				</ul>
			</div>
			<div class="content">
				<div class="widgets">
					<div class="widget products">
						<h1>
							<fmt:message key="wigets.header" />
						</h1>
						<%
						List<Card> cards = (List<Card>) request.getAttribute("cards");
						for (Card card : cards) {
							out.print("<div class=\"swiper\">" + "<div class=\"swiper-container\">" + "<div class=\"swiper-wrapper\">"
							+ "<div class=\"swiper-slide card clickWrapper active\">" + "<img src=\"\"> <span class=\"alias\">"
							+ card.getAccount().getName() + "</span> <span class=\"paymentSystem visa\">VISA</span>"
							+ "<div class=\"balance amount\">" + "<span class=\"sum\">" + card.getAccount().getBalance()
							+ "</span> <span class=\"currency\">₴</span>" + "</div>" + "<span class=\"expiration\">" + card.getExpDate()
							+ "</span>\n <span class=\"number\">" + card.getCardNumber() + "</span>\n"
							+ "<div class=\"actionBar\">\n<a href=\"\">" + bundle.getString("general.add_funds")
							+ "</a> \n <a href = \"\"> " + bundle.getString("general.block") + "</a>" + "</div>" + "</div>" + "</div>"
							+ "</div>" + "</div>");
						}
						%>
					</div>
					<div data-wid="TRANSACTIONS" class="widget transactions">
						<h1>
							<fmt:message key="main.operations_header" />
						</h1>
						<div class="transactions">
							<div class="dataList">
								<div class="group">
									<div class="groupHeader">22.03.2022</div>
									<div class="groupData">
										<div id="id9c" class="transaction clickWrapper productToProductTransfer">
											<span class="time">17:18</span> <span class="icon"></span>
											<div class="details">
												<div class="sender">
													<span class="paymentSystem visa">VISA</span> <a class="product actionProductDetails"
														href="./dashboard?_crypt_=ZoUaJ50YK8TsqgvjQfEMv1aJxGe9yRdLY1UnEaJM3LQMuLKJ7jEdZNyP_8-hcfLdaQRO2t--yX_nir9oL2-4fHuUmR5MXxqP4mbsi2Z-Ij_MDZuwOGLH2laMZcXQ1iNSS1d098T-tZJ2L-5cVD8WbOTPS4jaxxuHuQCo8nkiJqyGYkaHhUYK2w">
														•••• 5635 (Visa Fishka) </a>
												</div>
												<div class="recipient">
													<a class="product actionProductDetails"
														href="./dashboard?_crypt_=ZoUaJ50YK8TsqgvjQfEMv1aJxGe9yRdLY1UnEaJM3LQMuLKJ7jEdZNyP_8-hcfLdaQRO2t--yX_nir9oL2-4fHuUmR5MXxqP4mbsi2Z-Ij_MDZuwOGLH2laMZcXQ1iNSS1d098T-tZJ2L-5cVD8WbJ9V66vTGyxL4N1WyYOICpppQgKU2-0mtQ">
														262083503747 (Універсальний безстроковий) </a>
												</div>
												<span class="status completed">Виконано</span>
												<div class="amount">
													<span class="sum">6 806,00</span> <span class="currency">₴</span>
												</div>
											</div>
											<div class="actionBar">
												<a id="id9b" href="javascript:" class="actionRepeat"> Повторити </a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
