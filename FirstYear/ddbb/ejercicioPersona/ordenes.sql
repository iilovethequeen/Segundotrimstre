DROP DATABASE IF EXISTS andalucia_db;
CREATE DATABASE IF NOT EXISTS andalucia_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE andalucia_db;

-- Crear la tabla personas con estructura adecuada
CREATE TABLE personas (
    id INT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    sexo ENUM('M', 'H') NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    provincia VARCHAR(100) NOT NULL,
    municipio VARCHAR(150) NOT NULL,
    nivel_estudios VARCHAR(50),
    situacion_laboral VARCHAR(50),
    ingresos_anuales INT,
    estado_civil VARCHAR(20),
    nivel_socioeconomico VARCHAR(20),
    ocupacion VARCHAR(50)
);

alter table personas add column madre INT;
alter table personas add column padre INT;
alter table personas add foreign key (padre) references personas(id);
alter table personas add foreign key (madre) references personas(id);

SET FOREIGN_KEY_CHECKS=0;

LOAD DATA INFILE 'ddbb\ejercicioPersona\andalucia.csv' INTO TABLE personas FIELDS TERMINATED BY ','  ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 ROWS;

SET FOREIGN_KEY_CHECKS=1;













Very likely — simple queries
These are easy to ask and test if you understand SQL:

Total revenue per cinema

sqlSELECT c.nombre, SUM(e.precio) AS ingresos_totales
FROM Entrada e
JOIN Proyeccion pr ON pr.id = e.proyeccion_id
JOIN Sala s ON s.id = pr.sala_id
JOIN Cine c ON c.id = s.cine_id
GROUP BY c.id, c.nombre

Most popular movie this month (most tickets sold)
Average ticket price per cinema
How many disability tickets were sold vs normal tickets
List all screenings with available seats


Likely — modify existing things

Add a new statistic to the bash script — he gives you a query and asks how you would add it. Answer: just call run_stat "name" "SELECT..." at the bottom
Change the cron schedule — "how would you make it run daily instead of weekly?" Answer: 0 8 * * *
Add a cancellation procedure — similar to comprar_entrada but for deleting a ticket


Possible — new features

Add a price discount for disabilities automatically — a trigger that reduces price by X% if es_minusvalido = TRUE
Add a Cliente (customer) table — and link it to Entrada
Prevent buying tickets for past screenings — add a check in the trigger or procedure:

sqlIF NOW() > v_fecha_hora THEN
    SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se pueden comprar entradas para proyecciones pasadas';
END IF;

Less likely but possible — theory questions

"What is a stored procedure and why did you use one instead of just running the SQL directly?"
"What is the difference between a trigger and a stored procedure?"
"Why did you use DECIMAL(10,2) for the price?"
"What does ON DELETE CASCADE do in your schema?"
"What is a PRIMARY KEY and why is it important?"
"What does UNIQUE KEY uq_asiento_proyeccion do?"


The ones you should prepare answers for RIGHT NOW
These three are the most dangerous because they sound simple but need a good explanation:
1. "What is the difference between a trigger and a stored procedure?"

A trigger fires automatically when something happens to a table. A stored procedure is called manually. You cannot call a trigger directly.

2. "What does ON DELETE CASCADE do?"

If you delete a Cinema, all its Rooms get deleted automatically. If you delete a Room, all its Screenings get deleted. It keeps the database clean with no orphan records.

3. "What does UNIQUE KEY uq_asiento_proyeccion do?"

It makes sure the same seat number cannot be sold twice for the same screening. It is a database-level guarantee on top of the trigger logic.













Step 1 — Open terminal and connect to MySQL
bashmysql -u root -p
It will ask for your password, type it and press Enter.

Step 2 — Run your SQL file
Once you're inside MySQL:
sqlSOURCE /path/to/CineDB.sql;
Or you can do it directly from terminal without entering MySQL first:
bashmysql -u root -p < /path/to/CineDB.sql

Step 3 — Run the stored procedure file
bashmysql -u root -p < /path/to/comprar_entrada.sql

Step 4 — Test buying a ticket
Connect to MySQL and run:
sqlUSE CineDB;

CALL comprar_entrada('El Laberinto del Fauno', 'Cinepolis', '2025-06-01 18:00:00', 8.50, 0);

Step 5 — Run the stats script
First give it permission to execute:
bashchmod +x stats_semanales.sh
Then run it:
bash./stats_semanales.sh

Useful commands once inside MySQL
sqlSHOW DATABASES;        -- see all databases
USE CineDB;            -- select your database
SHOW TABLES;           -- see all tables
SELECT * FROM Cine;    -- see data in a table
SHOW TRIGGERS;