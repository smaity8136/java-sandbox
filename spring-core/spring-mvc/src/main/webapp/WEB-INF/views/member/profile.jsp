<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>

<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css"
href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1>PROFILE</h1>

<div><span>User Name: <c:out value="${targetMember.username}"/></span></div>
<div><span>E-mail: <c:out value="${targetMember.email}"/></span></div>
<div><span>Registration Date: <c:out value="${targetMember.registrationDate}"/></span></div>
<div><span>Active: <c:out value="${targetMember.active}"/></span></div>

<br/>
<a href="<c:url value="/" />">Home</a>
</body>
</html>