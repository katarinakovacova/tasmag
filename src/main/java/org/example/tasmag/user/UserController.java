package org.example.tasmag.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController manages user-related HTTP requests such as retrieving, creating, and deleting users.
 * It provides endpoints to work with user data in the system.
 * <p>
 * Endpoints:
 * <ul>
 *     <li>GET /users - retrieves a list of all users</li>
 *     <li>POST /users - creates a new user</li>
 *     <li>GET /users/{id} - retrieves a user by ID</li>
 *     <li>DELETE /users/{id} - deletes a user by ID</li>
 * </ul>
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructor for UserController.
     *
     * @param userService the user service to handle user operations
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users.
     *
     * @return ResponseEntity containing the list of all users with status 200 OK
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Create a new user.
     *
     * @param user the user to be created, provided in the request body
     * @return ResponseEntity containing the created user with status 201 CREATED
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * Get user by ID.
     *
     * @param id the ID of the user to be retrieved
     * @return ResponseEntity containing the user with the given ID and status 200 OK if found,
     *         or status 404 NOT FOUND if not found
     */
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a user by ID.
     *
     * @param id the ID of the user to be deleted
     * @return ResponseEntity with status 204 NO CONTENT if deletion is successful,
     *         or status 404 NOT FOUND if the user does not exist
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        if (userService.existsById(id)) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
