package com.kamenev.util;

import com.kamenev.entity.Status;

import java.util.Arrays;
import java.util.Optional;

public final class EnumUtil {

    private EnumUtil() {

    }

    public static Optional<Status> getStatusByDescription(String description) {
        return Arrays.stream(Status.values()).filter(e -> e.getDescription().equals(description)).findFirst();
    }

}
