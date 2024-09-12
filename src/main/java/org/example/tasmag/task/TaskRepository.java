package org.example.tasmag.task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("SELECT j FROM Task j WHERE LOWER(j.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Task> findAllByFullTextSearch(String searchTerm);
}

