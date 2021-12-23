package com.kamenev.service;

import com.kamenev.dao.TaskDao;
import com.kamenev.dto.TaskDto;
import com.kamenev.dto.TaskFilter;
import com.kamenev.mapper.TaskMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TaskService {

    private static final TaskService INSTANCE = new TaskService();

    private final TaskDao taskDao = TaskDao.getInstance();
    private final TaskMapper taskMapper = TaskMapper.getInstance();

    private TaskService() {

    }

    public List<TaskDto> findAllByTaskListId(Long taskListId) {
        TaskFilter taskFilter = new TaskFilter(taskListId, null, null);
        return taskDao.findAll(taskFilter).stream()
                .map(taskMapper::entityToDto)
                .collect(toList());
    }

    public List<TaskDto> findAll(TaskFilter taskFilter) {
        return taskDao.findAll(taskFilter).stream()
                .map(taskMapper::entityToDto)
                .collect(toList());
    }

    public static TaskService getInstance() {
        return INSTANCE;
    }

}
