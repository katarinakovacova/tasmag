package org.example.tasmag.user;

import jakarta.persistence.*;

/**
 * Entity representing a User in the system.
 * Contains basic user information such as username, email, and password.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    /**
     * Default constructor for JPA.
     */
    public User() {
    }

    /**
     * Constructs a new User with the provided username, email, and password.
     *
     * @param username The username of the user.
     * @param email The email address of the user.
     * @param password The password for the user account.
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
