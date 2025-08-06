-- =====================================================
-- SCRIPT PARA CREAR LAS TABLAS DE LA BASE DE DATOS
-- =====================================================

-- Crear tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
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
CREATE TABLE IF NOT EXISTS datos_fisicos (
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

-- Crear tabla de recetas
CREATE TABLE IF NOT EXISTS recetas (
    id SERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    image_url TEXT,
    calories INTEGER,
    protein INTEGER,
    carbs INTEGER,
    fat INTEGER,
    type VARCHAR(50), -- ejemplo: "Desayuno", "Almuerzo", "Cena", etc.
    ingredients TEXT[], -- arreglo de texto
    instructions TEXT
);

-- Crear tabla user_data (si la necesitas)
CREATE TABLE IF NOT EXISTS user_data (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER,
    size INTEGER,
    weight INTEGER,
    actividad_fisica TEXT,
    dias_entrenar TEXT,
    problemas_salud TEXT,
    preferencia_horario TEXT,
    motivacion TEXT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- =====================================================
-- DATOS DE EJEMPLO (OPCIONAL)
-- =====================================================

-- Insertar usuario de prueba
INSERT INTO usuarios (nombre, apellidos, email, username, password, fecha_nacimiento, sexo) VALUES
('Cristian', 'López', 'cristian@example.com', 'cristian', 'password123', '1995-08-10', 'Masculino')
ON CONFLICT (username) DO NOTHING;

-- Insertar datos físicos de ejemplo
INSERT INTO datos_fisicos (usuario_id, altura_cm, peso_kg, frecuencia_actividad, dias_entrenamiento_por_semana, problemas_salud, horario_preferido, motivacion) VALUES
(1, 175, 70.5, 'Moderada', 4, 'Ninguno', 'Tarde', 'Ganar músculo')
ON CONFLICT DO NOTHING;

-- Insertar recetas de ejemplo
INSERT INTO recetas (title, image_url, calories, protein, carbs, fat, type, ingredients, instructions) VALUES
('Panqueques de avena y plátano', 'https://example.com/avena-platano.jpg', 320, 15, 40, 10, 'Desayuno', ARRAY['1 plátano maduro', '2 huevos', '1/2 taza de avena'], 'Tritura todos los ingredientes y cocina en una sartén antiadherente.'),
('Ensalada de pollo con aguacate', 'https://example.com/ensalada-pollo.jpg', 450, 35, 15, 25, 'Almuerzo', ARRAY['150g de pechuga de pollo', '1/2 aguacate', '2 tazas de lechuga'], 'Cocina el pollo a la plancha. Corta los vegetales y mezcla todo.')
ON CONFLICT DO NOTHING;

-- =====================================================
-- VERIFICACIÓN
-- =====================================================

-- Verificar que las tablas se crearon correctamente
SELECT 'Tablas creadas:' as info;
SELECT table_name FROM information_schema.tables WHERE table_schema = 'public' AND table_name IN ('usuarios', 'datos_fisicos', 'recetas', 'user_data');

-- Verificar datos de ejemplo
SELECT 'Usuarios:' as info, COUNT(*) as cantidad FROM usuarios;
SELECT 'Datos físicos:' as info, COUNT(*) as cantidad FROM datos_fisicos;
SELECT 'Recetas:' as info, COUNT(*) as cantidad FROM recetas; 