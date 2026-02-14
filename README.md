# EvenHub - Enterprise Event Management Backend

EvenHub is a secure, production-style RESTful API built with **Spring Boot**. [cite_start]It serves as the backbone for the EvenHub Flutter Web Application, providing robust event management, user authentication, and role-based access control[cite: 34, 36].

## 🚀 Key Features (Beyond CRUD)
* [cite_start]**Hybrid Authentication:** Implements JWT-based stateless security integrated with a session/cookie flow[cite: 38, 39].
* [cite_start]**Role-Based Authorization:** Strict access control where only specific users or Admins can modify data[cite: 39].
* [cite_start]**Advanced Data Relationships:** Manages complex mappings between Users, Events, and Speakers[cite: 40, 46].
* [cite_start]**Security Validation:** All modifications are verified using **Unique IDs** to ensure data integrity[cite: 42, 44].
* **Nested Entity Management:** Create events and their associated speakers in a single atomic transaction using JPA Cascading.

---

## 🛠️ Tech Stack
* **Framework:** Spring Boot 3.x
* **Language:** Java 17
* **Security:** Spring Security & JWT
* **Database:** MySQL
* **ORM:** Spring Data JPA (Hibernate)
* **Build Tool:** Maven

---

## 📂 Project Structure
[cite_start]The project follows a **Layered Architecture** to ensure clean code and scalability[cite: 16, 17, 46]:

* [cite_start]`models/`: Entity classes (User, Event, Speaker) defining the database schema[cite: 46].
* [cite_start]`controllers/`: REST API Endpoints handling HTTP requests (GET, POST, PUT, DELETE)[cite: 46, 48].
* [cite_start]`services/`: Business logic layer for validation and security checks[cite: 46].
* [cite_start]`repository/`: Data access layer for MySQL interaction[cite: 46].
* `config/`: Security configurations for JWT and CORS.

---

## ⚙️ Setup & Installation

### 1. Database Configuration
Create a MySQL database named `even_hub_db`. Update the `src/main/resources/application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/even_hub_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
