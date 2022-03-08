package com.kamenev.mapper;

import com.kamenev.dto.CreateTaskListDto;
import com.kamenev.entity.TaskList;
import com.kamenev.entity.TypeTaskList;
import com.kamenev.entity.User;
import com.kamenev.util.EnumUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CreateTaskListMapper {

    private static final CreateTaskListMapper INSTANCE = new CreateTaskListMapper();

    private CreateTaskListMapper() {

    }

    public TaskList dtoToEntity(CreateTaskListDto dto) {
        TaskList taskList = new TaskList();

        Optional<TypeTaskList> typeTaskList = EnumUtil.getTypeTaskListByDescription(dto.getType());

        taskList.setType(typeTaskList.orElse(null));
        taskList.setDateCreation(LocalDateTime.now());
        taskList.setDeadline(LocalDate.parse(dto.getDeadline(), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay());

        // TODO: 08.03.2022 : убрать заглушку для user
        User user = new User();
        user.setId(1L);
        taskList.setUser(user);

        return taskList;
    }

    public static CreateTaskListMapper getInstance() {
        return INSTANCE;
    }


}
