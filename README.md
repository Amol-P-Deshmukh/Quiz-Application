# Quiz App – Microservices Architecture

A Spring Boot 3–based **Microservices Quiz Application** demonstrating modern distributed system concepts like **API Gateway**, **Service Discovery**, **Feign Client**, **Load Balancing**, and **Centralized Exception Handling**.

---

## Overview

This project consists of **four microservices**, each running independently with its own Maven build (`pom.xml`):

| Service Name | Description | Port | Spring Application Name |
|---------------|-------------|------|--------------------------|
| **service-registry** | Eureka Server for service discovery | `8761` | `service-registry` |
| **api-gateway** | API Gateway that routes requests to backend services | `8765` | `api-gateway` |
| **quiz-service** | Manages quizzes and communicates with question-service | `8081` | `quiz-service` |
| **question-service** | Manages questions and provides data to quiz-service | `8082` | `question-service` |

---

## Tech Stack

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Cloud 2023.x**
- **Spring Cloud Gateway**
- **Spring Cloud Netflix Eureka**
- **Spring Cloud OpenFeign**
- **Spring Cloud LoadBalancer**
- **Spring Web**
- **Lombok**
- **Maven**

---

## Microservice Details

### 1. Service Registry (Eureka Server)
Centralized registry where all services register themselves and discover other services.

**Port:** `8761`  
**Application Name:** `service-registry`

---

### 2. 🚪 API Gateway
Acts as the single entry point to the system. It routes requests to microservices using service discovery and handles load balancing.

**Port:** `8765`  
**Application Name:** `api-gateway`

---

### 3. Quiz Service
Handles all **quiz-related operations** and communicates with **question-service** via Feign Client.

---

## Load Balancing

Spring Cloud **LoadBalancer** automatically distributes incoming requests across multiple instances of a service.  
To test this:
1. Run multiple instances of `quiz-service` on different ports (e.g., 8081, 8083)
2. Access the service via API Gateway
3. Observe load-balanced routing

---

## Inter-Service Communication

Communication between `quiz-service` and `question-service` is handled via **Feign Client**.  
Service discovery is dynamic — URLs are resolved through **Eureka** instead of hardcoding host/port values.

---

## Exception Handling

Each service includes a **Global Exception Handler** using `@ControllerAdvice` for consistent error responses.

**Example Error Response:**
```json
{
  "timestamp": "2025-11-06T12:45:00",
  "status": 404,
  "error": "Not Found",
  "message": "Quiz not found with ID 10",
  "path": "/api/quizzes/10"
}
```
---

### Start Services (in order)
1. `service-registry`
2. `question-service`
3. `quiz-service`
4. `api-gateway`

### Verify on Eureka Dashboard
Visit: [http://localhost:8761](http://localhost:8761)  
You should see:
```
SERVICE-REGISTRY
API-GATEWAY
QUIZ-SERVICE
QUESTION-SERVICE
```

---

## Project Structure
```
Quiz-Application/
├── api-gateway/
│   └── pom.xml
├── quiz-service/
│   └── pom.xml
├── question-service/
│   └── pom.xml
├── service-registry/
│   └── pom.xml
└── README.md
```

---

## Key Highlights
1. Distributed Microservices Architecture
2. Centralized Service Discovery (Eureka)
3. API Gateway with dynamic routing and path rewriting
4. Inter-service communication using Feign Client
5. Built-in Load Balancing
6. Centralized Exception Handling
7. Ready for scaling and containerization (Docker/Kubernetes)

