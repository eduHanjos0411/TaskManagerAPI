package com.dudu.TaskManager.dto;

import com.dudu.TaskManager.model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskPatchRequest(
        String title,
        String description,
        Status status,
        LocalDate dueDate
) {
}
