# Feature: System Architecture

## Overview

The system is composed of:

- ms-clients
- ms-accounts

## Architecture Style

- Microservices
- Hexagonal architecture
- Event-driven communication

## Communication

- Kafka is used for asynchronous communication

## Data Flow

1. Client created in ms-clients
2. Event published to Kafka
3. ms-accounts consumes event

## Infrastructure

- Docker Compose for local orchestration
- Kubernetes for deployment

## Principles

- Decoupled services
- Scalability
- Maintainability