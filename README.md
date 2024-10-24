# Tasmag
# **Task Manager – Microservice Application**

## **Overview**
Task Manager is a microservice-based application designed for managing tasks. It allows users to create, update, and manage their tasks efficiently. The backend is implemented using Java (Spring Boot). 

## **Technologies Used**
- **Java** (Spring Boot) – Core services (User Management, Task Management)
- **PostgreSQL** – Database for persisting user and task data
- **JWT** – User authentication and authorization
- **Docker** – Containerization of services
- **Gradle/Maven** – Build tools for Java and Kotlin
- **JUnit & Mockito** – Testing for Java services

## **Features**
  
### **Task Management**
- Create, read, update, and delete tasks (CRUD operations)
- Set task priority (Low, Medium, High)
- Mark tasks as completed
- Task filtering by date, status, or priority

## **Architecture**
This project follows a microservice architecture with distinct services for different functionalities:
- **Java Service (Spring Boot)**: Handles user and task management.
- **Kotlin Service (Ktor)**: Responsible for sending notifications.
- **PostgreSQL**: Centralized database for storing all data.
- **JWT**: Security layer for authentication and authorization across services.
  
## Contact

For any questions or support, please contact [Katarína Kováčová](mailto:katarinakovacova100@gmail.com).

