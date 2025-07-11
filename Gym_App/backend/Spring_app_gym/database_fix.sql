-- Script para arreglar la estructura de la base de datos
-- Ejecutar este script en pgAdmin4 para actualizar las tablas existentes

-- 1. Verificar la estructura actual
SELECT column_name, data_type, is_nullable 
FROM information_schema.columns 
WHERE table_name = 'usuarios';

-- 2. Si existe la columna 'date', la eliminamos y creamos 'fecha_nacimiento'
ALTER TABLE usuarios DROP COLUMN IF EXISTS "date";
ALTER TABLE usuarios DROP COLUMN IF EXISTS "name";
ALTER TABLE usuarios DROP COLUMN IF EXISTS "lastname";
ALTER TABLE usuarios DROP COLUMN IF EXISTS "sex";

-- 3. Agregar las nuevas columnas si no existen
ALTER TABLE usuarios ADD COLUMN IF NOT EXISTS nombre VARCHAR(50);
ALTER TABLE usuarios ADD COLUMN IF NOT EXISTS apellidos VARCHAR(100);
ALTER TABLE usuarios ADD COLUMN IF NOT EXISTS fecha_nacimiento DATE;
ALTER TABLE usuarios ADD COLUMN IF NOT EXISTS sexo VARCHAR(10);

-- 4. Hacer las columnas obligatorias
ALTER TABLE usuarios ALTER COLUMN nombre SET NOT NULL;
ALTER TABLE usuarios ALTER COLUMN apellidos SET NOT NULL;

-- 5. Verificar la estructura final
SELECT column_name, data_type, is_nullable 
FROM information_schema.columns 
WHERE table_name = 'usuarios'
ORDER BY ordinal_position; 