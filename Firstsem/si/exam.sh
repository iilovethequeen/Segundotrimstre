#Crea un script de bash con las siguientes 10 funciones:

#4. Función multiplicar
#Crea una función que multiplique dos números pasados como argumentos.

#Uso: multiplicar 7 8


multiplicar() {
    validar_argumentos 2 $@ || return 1
    
    resultado=$(( $1 * $2 ))
    echo $resultado
}



#Salida esperada: 56

#5. Función potencia
#Crea una función que eleve un número a una potencia. El primer argumento es la base y el segundo el exponente.

#Uso: potencia 2 5

#Salida esperada: 32

#7. Función volumen_cubo
#Crea una función que calcule el volumen de un cubo recibiendo el lado como argumento. Formula: V = lado³

#Uso: volumen_cubo 4

#Salida esperada: 64





-- ============================================
-- EJERCICIO 1: Crear usuarios
-- ============================================

CREATE USER 'fallero_local'@'localhost' IDENTIFIED BY 'fallas2025';

CREATE USER 'organizadora_remota'@'%' IDENTIFIED BY 'paellas123';

CREATE USER 'control_oficina'@'192.168.20.%' IDENTIFIED BY 'seguridadfalla';


-- ============================================
-- EJERCICIO 2: Mostrar todos los usuarios
-- ============================================

SELECT user, host FROM mysql.user;


-- ============================================
-- EJERCICIO 3: Privilegios y roles
-- ============================================

-- 1. Todos los privilegios a fallero_local sobre 'fallas'
GRANT ALL PRIVILEGES ON fallas.* TO 'fallero_local'@'localhost';

-- 2. Solo lectura a organizadora_remota sobre la tabla eventos
GRANT SELECT ON fallas.eventos TO 'organizadora_remota'@'%';

-- 3. Crear rol coordinador_fallas con SELECT, INSERT, UPDATE
CREATE ROLE 'coordinador_fallas';
GRANT SELECT, INSERT, UPDATE ON fallas.* TO 'coordinador_fallas';

-- Asignarlo a control_oficina y configurarlo como rol predeterminado
GRANT 'coordinador_fallas' TO 'control_oficina'@'192.168.20.%';
SET DEFAULT ROLE 'coordinador_fallas' TO 'control_oficina'@'192.168.20.%';


-- ============================================
-- EJERCICIO 4
-- ============================================

-- 1. Mostrar privilegios de organizadora_remota
SHOW GRANTS FOR 'organizadora_remota'@'%';

-- 2. Revocar permiso UPDATE sobre todas las tablas de 'fallas' a control_oficina
REVOKE UPDATE ON fallas.* FROM 'control_oficina'@'192.168.20.%';

-- 3. Aplicar cambios
FLUSH PRIVILEGES;


-- ============================================
-- EJERCICIO 5: Índices
-- ============================================

-- 1. Índice sobre columna 'nombre' de la tabla eventos
CREATE INDEX idx_nombre_evento ON eventos(nombre);

-- 2. Índice compuesto sobre 'fecha' y 'ubicacion'
CREATE INDEX idx_fecha_ubicacion ON eventos(fecha, ubicacion);

-- 3. Mostrar índices de la tabla eventos
SHOW INDEX FROM eventos;


-- ============================================
-- EJERCICIO 6: Eliminar usuario e índices
-- ============================================

-- 1. Eliminar usuario organizadora_remota
DROP USER 'organizadora_remota'@'%';

-- 2. Eliminar los índices
DROP INDEX idx_nombre_evento ON eventos;
DROP INDEX idx_fecha_ubicacion ON eventos;


-- ============================================
-- EJERCICIO 7: Modificar usuario fallero_local
-- ============================================

-- 1. Cambiar contraseña
ALTER USER 'fallero_local'@'localhost' IDENTIFIED BY 'FallasSuperSegura2025!';

-- 2. Obligar a cambiar contraseña en el próximo inicio de sesión
ALTER USER 'fallero_local'@'localhost' PASSWORD EXPIRE;

-- 3. Cambiar host para que solo pueda conectarse desde 10.0.0.100
-- (hay que recrear el usuario con el nuevo host)
RENAME USER 'fallero_local'@'localhost' TO 'fallero_local'@'10.0.0.100';


-- ============================================
-- EJERCICIO 8: Alteraciones de tablas
-- ============================================

-- 1. Añadir columna 'comentarios' VARCHAR(255) a fallas_mayores
ALTER TABLE fallas_mayores ADD COLUMN comentarios VARCHAR(255);

-- 2. Modificar columna 'ubicacion' en eventos a VARCHAR(100)
ALTER TABLE eventos MODIFY COLUMN ubicacion VARCHAR(100);

-- 3. Eliminar columna 'comentarios' de fallas_mayores
ALTER TABLE fallas_mayores DROP COLUMN comentarios;