<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Список задач</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/taskLists" method="post">
        <label for="type">Тип списка задач:
            <select name="type" id="type" required>
                <c:forEach var="type" items="${applicationScope.typesTaskList}">
                    <option value="${type}">${type}</option>
                </c:forEach>
            </select>
        </label><br>
        <label for="deadline">Срок выполнения:
            <input type="date" name="deadline" id="deadline" required>
        </label><br>
        <button type="submit">Создать</button>
    </form>
</body>
</html>
