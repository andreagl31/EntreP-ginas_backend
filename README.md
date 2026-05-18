# 📚 Entrepaginas API

Backend desarrollado con Java y Spring Boot para una aplicación de biblioteca virtual donde los usuarios pueden registrarse, autenticarse y guardar libros en su biblioteca personal.

---

# 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA / Hibernate
- H2 Database
- Swagger / OpenAPI
- Lombok
- Maven
- JUnit + MockMvc

---

# 📖 Descripción del proyecto

Entrepaginas es una API REST que permite gestionar una biblioteca virtual.

Los usuarios pueden:

- Registrarse e iniciar sesión
- Consultar libros disponibles
- Guardar libros en su biblioteca personal
- Eliminar libros guardados

Los administradores además pueden:

- Crear libros
- Actualizar libros
- Eliminar libros
- Consultar todos los usuarios registrados

La aplicación utiliza autenticación basada en JWT y control de roles mediante Spring Security.

---

# 🏗️ Arquitectura del proyecto

El proyecto sigue una arquitectura por capas:

```txt
src/main/java/com/example/Entrepaginas
│
├── config
├── controller
├── dto
├── model
├── repository
├── security
├── service
├── testunitarios
```

---

# 📂 Descripción de paquetes

## `config`

Configuraciones generales de la aplicación.

### `OpenApiConfig`

Configuración de Swagger/OpenAPI.

Características:
- Documentación automática de endpoints
- Integración con JWT
- Definición del esquema Bearer Authentication

---

## `controller`

Controladores REST de la API.

### `AuthController`

Gestiona autenticación y registro de usuarios.

Endpoints:

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/auth/register` | Registro de usuario |
| POST | `/api/auth/login` | Inicio de sesión |

---

### `BookController`

Gestiona operaciones CRUD sobre libros.

Endpoints:

| Método | Endpoint | Rol requerido |
|---|---|---|
| GET | `/api/books` | Usuario autenticado |
| GET | `/api/books/{id}` | Usuario autenticado |
| POST | `/api/books` | ADMIN |
| PUT | `/api/books/{id}` | ADMIN |
| DELETE | `/api/books/{id}` | ADMIN |

---

### `LibraryController`

Gestiona la biblioteca personal de cada usuario.

Endpoints:

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/library/me` | Obtener libros guardados |
| POST | `/api/library/add/{bookId}` | Añadir libro |
| DELETE | `/api/library/remove/{bookId}` | Eliminar libro |

---

### `AdminController`

Funciones exclusivas para administradores.

Endpoints:

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/admin/users` | Obtener todos los usuarios |

---

### `GlobalExceptionHandler`

Manejo global de excepciones.

Errores controlados:
- `400 Bad Request`
- `401 Unauthorized`
- `403 Forbidden`
- `409 Conflict`

---

## `dto`

Objetos de transferencia de datos utilizados para comunicación entre cliente y servidor.

### DTOs implementados

- `BookDto`
- `LoginRequestDto`
- `LoginResponseDto`
- `RegisterRequestDto`
- `UserBookDto`

Características:
- Validaciones con Jakarta Validation
- Separación entre entidades y respuestas HTTP
- Mayor seguridad y control de datos

---

## `model`

Entidades principales de la aplicación.

### `User`

Representa un usuario del sistema.

Campos principales:
- id
- username
- password
- role

Implementa:
- `UserDetails`

---

### `Book`

Representa un libro disponible en la plataforma.

Campos:
- id
- title
- author
- genre

---

### `UserBook`

Entidad intermedia para relacionar usuarios y libros guardados.

Incluye:
- Usuario propietario
- Libro guardado
- Fecha de añadido

---

### `Role`

Enum de roles disponibles:

```java
ROLE_USER
ROLE_ADMIN
```

---

## `repository`

Acceso a datos mediante Spring Data JPA.

### Repositories implementados

- `UserRepository`
- `BookRepository`
- `UserBookRepository`

Funciones:
- Persistencia de datos
- Consultas automáticas
- Métodos personalizados

---

## `security`

Configuración de autenticación y autorización.

### `SecurityConfig`

Configuración principal de seguridad.

Características:
- JWT Authentication
- Seguridad Stateless
- Control de roles
- Configuración CORS
- Protección de endpoints

---

### `JwtAuthenticationFilter`

Filtro encargado de:
- Leer token JWT
- Validar token
- Autenticar usuario automáticamente

---

### `UserDetailsServiceImpl`

Carga usuarios desde base de datos para Spring Security.

---

## `service`

Contiene la lógica de negocio.

### `UserService`

Funciones:
- Registro de usuarios
- Login
- Gestión de usuarios

---

### `BookService`

Funciones:
- Obtener libros
- Crear libros
- Actualizar libros
- Eliminar libros

---

### `LibraryService`

Funciones:
- Gestionar biblioteca personal
- Añadir libros
- Eliminar libros

---

### `JwtService`

Funciones:
- Generar tokens JWT
- Validar tokens
- Extraer información del token

---

## `testunitarios`

Tests unitarios y de integración.

### `AuthControllerTest`

Pruebas:
- Registro exitoso
- Login exitoso
- Login inválido
- Acceso sin token
- Restricciones por rol

---

### `BookControllerTest`

Pruebas:
- Acceso autenticado
- Seguridad de endpoints
- Restricciones ADMIN

---

# 🔐 Seguridad

La aplicación utiliza:

- Spring Security
- JWT
- Roles y permisos
- BCrypt Password Encoder

---

# 👥 Roles disponibles

| Rol | Permisos |
|---|---|
| ROLE_USER | Consultar libros y gestionar biblioteca |
| ROLE_ADMIN | CRUD completo y administración |

---

# 🗄️ Base de datos

Se utiliza H2 Database en memoria para desarrollo y testing.

## Configuración

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
```

---

# ⚙️ Configuración JWT

```properties
jwt.secret=TU_SECRET
jwt.expiration=86400000
```

Duración:
- 24 horas

---

# ▶️ Ejecución del proyecto

## Clonar repositorio

```bash
git clone <url-repositorio>
```

---

## Ejecutar aplicación

```bash
mvn spring-boot:run
```

---

# 📚 Swagger / OpenAPI

Documentación disponible en:

```txt
http://localhost:8081/swagger-ui/index.html
```

Swagger incluye autenticación JWT integrada.

---

# 🔑 Autenticación

## Registro

### POST `/api/auth/register`

### Body

```json
{
  "username": "usuario",
  "password": "password123"
}
```

---

## Login

### POST `/api/auth/login`

### Body

```json
{
  "username": "usuario",
  "password": "password123"
}
```

### Respuesta

```json
{
  "token": "jwt-token",
  "username": "usuario",
  "role": "ROLE_USER"
}
```

---

# 📖 Gestión de libros

## Obtener todos los libros

### GET `/api/books`

Requiere JWT.

---

## Obtener libro por ID

### GET `/api/books/{id}`

---

## Crear libro

### POST `/api/books`

Requiere:
- ROLE_ADMIN

### Body

```json
{
  "title": "Dune",
  "author": "Frank Herbert",
  "genre": "Ciencia ficción"
}
```

---

## Actualizar libro

### PUT `/api/books/{id}`

Requiere:
- ROLE_ADMIN

---

## Eliminar libro

### DELETE `/api/books/{id}`

Requiere:
- ROLE_ADMIN

---

# 📚 Biblioteca personal

## Obtener libros guardados

### GET `/api/library/me`

---

## Añadir libro

### POST `/api/library/add/{bookId}`

---

## Eliminar libro

### DELETE `/api/library/remove/{bookId}`

---

# 👤 Usuarios iniciales

La aplicación crea automáticamente usuarios de prueba.

## Administrador

```txt
usuario: admin
contraseña: admin123
```

---

## Usuario normal

```txt
usuario: usuario
contraseña: usuario123
```

---

# 🧪 Testing

El proyecto incluye:
- Tests unitarios
- Tests de integración
- Validaciones de seguridad

Tecnologías:
- JUnit 5
- MockMvc
- Spring Boot Test

---

## Ejecutar tests

```bash
mvn test
```

---

# ⚡ Características implementadas

- API REST
- Arquitectura por capas
- CRUD de libros
- JWT Authentication
- Roles y permisos
- Biblioteca personalizada
- Validaciones
- Manejo global de errores
- Swagger/OpenAPI
- Testing automatizado
- Base de datos H2
- Inicialización automática de datos

---

# 👨‍💻 Autor

Proyecto desarrollado como práctica de Andrea González Llamas de backend utilizando Spring Boot, JWT y arquitectura REST.
