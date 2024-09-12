package org.example.tasmag.task;

/**
 * Enum representing the various statuses a task can have.
 */
public enum TaskStatus {
    /**
     * Task has been created but not started yet.
     */
    PENDING,

    /**
     * Task is currently working on.
     */
    IN_PROGRESS,

    /**
     * Task has been completed successfully.
     */
    COMPLETED,

    /**
     * Task was canceled before completion.
     */
    CANCELLED,

    /**
     * The task could not be completed.
     */
    FAILED,
}
