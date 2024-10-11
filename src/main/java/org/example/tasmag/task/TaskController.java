package org.example.tasmag.task;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for managing Task resources.
 * Handles HTTP request and delegates business logic to TaskService.
 */
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Constructor for TaskController.
     * @param taskService Service layer to handle Task-related business logic.
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Retrieve all tasks.
     * @return ResponseEntity containing the list of all tasks and HTTP status 200 OK.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    /**
     * Create a new task.
     * @param task The task to be created.
     * @return ResponseEntity containing the created the task and HTTP status 201 Created.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    /**
     * Retrieve a task by its ID.
     * @param id the ID of the task to retrieve.
     * @return Response entity containing the task if found, or HTTP status 404 if not found.
     */
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update an existing task.
     * @param id The ID of the task to update.
     * @param task The update Task details.
     * @return ResponseEntity containing the update task if successful, or HTTP status 404 if the task doesn't exist.
     */
    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.findTaskById(id)
                .map(existingTask -> {
                    task.setId(id);
                    Task updatedTask = taskService.saveTask(task);
                    return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Delete a task byt its ID.
     * @param id The ID of the task to delete.
     * @return ResponseEntity with HTTP status 204 No Content if successful or 404 Not Found if the task doesn't exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.existsById(id)) {
            taskService.deleteTask(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
