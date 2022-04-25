<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<fmt:setLocale value="${param.language}" />
<fmt:setBundle basename="translate" />
<%
String userName = (String) session.getAttribute("userName");
Integer type = (Integer) session.getAttribute("type");
String fileName = "userLinks.jsp";
if (type == 2) {
	fileName = "adminLinks.jsp";
}
%>
<div class="header fh">
	<a class="logo" href="<%out.print(request.getContextPath());%>"></a> <span> <a class="languages_reference"
		href="?language=ru">UA</a> <a class="languages_reference" href="?language=en">EN</a>
	</span>
	<div class="user_logo">
		<%
		out.print(userName);
		%>
		<a href="logout"><fmt:message key="general.logout" /></a>
	</div>
</div>
