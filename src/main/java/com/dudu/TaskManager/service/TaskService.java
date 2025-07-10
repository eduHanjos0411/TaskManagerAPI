package com.dudu.TaskManager.service;

import com.dudu.TaskManager.exception.InvalidTaskDateException;
import com.dudu.TaskManager.exception.TaskNotFoundException;
import com.dudu.TaskManager.dto.TaskPatchRequest;
import com.dudu.TaskManager.dto.TaskRequest;
import com.dudu.TaskManager.dto.TaskResponse;
import com.dudu.TaskManager.model.Task;
import com.dudu.TaskManager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    public final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse createNewTask( TaskRequest tr) {
        Task newTask = new Task();

        if (tr.dueDate().isBefore(LocalDate.now())) {
            throw new InvalidTaskDateException();
        }
        newTask.setTitle(tr.title());
        newTask.setDescription(tr.description());
        newTask.setStatus(tr.status());
        newTask.setDuedate(tr.dueDate());
        taskRepository.save(newTask);

        return new TaskResponse(newTask);
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream().map(TaskResponse::new).toList();
    }

    public TaskResponse getTaskById(Long id) {
        Task taskFound = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        return new TaskResponse(taskFound);
    }


    public TaskResponse updateEntireTask(Long id, TaskRequest newData) {
        Task taskToUpdate = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        if(newData.dueDate().isBefore(LocalDate.now())) {
            throw  new InvalidTaskDateException();
        }

        taskToUpdate.setTitle(newData.title());
        taskToUpdate.setDescription(newData.description());
        taskToUpdate.setStatus(newData.status());
        taskToUpdate.setDuedate(newData.dueDate());
        taskRepository.save(taskToUpdate);

        return new TaskResponse(taskToUpdate);
    }

    public TaskResponse updateTaskPartially(Long id, TaskPatchRequest patch) {
        Task taskToUpdate = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        if (patch.title() != null) {taskToUpdate.setTitle(patch.title());}
        if (patch.description() != null) {taskToUpdate.setDescription(patch.description());}
        if (patch.status() != null) {taskToUpdate.setStatus(patch.status());}
        if (patch.dueDate() != null) {
            if (patch.dueDate().isBefore(LocalDate.now())) {
                throw new InvalidTaskDateException();
            }
            taskToUpdate.setDuedate(patch.dueDate());
        }
        taskRepository.save(taskToUpdate);

        return new TaskResponse(taskToUpdate);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }

        taskRepository.deleteById(id);
    }
}
