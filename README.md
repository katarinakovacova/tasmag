# Tasmag - Task and User Management API

Tasmag is a RESTful API built with Spring Boot that manages tasks and users. It provides endpoints for creating, retrieving, updating, and deleting both tasks and users, allowing efficient management of this data.

## Technologies Used

- Java 11
- Spring Boot 2.x
- Maven
- PostgreSQL (or any database supported by Spring Data)
- RabbitMQ (optional for future messaging services)

## Prerequisites

To run this project locally, ensure that you have the following installed:

- Java 11+
- Maven 3+
- PostgreSQL or a compatible database
- RabbitMQ (optional)

## Setup

1. Clone the repository.
2. Update `application.properties` with your database configuration.
3. Build the project using Maven:
   ```bash
   mvn clean install
4. Run the Spring Boot application:
      ```bash
    mvn spring-boot:run

## API Endpoints

### Task Management

#### 1. Retrieve All Tasks
- **Endpoint:** `GET /api/v1/tasks`
- **Description:** Retrieves a list of all tasks.
- **Response:**
  - `200 OK` - Returns a list of tasks in JSON format.

#### 2. Retrieve Task by ID
- **Endpoint:** `GET /api/v1/tasks/{id}`
- **Description:** Retrieves a specific task by its ID.
- **Path Parameter:** 
  - `id` (Long) - The ID of the task.
- **Response:**
  - `200 OK` - Returns the task details in JSON format.
  - `404 Not Found` - Task with the specified ID does not exist.

#### 3. Create a New Task
- **Endpoint:** `POST /api/v1/tasks`
- **Description:** Creates a new task.
- **Request Body:** 
  - Task object in JSON format.
- **Response:**
  - `201 Created` - Returns the created task in JSON format.

#### 4. Update an Existing Task
- **Endpoint:** `PUT /api/v1/tasks/{id}`
- **Description:** Updates the details of an existing task.
- **Path Parameter:** 
  - `id` (Long) - The ID of the task to be updated.
- **Request Body:** 
  - Updated task object in JSON format.
- **Response:**
  - `200 OK` - Returns the updated task details.
  - `404 Not Found` - Task with the specified ID does not exist.

#### 5. Delete a Task
- **Endpoint:** `DELETE /api/v1/tasks/{id}`
- **Description:** Deletes a task by its ID.
- **Path Parameter:** 
  - `id` (Long) - The ID of the task to be deleted.
- **Response:**
  - `204 No Content` - Task deleted successfully.
  - `404 Not Found` - Task with the specified ID does not exist.


### User Management

#### 1. Retrieve All Users
- **Endpoint:** `GET /users`
- **Description:** Retrieves a list of all users.
- **Response:**
  - `200 OK` - Returns a list of users in JSON format.

#### 2. Retrieve User by ID
- **Endpoint:** `GET /users/{id}`
- **Description:** Retrieves a specific user by their ID.
- **Path Parameter:** 
  - `id` (Long) - The ID of the user.
- **Response:**
  - `200 OK` - Returns the user details in JSON format.
  - `404 Not Found` - User with the specified ID does not exist.

#### 3. Create a New User
- **Endpoint:** `POST /users`
- **Description:** Creates a new user.
- **Request Body:** 
  - User object in JSON format.
- **Response:**
  - `201 Created` - Returns the created user in JSON format.

#### 4. Delete a User
- **Endpoint:** `DELETE /users/{id}`
- **Description:** Deletes a user by their ID.
- **Path Parameter:** 
  - `id` (Long) - The ID of the user to be deleted.
- **Response:**
  - `204 No Content` - User deleted successfully.
  - `404 Not Found` - User with the specified ID does not exist.
 
## Contact
For any questions, feedback, or support, please reach out to [Katarína Kováčová](mailto:katarinakovacova100@gmail.com).
