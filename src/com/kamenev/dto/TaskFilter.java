package com.kamenev.dto;

import java.util.Objects;

public class TaskFilter {

    private final Long taskListId;
    private final String description;
    private final String status;

    public TaskFilter(Long taskListId, String description, String status) {
        this.taskListId = taskListId;
        this.description = description;
        this.status = status;
    }

    public Long getTaskListId() {
        return taskListId;
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
        TaskFilter that = (TaskFilter) o;
        return Objects.equals(taskListId, that.taskListId) &&
                Objects.equals(description, that.description) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskListId, description, status);
    }

    @Override
    public String toString() {
        return "TaskFilter{" +
                "taskListId=" + taskListId +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
