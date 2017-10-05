<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>

<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css"
href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1>Register</h1>
<form method="POST" action="/mobshop/member/register">
E-mail: <input type="text" name="email" /><br/>
Username: <input type="text" name="username" /><br/>
Password: <input type="password" name="password" /><br/>
<input type="submit" value="Register" />
</form>
</body>
</html>