# Spring Boot Event-Driven Demo

## Overview
This project demonstrates an **event-driven architecture** using Spring Boot.  
It provides REST APIs for managing **Users** and **Orders**. Each event type can have multiple listeners, some running asynchronously.

---

## Features
- REST API for Users and Orders
- Event-driven design using Spring `ApplicationEventPublisher`
- Multiple listeners per event:
    - **Email Listener** – Sends notifications
    - **Logging Listener** – Logs events to console
    - **Audit Listener** – Records user creation
    - **Payment Listener** – Processes orders
    - **Notification Listener** – Sends order notifications
    - **Order Event Listener** – Generic order event handling
    - **User Event Listener** – Generic user event handling

---

## Tech Stack
- Java 17
- Spring Boot 3.2.7
- Maven

---

