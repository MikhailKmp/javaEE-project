package com.kamenev.servlet;

import com.kamenev.dto.TaskDto;
import com.kamenev.service.TaskService;
import com.kamenev.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    private final TaskService taskService = TaskService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long taskListId = Long.parseLong(req.getParameter("taskListId"));
        List<TaskDto> taskDtoList = taskService.findAllByTaskListId(taskListId);
        req.setAttribute("taskDtoList", taskDtoList);
        req.getRequestDispatcher(JspHelper.getPath("tasks")).forward(req, resp);
    }
}
