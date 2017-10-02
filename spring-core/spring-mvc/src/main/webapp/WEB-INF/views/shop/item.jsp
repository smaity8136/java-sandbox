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
<h1>MOB SHOP - VIEW ITEM</h1>

<div>ID - <c:out value="${targetItem.id}"/></div>
<div>NAME - <c:out value="${targetItem.name}"/></div>
<div>DESCRIPTION - <c:out value="${targetItem.description}"/></div>
<div>DAMAGE INDICATOR - <c:out value="${targetItem.damageIndicator}"/></div>
<div>PRICE - <c:out value="${targetItem.price}"/></div>
<div>IN STOCK - <c:out value="${targetItem.inStock}"/></div>

<br/>
<br/>
<a href="<c:url value="/shop" />">Back To Shop</a>

</body>
</html>