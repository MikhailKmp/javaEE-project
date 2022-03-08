package com.kamenev.util;

import com.kamenev.entity.Status;
import com.kamenev.entity.TypeTaskList;

import java.util.Arrays;
import java.util.Optional;

public final class EnumUtil {

    private EnumUtil() {

    }

    public static Optional<Status> getStatusByDescription(String description) {
        return Arrays.stream(Status.values()).filter(e -> e.getDescription().equals(description)).findFirst();
    }

    public static Optional<TypeTaskList> getTypeTaskListByDescription(String description) {
        return Arrays.stream(TypeTaskList.values()).filter(e -> e.getDescription().equals(description)).findFirst();
    }

}
