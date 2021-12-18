package com.kamenev.entity;

public enum Status {
    DONE("Выполнено"),
    NOT_DONE("Не выполнено"),
    IN_WORK("В работе"),
    DO("Сделать");

    private String name;

    private Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
