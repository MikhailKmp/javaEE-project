package com.kamenev.dto;

import java.util.Objects;

public class TaskListDto {

    private final Long id;
    private final String type;
    private final String dateCreation;
    private final String deadline;
    private final Integer numberOfTasks;

    public TaskListDto(Long id, String type, String dateCreation, String deadline, Integer numberOfTasks) {
        this.id = id;
        this.type = type;
        this.dateCreation = dateCreation;
        this.deadline = deadline;
        this.numberOfTasks = numberOfTasks;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public String getDeadline() {
        return deadline;
    }

    public Integer getNumberOfTasks() {
        return numberOfTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskListDto listDto = (TaskListDto) o;
        return Objects.equals(id, listDto.id) &&
                Objects.equals(type, listDto.type) &&
                Objects.equals(dateCreation, listDto.dateCreation) &&
                Objects.equals(deadline, listDto.deadline) &&
                Objects.equals(numberOfTasks, listDto.numberOfTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, dateCreation, deadline, numberOfTasks);
    }

    @Override
    public String toString() {
        return "TaskListDto{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", dateCreation='" + dateCreation + '\'' +
                ", deadline='" + deadline + '\'' +
                ", numberOfTasks=" + numberOfTasks +
                '}';
    }
}
