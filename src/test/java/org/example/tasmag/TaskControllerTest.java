package org.example.tasmag;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.tasmag.task.Task;
import org.example.tasmag.task.TaskStatus;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * This class contains integration tests for the TaskController in the Tasmag application.
 * It uses MockMvc to simulate HTTP requests and verify the behavior of the task management API.
 *
 * The tests cover:
 * - Creating tasks using POST requests.
 * - Retrieving all tasks and individual tasks by ID using GET requests.
 * - Updating tasks by ID using PUT requests.
 * - Deleting tasks by ID using DELETE requests.
 *
 * Annotations:
 * - @SpringBootTest: Loads the full application context for testing.
 * - @AutoConfigureMockMvc: Auto-configures the MockMvc object for simulating HTTP requests.
 * - @TestPropertySource: Loads test-specific properties from the application-test.properties file.
 *
 * Transactional and rollback are applied to ensure test isolation and avoid persistence of test data.
 */

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String path = "/api/v1/tasks";
    private final String pathWithId = path + "/{id}";

    @Autowired
    private ObjectMapper objectMapper;

    private final LocalDateTime dueDate1 = LocalDateTime.of(2020, 1, 1, 0, 0);
    private final Task task1 = new Task("task1", "description1", dueDate1, TaskStatus.PENDING);

    private final LocalDateTime dueDate2 = LocalDateTime.of(2023, 1, 1, 0, 0);
    private final Task task2 = new Task("task2", "description2", dueDate2, TaskStatus.FAILED);

    private String dateToString(LocalDateTime date) throws JsonProcessingException {
        return objectMapper.writeValueAsString(date).replace("\"", "");
    }

    @Test
    @Order(0)
    void createTasks() throws Exception {
        String json1 = objectMapper.writeValueAsString(task1);
        String json2 = objectMapper.writeValueAsString(task2);

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json1))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("task1")))
                .andExpect(jsonPath("$.description", is("description1")))
                .andExpect(jsonPath("$.dueDate", is(dateToString(dueDate1))))
                .andExpect(jsonPath("$.status", is("PENDING")));

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json2))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("task2")))
                .andExpect(jsonPath("$.description", is("description2")))
                .andExpect(jsonPath("$.dueDate", is(dateToString(dueDate2))))
                .andExpect(jsonPath("$.status", is("FAILED")));

    }

    @Test
    @Transactional
    @Rollback
    void getTasks() throws Exception {
        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @Transactional
    @Rollback
    void getTaskById() throws Exception {
        mockMvc.perform(get(pathWithId, 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("task2")))
                .andExpect(jsonPath("$.description", is("description2")))
                .andExpect(jsonPath("$.dueDate", is(dateToString(dueDate2))))
                .andExpect(jsonPath("$.status", is("FAILED")));

        mockMvc.perform(get(pathWithId, 9))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback
    void updateTaskById() throws Exception {
        String json1 = objectMapper.writeValueAsString(task1);

        mockMvc.perform(put(pathWithId, 2)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("task1")))
                .andExpect(jsonPath("$.description", is("description1")))
                .andExpect(jsonPath("$.dueDate", is(dateToString(dueDate1))))
                .andExpect(jsonPath("$.status", is("PENDING")));

        mockMvc.perform(put(pathWithId, 9)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json1))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback
    void deleteTaskById() throws Exception {
        mockMvc.perform(delete(pathWithId, 1))
                .andExpect(status().isNoContent());

        mockMvc.perform(get(pathWithId, 1))
                .andExpect(status().isNotFound());
    }
}
