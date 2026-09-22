-- elimina la base de datos si ya existe
drop database if exists techcorp_db;

-- crea la base de datos
create database techcorp_db;
use techcorp_db;

-- tabla empleados
-- crea la tabla empleados con las columnas:
-- id (entero, clave primaria)
-- nombre (nombre del empleado)
-- edad (edad del empleado)
-- salario (salario mensual del empleado)
-- departamento (departamento donde trabaja)
-- ciudad (ciudad donde trabaja)
create table empleados (
    id int primary key,
    nombre varchar(50),
    edad int,
    salario decimal(10, 2),
    departamento varchar(50),
    ciudad varchar(50)
);

-- inserta registros de ejemplo en la tabla empleados
-- datos de empleados con diferentes departamentos, ciudades y salarios
insert into empleados values
(1, 'laura', 35, 4500, 'it', 'madrid'),
(2, 'carlos', 28, 3200, 'ventas', 'barcelona'),
(3, 'ana', 45, 6000, 'marketing', 'madrid'),
(4, 'juan', 50, 5200, 'recursos humanos', 'sevilla'),
(5, 'luis', 29, 3400, 'it', 'madrid'),
(6, 'marta', 33, 5800, 'desarrollo', 'valencia');

-- tabla clientes
-- crea la tabla clientes con las columnas:
-- id (entero, clave primaria)
-- nombre (nombre del cliente)
-- ciudad (ciudad donde reside el cliente)
create table clientes (
    id int primary key,
    nombre varchar(50),
    ciudad varchar(50)
);

-- inserta registros de ejemplo en la tabla clientes
-- datos de clientes con diferentes ciudades
insert into clientes values
(1, 'tech solutions', 'madrid'),
(2, 'innovate inc', 'barcelona'),
(3, 'nextgen ltd', 'valencia'),
(4, 'futureworks', 'madrid'),
(5, 'digitalsoft', 'sevilla');

use techcorp_db;

-- ejercicios para realizar consultas sql
-- 1. recupera los nombres y salarios de todos los empleados.


-- 2. selecciona todos los datos de la tabla empleados.


-- 3. recupera los datos de los empleados que trabajan en el departamento de it y cuya ciudad sea madrid.


-- 4. selecciona todos los empleados con un salario mayor a 3000 pero menor o igual a 6000.


-- 5. muestra una lista única de todas las ciudades en las que trabajan los empleados.


-- 6. encuentra todos los empleados que trabajan en ventas o marketing y tienen un salario mayor a 5000.


-- 7. recupera todos los empleados que no trabajan en el departamento de recursos humanos.

-- 8. encuentra todos los clientes que residen en barcelona.


-- 9. muestra una lista única de nombres de departamentos donde hay empleados menores de 30 años que ganan más de 4000.


-- 10. recupera los empleados que tienen un salario mayor o igual a 3500 y están en el departamento desarrollo.


Concatenar nombre y apellido como nombre_completo
SELECT 
  CONCAT(nombre, ' ', apellido) AS nombre_completo
FROM Ciudadano;

 Primeras 3 letras del nombre
SELECT 
  nombre,
  SUBSTRING(nombre, 1, 3) AS primeras_3_letras
FROM Ciudadano;

 Longitud del nombre
SELECT 
  nombre,
  LENGTH(nombre) AS longitud_nombre
FROM Ciudadano;

 Reemplazar “hospitales” por “clínicas”
SELECT 
  descripcion,
  REPLACE(descripcion, 'hospitales', 'clínicas') AS descripcion_modificada
FROM Actividad;

 Eliminar espacios al inicio y final del nombre del ministerio
SELECT 
  TRIM(nombre) AS nombre_limpio
FROM Ministerio;

Funciones NUMÉRICAS
 Redondear presupuesto de actividades a millones
SELECT 
  descripcion,
  ROUND(presupuesto_asignado / 1000000, 0) AS presupuesto_millones
FROM Actividad;

 Diferencia entre ingresos y el promedio
SELECT 
  nombre,
  apellido,
  ingresos_anuales,
  ingresos_anuales - (SELECT AVG(ingresos_anuales) FROM Ciudadano) AS diferencia_promedio
FROM Ciudadano;

 Entero hacia abajo y hacia arriba del presupuesto
SELECT 
  nombre,
  FLOOR(presupuesto) AS entero_abajo,
  CEILING(presupuesto) AS entero_arriba
FROM Ministerio;

 Número aleatorio para cada actividad
SELECT 
  id,
  descripcion,
  RAND() AS identificador_temporal
FROM Actividad;

Funciones de FECHA y HORA
 Antigüedad de cada ministerio en años
SELECT 
  TRIM(nombre) AS ministerio,
  TIMESTAMPDIFF(YEAR, fecha_creacion, CURDATE()) AS antiguedad_anios
FROM Ministerio;

 Formatear fechas (DD-MM-YYYY) ⭐
SELECT 
  descripcion,
  DATE_FORMAT(fecha_inicio, '%d-%m-%Y') AS inicio,
  DATE_FORMAT(fecha_fin, '%d-%m-%Y') AS fin
FROM Actividad;

1 Días restantes para que termine cada actividad
SELECT 
  descripcion,
  DATEDIFF(fecha_fin, CURDATE()) AS dias_restantes
FROM Actividad;

1Extraer mes y año de nacimiento
SELECT 
  nombre,
  apellido,
  MONTH(fecha_nacimiento) AS mes_nacimiento,
  YEAR(fecha_nacimiento) AS anio_nacimiento
FROM Ciudadano;

 Ciudadanos nacidos después de 1990 ⭐
SELECT *
FROM Ciudadano
WHERE YEAR(fecha_nacimiento) > 1990;

1 Duración total de cada actividad (en días)
SELECT 
  descripcion,
  DATEDIFF(fecha_fin, fecha_inicio) AS duracion_dias
FROM Actividad;