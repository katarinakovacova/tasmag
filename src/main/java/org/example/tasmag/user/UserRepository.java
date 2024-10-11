package org.example.tasmag.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 * This interface provides methods to perform CRUD operations and custom queries on the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a User entity by its email.
     *
     * @param email the email of the User to retrieve
     * @return an Optional containing the User if found, or an empty Optional if not
     */
    Optional<User> findByEmail(String email);
}
