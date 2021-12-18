<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Задачи</title>
</head>
<body>
<c:if test="${not empty requestScope.taskDtoList}">
    <h1>Задачи:</h1>
    <ul>
        <c:forEach var="task" items="${requestScope.taskDtoList}">

            <li>
                Описание задачи: ${task.description}. Статус: ${task.status}
            </li>

        </c:forEach>
    </ul>
</c:if>
</body>
</html>
