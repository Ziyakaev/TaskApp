package com.hh.TaskApp.model;

import java.util.Arrays;

public enum TaskStatus {
    IN_PROGRESS(0), COMPLETED(1), FAILED(2), PAUSE(3);
    final int id;

    TaskStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TaskStatus valueById(int id) {
        return Arrays.stream(values())
                .filter(taskStatus -> taskStatus.getId() == id)
                .findFirst()
                .orElse(TaskStatus.FAILED);
    }

    public static TaskStatus valueByName(String name) {
        return Arrays.stream(values())
                .filter(taskStatus -> taskStatus.name().equals(name))
                .findFirst()
                .orElse(TaskStatus.FAILED);
    }
}
