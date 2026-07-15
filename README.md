# 🛒 Secure E-Commerce REST API

A highly scalable, production-leaning RESTful API for an E-Commerce platform. This project demonstrates enterprise-level backend engineering, focusing on robust security, database optimization, and clean architectural patterns.

## 🚀 Key Features

* **Advanced Authentication:** Dual-layer security featuring custom stateless JWT token filtering and OAuth2 (Google) login integration.
* **Role-Based Access Control (RBAC):** Method-level security (`@PreAuthorize`, `@PostAuthorize`) strictly separating `ADMIN` and `USER` privileges.
* **Optimized Data Fetching:** Spring Data JPA Pagination implemented across all catalog endpoints to ensure low-latency responses.
* **Enterprise Data Integrity:** Utilizes Hibernate `@Transactional` dirty checking for efficient updates and "soft deletion" (boolean flagging) to protect database history.
* **Global Exception Handling:** Centralized `@RestControllerAdvice` guarantees that frontend clients always receive standardized, predictable JSON error responses (e.g., 404 Not Found, 403 Forbidden).

## 🛠️ Tech Stack

* **Core:** Java 17, Spring Boot 3
* **Security:** Spring Security, JSON Web Tokens (JJWT), OAuth2
* **Database & ORM:** MySQL, Spring Data JPA, Hibernate
* **Tools:** Maven, Postman (API Testing), Lombok

## 📡 Core API Endpoints

### Authentication
* `POST /api/auth/register` - Register a new user
* `POST /api/auth/login` - Authenticate and receive a JWT

### Products (Public/User)
* `GET /api/products` - Fetch all products (Paginated)
* `GET /api/products/filter?category={name}` - Filter products by category
* `POST /api/products/review` - Submit a product review (Requires JWT)

### Admin Operations (Requires ADMIN Role)
* `POST /api/products` - Add a new product to the catalog
* `PUT /api/products/{id}` - Update existing product details
* `DELETE /api/products/{id}` - Soft-delete a product
* `GET /api/admin/users` - Fetch paginated list of registered users

## ⚙️ Setup and Installation

1. Clone the repository: `git clone https://github.com/your-username/your-repo-name.git`
2. Configure your MySQL database credentials in `src/main/resources/application.properties`.
3. Add your specific JWT Secret Key and OAuth2 Client IDs to the properties file.
4. Run the application using Maven: `mvn spring-boot:run`