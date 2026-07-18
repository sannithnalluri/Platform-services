# Platform-services (Food App Microservices Infrastructure)

Centralized microservices cloud deployment architecture for a food delivery platform. This repository contains the foundational services, observability stack, and CI/CD pipelines.

## 🛠️ Microservices Architecture

The architecture consists of the following Spring Boot 3 & Spring Cloud components:
1. **Config Server** (Port `8888`): Centralized, dynamic property management.
2. **Eureka Server** (Port `8761`): Service registry and discovery.
3. **API Gateway** (Port `8080`): Request router, path rewriter, and Swagger UI aggregator.
4. **Food Service** (Port `8081`): Blueprint catalog service connected to PostgreSQL.

## 📊 Local Observability & Orchestration
Includes a complete monitoring pipeline:
* **Zipkin** (Port `9411`): Distributed transaction tracing.
* **Prometheus** (Port `9090`): Application metric scraping.
* **Grafana** (Port `3000`): Metric visualization dashboards (pre-wired with Prometheus & Zipkin).
* **PostgreSQL** (Port `5432`): Database simulating cloud RDS.

## 🚀 How to Run Locally

### 1. Compile the Project
```bash
mvn clean package -DskipTests
```

### 2. Start the Stack in Docker
```bash
docker compose up --build -d
```

### 3. Verify Routing
```bash
curl http://localhost:8080/food-service/api/foods
```

---

## ☁️ AWS production deployment via GitHub Actions
We use GitHub Actions to automate compilation, build Docker containers, push to Docker Hub, copy compose settings to EC2 via SCP, and deploy via SSH.

See **[walkthrough.md](file:///C:/Users/Sanni/.gemini/antigravity/brain/c36c7231-8bdb-4fb0-b864-4f4c2773925d/walkthrough.md)** for a complete step-by-step AWS and GitHub Secrets setup guide.
