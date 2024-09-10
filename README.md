# **Tasmag
# **Task Manager – Microservice Application**

## **Overview**
Task Manager is a microservice-based application designed for managing tasks. It allows users to create, update, and manage their tasks efficiently. The backend is implemented using Java (Spring Boot) and Kotlin (Ktor). The application supports user authentication via JWT, task notifications, and role-based access control for administrators and users.

## **Technologies Used**
- **Java** (Spring Boot) – Core services (User Management, Task Management)
- **Kotlin** (Ktor) – Notification microservice
- **PostgreSQL** – Database for persisting user and task data
- **JWT** – User authentication and authorization
- **Docker** – Containerization of services
- **Gradle/Maven** – Build tools for Java and Kotlin
- **JUnit & Mockito** – Testing for Java services
- **KotlinTest** – Testing for Kotlin services

## **Features**
### **User Management**
- User registration and login
- Password reset functionality
- JWT-based authentication for secure API access
- Role-based access control (User and Admin roles)
  
### **Task Management**
- Create, read, update, and delete tasks (CRUD operations)
- Set task priority (Low, Medium, High)
- Mark tasks as completed
- Task filtering by date, status, or priority

### **Role Management**
- Regular users: Can manage only their own tasks
- Administrators: Can manage all users and tasks

### **Notifications (Kotlin Microservice)**
- Email reminders for upcoming deadlines
- Confirmation emails when tasks are completed
- Optional weekly task summary reports

### **Statistics and Reporting**
- User productivity statistics (e.g., tasks completed per week)
- Admin access to system-wide statistics (e.g., total users, total tasks)

## **Architecture**
This project follows a microservice architecture with distinct services for different functionalities:
- **Java Service (Spring Boot)**: Handles user and task management.
- **Kotlin Service (Ktor)**: Responsible for sending notifications.
- **PostgreSQL**: Centralized database for storing all data.
- **JWT**: Security layer for authentication and authorization across services.

### **Service Interactions**
- Java microservice communicates with the Kotlin microservice via REST API to trigger task notifications (e.g., when deadlines are near).
  
## **Getting Started**

### **Prerequisites**
- JDK 11+
- Kotlin 1.6+
- Docker
- PostgreSQL
- Gradle/Maven (depending on your preference for Java/Kotlin projects)
