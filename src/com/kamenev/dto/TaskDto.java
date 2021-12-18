package com.kamenev.dto;

import java.util.Objects;

public class TaskDto {

    private final Long id;
    private final String description;
    private final String status;

    public TaskDto(Long id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
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
        TaskDto taskDto = (TaskDto) o;
        return Objects.equals(id, taskDto.id) &&
                Objects.equals(description, taskDto.description) &&
                Objects.equals(status, taskDto.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status);
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
