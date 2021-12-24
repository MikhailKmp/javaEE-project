<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Задачи</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/tasks?taskListId=${requestScope.taskListId}" method="post" enctype="application/x-www-form-urlencoded">

    <label for="description">Описание задачи:
        <input type="text" name="description" id="description" value="${requestScope.description}">
    </label>

    <label for="status">Статус задачи:
        <select name="status" id="status">
            <c:if test="${not empty requestScope.status}">
                <option value="${requestScope.status}">${requestScope.status}</option>
            </c:if>
            <option value=""></option>
            <c:forEach var="status" items="${applicationScope.statuses}">
                <c:if test="${status ne requestScope.status}">
                    <option value="${status}">${status}</option>
                </c:if>
            </c:forEach>
        </select>
    </label>

    <button type="submit">Обновить</button>

</form>

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
