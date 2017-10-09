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
<h1>EVIDENCE CLOUD - Part (Servlet 3.0) DOCUMENT UPLOAD</h1>

<sf:form method="POST" action="/evidence-cloud/part/upload" enctype="multipart/form-data">

<label>Evidence</label>:
<input type="file" name="evidenceDocument"
accept="image/jpeg,image/png,image/gif,application/pdf" /><sf:errors path="evidenceDocument" cssClass="error" /><br/>
<input type="submit" value="Upload" />
</sf:form>

</br>
<h3>
<c:if test="${successfulUpload}">
    File successfully upload!
</c:if>

<c:if test="${uploadFailed}">
    File upload failed!
</c:if>
</h3>

</br>
<a href="<c:url value="/" />">Home</a></br>

</body>
</html>