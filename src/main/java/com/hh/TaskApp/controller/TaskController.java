package com.hh.TaskApp.controller;

import com.hh.TaskApp.dto.TaskInfo;
import com.hh.TaskApp.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "{taskId}")
    public TaskInfo getTask(@PathVariable("taskId") UUID taskId) {
        return taskService.getTaskById(taskId);
    }

    @GetMapping
    public List<TaskInfo> getAllActiveNotCompletedTasks() {
        return taskService.getAllActiveNotCompletedTasks();
    }


    @PostMapping()
    public TaskInfo createTask(@RequestBody TaskInfo taskInfo) {
        return taskService.createTask(taskInfo);
    }

    @PutMapping(value = "{taskId}")
    public TaskInfo updateTask(@PathVariable UUID taskId, @RequestBody TaskInfo taskInfo) {
        return taskService.updateTask(taskInfo);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public UUID deleteTask(@PathVariable UUID taskId) {
        return taskService.deleteTask(taskId);
    }
}
