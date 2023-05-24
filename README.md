# Spring Boot Demo Projects
This repository contains projects that I've built while exploring the Java Spring framework.

## Project1 - Spring Boot Demo API

This is a simple REST API involving a student class, that's saved as a model in PostgreSQL. The purpose of this project is to become familiar with the basics of Spring Boot, creating endpoints, performing Create, Read, Update, Delete (CRUD) operations, and utilizing the provided annotations to write elegant code.

### Endpoints

#### GET requests
Returns all students.
```
GET http://localhost:8080/
```

#### POST requests
Creates a new student.
```
POST http://localhost:8080/api/v1/student
Content-Type: application/json

{
  "name": "Bilal",
  "email": "bilal@gmail.com",
  "dob": "1995-12-17"
}
``` 

#### PUT requests
Updates existing student (with id of 1).
```
PUT http://localhost:8080/api/v1/student/1?name=Maria&email=maria@gmail.com
Content-Type: application/json
```
#### DELETE requests
Deletes a student (with id of 1).
```
DELETE http://localhost:8080/api/v1/student/1
```

### References:
1. [Spring Boot Tutorial Full Course](https://www.youtube.com/watch?v=9SGDpanrc8U)

## Project2 - Spring Security Demo API
This is a simple REST API involving a User class, that's saved as a model in MySql. This project served as a good introduction to Spring Security, and how basic authentication/authorization work within the Spring framework. Normal GET requests are *not* enabled. 

### Endpoints
#### POST requests
Register a new user.
```
POST http://localhost:8080/api/v1/auth/register
Content-Type: application/json

{
   "firstName": "blah",
   "lastName": "hablah",
   "email": "blah@gmail.com",
   "password": "123"
}

# Returns a JWT token:
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJibGFoQGdtYWlsLmNvbSIsImlhdCI6MTY4NDkzNDE4MiwiZXhwIjoxNjg0OTM1NjIyfQ.OK_JRSCa0mDCfUzBusQ2-o1X74E-UhdIb3RSKt6XiTg"
}
```

Authenticate a user.
```
POST http://localhost:8080/api/v1/auth/authenticate
Content-Type: application/json

{
   "email": "blah@gmail.com",
   "password": "123"
}
```

#### GET requests
Test endpoint that requires the JWT token generated during register.
```
GET http://localhost:8080/
Authorization: Bearer <token generated in the register step>
```
Alternatively, if using Postman, you can add the Bearer token in the authorization tab.

### References:
1. [Spring Boot + Spring Security + JWT Authentication](https://www.youtube.com/watch?v=KxqlJblhzfI)