<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<fmt:setLocale value="${param.language}" />
<fmt:setBundle basename="translate" />
<header class="header_block">
	<div class="menu_content">
		<div class="menu_container">
			<div class="container"></div>
			<div class="navigation-content container">
				<nav class="navigation">
					<ul>
						<li><a href="<%out.print(request.getContextPath());%>"><fmt:message
									key="header.main" /></a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</header>
