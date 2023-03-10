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
}
