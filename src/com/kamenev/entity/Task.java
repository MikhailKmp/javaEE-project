package com.kamenev.entity;

public class Task {

    private int id;
    private TaskList taskList;
    private String description;
    private Status status;

    public Task(int id, TaskList taskList, String description, Status status) {
        this.id = id;
        this.taskList = taskList;
        this.description = description;
        this.status = status;
    }

    public Task() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskList=" + taskList +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
