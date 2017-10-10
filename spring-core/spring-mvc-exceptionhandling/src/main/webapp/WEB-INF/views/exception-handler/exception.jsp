<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
<title>Spring MVC - Exception Handling</title>
<link rel="stylesheet"
type="text/css"
href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1>@ExceptionHandler Example</h1>

<a href="<c:url value="/exception-handler/trigger" />">Trigger Exception</a></br>

</body>
</html>