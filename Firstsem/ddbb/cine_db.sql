DROP DATABASE IF EXISTS cine_db;
CREATE DATABASE cine_db;
USE cine_db;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE pelicula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    anio INT NOT NULL,
    genero VARCHAR(50) NOT NULL,
    duracion INT NOT NULL
);


INSERT INTO pelicula (titulo, anio, genero, duracion)
VALUES ('Inception', 2010, 'Ciencia Ficción', 148),
    ('Titanic', 1997, 'Drama', 195),
    ('El Padrino', 1972, 'Crimen', 175),
    ('Interestelar', 2014, 'Ciencia Ficción', 169),
    ('Parasite', 2019, 'Drama', 132),
    ('The Dark Knight', 2008, 'Acción', 152),
    ('Forrest Gump', 1994, 'Drama', 142),
    ('Avengers: Endgame', 2019, 'Acción', 181),
    ('The Matrix', 1999, 'Ciencia Ficción', 136),
    ('Gladiator', 2000, 'Acción', 155),
    ('Joker', 2019, 'Drama', 122),
    ('Oppenheimer', 2023, 'Drama', 180);


CREATE TABLE director (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    pais_origen VARCHAR(50) NOT NULL,
    anio_nacimiento INT NOT NULL
);


INSERT INTO director (nombre, pais_origen, anio_nacimiento)
VALUES ('Christopher Nolan', 'Reino Unido', 1970),
    ('James Cameron', 'Canadá', 1954),
    ('Francis Ford Coppola', 'EE.UU.', 1939),
    ('Bong Joon-ho', 'Corea del Sur', 1969),
    ('Ridley Scott', 'Reino Unido', 1937),
    ('The Wachowskis', 'EE.UU.', 1965),
    ('Robert Zemeckis', 'EE.UU.', 1952),
    ('Anthony Russo', 'EE.UU.', 1970),
    ('Steven Spielberg', 'EE.UU.', 1946),
    ('Todd Phillips', 'EE.UU.', 1970);
CREATE TABLE actor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    pais_origen VARCHAR(50) NOT NULL,
    anio_nacimiento INT NOT NULL
);
INSERT INTO actor (nombre, pais_origen, anio_nacimiento)
VALUES ('Leonardo DiCaprio', 'EE.UU.', 1974),
    ('Kate Winslet', 'Reino Unido', 1975),
    ('Al Pacino', 'EE.UU.', 1940),
    ('Matthew McConaughey', 'EE.UU.', 1969),
    ('Song Kang-ho', 'Corea del Sur', 1967),
    ('Christian Bale', 'Reino Unido', 1974),
    ('Tom Hanks', 'EE.UU.', 1956),
    ('Keanu Reeves', 'Canadá', 1964),
    ('Russell Crowe', 'Nueva Zelanda', 1964),
    ('Scarlett Johansson', 'EE.UU.', 1984),
    ('Joaquin Phoenix', 'EE.UU.', 1974),
    ('Cillian Murphy', 'Irlanda', 1976),
    ('Marlon Brando', 'EE.UU.', 1924);
CREATE TABLE reparto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pelicula INT NOT NULL,
    id_actor INT NOT NULL,
    FOREIGN KEY (id_pelicula) REFERENCES pelicula(id),
    FOREIGN KEY (id_actor) REFERENCES actor(id)
);
INSERT INTO reparto (id_pelicula, id_actor)
VALUES -- Inception
    (1, 1),
    -- Titanic
    (2, 1),
    (2, 2),
    -- El Padrino
    (3, 3),
    (3, 13),
    -- Interestelar
    (4, 4),
    -- Parasite
    (5, 5),
    -- The Dark Knight
    (6, 6),
    -- Forrest Gump
    (7, 7),
    -- Avengers Endgame
    (8, 10),
    -- Matrix
    (9, 8),
    -- Gladiator
    (10, 9),
    -- Joker
    (11, 11),
    -- Oppenheimer
    (12, 12);



