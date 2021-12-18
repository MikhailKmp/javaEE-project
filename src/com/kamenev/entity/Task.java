package com.kamenev.entity;

import java.util.Objects;

public class Task {

    private Long id;
    private TaskList taskList;
    private String description;
    private Status status;

    public Task(Long id, TaskList taskList, String description, Status status) {
        this.id = id;
        this.taskList = taskList;
        this.description = description;
        this.status = status;
    }

    public Task() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskList=" + taskList +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
