package com.kamenev.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskList {

    private Long id;
    private TypeTaskList type;
    private LocalDateTime dateCreation;
    private LocalDateTime deadline;
    private User user;

    public TaskList(Long id, TypeTaskList type, LocalDateTime dateCreation, LocalDateTime deadline, User user) {
        this.id = id;
        this.type = type;
        this.dateCreation = dateCreation;
        this.deadline = deadline;
        this.user = user;
    }

    public TaskList() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskList taskList = (TaskList) o;
        return Objects.equals(id, taskList.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "id=" + id +
                ", type=" + type +
                ", dateCreation=" + dateCreation +
                ", deadline=" + deadline +
                ", user=" + user +
                '}';
    }
}
