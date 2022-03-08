package com.kamenev.entity;

public enum TypeTaskList {
    DAY("Задачи на день"),
    DEADLINE("Задачи к определенной дате");

    private String description;

    private TypeTaskList(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
