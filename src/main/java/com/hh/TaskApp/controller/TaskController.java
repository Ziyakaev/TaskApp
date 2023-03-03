package com.hh.TaskApp.controller;

import com.hh.TaskApp.dto.TaskInfo;
import com.hh.TaskApp.model.Task;
import com.hh.TaskApp.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "{taskId}")
    public ResponseEntity<?> getTask(@PathVariable("taskId") UUID taskId) {
        Optional<TaskInfo> taskInfo = taskService.getTaskById(taskId);
        if (taskInfo.isPresent()) {
            return ResponseEntity.ok(taskInfo);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping
    public ResponseEntity<?> getAllActiveTasks() {
        List<TaskInfo> taskInfo = taskService.getAllActiveTasks();
        return ResponseEntity.ok(taskInfo);
    }


    @PostMapping()
    public ResponseEntity<?> createTask(@RequestBody TaskInfo taskInfo) {
        try {
            Task task = taskService.createTask(taskInfo);
            return ResponseEntity.ok(task.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping(value = "{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable UUID taskId, @RequestBody TaskInfo taskInfo) {
        try {
            Task task = taskService.updateTask(taskInfo);
            return ResponseEntity.ok(task.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID taskId) {
        try {
            Optional<UUID> uuidDeletedTask = taskService.deleteTask(taskId);
            return uuidDeletedTask.isPresent()
                    ? ResponseEntity.accepted().build()
                    : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            log.warn("can not delete task with id {}", taskId, e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }




}
