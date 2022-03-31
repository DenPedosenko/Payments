<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="translate"/>
<%@ page contentType="text/html; charset=UTF-8" %>
<li><a href="profile" class="users"><fmt:message key="profile.user"/></a></li>
<li><a href="payments" class="users"><fmt:message key="profile.payments"/></a></li>