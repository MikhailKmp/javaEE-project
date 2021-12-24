package com.kamenev.servlet;

import com.kamenev.dto.TaskDto;
import com.kamenev.dto.TaskFilter;
import com.kamenev.entity.Status;
import com.kamenev.service.TaskService;
import com.kamenev.util.EnumUtil;
import com.kamenev.util.JspHelper;
import com.kamenev.util.ParseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    private final TaskService taskService = TaskService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long taskListId = ParseUtil.parseLong(req.getParameter("taskListId"));
        List<TaskDto> taskDtoList = taskService.findAllByTaskListId(taskListId);

        req.setAttribute("taskListId", taskListId);
        req.setAttribute("taskDtoList", taskDtoList);
        req.getRequestDispatcher(JspHelper.getPath("tasks")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long taskListId = ParseUtil.parseLong(req.getParameter("taskListId"));
        String description = req.getParameter("description");
        String statusParam = req.getParameter("status");

        Optional<Status> status = EnumUtil.getStatusByDescription(statusParam);
        String statusName = status.map(Enum::name).orElse(null);
        String statusDescription = status.map(Status::getDescription).orElse("");

        TaskFilter taskFilter = new TaskFilter(taskListId, description, statusName);
        List<TaskDto> taskDtoList = taskService.findAll(taskFilter);

        req.setAttribute("taskListId", taskListId);
        req.setAttribute("taskDtoList", taskDtoList);
        req.setAttribute("description", description);
        req.setAttribute("status", statusDescription);
        req.getRequestDispatcher(JspHelper.getPath("tasks")).forward(req, resp);
    }
}
