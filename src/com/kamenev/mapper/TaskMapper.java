package com.kamenev.mapper;

import com.kamenev.dto.TaskDto;
import com.kamenev.entity.Task;

public class TaskMapper implements Mapper<Task, TaskDto> {

    private static final TaskMapper INSTANCE = new TaskMapper();

    private TaskMapper() {

    }

    @Override
    public TaskDto entityToDto(Task task) {
        return new TaskDto(task.getId(), task.getDescription(), task.getStatus().getName());
    }

    public static TaskMapper getInstance() {
        return INSTANCE;
    }
}
