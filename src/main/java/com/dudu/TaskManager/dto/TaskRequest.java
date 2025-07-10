package com.dudu.TaskManager.dto;

import com.dudu.TaskManager.model.Status;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record TaskRequest(
        @NotBlank(message = "Title is needed") @Size(min = 10, max = 300)
        String title,
        @NotBlank(message = "Description is needed") @Size(min = 10, max = 300)
        String description,
        @NotNull
        Status status,
        @FutureOrPresent
        LocalDate dueDate
) {
}
