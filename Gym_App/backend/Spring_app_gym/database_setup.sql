-- Script de configuración de base de datos para App Gym
-- Base de datos: PostgreSQL

-- Crear tabla de usuarios
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    fecha_nacimiento DATE,
    sexo VARCHAR(10)
);

-- Crear tabla de datos físicos
CREATE TABLE datos_fisicos (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL,
    altura_cm INTEGER, -- altura en cm
    peso_kg NUMERIC(5,2), -- peso en kg
    frecuencia_actividad TEXT, -- ejemplo: "Baja", "Moderada", "Alta"
    dias_entrenamiento_por_semana INTEGER,
    problemas_salud TEXT,
    horario_preferido TEXT, -- ejemplo: "Mañana", "Tarde", "Noche"
    motivacion TEXT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Insertar algunos datos de ejemplo
INSERT INTO usuarios (nombre, apellidos, email, username, password, fecha_nacimiento, sexo) VALUES
('Juan', 'Pérez García', 'juan.perez@email.com', 'juanperez', 'password123', '1990-05-15', 'Masculino'),
('María', 'López Rodríguez', 'maria.lopez@email.com', 'marialopez', 'password123', '1988-12-03', 'Femenino'),
('Carlos', 'González Martín', 'carlos.gonzalez@email.com', 'carlosgonz', 'password123', '1995-08-22', 'Masculino');

-- Insertar datos físicos de ejemplo
INSERT INTO datos_fisicos (usuario_id, altura_cm, peso_kg, frecuencia_actividad, dias_entrenamiento_por_semana, problemas_salud, horario_preferido, motivacion) VALUES
(1, 175, 70.5, 'Moderada', 3, 'Ninguno', 'Tarde', 'Mantener buena forma física'),
(2, 162, 58.2, 'Alta', 5, 'Problemas de espalda leves', 'Mañana', 'Perder peso y tonificar'),
(3, 180, 85.0, 'Baja', 2, 'Ninguno', 'Noche', 'Ganar masa muscular'); 