package com.hh.TaskApp.service;

import com.hh.TaskApp.converter.TaskConverter;
import com.hh.TaskApp.dao.TaskDao;
import com.hh.TaskApp.dto.TaskInfo;
import com.hh.TaskApp.model.Task;
import com.hh.TaskApp.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskDao taskDao;
    private final TaskConverter taskConverter;

    public TaskService(TaskDao taskDao, TaskConverter taskConverter) {

        this.taskDao = taskDao;
        this.taskConverter = taskConverter;
    }

    public Optional<TaskInfo> getTaskById(UUID taskId) {
        Task task = taskDao.findTaskByIdAndDeletedIsFalse(taskId);
        if (task == null) {
            return Optional.empty();
        }
        return Optional.of(taskConverter.convertToDto(task));
    }

    public List<TaskInfo> getTasks() {
        List<Task> tasks = taskDao.findAll();
        return tasks.stream()
                .map(taskConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TaskInfo> getAllActiveTasks() {
        List<Task> tasks = taskDao.findAllByDeletedIsFalse();
        return tasks.stream()
                .map(taskConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public Task createTask(TaskInfo taskInfo) {
        if (taskInfo.getId() != null) {
            throw new IllegalArgumentException("task already exist with id" + taskInfo.getId());
        }
        Task task = new Task();
        task.setCreated(LocalDateTime.now());
        task.setUpdated(LocalDateTime.now());
        task.setTitle(taskInfo.getTitle());
        task.setDeleted(false);
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setNotes(taskInfo.getNotes());

        return taskDao.save(task);
    }


    public Task updateTask(TaskInfo taskInfo) {
        Task task = taskDao.findTaskByIdAndDeletedIsFalse(taskInfo.getId());
        task.setTitle(taskInfo.getTitle());
        task.setDeleted(taskInfo.isDeleted());
        task.setUpdated(LocalDateTime.now());
        task.setStatus(TaskStatus.valueByName(taskInfo.getStatus()));
        task.setNotes(taskInfo.getNotes());
        return taskDao.save(task);
    }

    public Optional<UUID> deleteTask(UUID taskId) {
        Task task = taskDao.findTaskByIdAndDeletedIsFalse(taskId);
        if (task != null) {
            task.setDeleted(true);
            task.setUpdated(LocalDateTime.now());
            Task savedTask = taskDao.save(task);
            return Optional.of(savedTask.getId());
        }
        return Optional.empty();

    }
}
