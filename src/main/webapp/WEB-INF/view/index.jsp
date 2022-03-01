<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payments</title>
<jsp:include page="../../includeStyle.jsp"/>
</head>
<body>
	<main>
		<div class="login_page">
			<div class="container fh">
				<div class="about">
					<h1>Hello world!!</h1>
				</div>
				<div class="login_form">
					<form action="login" method="post">
						<div class="login_inputs fv">
							<label>Login:</label><input name="first_name" /> <label>Password:</label><input
								name="password" type="password" /> <input type="submit"
								value="Login" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
</body>
</html>