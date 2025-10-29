# Social Media Application (Backend)

This repository contains the backend implementation of a **Social Media Application** built using **Spring Boot**.  
The project follows a **four-layer architecture** — Model, Repository, Service, and Controller — ensuring a clean separation of concerns and maintainable code structure.

# Model Layer:
Represents the data structure of the application.
Contains entity classes mapped to database tables using JPA annotations.
Defines relationships (e.g., OneToMany, ManyToOne) between entities.
Ensures consistency between Java objects and database schema.

# Repository Layer:
Acts as the data access layer for performing CRUD operations.
Interfaces extend JpaRepository or CrudRepository from Spring Data JPA.
Eliminates boilerplate SQL by providing built-in query methods.
Can define custom queries using method names or @Query annotations.

# Service Layer:
Contains the business logic of the application.
Handles data processing, validation, and transformation before persistence or response.
Acts as a bridge between the Controller and Repository layers.
Promotes reusability and separation of concerns.

# Controller Layer:
Exposes RESTful endpoints to interact with the application.
Handles HTTP requests and responses (GET, POST, PUT, DELETE).
Uses @RestController and @RequestMapping annotations.
Delegates logic to the Service Layer for execution.
Returns structured JSON responses to clients.





