<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Списки задач</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/taskLists/new" method="get">
        <button type="submit">Создать список</button>
    </form>
    <c:if test="${not empty requestScope.taskListDtoList}">
        <h1>Списки задач:</h1>
        <ul>
            <c:forEach var="taskList" items="${requestScope.taskListDtoList}">

                <li>
                    <a href="${pageContext.request.contextPath}/tasks?taskListId=${taskList.id}">
                        Тип списка задач: ${taskList.type}. Дата создания: ${taskList.dateCreation}. Дата дедлайна: ${taskList.deadline}. Количество задач: ${taskList.numberOfTasks}.
                    </a>
                </li>

            </c:forEach>
        </ul>
    </c:if>
</body>
</html>
