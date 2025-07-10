package com.dudu.TaskManager.controller;

import com.dudu.TaskManager.dto.TaskPatchRequest;
import com.dudu.TaskManager.dto.TaskRequest;
import com.dudu.TaskManager.dto.TaskResponse;
import com.dudu.TaskManager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    public final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/newTask")
    public ResponseEntity<TaskResponse> createTask(@RequestBody  @Valid TaskRequest trCreate) {
        TaskResponse newTask = taskService.createNewTask(trCreate);

        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @GetMapping("/all")
    public List<TaskResponse> allTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<TaskResponse> findTaskById(@PathVariable Long id) {
        TaskResponse taskFound = taskService.getTaskById(id);

        return ResponseEntity.ok(taskFound);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskResponse> updateEntireTask(@PathVariable Long id, @RequestBody @Valid TaskRequest trUpdate) {
        TaskResponse taskFoundUpdate = taskService.updateEntireTask(id, trUpdate);

        return ResponseEntity.ok(taskFoundUpdate);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<TaskResponse> updateTaskPartially(@PathVariable Long id, @RequestBody TaskPatchRequest trPatch) {
        TaskResponse taskFoundPatch = taskService.updateTaskPartially(id, trPatch);

        return ResponseEntity.ok(taskFoundPatch);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }
}
