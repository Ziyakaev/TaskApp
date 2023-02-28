package com.hh.TaskApp.service;

import com.hh.TaskApp.converter.TaskConverter;
import com.hh.TaskApp.dao.TaskDao;
import com.hh.TaskApp.dto.TaskInfo;
import com.hh.TaskApp.model.Task;
import com.hh.TaskApp.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    public TaskInfo getTaskById(UUID taskId) {
        Task task = taskDao.findTaskById(taskId);
        return taskConverter.convertToDto(task);
    }

    public List<TaskInfo> getTasks() {
        List<Task> tasks = taskDao.findAll();
        return tasks.stream()
                .map(taskConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public Task createTask(TaskInfo taskInfo) throws Exception {
        if (taskInfo.getId() != null) {
            throw new Exception("task already exist");
        }
        Task task = new Task();
        task.setCreated(LocalDateTime.now());
        task.setTitle(taskInfo.getTitle());
        task.setDeleted(false);
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setNotes(taskInfo.getNotes());

        return taskDao.save(task);
    }


    public Task updateTask(TaskInfo taskInfo) {
        Task task = taskDao.findTaskById(taskInfo.getId());
        task.setTitle(taskInfo.getTitle());
        task.setDeleted(taskInfo.isDeleted());
        task.setStatus(TaskStatus.valueByName(taskInfo.getStatus()));
        task.setNotes(taskInfo.getNotes());
        return taskDao.save(task);
    }

    public void deleteTask(UUID taskId) {
        taskDao.deleteById(taskId);
    }
}
