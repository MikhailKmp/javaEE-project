package com.kamenev.entity;

public enum Status {
    DONE("Выполнено"),
    NOT_DONE("Не выполнено"),
    IN_WORK("В работе"),
    DO("Сделать");

    private String description;

    private Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
