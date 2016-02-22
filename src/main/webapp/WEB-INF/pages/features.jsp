<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Spring Profiles</h1>

<h2>Spring profiles</h2>
<c:out value="${profiles}"/>
<h2>Enabled profiles</h2>
<ul>
    <c:forEach items="${managedFeatures}" var="profile">
        <li>${profile.name}</li>
    </c:forEach>
</ul>

</body>
</html>