package com.dudu.TaskManager.exception;

public class InvalidTaskDateException extends RuntimeException {
    public InvalidTaskDateException() {
        super("Due date cannot be in the past");
    }
}
