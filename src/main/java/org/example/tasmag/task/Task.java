package org.example.tasmag.task;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Task {

    /**
     * Unique identifier for the task.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the task.
     */
    private String name;

    /**
     * Description of the task.
     */
    private String taskDescription;

    /**
     * The date and the time when task was created.
     */
    private  LocalDateTime createdAt;

    /**
     * The date by which the task should be completed (due date).
     */
    private LocalDateTime dueDate;

    /**
     * The current status of the task.
     */
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public Task() {
        this.createdAt = LocalDateTime.now();
        this.status = TaskStatus.PENDING;
    }

    public Task(String name, String taskDescription, LocalDateTime dueDate, TaskStatus status) {
        this.name = name;
        this.taskDescription = taskDescription;
        this.createdAt = LocalDateTime.now();
        this.dueDate = dueDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
