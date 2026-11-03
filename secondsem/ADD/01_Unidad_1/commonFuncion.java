
package funciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class Funciones {

    // =====================================================
    // 1. MENÚ (cambia los textos)
    // =====================================================

    public static void mostrarMenu() {
        System.out.println();
        System.out.println("1. Modificar stock");
        System.out.println("2. Restaurar copia");
        System.out.println("0. Salir");
    }

    // =====================================================
    // 2. TECLADO  (devuelven null si la entrada no vale)
    // =====================================================

    // Entero. Úsalo para: opción de menú, ID, stock, plazas.
    public static Integer leerEntero(Scanner teclado, String mensaje) {
        System.out.print(mensaje);
        String entrada = teclado.nextLine();

        Integer numero;

        try {
            numero = Integer.valueOf(entrada.trim());
        } catch (NumberFormatException e) {
            numero = null;
        }

        return numero;
    }

    // Decimal. Úsalo para: precio.
    public static Double leerDecimal(Scanner teclado, String mensaje) {
        System.out.print(mensaje);
        String entrada = teclado.nextLine();

        Double numero;

        try {
            numero = Double.valueOf(entrada.trim().replace(",", "."));
        } catch (NumberFormatException e) {
            numero = null;
        }

        return numero;
    }

    // Texto. Devuelve null si está vacío. Úsalo para: nombre, persona, material, título.
    public static String leerTexto(Scanner teclado, String mensaje) {
        System.out.print(mensaje);
        String texto = teclado.nextLine().trim();

        if (texto.isEmpty()) {
            texto = null;
        }

        return texto;
    }

    // Confirmación S/N. Úsalo para: restaurar copia, borrar.
    public static boolean confirmar(Scanner teclado, String mensaje) {
        System.out.print(mensaje + " (S/N): ");
        String respuesta = teclado.nextLine().trim();

        return respuesta.equalsIgnoreCase("S")
                || respuesta.equalsIgnoreCase("SI");
    }

    // =====================================================
    // 3. ARCHIVOS Y CARPETAS
    // =====================================================

    public static boolean existeArchivo(Path archivo) {
        boolean existe = Files.exists(archivo);

        if (!existe) {
            System.out.println("No existe el archivo: " + archivo);
        }

        return existe;
    }

    public static void crearCarpetaSiNoExiste(Path carpeta) throws IOException {
        if (!Files.exists(carpeta)) {
            Files.createDirectories(carpeta);
        }
    }

    // Crea el archivo vacío solo si no existe (no sobrescribe).
    public static void crearArchivoSiNoExiste(Path archivo) throws IOException {
        crearCarpetaSiNoExiste(archivo.getParent());

        if (!Files.exists(archivo)) {
            Files.createFile(archivo);
        }
    }

    // Muestra nombre de los archivos normales de una carpeta (no subcarpetas).
    public static void listarArchivos(Path carpeta) {
        if (!Files.exists(carpeta)) {
            System.out.println("No existe la carpeta: " + carpeta);
        } else {
            try (DirectoryStream<Path> elementos = Files.newDirectoryStream(carpeta)) {
                for (Path elemento : elementos) {
                    if (Files.isRegularFile(elemento)) {
                        System.out.println(elemento.getFileName());
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al leer la carpeta: " + e.getMessage());
            }
        }
    }

    // Muestra existencia, ruta absoluta y tamaño.
    public static void inspeccionar(Path archivo) {
        System.out.println("Ruta absoluta: " + archivo.toAbsolutePath());

        if (Files.notExists(archivo)) {
            System.out.println("El archivo no existe.");
        } else {
            try {
                System.out.println("Tamaño: " + Files.size(archivo) + " bytes");
            } catch (IOException e) {
                System.out.println("Error de acceso: " + e.getMessage());
            }
        }
    }

    // =====================================================
    // 4. TEXTO: LEER / ESCRIBIR / AÑADIR
    // =====================================================

    // Lee todas las líneas (incluida cabecera).
    public static List<String> leerLineas(Path archivo) throws IOException {
        return Files.readAllLines(archivo, StandardCharsets.UTF_8);
    }

    // Sustituye TODO el contenido del archivo por esas líneas.
    public static void escribirLineas(Path archivo, List<String> lineas) throws IOException {
        crearCarpetaSiNoExiste(archivo.getParent());
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }

    // Añade UNA línea al final (CREATE + APPEND). Conserva lo anterior.
    public static void anadirLinea(Path archivo, String linea) throws IOException {
        crearCarpetaSiNoExiste(archivo.getParent());

        try (BufferedWriter escritor = Files.newBufferedWriter(
                archivo,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            escritor.write(linea);
            escritor.newLine();
        }
    }

    // Imprime el archivo línea a línea con BufferedReader.
    public static void mostrarArchivo(Path archivo) throws IOException {
        try (BufferedReader lector = Files.newBufferedReader(
                archivo,
                StandardCharsets.UTF_8)) {

            String linea = lector.readLine();

            while (linea != null) {
                System.out.println(linea);
                linea = lector.readLine();
            }
        }
    }

    // =====================================================
    // 5. CSV: VALIDAR Y BUSCAR (la línea 0 es la cabecera)
    // =====================================================

    // ¿La línea tiene exactamente n campos?
    public static boolean tieneCampos(String linea, int n) {
        return linea.split(";", -1).length == n;
    }

    // Convierte texto a entero. Devuelve null si no es número.
    public static Integer aEntero(String texto) {
        Integer numero;

        try {
            numero = Integer.valueOf(texto.trim());
        } catch (NumberFormatException e) {
            numero = null;
        }

        return numero;
    }

    // Convierte texto a decimal. Devuelve null si no es número.
    public static Double aDecimal(String texto) {
        Double numero;

        try {
            numero = Double.valueOf(texto.trim().replace(",", "."));
        } catch (NumberFormatException e) {
            numero = null;
        }

        return numero;
    }

    // Devuelve la POSICIÓN de la línea cuyo ID (campo 0) coincide, o -1.
    // Salta la cabecera (empieza en 1) e ignora filas mal formadas.
    public static int buscarPosicionPorId(List<String> lineas, int id) {
        int posicion = -1;

        for (int i = 1; i < lineas.size() && posicion == -1; i++) {
            String[] campos = lineas.get(i).split(";", -1);

            if (campos.length >= 1) {
                Integer idLinea = aEntero(campos[0]);

                if (idLinea != null && idLinea == id) {
                    posicion = i;
                }
            }
        }

        return posicion;
    }

    // ¿Existe ya este texto en la columna indicada? (ignora mayúsculas y espacios)
    // Úsalo para: títulos repetidos, nombres repetidos. columna 1 = segundo campo.
    public static boolean existeTexto(List<String> lineas, int columna, String texto) {
        boolean existe = false;

        for (int i = 1; i < lineas.size() && !existe; i++) {
            String[] campos = lineas.get(i).split(";", -1);

            if (campos.length > columna
                    && campos[columna].trim().equalsIgnoreCase(texto.trim())) {
                existe = true;
            }
        }

        return existe;
    }

    // Comprueba que TODO el CSV es válido: n campos, ID entero y sin ID repetidos.
    // Devuelve null si todo va bien, o el mensaje del primer error.
    public static String validarCsv(List<String> lineas, int numCampos) {
        String error = null;
        List<Integer> ids = new ArrayList<>();

        for (int i = 1; i < lineas.size() && error == null; i++) {
            String[] campos = lineas.get(i).split(";", -1);

            if (campos.length != numCampos) {
                error = "Línea " + (i + 1) + ": número de campos incorrecto.";
            } else {
                Integer id = aEntero(campos[0]);

                if (id == null) {
                    error = "Línea " + (i + 1) + ": ID no numérico.";
                } else if (ids.contains(id)) {
                    error = "Línea " + (i + 1) + ": ID repetido " + id + ".";
                } else {
                    ids.add(id);
                }
            }
        }

        return error;
    }

    // =====================================================
    // 6. COPIAS DE SEGURIDAD
    // =====================================================

    public static void crearCopiaSeguridad(
            Path archivo,
            Path carpetaCopias,
            Path copia) throws IOException {

        crearCarpetaSiNoExiste(carpetaCopias);

        Files.copy(
                archivo,
                copia,
                StandardCopyOption.REPLACE_EXISTING
        );
    }

    // Copia al lado del archivo: inventario.csv -> inventario.csv.bak
    // Solo copia si el archivo existe. Devuelve la ruta de la copia.
    public static Path crearCopiaSiExiste(Path archivo) throws IOException {
        Path copia = archivo.resolveSibling(archivo.getFileName() + ".bak");

        if (Files.exists(archivo)) {
            Files.copy(archivo, copia, StandardCopyOption.REPLACE_EXISTING);
        }

        return copia;
    }

    // Restaura la copia sobre el archivo. true si se restauró, false si no hay copia.
    // NO pregunta nada por teclado: la confirmación se pide antes, en el main.
    public static boolean restaurarCopia(Path copia, Path archivo) throws IOException {
        boolean restaurada = false;

        if (Files.exists(copia)) {
            Files.copy(copia, archivo, StandardCopyOption.REPLACE_EXISTING);
            restaurada = true;
        }

        return restaurada;
    }

    // =====================================================
    // 7. BINARIO (Serializable)
    // =====================================================

    // Guarda un objeto (normalmente una List<Producto>). SIEMPRE sustituye, nunca APPEND.
    public static void guardarObjeto(Path archivo, Object objeto) throws IOException {
        crearCarpetaSiNoExiste(archivo.getParent());

        try (ObjectOutputStream salida = new ObjectOutputStream(
                Files.newOutputStream(archivo))) {

            salida.writeObject(objeto);
        }
    }

    // Lee una lista de objetos. Devuelve List<?>; tú compruebas cada elemento
    // con instanceof (ver plantilla). Lanza IOException si no es una lista.
    public static List<?> leerLista(Path archivo) throws IOException {
        List<?> lista = null;

        try (ObjectInputStream entrada = new ObjectInputStream(
                Files.newInputStream(archivo))) {

            Object objeto = entrada.readObject();

            if (objeto instanceof List<?>) {
                lista = (List<?>) objeto;
            } else {
                throw new IOException("El archivo no contiene una lista.");
            }

        } catch (ClassNotFoundException e) {
            throw new IOException("Clase no encontrada: " + e.getMessage());
        }

        return lista;
    }

    // =====================================================
    // 8. XML (DOM)
    // =====================================================

    // DocumentBuilder SEGURO (sin DTD ni entidades externas). Úsalo antes de parse().
    public static DocumentBuilder crearConstructorXmlSeguro()
            throws ParserConfigurationException {

        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();

        fabrica.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        fabrica.setFeature("http://xml.org/sax/features/external-general-entities", false);
        fabrica.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        fabrica.setXIncludeAware(false);
        fabrica.setExpandEntityReferences(false);

        return fabrica.newDocumentBuilder();
    }

    // Escribe un Document en un archivo UTF-8 con indentación.
    public static void guardarXml(Document documento, Path archivo)
            throws IOException, TransformerException {

        crearCarpetaSiNoExiste(archivo.getParent());

        Transformer transformador = TransformerFactory.newInstance().newTransformer();
        transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformador.setOutputProperty(OutputKeys.INDENT, "yes");
        transformador.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        try (java.io.OutputStream salida = Files.newOutputStream(archivo)) {
            transformador.transform(new DOMSource(documento), new StreamResult(salida));
        }
    }
}