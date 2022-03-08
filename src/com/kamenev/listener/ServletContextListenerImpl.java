package com.kamenev.listener;

import com.kamenev.entity.Status;
import com.kamenev.entity.TypeTaskList;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        List<String> statuses = Arrays.stream(Status.values()).map(Status::getDescription).collect(toList());
        List<String> typesTaskList = Arrays.stream(TypeTaskList.values()).map(TypeTaskList::getDescription).collect(toList());

        servletContext.setAttribute("statuses", statuses);
        servletContext.setAttribute("typesTaskList", typesTaskList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
