package com.hh.TaskApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hh.TaskApp.model.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskInfo {
    UUID id;
    String title;
    String notes;

    LocalDateTime created;
    String updated;

    String parent;
    TaskStatus status;
    boolean deleted;
    String due;
    boolean completed;
    boolean hidden;

    public TaskInfo(UUID id, String title, String notes, LocalDateTime created, String updated, String parent,
                    TaskStatus status, boolean deleted, String due, boolean completed, boolean hidden) {
        this.id = id;
        this.title = title;
        this.notes = notes;
        this.updated = updated;
        this.created = created;
        this.parent = parent;
        this.status = status;
        this.deleted = deleted;
        this.due = due;
        this.completed = completed;
        this.hidden = hidden;
    }

    public TaskInfo() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
