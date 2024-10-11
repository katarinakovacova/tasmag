package org.example.tasmag.task;

import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing tasks.
 * Provides methods for CRUD operations and task existence checks.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Retrieves all tasks from the repository.
     *
     * @return An iterable collection of all tasks.
     */
    public Iterable<Task> findAllTasks(){
        return taskRepository.findAll();
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id The ID of the task.
     * @return An Optional containing the task if found, or empty if not found.
     */
    public Optional<Task> findTaskById(Long id){
        return taskRepository.findById(id);
    }

    /**
     * Saves a new or existing task to the repository.
     *
     * @param task The task to save.
     * @return The saved task.
     */
    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id The ID of the task to delete.
     */
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    /**
     * Checks if a task exists by its ID.
     *
     * @param id The ID of the task.
     * @return True if the task exists, false otherwise.
     */
    public boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }
}
