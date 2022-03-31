<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="translate"/>
<%@ page contentType="text/html; charset=UTF-8" %>

<li><a href="users" class="users"><fmt:message key="profile.users"/></a></li>
<li><a href="requests" class="users"><fmt:message key="profile.requests"/></a></li>