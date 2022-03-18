<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="translate" />
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Registration</title>
      <jsp:include page="../../includeStyle.jsp" />
   </head>
   <body>
    <div class="container">
      <div class="content">
        <div class="content_header">
          <h1>Payments</h1>
        </div>
        <form action="registration" method = "post">
        <div class="login_form fv">
          <h2><fmt:message key="registration.header" /></h2>
          <p><fmt:message key="registration.subheader" /></p>
         <div class = "inline_inputs">
            <input class="login_input" name="first_name" placeholder="<fmt:message key="registration.first_name" />" />
            <input class="login_input" name="last_name" placeholder="<fmt:message key="registration.last_name" />" />
         </div> 
          <input class="login_input" name="email" placeholder="<fmt:message key="registration.email" />" />
          <input
            class="login_input"
            name="password"
            type="password"
            placeholder="<fmt:message key="registration.password" />"
          />
          <input class="login_input" name="submit" type="submit" value="<fmt:message key="registration.registration" />"/>
          <a class="registration_link" href="login"><fmt:message key="registration.back_to_login" /></a>
        </div>
        </form>
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