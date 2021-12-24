package com.kamenev.dto;

import java.util.Objects;

public class TaskFilterDto {

    private final String description;
    private final String status;

    public TaskFilterDto(String description, String status) {
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskFilterDto that = (TaskFilterDto) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, status);
    }

    @Override
    public String toString() {
        return "TaskFilterDto{" +
                "description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
