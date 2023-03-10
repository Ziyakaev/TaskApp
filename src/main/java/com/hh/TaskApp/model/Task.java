package com.hh.TaskApp.model;
import jakarta.persistence.EnumType;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", nullable = false)
    UUID id;
    String title;
    String notes;
    LocalDateTime updated;
    LocalDateTime created;

    String parent;
    TaskStatus status;
    boolean deleted;
    String due;
    boolean completed;
    boolean hidden;

    public Task(String title, String notes) {
        this.title = title;
        this.notes = notes;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
        this.deleted = false;
        this.completed = false;
        this.status = TaskStatus.IN_PROGRESS;
    }

    public Task() {
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

    public void setNotes(String description) {
        this.notes = description;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Enumerated(value = EnumType.ORDINAL)
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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getParent() {
        return parent;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", notes='" + notes + '\'' +
                ", updated=" + updated +
                ", created=" + created +
                ", parent='" + parent + '\'' +
                ", status=" + status +
                ", deleted=" + deleted +
                ", due='" + due + '\'' +
                ", completed=" + completed +
                ", hidden=" + hidden +
                '}';
    }
}
