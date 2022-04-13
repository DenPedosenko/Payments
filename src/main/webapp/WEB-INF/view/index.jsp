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
String userName = (String) session.getAttribute("userName");
Integer type = (Integer) session.getAttribute("type");
String fileName = "../../userIndex.jsp";
if (type == 2) {
	fileName = "../../adminIndex.jsp";
}
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
<jsp:include page="<%=fileName%>"></jsp:include>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>
