package com.kamenev.servlet;

import com.kamenev.service.TaskListService;
import com.kamenev.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/taskLists")
public class TaskListServlet extends HttpServlet {

    private final TaskListService taskListService = TaskListService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("taskListDtoList", taskListService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("taskLists")).forward(req, resp);
    }
}
