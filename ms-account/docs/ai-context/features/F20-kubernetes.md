# Feature: Kubernetes Deployment

## User Story

As a system,
I want to deploy the microservice in Kubernetes,
so that it can run in a scalable and managed environment.

---

## Tasks

### 1. Create Kubernetes Structure

Create folder:

k8s/

### 2. Create Deployment

File:

k8s/deployment.yaml

The deployment must include:

- apiVersion: apps/v1
- kind: Deployment
- metadata with service name
- replicas: 1

Container configuration:

- name: ms-account
- image: must match Docker image
- containerPort: 8080

Environment variables:

- Kafka configuration
- Database configuration

### 3. Create Service

File:

k8s/service.yaml

The service must include:

- type: ClusterIP
- port: 80
- targetPort: 8080

### 4. Health Checks

Add probes:

- readinessProbe
- livenessProbe

Using:

/actuator/health

### 5. Resource Configuration

Add:

- CPU and memory requests
- CPU and memory limits

---

## Rules

- Do not hardcode values
- Use environment variables
- Configuration must match Docker container
- Naming must be consistent with microservice name

---

## Acceptance Criteria

- Deployment is created successfully
- Pod is running
- Service exposes the application
- Health checks respond correctly
- Application logs are visible
