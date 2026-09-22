
-- Ejercicios de MySQL - Base de Datos de Películas (Extendida)
drop database cine_db;
CREATE DATABASE if not exists cine_db;
USE cine_db;

-- Tabla pelicula
CREATE TABLE pelicula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    anio INT NOT NULL,
    genero VARCHAR(50) NOT NULL,
    duracion INT NOT NULL
);

-- Tabla director
CREATE TABLE director (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    pais_origen VARCHAR(50) NOT NULL,
    anio_nacimiento INT NOT NULL
);

-- Tabla actor
CREATE TABLE actor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    pais_origen VARCHAR(50) NOT NULL,
    anio_nacimiento INT NOT NULL
);

-- Tabla reparto
CREATE TABLE reparto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pelicula INT NOT NULL,
    id_actor INT NOT NULL,
    FOREIGN KEY (id_pelicula) REFERENCES pelicula(id),
    FOREIGN KEY (id_actor) REFERENCES actor(id)
);

-- Inserciones en la tabla pelicula
INSERT INTO pelicula (titulo, anio, genero, duracion) VALUES
('Inception', 2010, 'Ciencia Ficción', 148),
('Titanic', 1997, 'Drama', 195),
('El Padrino', 1972, 'Crimen', 175),
('Interestelar', 2014, 'Ciencia Ficción', 169),
('Parasite', 2019, 'Drama', 132),
('The Dark Knight', 2008, 'Acción', 152),
('Forrest Gump', 1994, 'Drama', 142),
('Avengers: Endgame', 2019, 'Acción', 181),
('The Matrix', 1999, 'Ciencia Ficción', 136),
('Gladiator', 2000, 'Acción', 155);

-- Inserciones en la tabla director
INSERT INTO director (nombre, pais_origen, anio_nacimiento) VALUES
('Christopher Nolan', 'Reino Unido', 1970),
('James Cameron', 'Canadá', 1954),
('Francis Ford Coppola', 'EE.UU.', 1939),
('Bong Joon-ho', 'Corea del Sur', 1969),
('Ridley Scott', 'Reino Unido', 1937),
('The Wachowskis', 'EE.UU.', 1965),
('Robert Zemeckis', 'EE.UU.', 1952),
('Anthony Russo', 'EE.UU.', 1970),
('Joe Russo', 'EE.UU.', 1971),
('Steven Spielberg', 'EE.UU.', 1946);

-- Inserciones en la tabla actor
INSERT INTO actor (nombre, pais_origen, anio_nacimiento) VALUES
('Leonardo DiCaprio', 'EE.UU.', 1974),
('Kate Winslet', 'Reino Unido', 1975),
('Al Pacino', 'EE.UU.', 1940),
('Matthew McConaughey', 'EE.UU.', 1969),
('Song Kang-ho', 'Corea del Sur', 1967),
('Christian Bale', 'Reino Unido', 1974),
('Tom Hanks', 'EE.UU.', 1956),
('Keanu Reeves', 'Canadá', 1964),
('Russell Crowe', 'Nueva Zelanda', 1964),
('Scarlett Johansson', 'EE.UU.', 1984);

-- Inserciones en la tabla reparto
INSERT INTO reparto (id_pelicula, id_actor) VALUES
(1, 1), -- Inception - Leonardo DiCaprio
(2, 1), -- Titanic - Leonardo DiCaprio
(2, 2), -- Titanic - Kate Winslet
(3, 3), -- El Padrino - Al Pacino
(4, 4), -- Interestelar - Matthew McConaughey
(5, 5), -- Parasite - Song Kang-ho
(6, 6), -- The Dark Knight - Christian Bale
(7, 7), -- Forrest Gump - Tom Hanks
(8, 10), -- Avengers: Endgame - Scarlett Johansson
(9, 8), -- The Matrix - Keanu Reeves
(10, 9); -- Gladiator - Russell Crowe

-- Ejercicios (Ampliados)
-- 1. Listar todos los títulos de las películas.

-- 2. Mostrar los géneros de las películas sin repetir.


-- 3. Seleccionar el título y año de todas las películas estrenadas después del año 2000.


-- 4. Listar los nombres de todos los directores y sus países de origen.


-- 5. Mostrar los títulos de las películas con duración mayor a 150 minutos.

-- 6. Seleccionar el título de las películas del género 'Drama'.


-- 7. Mostrar los id de los actores que participan en la película 'Titanic'.
-- Enunciado: Mostrar los id de los actores asociados con el id de 'Titanic'.



-- 8. Contar cuántas películas hay en cada género.

-- 9. Mostrar los directores nacidos después de 1950.


-- 10. Listar los títulos de las películas y su duración ordenados de mayor a menor.


-- 12. Seleccionar las películas cuya duración está entre 120 y 180 minutos.


-- 13. Mostrar los títulos de las películas ordenados por año de estreno.


-- 14. Calcular el promedio de duración de las películas.

-- 15. Mostrar los actores que no nacieron en EE.UU.


-- 17. Mostrar el id de las películas que tienen más de un actor en su reparto.


-- 18. Calcular el número total de actores en la base de datos.


-- 19. Mostrar las películas que no tienen actores en el reparto.

-- 20. Seleccionar las películas estrenadas en los últimos 10 años.

-- 22 dime la moda del año de nacimiento de los actores.

-- 23 dime la media de edad de los actores agrupados por pais de nacimiento.














SELECT nombre, apellido
FROM personas
WHERE provincia = 'Málaga' AND sexo = 'M'
ORDER BY edad DESC
LIMIT 5;







SELECT MAX(ingresos_anuales)
FROM personas
WHERE estudios = 'Primarios';


SELECT AVG(edad)
FROM personas
WHERE ocupacion LIKE 'A%';


SELECT SUBSTRING(estado_civil, 1, 3)
FROM personas
WHERE municipio = 'Dos Hermanas';

SELECT COUNT(*)
FROM personas p
LEFT JOIN personas padre ON p.padre_id = padre.id
LEFT JOIN personas madre ON p.madre_id = madre.id
WHERE padre.sexo = 'M'
   OR madre.sexo = 'H';


SELECT estado_civil
FROM personas
GROUP BY estado_civil
ORDER BY COUNT(*) DESC
LIMIT 1;


SELECT COUNT(DISTINCT municipio)
FROM personas
WHERE provincia = 'Cádiz';


SELECT abuelos.*
FROM personas p
JOIN personas padre ON p.padre_id = padre.id
JOIN personas abuelo1 ON padre.padre_id = abuelo1.id
JOIN personas abuelo2 ON padre.madre_id = abuelo2.id
JOIN personas madre ON p.madre_id = madre.id
JOIN personas abuelo3 ON madre.padre_id = abuelo3.id
JOIN personas abuelo4 ON madre.madre_id = abuelo4.id
WHERE p.id = 1442;


SELECT n.*
FROM personas p
JOIN personas h ON h.padre_id = p.id OR h.madre_id = p.id
JOIN personas n ON n.padre_id = h.id OR n.madre_id = h.id
WHERE p.id = 1093;


SELECT sexo, AVG(edad)
FROM personas
GROUP BY sexo;


SELECT provincia
FROM personas
ORDER BY LENGTH(ocupacion)
LIMIT 5;


SELECT provincia
FROM personas
GROUP BY provincia
ORDER BY AVG(ingresos_anuales)
LIMIT 1;



SELECT provincia
FROM personas
WHERE ocupacion = 'Jubilado'
GROUP BY provincia
ORDER BY AVG(ingresos_anuales)
LIMIT 1;


SELECT municipio, AVG(ingresos_anuales)
FROM personas
WHERE cobra_renta = 0
GROUP BY municipio;


SELECT AVG(p.edad)
FROM personas p
WHERE EXISTS (
  SELECT 1
  FROM personas h
  WHERE (h.padre_id = p.id OR h.madre_id = p.id)
    AND h.nombre LIKE 'L%'
);


SELECT AVG(h.edad)
FROM personas p
JOIN personas h
  ON (h.padre_id = p.padre_id OR h.madre_id = p.madre_id)
WHERE p.municipio = 'Málaga'
  AND h.id <> p.id;
