package com.kamenev.mapper;

import com.kamenev.dao.TaskDao;
import com.kamenev.dto.TaskFilter;
import com.kamenev.dto.TaskListDto;
import com.kamenev.entity.Task;
import com.kamenev.entity.TaskList;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskListMapper implements Mapper<TaskList, TaskListDto> {

    private static final TaskListMapper INSTANCE = new TaskListMapper();
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    private final TaskDao taskDao = TaskDao.getInstance();


    private TaskListMapper() {

    }

    @Override
    public TaskListDto entityToDto(TaskList taskList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String dateCreation = taskList.getDateCreation().format(formatter);
        String deadLine = taskList.getDeadline().format(formatter);

        TaskFilter taskFilter = new TaskFilter(taskList.getId(), null, null);
        List<Task> tasks = taskDao.findAll(taskFilter);

        return new TaskListDto(taskList.getId(), taskList.getType().getDescription(), dateCreation, deadLine, tasks.size());
    }

    public static TaskListMapper getInstance() {
        return INSTANCE;
    }
}
