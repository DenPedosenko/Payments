<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="translate" />
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<title><fmt:message key="header.login"/></title>
</head>
<body>
	<div class="container-sm mt-1">
		<div class="dropdown">
			<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown"
				aria-expanded="false">${language}</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
				<li><a class="dropdown-item" href="?language=en">English</a></li>
				<li><a class="dropdown-item" href="?language=ua">Українська</a></li>
			</ul>
		</div>
		<div class="row align-items-center">
			<div class="col-md-5 offset-md-4">
				<h1 class="text-primary">Payments</h1>
				<form action="login" method="post">
					<div class="mb-3">
						<label for="inputEmail1" class="form-label"><fmt:message key="registration.email"/></label> <input type="email" class="form-control"
							 aria-describedby="emailHelp" name="email">
						<div id="emailHelp" class="form-text"><fmt:message key="login.text"/></div>
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label"><fmt:message key="registration.password"/></label> <input type="password" class="form-control"
						 name="password">
					</div>
					<button type="submit" class="btn btn-primary"><fmt:message key="login.button"/></button>
					<a class="btn btn-outline-primary" href="registration" role="button"><fmt:message key = "registration.registration"/></a>
					
				</form>
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>