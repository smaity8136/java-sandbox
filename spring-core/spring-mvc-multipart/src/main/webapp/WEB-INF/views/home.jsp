<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
<title>Evidence Cloud Application</title>
<link rel="stylesheet"
type="text/css"
href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1>EVIDENCE CLOUD - FILE UPLOAD DEMO</h1>

<a href="<c:url value="/requestpart/upload" />">Request Part Upload</a></br>
<a href="<c:url value="/multipartfile/upload" />">MultiPartFile Upload</a></br>
<a href="<c:url value="/part/upload" />">Part Upload (Servlet 3.0)</a></br>

</body>
</html>