# App Gym - Backend Simplificado

Backend simple para una aplicación de gimnasio desarrollado con Spring Boot.

## Características

- **Gestión de usuarios**: Registro, actualización y eliminación de usuarios
- **Datos físicos**: Seguimiento de altura, peso, actividad física y preferencias
- **API REST**: Endpoints simples y fáciles de usar
- **Base de datos PostgreSQL**: Modelo de datos simplificado

## Estructura del Proyecto

```
src/main/java/com/backend/App_gym/
├── entity/
│   ├── Usuario.java          # Entidad de usuario
│   └── DatosFisicos.java     # Entidad de datos físicos
├── repository/
│   ├── UsuarioRepository.java        # Repositorio de usuarios
│   └── DatosFisicosRepository.java   # Repositorio de datos físicos
├── controller/
│   ├── UsuarioController.java        # Controlador de usuarios
│   └── DatosFisicosController.java   # Controlador de datos físicos
├── dto/
│   ├── UsuarioRequest.java           # DTO para peticiones de usuario
│   └── DatosFisicosRequest.java      # DTO para peticiones de datos físicos
└── config/
    └── SecurityConfig.java           # Configuración de seguridad
```

## Modelo de Base de Datos

### Tabla: usuarios
- `id` (SERIAL PRIMARY KEY)
- `nombre` (VARCHAR(50))
- `apellidos` (VARCHAR(100))
- `email` (VARCHAR(100) UNIQUE)
- `username` (VARCHAR(50) UNIQUE)
- `password` (TEXT)
- `fecha_nacimiento` (DATE)
- `sexo` (VARCHAR(10))

### Tabla: datos_fisicos
- `id` (SERIAL PRIMARY KEY)
- `usuario_id` (INTEGER, FOREIGN KEY)
- `altura_cm` (INTEGER)
- `peso_kg` (NUMERIC(5,2))
- `frecuencia_actividad` (TEXT)
- `dias_entrenamiento_por_semana` (INTEGER)
- `problemas_salud` (TEXT)
- `horario_preferido` (TEXT)
- `motivacion` (TEXT)

## Endpoints de la API

### Usuarios (`/api/usuarios`)
- `GET /` - Obtener todos los usuarios
- `GET /{id}` - Obtener usuario por ID
- `POST /` - Crear nuevo usuario
- `PUT /{id}` - Actualizar usuario
- `DELETE /{id}` - Eliminar usuario
- `GET /email/{email}` - Buscar usuario por email
- `GET /username/{username}` - Buscar usuario por username

### Datos Físicos (`/api/datos-fisicos`)
- `GET /` - Obtener todos los datos físicos
- `GET /{id}` - Obtener datos físicos por ID
- `GET /usuario/{usuarioId}` - Obtener datos físicos de un usuario
- `GET /usuario/{usuarioId}/ultimo` - Obtener último dato físico de un usuario
- `POST /` - Crear nuevos datos físicos
- `PUT /{id}` - Actualizar datos físicos
- `DELETE /{id}` - Eliminar datos físicos

## Configuración

### Base de Datos
1. Crear una base de datos PostgreSQL
2. Ejecutar el script `database_setup.sql`
3. Configurar las credenciales en `application.properties`

### Aplicación
1. Clonar el repositorio
2. Configurar las variables de entorno
3. Ejecutar `mvn spring-boot:run`

## Tecnologías Utilizadas

- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Java 17**

## Ejemplo de Uso

### Crear un usuario
```bash
curl -X POST http://localhost:3000/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan",
    "apellidos": "Pérez García",
    "email": "juan@email.com",
    "username": "juanperez",
    "password": "password123",
    "fechaNacimiento": "1990-05-15",
    "sexo": "Masculino"
  }'
```

### Crear datos físicos
```bash
curl -X POST http://localhost:3000/api/datos-fisicos \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "alturaCm": 175,
    "pesoKg": 70.5,
    "frecuenciaActividad": "Moderada",
    "diasEntrenamientoPorSemana": 3,
    "problemasSalud": "Ninguno",
    "horarioPreferido": "Tarde",
    "motivacion": "Mantener buena forma física"
  }'
``` 