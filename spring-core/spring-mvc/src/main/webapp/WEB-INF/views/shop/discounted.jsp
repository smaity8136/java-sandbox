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
<h1>MOB SHOP - DISCOUNTED ITEMS</h1>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Damage Indicator</th>
        <th>Price</th>
        <th>In Stock</th>
    </tr>

<c:forEach items="${discountedItems}" var="item" >
    <tr>
        <td><a href="<c:url value="/shop/view-item?itemId=1" />"><c:out value="${item.id}"/></a></td>
        <td><c:out value="${item.name}"/></td>
        <td><c:out value="${item.description}"/></td>
        <td><c:out value="${item.damageIndicator}"/></td>
        <td><c:out value="${item.price}"/></td>
        <td><c:out value="${item.inStock}"/></td>
    </tr>

</c:forEach>
</table>

<br/>
<a href="<c:url value="/" />">Home</a>

</body>
</html>