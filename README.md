# 🎬 Movie Review System

A Spring Boot REST API for managing movies, users, reviews and ratings.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=springboot&logoColor=white)

## Features
- Full CRUD for users and reviews
- Add movies and rate them
- Request/response DTOs and a common response wrapper
- Global exception handling (`GlobalControllerAdvice`) with typed errors: `MovieNotFound`, `ReviewNotFound`, `UserNotFound`

## API
| Resource | Endpoints |
|---|---|
| Movies | `GET /movies` · `GET /movies/{id}` · `POST /movies` · `POST /movies/{id}/rate` |
| Users | `POST /users` · `GET /users` · `GET /users/{id}` · `PATCH /users/{id}` · `DELETE /users/{id}` |
| Reviews | `POST /reviews/{movieId}` · `PATCH /reviews/{id}` · `DELETE /reviews/{id}` |

## Run locally
```bash
./mvnw spring-boot:run
```
