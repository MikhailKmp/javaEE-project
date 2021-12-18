<%@ page import="com.kamenev.dto.TaskListDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Списки задач</title>
</head>
<body>
    <h2>Списки задач:</h2>
    <ul>
        <%
            List<TaskListDto> taskListDtoList = (List<TaskListDto>) request.getAttribute("taskListDtoList");
            for (TaskListDto dto : taskListDtoList) {
                out.write(String.format("Тип списка задач: %s. Дата создания: %s. Дата дедлайна: %s. Количество задач: %d",
                        dto.getType(),
                        dto.getDateCreation().toString(),
                        dto.getDeadline().toString(),
                        dto.getNumberOfTasks()));
            }
        %>
    </ul>
</body>
</html>
