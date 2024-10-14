package org.example.tasmag;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.tasmag.user.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * This class contains integration tests for the UserController in the Tasmag application.
 * It uses MockMvc to simulate HTTP requests and verify the behavior of the user management API.
 *
 * The tests cover:
 * - Creating users using POST requests.
 * - Retrieving all users and individual users by ID using GET requests.
 * - Deleting users by ID using DELETE requests.
 *
 * Annotations:
 * - @SpringBootTest: Loads the full application context for testing.
 * - @AutoConfigureMockMvc: Auto-configures the MockMvc object for simulating HTTP requests.
 * - @TestPropertySource: Loads test-specific properties from the application-test.properties file.
 *
 * Transactional and rollback mechanisms are applied to ensure test isolation and prevent test data persistence.
 */

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String path = "/users";
    private final String pathWithId = "/users/{id}";

    @Autowired
    private ObjectMapper objectMapper;

    private final User user1 = new User("Balovic", "balovic@example.com", "123");
    private final User user2 = new User("Brmbal", "brmbal@example.com", "456");

    @Test
    @Order(0)
    void createUser() throws Exception {
        String json1 = objectMapper.writeValueAsString(user1);
        String json2 = objectMapper.writeValueAsString(user2);

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json1))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("Balovic")))
                .andExpect(jsonPath("$.email", is("balovic@example.com")))
                .andExpect(jsonPath("$.password", is("123")));

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json2))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.username", is("Brmbal")))
                .andExpect(jsonPath("$.email", is("brmbal@example.com")))
                .andExpect(jsonPath("$.password", is("456")));
    }

    @Test
    @Transactional
    @Rollback
    void getUsers() throws Exception {
        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @Transactional
    @Rollback
    void getUserById() throws Exception {
        mockMvc.perform(get(pathWithId, 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.username", is("Brmbal")))
                .andExpect(jsonPath("$.email", is("brmbal@example.com")));

        mockMvc.perform(get(pathWithId, 9))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback
    void deleteUser() throws Exception {
        mockMvc.perform(delete(pathWithId, 1))
                .andExpect(status().isNoContent());

        mockMvc.perform(get(pathWithId, 1))
                .andExpect(status().isNotFound());
    }
}
