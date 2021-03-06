package com.kamenev.servlet;

import com.kamenev.dto.CreateTaskListDto;
import com.kamenev.service.TaskListService;
import com.kamenev.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/taskLists/*")
public class TaskListServlet extends HttpServlet {

    private final TaskListService taskListService = TaskListService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/taskLists/new")) {
            req.getRequestDispatcher(JspHelper.getPath("taskList")).forward(req, resp);
        }
        req.setAttribute("taskListDtoList", taskListService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("taskLists")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String deadline = req.getParameter("deadline");
        CreateTaskListDto dto = new CreateTaskListDto(type, deadline);
        taskListService.create(dto);
        resp.sendRedirect("/taskLists");
    }
}
