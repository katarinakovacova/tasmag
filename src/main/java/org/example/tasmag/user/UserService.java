package org.example.tasmag.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Service class for managing {@link User} entities.
 * This class provides methods to perform operations related to User entities,
 * including retrieving, creating, and deleting users.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Retrieves all users from the database.
     *
     * @return a list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a User by its ID.
     *
     * @param id the ID of the User to retrieve
     * @return an Optional containing the User if found, or an empty Optional if not
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Creates a new User in the database.
     *
     * @param user the User entity to create
     * @return the created User entity
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a User by its ID.
     *
     * @param id the ID of the User to delete
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Retrieves a User by its email.
     *
     * @param email the email of the User to retrieve
     * @return an Optional containing the User if found, or an empty Optional if not
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Checks if a User exists by its ID.
     *
     * @param id the ID of the User to check
     * @return true if the User exists, false otherwise
     */
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
