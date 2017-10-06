<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>

<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css"
href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1>Register</h1>

<sf:form method="POST" modelAttribute="regForm" action="/mobshop/member/register">
Username: <sf:input path="username" /><sf:errors path="username" cssClass="error" /><br/>
Password: <sf:password path="password" /><sf:errors path="password" cssClass="error" /><br/>
Email: <sf:input path="email" type="email"/><sf:errors path="email" cssClass="error" /><br/>
Registration Date: <sf:input path="registrationDate" /><sf:errors path="registrationDate" cssClass="error" /><br/>
Birth Date: <sf:input path="birthDate" /><sf:errors path="birthDate" cssClass="error" /><br/>
Active: <sf:input path="active" /><sf:errors path="active" cssClass="error" /><br/>
Black-Listed: <sf:input path="blacklisted" /><sf:errors path="blacklisted" cssClass="error" /><br/>
Risk Weighting: <sf:input path="riskWeighting" /><sf:errors path="riskWeighting" cssClass="error" /><br/>
Age: <sf:input path="age" /><sf:errors path="age" cssClass="error" /><br/>
<input type="submit" value="Register" />
</sf:form>
</body>
</html>