package com.hh.TaskApp.service;

import com.hh.TaskApp.converter.TaskConverter;
import com.hh.TaskApp.dao.TaskRepository;
import com.hh.TaskApp.dto.TaskInfo;
import com.hh.TaskApp.exception.InternalErrorException;
import com.hh.TaskApp.exception.ResourceForbiddenException;
import com.hh.TaskApp.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;

    public TaskService(TaskRepository taskRepository, TaskConverter taskConverter) {

        this.taskRepository = taskRepository;
        this.taskConverter = taskConverter;
    }

    public TaskInfo getTaskById(UUID taskId) {
        Task task = execute(() -> taskRepository.findTaskByIdAndDeletedIsFalse(taskId), taskId);
        return Optional.of(task)
                .map(taskConverter::convertToDto)
                .orElseThrow(() -> new InternalErrorException("Произошла внутренняя ошибка"));
    }

    private static <T> T execute(Supplier<T> function, UUID id) {
        try {
            T result = function.get();
            if (result == null) {
                throw new ResourceForbiddenException("access denied with id " + id);
            }
            return result;
        } catch (ResourceForbiddenException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException("Произошла внутренняя ошибка", e);
        }
    }

    public List<TaskInfo> getAllActiveNotCompletedTasks() {
        return taskRepository.findAllByDeletedIsFalseAndCompletedIsFalse().stream()
                .map(taskConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public TaskInfo createTask(TaskInfo taskInfo) {
        if (taskInfo.getId() != null) {
            throw new IllegalArgumentException("task already exist with id" + taskInfo.getId());
        }
        throwExceptionIfInvalidTask(taskInfo);


        Task task = new Task(taskInfo.getTitle(), taskInfo.getNotes());
        Task savedTask = execute(() -> taskRepository.save(task), task.getId());
        return Optional.of(savedTask)
                .map(taskConverter::convertToDto)
                .orElseThrow(() -> new InternalErrorException("Произошла внутренняя ошибка"));
    }

    private void throwExceptionIfInvalidTask(TaskInfo taskInfo) {
        boolean hasValidArgument = validateTask(taskInfo);
        if (!hasValidArgument) {
            throw new IllegalArgumentException("not correct task");
        }
    }

    private boolean validateTask(TaskInfo taskInfo) {
        return taskInfo != null && taskInfo.getTitle() != null && !taskInfo.getTitle().isEmpty();
    }


    public TaskInfo updateTask(TaskInfo taskInfo) {
        throwExceptionIfInvalidTask(taskInfo);
        Task task = execute(() -> taskRepository.findTaskByIdAndDeletedIsFalse(taskInfo.getId()), taskInfo.getId());

        task.setTitle(taskInfo.getTitle());
        task.setDeleted(taskInfo.isDeleted());
        task.setCompleted(taskInfo.isCompleted());
        task.setUpdated(LocalDateTime.now());
        task.setStatus(taskInfo.getStatus());
        task.setNotes(taskInfo.getNotes());

        Task savedTask = execute(() -> taskRepository.save(task), task.getId());
        return Optional.of(savedTask)
                .map(taskConverter::convertToDto)
                .orElseThrow(() -> new InternalErrorException("Произошла внутренняя ошибка"));
    }

    public UUID deleteTask(UUID taskId) {
        Task task = execute(() -> taskRepository.findTaskByIdAndDeletedIsFalse(taskId), taskId);
        task.setDeleted(true);
        task.setUpdated(LocalDateTime.now());
        Task savedTask = execute(() -> taskRepository.save(task), taskId);
        return savedTask.getId();
    }
}
