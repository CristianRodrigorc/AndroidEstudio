# 🏋️ Gym App - Aplicación de Fitness

Una aplicación Android nativa moderna desarrollada en Kotlin que permite a los usuarios gestionar su rutina de fitness de manera integral. Los usuarios pueden registrarse, autenticarse y acceder a funcionalidades completas de seguimiento personal, incluyendo gestión de perfil de usuario, planificación nutricional con recetas personalizadas, y monitoreo de condiciones meteorológicas para optimizar sus entrenamientos.

## 📱 Características Principales

- ✅ **Sistema de Autenticación Completo**: Registro y login con validaciones en tiempo real
- 👤 **Gestión de Perfil**: Datos personales, físicos y preferencias del usuario
- 🍽️ **Planificación Nutricional**: Base de datos de recetas con filtros por tipo y calorías
- 🌤️ **Información Meteorológica**: Integración con API del clima para optimizar entrenamientos
- 🎨 **Interfaz Moderna**: Navigation drawer, temas adaptables y soporte multiidioma
- 🏗️ **Arquitectura Robusta**: MVVM con Repository Pattern y Retrofit para APIs
- 💾 **Persistencia Local**: SharedPreferences para configuraciones y sesiones
- ✅ **Validaciones Avanzadas**: Verificación de emails/usernames únicos en tiempo real

## 🛠️ Tecnologías Utilizadas

### Frontend (Android)
- **Lenguaje**: Kotlin
- **Arquitectura**: MVVM (Model-View-ViewModel)
- **Patrón**: Repository Pattern
- **Navegación**: Navigation Component
- **Networking**: Retrofit + OkHttp
- **UI**: Material Design Components
- **Inyección de Dependencias**: Manual (sin librerías externas)
- **Base de Datos Local**: SharedPreferences
- **Carga de Imágenes**: Glide

### Backend (Spring Boot)
- **Lenguaje**: Java
- **Framework**: Spring Boot
- **Base de Datos**: MySQL
- **ORM**: JPA/Hibernate
- **Documentación**: Swagger/OpenAPI
- **Seguridad**: CORS configurado

## 📁 Estructura del Proyecto

```
Gym_App/
├── app/                          # Aplicación Android
│   ├── src/main/
│   │   ├── java/com/cristian/appgym/
│   │   │   ├── model/            # Modelos de datos
│   │   │   │   ├── model_db/     # Modelos de base de datos
│   │   │   │   ├── model_receta/ # Modelos de recetas
│   │   │   │   ├── model_weather/# Modelos del clima
│   │   │   │   └── model_ejercicio/ # Modelos de ejercicios
│   │   │   ├── network/          # Capa de red
│   │   │   │   ├── ApiService.kt # Interfaz de API
│   │   │   │   └── RetrofitClient.kt # Cliente HTTP
│   │   │   ├── repository/       # Repositorios
│   │   │   │   ├── UserRepository.kt
│   │   │   │   └── RecipeRepository.kt
│   │   │   ├── ui/               # Interfaz de usuario
│   │   │   │   ├── fragments/    # Fragmentos de pantalla
│   │   │   │   ├── adapters/     # Adaptadores para RecyclerViews
│   │   │   │   ├── auth/         # Autenticación
│   │   │   │   └── viewmodel/    # ViewModels
│   │   │   ├── viewmodel/        # ViewModels principales
│   │   │   ├── util/             # Utilidades de interfaz
│   │   │   ├── utils/            # Utilidades del sistema
│   │   │   ├── BaseActivity.kt   # Actividad base
│   │   │   └── MyApplication.kt  # Clase de aplicación
│   │   └── res/                  # Recursos (layouts, drawables, etc.)
├── backend/                      # Backend Spring Boot
│   └── Spring_app_gym/
│       ├── src/main/java/
│       │   └── com/backend/App_gym/
│       │       ├── controller/   # Controladores REST
│       │       ├── entity/       # Entidades JPA
│       │       ├── repository/   # Repositorios JPA
│       │       ├── dto/          # Data Transfer Objects
│       │       └── config/       # Configuraciones
│       └── pom.xml               # Dependencias Maven
└── create_tables.sql             # Script de base de datos
```

## 🚀 Cómo Ejecutar el Proyecto

### Prerrequisitos
- Android Studio (versión recomendada: Arctic Fox o superior)
- JDK 11 o superior
- MySQL Server
- Maven (para el backend)

### 1. Configurar la Base de Datos
```sql
-- Ejecutar el script create_tables.sql en MySQL
mysql -u root -p < create_tables.sql
```

### 2. Configurar el Backend
```bash
cd backend/Spring_app_gym
mvn clean install
mvn spring-boot:run
```

El backend estará disponible en: `http://localhost:8080`

### 3. Configurar el Frontend
1. Abrir Android Studio
2. Abrir el proyecto `Gym_App`
3. Configurar la URL del backend en `RetrofitClient.kt`:
```kotlin
private const val BASE_URL = "http://TU_IP_LOCAL:8080/"
```
4. Sincronizar el proyecto con Gradle
5. Ejecutar en un emulador o dispositivo físico

## 📱 Pantallas de la Aplicación

### Pantalla de Inicio
- Botones de Login y Registro
- Navegación principal

### Autenticación
- **Login**: Inicio de sesión por email o username
- **Registro**: Creación de cuenta con validaciones

### Funcionalidades Principales
- **Perfil**: Gestión de datos personales y físicos
- **Recetas**: Lista de recetas nutricionales con filtros
- **Clima**: Información meteorológica para entrenamientos
- **Configuraciones**: Idioma, tema, preferencias

## 🔧 Configuraciones Importantes

### Cambiar URL del Backend
Editar en `app/src/main/java/com/cristian/appgym/network/RetrofitClient.kt`:
```kotlin
private const val BASE_URL = "http://TU_IP:8080/"
```

### Configurar Base de Datos
Editar en `backend/Spring_app_gym/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gym_app
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

## 🧪 Testing

### Frontend
- Tests unitarios en `app/src/test/`
- Tests de instrumentación en `app/src/androidTest/`

### Backend
- Tests unitarios en `backend/Spring_app_gym/src/test/`

## 📚 Conceptos Clave para Entender

### MVVM (Model-View-ViewModel)
- **Model**: Datos y lógica de negocio
- **View**: Interfaz de usuario (Fragments)
- **ViewModel**: Intermediario entre Model y View

### Repository Pattern
- Abstrae el acceso a datos
- Permite cambiar fuentes de datos fácilmente
- Centraliza la lógica de acceso a datos

### Retrofit
- Cliente HTTP para Android
- Convierte APIs REST en interfaces Java/Kotlin
- Maneja automáticamente JSON serialización/deserialización

## 🤝 Contribuir

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para detalles.

## 👨‍💻 Autor

**Cristian** - Desarrollador Android

## 🙏 Agradecimientos

- Material Design Components
- Retrofit para networking
- Spring Boot para el backend
- Comunidad de desarrolladores Android

---

⭐ **¡No olvides dar una estrella al proyecto si te fue útil!**
