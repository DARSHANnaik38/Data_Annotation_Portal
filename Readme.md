# Data Annotation Portal

A full-stack web application developed using Spring Boot, React, and MySQL for managing image annotation workflows. The system allows administrators to manage datasets, users (annotators), assignments, and monitor overall project progress through a dashboard.

---

## Features

### Authentication
- User Login using JWT Authentication
- Role-based users (Admin, Annotator)

### Dashboard
- Total Datasets
- Total Users
- Total Assignments
- Completed Assignments

### Dataset Management
- Create Dataset
- View All Datasets
- Update Dataset
- Delete Dataset

### User Management
- Create Users
- View Users
- Update Users
- Delete Users

### Assignment Management
- Assign Datasets to Annotators
- View Assignments
- Update Assignment Status
- Delete Assignment

---

## Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT
- Hibernate
- Maven

### Frontend
- React
- Vite
- React Router
- Axios
- Bootstrap

### Database
- MySQL 8

---

## Project Structure

```
AnnotationPortal
│
├── backend
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── exception
│   ├── repository
│   ├── security
│   ├── service
│   └── resources
│
└── frontend
    ├── components
    ├── layouts
    ├── pages
    ├── routes
    ├── services
    └── api
```

---

## Backend Setup

1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/Data_Annotation_Portal.git
```

2. Open backend project

3. Configure MySQL

Create database

```sql
CREATE DATABASE annotationhub;
```

4. Update

```
application.properties
```

with your MySQL username and password.

5. Run

```
AnnotationPortalApplication.java
```

Backend runs on

```
http://localhost:8080
```

---

## Frontend Setup

Navigate to frontend

```bash
cd frontend
```

Install dependencies

```bash
npm install
```

Run

```bash
npm run dev
```

Frontend runs on

```
http://localhost:5173
```

---

## REST APIs

### Authentication

| Method | Endpoint |
|---------|----------|
| POST | /api/auth/register |
| POST | /api/auth/login |

### Dataset

| Method | Endpoint |
|---------|----------|
| GET | /api/datasets |
| POST | /api/datasets |
| PUT | /api/datasets/{id} |
| DELETE | /api/datasets/{id} |

### Users

| Method | Endpoint |
|---------|----------|
| GET | /api/users |
| POST | /api/users |
| PUT | /api/users/{id} |
| DELETE | /api/users/{id} |

### Assignments

| Method | Endpoint |
|---------|----------|
| GET | /api/assignments |
| POST | /api/assignments |
| PUT | /api/assignments/{id}/status |
| DELETE | /api/assignments/{id} |

---

## Future Improvements

- File Upload Support
- Image Annotation Interface
- Search and Filtering
- Dashboard Charts
- Pagination
- Docker Deployment
- Unit Testing
- Role-based Authorization Enhancements

---

## Author

**Darshan Naik**

BE Computer Science and Engineering

2026 Graduate

GitHub:
https://github.com/DARSHANnaik38

---

## License

This project is developed for educational and learning purposes.