package com.kamenev.service;

import com.kamenev.dao.TaskListDao;
import com.kamenev.dto.TaskListDto;
import com.kamenev.mapper.TaskListMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TaskListService {

    private static final TaskListService INSTANCE = new TaskListService();

    private final TaskListDao taskListDao = TaskListDao.getInstance();
    private final TaskListMapper taskListMapper = TaskListMapper.getInstance();

    private TaskListService() {

    }

    public List<TaskListDto> findAll() {
        return taskListDao.findAll().stream()
                .map(taskListMapper::entityToDto)
                .collect(toList());
    }

    public static TaskListService getInstance() {
        return INSTANCE;
    }

}
