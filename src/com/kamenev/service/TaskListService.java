package com.kamenev.service;

import com.kamenev.dao.TaskListDao;
import com.kamenev.dto.CreateTaskListDto;
import com.kamenev.dto.TaskListDto;
import com.kamenev.entity.TaskList;
import com.kamenev.mapper.CreateTaskListMapper;
import com.kamenev.mapper.TaskListMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TaskListService {

    private static final TaskListService INSTANCE = new TaskListService();

    private final TaskListDao taskListDao = TaskListDao.getInstance();
    private final TaskListMapper taskListMapper = TaskListMapper.getInstance();
    private final CreateTaskListMapper createTaskListMapper = CreateTaskListMapper.getInstance();

    private TaskListService() {

    }

    public List<TaskListDto> findAll() {
        return taskListDao.findAll().stream()
                .map(taskListMapper::entityToDto)
                .collect(toList());
    }

    public TaskList create(CreateTaskListDto dto) {
        TaskList taskList = createTaskListMapper.dtoToEntity(dto);
        return taskListDao.save(taskList);
    }

    public static TaskListService getInstance() {
        return INSTANCE;
    }

}
