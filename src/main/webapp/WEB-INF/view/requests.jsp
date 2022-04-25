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
String operationStatus = request.getParameter("operationStatus") != null ? request.getParameter("operationStatus") : "";
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
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="./"><fmt:message key="header.main" /></a></li>
						<li class="nav-item"><a class="nav-link" href="users"
							class="users"><fmt:message key="profile.users" /></a></li>
						<li class="nav-item"><a class="nav-link active"
							href="requests" class="users"><fmt:message
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
			<fmt:message key="header.operations" />
		</h1>
		<%
		switch (operationStatus) {
		case "success":
			out.print("<h2 class=\"text-center text-success\">Operation succeeded</h2>");
			break;
		case "error":
			out.print("<h2 class=\"text-center text-danger\">You do not have enough funds in your account</h2>");
			break;
		}
		%>
		<div class="container">
			<table class="table table-sm align-middle mt-2">
				<thead>
					<tr>
						<th scope="col" role="button"
							onclick="document.location = '<%out.print(request.getContextPath());%>/requests?language=<%out.print(language);%>&orderBy=id&ascending=<%=orderBy.equals("date") || orderBy.equals("requestStatus") ? "true"
		: ascending.equals("true") ? "false" : "true"%>';">
							<fmt:message key="operations.number" /> <c:choose>
								<c:when test="${orderBy == 'id' && ascending=='true'}">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-arrow-up" viewBox="0 0 16 16">
  										<path fill-rule="evenodd"
											d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5z" />
									</svg>
								</c:when>
								<c:when test="${orderBy == 'id' && ascending=='false'}">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-arrow-down"
										viewBox="0 0 16 16">
  										<path fill-rule="evenodd"
											d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1z" />
									</svg>
								</c:when>
							</c:choose>
						</th>
						<th scope="col" role="button"
							onclick="document.location = '<%out.print(request.getContextPath());%>/requests?language=<%out.print(language);%>&orderBy=date&ascending=<%=orderBy.equals("id") || orderBy.equals("requestStatus") ? "true" : ascending.equals("true") ? "false" : "true"%>';">
							<fmt:message key="operations.date" /> <c:choose>
								<c:when test="${orderBy == 'date' && ascending=='true'}">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-arrow-up" viewBox="0 0 16 16">
  										<path fill-rule="evenodd"
											d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5z" />
									</svg>
								</c:when>
								<c:when test="${orderBy == 'date' && ascending=='false'}">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-arrow-down"
										viewBox="0 0 16 16">
  										<path fill-rule="evenodd"
											d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1z" />
									</svg>
								</c:when>
							</c:choose>
						</th>
						<th scope="col"><fmt:message key="users.name" /></th>
						<th scope="col"><fmt:message key="operations.payment_type" /></th>
						<th scope="col" role="button"
							onclick="document.location = '<%out.print(request.getContextPath());%>/requests?language=<%out.print(language);%>&orderBy=requestStatus&ascending=<%=orderBy.equals("id") || orderBy.equals("date") ? "true" : ascending.equals("true") ? "false" : "true"%>';">
							<fmt:message key="operations.payment_status" /> <c:choose>
								<c:when
									test="${orderBy == 'requestStatus' && ascending=='true'}">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-arrow-up" viewBox="0 0 16 16">
  										<path fill-rule="evenodd"
											d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5z" />
									</svg>
								</c:when>
								<c:when
									test="${orderBy == 'requestStatus' && ascending=='false'}">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-arrow-down"
										viewBox="0 0 16 16">
  										<path fill-rule="evenodd"
											d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1z" />
									</svg>
								</c:when>
							</c:choose>
						</th>
						<th scope="col"><fmt:message key="requests.action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="request" items="${requests}">
						<tr>
							<th scope="row">${request.getId()}</th>
							<td>${DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(request.getDate())}</td>
							<td>${request.getUser().getUserName()}</td>
							<td>${request.getAccount().getName()}</td>
							<td>${request.getStatus().getName()}</td>
							<c:choose>
								<c:when test="${request.getStatus().getId() == 1}">
									<td><a href="?dismissRequest=${request.getId()}" class="btn btn-danger"
										aria-current="page"><fmt:message key="admin.dismiss" /></a> <a
										href="?acceptRequest=${request.getId()}" class="btn btn-primary"><fmt:message
												key="admin.accept" /></a></td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-end">
					<c:choose>
						<c:when test="${page == 1}">
							<li class="page-item disabled"><span class="page-link">Previous</span></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="<%out.print(request.getContextPath());%>/requests?language=${language}&orderBy=${orderBy}&ascending=${ascending}&page=${page-1}">Previous</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="1"
						end="${size%10 == 0?size/10:size/10+1}">
						<li class="page-item ${page == i?'active':'' }"><a
							class="page-link"
							href="<%out.print(request.getContextPath());%>/requests?language=${language}&orderBy=${orderBy}&ascending=${ascending}&page=${i}"><c:out
									value="${i}" /></a></li>
					</c:forEach>
					<c:choose>
						<c:when
							test="${page + 1 > (size % 10 == 0 ? size / 10 : size / 10 + 1)}">
							<li class="page-item disabled"><span class="page-link">Next</span></li>
						</c:when>
						<c:otherwise>
							<li class="page-item "><a class="page-link"
								href="<%out.print(request.getContextPath());%>/operations?language=${language}&orderBy=${orderBy}&ascending=${ascending}&page=${page+1}">Next</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
		</div>
	</div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</html>