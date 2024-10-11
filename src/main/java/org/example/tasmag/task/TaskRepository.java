package org.example.tasmag.task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for managing Task entities.
 * Extends CrudRepository to provide basic CRUD operations.
 * Includes custom query for full-text search on task names.
 */
public interface TaskRepository extends CrudRepository<Task, Long> {

    /**
     * Find all tasks whose names contain the specific search term.
     * This method performs a case-insensitive search.
     *
     * @param searchTerm The term to search for within task names.
     * @return A list of task matching the search term.
     */
    @Query("SELECT j FROM Task j WHERE LOWER(j.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Task> findAllByFullTextSearch(String searchTerm);
}

