package com.kamenev.entity;

import java.time.LocalDateTime;

public class TaskList {

    private int id;
    private TypeTaskList type;
    private LocalDateTime dateCreation;
    private LocalDateTime deadline;
    private int userId;

    public TaskList(int id, TypeTaskList type, LocalDateTime dateCreation, LocalDateTime deadline, int userId) {
        this.id = id;
        this.type = type;
        this.dateCreation = dateCreation;
        this.deadline = deadline;
        this.userId = userId;
    }

    public TaskList() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeTaskList getType() {
        return type;
    }

    public void setType(TypeTaskList type) {
        this.type = type;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "id=" + id +
                ", type=" + type +
                ", dateCreation=" + dateCreation +
                ", deadline=" + deadline +
                ", userId=" + userId +
                '}';
    }
}
