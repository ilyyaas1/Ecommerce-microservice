# 🛒 E-Commerce Microservices (Event-Driven with Kafka)

A scalable, distributed E-commerce backend built with **Spring Boot 3**, **Apache Kafka**, and **Docker**.

This project implements an **Event-Driven Architecture** where services are decoupled. Instead of synchronous HTTP calls, the **Order Service** produces events that are consumed asynchronously by the **Payment** and **Notification** services.

## 🏗 Architecture Overview

The system utilizes the **Saga Pattern** (Choreography-based) to handle distributed transactions.



| Service | Port | Description |
| :--- | :--- | :--- |
| **Discovery Server** | `8761` | Eureka Server for Service Discovery. |
| **API Gateway** | `8080` | Unified entry point (Spring Cloud Gateway). Routes traffic. |
| **Order Service** | `8081` | Manages Order lifecycle. Producer to `order-topic`. |
| **Payment Service** | `8082` | Consumes orders, processes payments, produces `payment-updates`. |
| **Notification Service**| `8083` | Listens to payment success events and sends emails via SMTP. |
| **Config Server** | `8888` | Centralized configuration management (Git/Local). |

---

## ⚙️ Tech Stack

* **Core:** Java 17, Spring Boot 3.2
* **Messaging:** Apache Kafka, Zookeeper
* **Database:** PostgreSQL (One database per service pattern)
* **Discovery & Routing:** Netflix Eureka, Spring Cloud Gateway
* **Email:** Spring Mail, Thymeleaf, MailDev (Docker)
* **Containerization:** Docker, Docker Compose
* **Build Tool:** Maven

---

## 🔄 The Event Flow

1.  **Order Placement:** User sends a POST request to create an order.
2.  **Order Created Event:** `Order Service` saves the order as `PENDING` and pushes an event to the **Kafka Topic** `t_orders`.
3.  **Payment Processing:** `Payment Service` listens to `t_orders`, charges the customer, and saves the transaction.
4.  **Payment Update Event:** `Payment Service` pushes a result event (SUCCESS/FAILED) to `t_payment_updates`.
5.  **Completion:**
    * `Notification Service` consumes the update and sends an HTML email receipt.
    * `Order Service` consumes the update and finalizes the order status to `CONFIRMED`.

---

## 🚀 Getting Started

### 1. Prerequisites
Ensure you have the following installed:
* Docker & Docker Compose
* Java JDK 17+
* Maven

### 2. Infrastructure Setup
Start the required infrastructure (Kafka, Postgres, MailDev, Zookeeper) using Docker:

```bash
docker-compose up -d
