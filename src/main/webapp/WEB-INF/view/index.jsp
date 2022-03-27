<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="translate" />
<%String userName = (String) session.getAttribute("userName");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payments</title>
<jsp:include page="../../includeStyle.jsp" />
</head>
<body>
	<main>
		Hello world! <% out.println(userName); %>
		<a href="logout" class = "logout">Logout</a>
	</main>
</body>
</html>
