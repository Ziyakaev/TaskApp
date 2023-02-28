package com.hh.TaskApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hh.TaskApp.dto.TaskInfo;
import com.hh.TaskApp.model.Task;
import com.hh.TaskApp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
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
    public ResponseEntity<?> getTask(@PathVariable("taskId") UUID taskId) {
        TaskInfo taskInfo = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskInfo);
    }

    @GetMapping
    public ResponseEntity<?> getTasks() {
        List<TaskInfo> taskInfo = taskService.getTasks();
        return ResponseEntity.ok(taskInfo);
    }


    @PostMapping()
    public ResponseEntity<?> createTask(@RequestBody TaskInfo taskInfo) throws IOException {
        try {
            Task task = taskService.createTask(taskInfo);
            return ResponseEntity.ok(task.getId());
        } catch (Exception e) {
            ObjectMapper objectMapper = new ObjectMapper();
            TaskInfo taskInfo1 = new TaskInfo();
            taskInfo1.setTitle("title");
            taskInfo1.setNotes("yest");
            objectMapper.writeValue(new File("test"), taskInfo1);
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
            taskService.deleteTask(taskId);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}
