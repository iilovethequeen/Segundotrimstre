-- Crear la base de datos
DROP DATABASE IF EXISTS Gobierno;
CREATE DATABASE Gobierno;
USE Gobierno;

-- Tabla 1: Ministerio
CREATE TABLE Ministerio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    presupuesto DECIMAL(15, 2) NOT NULL,
    fecha_creacion DATE NOT NULL
);

-- Tabla 2: Ciudadano
CREATE TABLE Ciudadano (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    ciudad VARCHAR(50),
    ocupacion VARCHAR(50),
    ingresos_anuales DECIMAL(10, 2)
);

-- Tabla 3: Actividad
CREATE TABLE Actividad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    id_ministerio INT,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    presupuesto_asignado DECIMAL(15, 2),
    FOREIGN KEY (id_ministerio) REFERENCES Ministerio(id)
);

-- Insertar datos en Ministerio
INSERT INTO Ministerio (nombre, presupuesto, fecha_creacion)
VALUES
('           Ministerio de Educación', 150000000.00, '1990-01-15'),
('Ministerio de Salud', 250000000.00, '1985-06-30'),
('           Ministerio de Transporte', 120000000.00, '2000-03-12');

-- Insertar datos en Ciudadano
INSERT INTO Ciudadano (nombre, apellido, fecha_nacimiento, ciudad, ocupacion, ingresos_anuales)
VALUES
('Juan', 'Pérez', '1980-02-14', 'Madrid', 'Profesor', 35000.00),
('Laura', 'Gómez', '1992-07-21', 'Barcelona', 'Médico', 45000.00),
('Carlos', 'Sánchez', '1975-09-10', 'Sevilla', 'Ingeniero', 60000.00),
('Ana', 'Fernández', '1985-03-25', 'Bilbao', 'Abogada', 55000.00),
('Mario', 'López', '1990-05-05', 'Valencia', 'Desempleado', 0.00);

-- Insertar datos en Actividad
INSERT INTO Actividad (descripcion, id_ministerio, fecha_inicio, fecha_fin, presupuesto_asignado)
VALUES
('Construcción de hospitales', 2, '2023-01-01', '2025-12-31', 50000000.00),
('Reparación de carreteras', 3, '2023-04-01', '2025-09-30', 30000000.00),
('Capacitación docente', 1, '2023-06-01', '2025-11-30', 10000000.00);

-- Ejercicios de Funciones de Texto
-- 1. Concatenar nombres y apellidos de los ciudadanos en un único campo "nombre_completo".
SELECT 
  CONCAT(nombre, ' ', apellido) AS nombre_completo
FROM Ciudadano;
-- 2. Obtener las primeras 3 letras de los nombres de los ciudadanos.
SELECT 
  nombre,
  SUBSTRING(nombre, 1, 3) AS primeras_3_letras
FROM Ciudadano;

-- 3. Calcular la longitud de los nombres de los ciudadanos.
 Longitud del nombre
SELECT 
  nombre,
  LENGTH(nombre) AS longitud_nombre
FROM Ciudadano;

-- 4. Reemplazar la palabra "hospitales" por "clínicas" en las descripciones de actividades.
SELECT 
  descripcion,
  REPLACE(descripcion, 'hospitales', 'clínicas') AS descripcion_modificada
FROM Actividad;

-- 5. Eliminar espacios al inicio y al final de los nombres de los ministerios.


 Eliminar espacios al inicio y final del nombre del ministerio
SELECT 
  TRIM(nombre) AS nombre_limpio
FROM Ministerio;
-- Ejercicios de Funciones NuEXTRACTméricas
-- 6. Redondear el presupuesto de las actividades a millones.

-- 7. Calcular la diferencia entre los ingresos anuales de cada ciudadano y el promedio de ingresos.

-- 8. Obtener el entero más cercano hacia abajo y hacia arriba de los presupuestos de los ministerios.

-- 9. Generar un número aleatorio para asignar un identificador único temporal a cada actividad.

-- Ejercicios de Funciones de Fecha y Hora
-- 10. Calcular la antigüedad de cada ministerio en años.

-- Para mi esta es la mejor:

-- 11. Formatear las fechas de inicio y fin de las actividades en formato "DD-MM-YYYY".

-- 12. Calcular cuántos días faltan para que termine cada actividad.

-- 13. Extraer el mes y el año de las fechas de nacimiento de los ciudadanos.

-- Para mi la mejor es:

-- 14. Filtrar ciudadanos nacidos después del año 1990.


-- 15. Calcular el tiempo total en días que dura cada actividad gubernamental.