# 📚 Bookstore API – Spring Boot

## 🚀 Descripción
Bookstore API es un proyecto desarrollado en Spring Boot 3.x que demuestra cómo construir una API REST robusta y lista para producción, integrando seguridad, manejo de archivos, auditoría, caching, rate limiting y más.  
Es parte de mi portafolio como desarrollador backend.

---

## 📌 Requisitos del proyecto

### 1. Requisitos funcionales
- CRUD de Libros (crear, listar, actualizar, eliminar).
- CRUD de Usuarios con roles (USER, ADMIN).
- CRUD de Órdenes y carrito de compras.
- CRUD de Reseñas y ratings para libros.
- Paginación y Sorting en listados.
- Seguridad con JWT (login, registro, refresh token).
- Manejo de Archivos (subida y descarga de portadas).
- Manejo de Errores global con respuestas JSON claras.
- Documentación automática con Swagger/OpenAPI.
- Tests unitarios e integración (JUnit + Mockito).
- Migraciones de base de datos con Flyway/Liquibase.

---

### 2. Requisitos no funcionales
- Entornos Dev/Prod con perfiles (`application-dev.yml`, `application-prod.yml`).
- Logs profesionales con Logback (niveles INFO, ERROR, DEBUG).
- Auditoría: registrar quién creó/actualizó registros.
- Caching: cachear libros más consultados (Spring Cache/Redis).
- Rate Limiting / Throttling: limitar requests por usuario.
- Internacionalización (i18n): mensajes en varios idiomas.
- Health Checks y métricas con Spring Boot Actuator.
- CI/CD y despliegue con Docker + pipeline (GitHub Actions/GitLab CI).

---

### 3. Requisitos técnicos
- Framework: Spring Boot 3.x
- Seguridad: Spring Security 6 + JWT
- Persistencia: Spring Data JPA
- Base de datos: PostgreSQL/MySQL
- Documentación: springdoc-openapi
- Testing: JUnit 5 + Mockito
- Migraciones: Flyway
- Cache: Spring Cache + Redis (opcional)

---

## ✅ Resultado esperado
Un proyecto que no solo demuestra CRUD, sino que refleja nivel profesional: seguridad, auditoría, caching, rate limiting, entornos, logs, documentación y pruebas.  
Esto posiciona la API como lista para producción y muestra mis capacidades como desarrollador backend.