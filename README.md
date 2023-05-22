# Spring Boot Demo API

This is a simple REST API involving a student class, that's saved as a model in PostgreSQL. The purpose of this project is to become familiar with the basics of Spring Boot, creating endpoints, performing Create, Read, Update, Delete (CRUD) operations, and utilizing the provided annotations to write elegant code.

## Endpoints

### GET requests
Returns all students.
```
GET http://localhost:8080/
```

### POST requests
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

### PUT requests
Updates existing student (with id of 1).
```
PUT http://localhost:8080/api/v1/student/1?name=Maria&email=maria@gmail.com
Content-Type: application/json
```
### DELETE requests
Deletes a student (with id of 1).
```
DELETE http://localhost:8080/api/v1/student/1
```