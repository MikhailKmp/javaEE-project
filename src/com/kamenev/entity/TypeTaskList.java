package com.kamenev.entity;

public enum TypeTaskList {
    DAY("Задачи на день"),
    DEADLINE("Задачи к определенной дате");

    private String name;

    private TypeTaskList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
