package com.kamenev.service;

import com.kamenev.dao.TaskListDao;
import com.kamenev.dto.CreateTaskListDto;
import com.kamenev.dto.TaskListDto;
import com.kamenev.entity.TaskList;
import com.kamenev.entity.TypeTaskList;
import com.kamenev.entity.User;
import com.kamenev.mapper.TaskListMapper;
import com.kamenev.util.EnumUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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

    public TaskList create(CreateTaskListDto dto) {
        TaskList taskList = new TaskList();

        // TODO: 08.03.2022 : заменить на маппер 
        Optional<TypeTaskList> typeTaskList = EnumUtil.getTypeTaskListByDescription(dto.getType());
        taskList.setType(typeTaskList.orElse(null));

        taskList.setDateCreation(LocalDateTime.now());
        taskList.setDeadline(LocalDate.parse(dto.getDeadline(), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay());

        // TODO: 08.03.2022 : убрать заглушку для user
        User user = new User();
        user.setId(1L);
        taskList.setUser(user);

        return taskListDao.save(taskList);
    }

    public static TaskListService getInstance() {
        return INSTANCE;
    }

}
