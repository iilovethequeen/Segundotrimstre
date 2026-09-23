-- Ejecutar en el cliente SQL sobre la BD de laboratorio ad_clubes, una sola vez.
USE ad_clubes;
CREATE PROCEDURE buscar_clubes_por_ciudad(IN p_ciudad VARCHAR(80))
SELECT id, nombre, ciudad FROM club WHERE ciudad = p_ciudad ORDER BY nombre;
-- Invocación de prueba:
-- CALL buscar_clubes_por_ciudad('Granada');
