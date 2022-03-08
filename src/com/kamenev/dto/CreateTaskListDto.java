package com.kamenev.dto;

import java.util.Objects;

public class CreateTaskListDto {

    private final String type;
    private final String deadline;

    public CreateTaskListDto(String type, String deadline) {
        this.type = type;
        this.deadline = deadline;
    }

    public String getType() {
        return type;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTaskListDto that = (CreateTaskListDto) o;
        return Objects.equals(type, that.type) && Objects.equals(deadline, that.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, deadline);
    }

    @Override
    public String toString() {
        return "CreateTaskListDto{" +
                "type='" + type + '\'' +
                ", deadline='" + deadline + '\'' +
                '}';
    }
}
