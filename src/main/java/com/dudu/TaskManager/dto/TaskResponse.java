package com.dudu.TaskManager.dto;

import com.dudu.TaskManager.model.Status;
import com.dudu.TaskManager.model.Task;

import java.time.LocalDate;

public record TaskResponse(
        Long id,
        String title,
        String description,
        Status status,
        LocalDate dueDate
) {

    public TaskResponse (Task t) {
        this(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getStatus(),
                t.getDuedate()
        );
    }
}
