<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>MOB-SHOP</title>
<link rel="stylesheet"
type="text/css"
href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1>MOB SHOP - OPEN FOR BUSINESS</h1>
<a href="<c:url value="/shop" />">Shop</a> |
<a href="<c:url value="/discounts?amount=2000" />">Discounts</a> |
<a href="<c:url value="/member/register" />">Register</a>
</body>
</html>