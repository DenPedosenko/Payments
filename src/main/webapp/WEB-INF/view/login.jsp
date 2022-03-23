<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="translate" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Login</title>
<jsp:include page="../../includeStyle.jsp" />
</head>
<body>
	<div class="container">
		<div class="content">
			<div class="content_header">
				<h1>Payments</h1>
			</div>
			<form action="login" method="post">
				<div class="login_form fv">
					<p>
						<fmt:message key="login.header" />
					</p>
					<input class="login_input" name="email" placeholder="<fmt:message key="registration.email" />" />
					<input class="login_input" name="password" type="password"
						placeholder="<fmt:message key="registration.password" />" />
					<input class="login_input" name="submit" type="submit" value="<fmt:message key="login.button" />" />
					<a class="registration_link" href="registration">
						<fmt:message key="registration.registration" />
					</a>
				</div>
			</form>
			<%
				boolean error = false;
				if (request.getAttribute("error") != null) {
					error = (Boolean) request.getAttribute("error");
				}
				if (error) {
					out.print("Wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
				}
				%>
		</div>
		<div class="languages">
			<span>
				<a class="languages_reference" href="?language=ru">UA</a>
				<a class="languages_reference" href="?language=en">EN</a>
			</span>
		</div>
	</div>
</body>
</html>