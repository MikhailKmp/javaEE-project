package com.kamenev.listener;

import com.kamenev.entity.Status;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        List<String> statuses = Arrays.stream(Status.values()).map(Status::getDescription).collect(toList());

        servletContext.setAttribute("statuses", statuses);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
