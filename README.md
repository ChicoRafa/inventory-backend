# Inventory Backend

Web-App Inventory project built with Spring Boot and Angular. This repository hosts the backend part.

## 📋 Project Overview

This is a RESTful API backend for an inventory management system built with Spring Boot 3.5.7. The application provides CRUD operations for managing product categories with a MySQL database.

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot 3.5.7**
  - Spring Data JPA
  - Spring Web
  - Spring DevTools
- **MySQL 8.0**
- **Lombok** - To reduce boilerplate code
- **SLF4J** - For logging
- **Maven** - Dependency management
- **Docker Compose** - For containerization (configured for future use)

## 📦 Project Structure

```
src/main/java/com/company/inventory/
├── controller/
│   └── CategoryRestController.java    # REST API endpoints
├── dao/
│   └── ICategoryDao.java              # JPA Repository interface
├── model/
│   └── Category.java                  # Entity model
├── services/
│   ├── ICategoryService.java          # Service interface
│   └── CategoryServicesImpl.java      # Service implementation
├── response/
│   ├── ResponseRest.java              # Base response metadata
│   ├── CategoryResponse.java          # Category data wrapper
│   └── CategoryResponseRest.java      # Complete API response
└── InventoryApplication.java          # Main application class
```

## 🚀 Features Implemented

### Category Management API

The following REST endpoints are available at `/api/v1/categories`:

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/categories` | Fetch all categories |
| GET | `/categories/{id}` | Fetch category by ID |
| POST | `/categories` | Create a new category |
| PUT | `/categories/{id}` | Update an existing category |
| DELETE | `/categories/{id}` | Delete category by ID |
| DELETE | `/categories` | Delete all categories |

### Category Model

```java
{
  "id": Long,
  "name": String,
  "description": String
}
```

### Response Structure

All endpoints return a standardized response with metadata:

```json
{
  "metadata": [
    {
      "type": "OK|ERROR|NOT_FOUND",
      "code": "00|-1|01",
      "date": "timestamp"
    }
  ],
  "categoryResponse": {
    "category": [...]
  }
}
```

## ⚙️ Configuration

### Database Configuration

The application is configured to connect to a local MySQL database:

- **Database**: `db_inventory`
- **Host**: `localhost:3306`
- **Username**: `root`
- **Password**: `test`

### Docker Compose (Future Use)

A `compose.yaml` file is configured for running MySQL in Docker:
- Currently disabled in `application.properties`
- Ready for future integration with Angular frontend

## 🏃 Running the Application

### Prerequisites

- Java 21 or higher
- MySQL 8.0 running locally
- Maven 3.6+

### Steps

1. **Create the database**:
   ```sql
   CREATE DATABASE db_inventory;
   ```

2. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the API**:
   - Base URL: `http://localhost:8080/api/v1`

### Using Docker (Future)

When ready to use Docker:

1. Update `application.properties`:
   ```properties
   spring.docker.compose.enabled=true
   ```

2. Start Docker Compose:
   ```bash
   docker-compose up -d
   ```

## 🔍 Best Practices Implemented

- ✅ **Layered Architecture**: Clear separation between Controller, Service, and DAO layers
- ✅ **RESTful Design**: Standard HTTP methods and status codes
- ✅ **Exception Handling**: Comprehensive error handling with custom responses
- ✅ **Logging**: SLF4J logger for production-ready error tracking
- ✅ **CORS Configuration**: Ready for Angular frontend integration
- ✅ **Transaction Management**: `@Transactional` annotations for data integrity
- ✅ **Lombok**: Reduces boilerplate code with annotations

## 🔜 Future Enhancements

- [ ] Angular frontend integration
- [ ] Docker containerization for full stack
- [ ] Product entity and management
- [ ] Authentication and authorization
- [ ] Pagination and filtering
- [ ] Unit and integration tests
- [ ] API documentation with Swagger/OpenAPI

## 📝 License

This project is licensed under the terms specified in the LICENSE file.

