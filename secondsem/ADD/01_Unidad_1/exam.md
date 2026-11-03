# Exam cheat sheet – Data Access, Unit 1 (English version)

Package: `funciones.Funciones` (file `Funciones.java`). Import with `import funciones.Funciones;`

Note: code, method names and console messages are kept in Spanish on purpose, so they match your teacher's style and the exam. Only the explanations are translated.

## 1. Which function do I use?

| They ask me to...                              | I use                                                                |
|------------------------------------------------|----------------------------------------------------------------------|
| Ask for a number (ID, stock, seats, menu option) | `Funciones.leerEntero(teclado, "ID: ")` → `Integer` or `null`      |
| Ask for a price                                | `Funciones.leerDecimal(teclado, "Precio: ")` → `Double` or `null`    |
| Ask for text that can't be empty               | `Funciones.leerTexto(teclado, "Nombre: ")` → `String` or `null`      |
| Confirm Y/N (restore, delete)                  | `Funciones.confirmar(teclado, "¿Seguro?")` → `boolean`               |
| Check that a file exists                       | `Funciones.existeArchivo(ruta)`                                      |
| Create the `datos` / `copias` folder           | `Funciones.crearCarpetaSiNoExiste(ruta)`                             |
| Create an empty file without overwriting       | `Funciones.crearArchivoSiNoExiste(ruta)`                             |
| List the files in a folder                     | `Funciones.listarArchivos(carpeta)`                                  |
| Show existence and size                        | `Funciones.inspeccionar(ruta)`                                       |
| Read all lines                                 | `Funciones.leerLineas(ruta)`                                         |
| Overwrite the file with a list                 | `Funciones.escribirLineas(ruta, lista)`                              |
| Append one line at the end (add record)        | `Funciones.anadirLinea(ruta, "id;a;b")`                              |
| Print the whole file                           | `Funciones.mostrarArchivo(ruta)`                                     |
| Convert text to a number without crashing      | `Funciones.aEntero(texto)` / `Funciones.aDecimal(texto)` (or `null`) |
| Does the line have N fields?                   | `Funciones.tieneCampos(linea, 3)`                                    |
| Find an ID in the CSV                          | `Funciones.buscarPosicionPorId(lineas, id)` → position or `-1`       |
| Is a title/name duplicated?                    | `Funciones.existeTexto(lineas, 1, titulo)`                           |
| Validate the WHOLE CSV (fields, ID, duplicates)| `Funciones.validarCsv(lineas, 3)` → `null` if OK, else error message |
| Backup into a `copias/` folder with fixed name | `Funciones.crearCopiaSeguridad(archivo, carpetaCopias, copia)`       |
| Backup `file.csv.bak` next to the file         | `Funciones.crearCopiaSiExiste(archivo)`                              |
| Restore a backup (no prompting)                | `Funciones.restaurarCopia(copia, archivo)` → `true`/`false`          |
| Save a list in binary                          | `Funciones.guardarObjeto(ruta, lista)`                               |
| Read a list from binary                        | `Funciones.leerLista(ruta)` → `List<?>`                              |
| Create a safe XML parser                       | `Funciones.crearConstructorXmlSeguro()`                              |
| Save a `Document` as UTF-8 XML                 | `Funciones.guardarXml(documento, ruta)`                              |

Rules your teacher keeps repeating:
- **No `System.out` inside logic methods** (only in `main`).
- **`split(";", -1)`** always.
- **Header = line 0, so loops start at `i = 1`.**
- **Don't rewrite the file if the ID doesn't exist.**
- **Make the backup BEFORE writing.**
- **Ask Y/N in `main`, never inside the restore method.**

---

## 2. Template: CSV manager class (inscriptions / loans / reservations exercise)

Replace `Inscripcion` with your own class and adjust the number of fields.

```java
// ---------- cargar (load, TODO 1.1) ----------
public static List<Inscripcion> cargar(Path archivo) throws IOException {
    List<Inscripcion> lista = new ArrayList<>();

    if (Files.exists(archivo)) {
        List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);

        if (lineas.isEmpty() || !lineas.get(0).equals("id;persona;taller;plazas")) {
            throw new IOException("Cabecera incorrecta.");
        }

        for (int i = 1; i < lineas.size(); i++) {
            String[] campos = lineas.get(i).split(";", -1);

            if (campos.length != 4) {
                throw new IOException("Línea " + (i + 1) + ": campos incorrectos.");
            }

            try {
                int id = Integer.parseInt(campos[0].trim());
                int plazas = Integer.parseInt(campos[3].trim());

                if (id <= 0 || plazas < 0 || campos[1].trim().isEmpty()
                        || campos[2].trim().isEmpty()) {
                    throw new IOException("Línea " + (i + 1) + ": datos no válidos.");
                }
                if (buscar(lista, id) != null) {
                    throw new IOException("Línea " + (i + 1) + ": ID repetido.");
                }

                lista.add(new Inscripcion(id, campos[1].trim(), campos[2].trim(), plazas));

            } catch (NumberFormatException e) {
                throw new IOException("Línea " + (i + 1) + ": número no válido.");
            }
        }
    }

    return lista;   // empty list if the file doesn't exist
}

// ---------- buscar (search, TODO 1.2) ----------
public static Inscripcion buscar(List<Inscripcion> lista, int id) {
    Inscripcion encontrada = null;

    for (Inscripcion i : lista) {
        if (i.getId() == id) {
            encontrada = i;
        }
    }

    return encontrada;   // null if not found
}

// ---------- guardarTodo (save everything) ----------
public static void guardarTodo(Path archivo, List<Inscripcion> lista) throws IOException {
    List<String> lineas = new ArrayList<>();
    lineas.add("id;persona;taller;plazas");

    for (Inscripcion i : lista) {
        lineas.add(i.getId() + ";" + i.getPersona() + ";" + i.getTaller() + ";" + i.getPlazas());
    }

    Files.write(archivo, lineas, StandardCharsets.UTF_8);
}

// ---------- actualizarPlazas (update, TODO 1.3) ----------
public static boolean actualizarPlazas(Path archivo, List<Inscripcion> lista,
        int id, int nuevasPlazas) throws IOException {
    boolean actualizado = false;
    Inscripcion inscripcion = buscar(lista, id);

    if (nuevasPlazas >= 0 && inscripcion != null) {
        crearCopiaSiExiste(archivo);          // backup BEFORE writing
        inscripcion.setPlazas(nuevasPlazas);
        guardarTodo(archivo, lista);
        actualizado = true;
    }

    return actualizado;
}

// ---------- eliminar (delete, TODO 1.4) ----------
public static boolean eliminar(Path archivo, List<Inscripcion> lista, int id) throws IOException {
    boolean eliminado = false;
    List<Inscripcion> nueva = new ArrayList<>();

    for (Inscripcion i : lista) {
        if (i.getId() == id) {
            eliminado = true;
        } else {
            nueva.add(i);
        }
    }

    if (eliminado) {
        crearCopiaSiExiste(archivo);
        guardarTodo(archivo, nueva);
        lista.clear();
        lista.addAll(nueva);
    }

    return eliminado;
}

// ---------- restaurar (restore, TODO 1.5) – no Scanner, no System.out ----------
public static boolean restaurar(Path archivo) throws IOException {
    Path copia = archivo.resolveSibling(archivo.getFileName() + ".bak");
    boolean restaurada = false;

    if (Files.exists(copia)) {
        Files.copy(copia, archivo, StandardCopyOption.REPLACE_EXISTING);
        restaurada = true;
    }

    return restaurada;
}
```

### `Main` (cases 4, 5, 6 – TODO 1.6)

```java
case 4: {
    Integer id = Funciones.leerEntero(teclado, "ID: ");
    Integer plazas = Funciones.leerEntero(teclado, "Nuevas plazas: ");

    if (id == null || plazas == null) {
        System.out.println("Debes introducir números enteros.");
    } else if (GestorInscripciones.actualizarPlazas(archivo, lista, id, plazas)) {
        System.out.println("Plazas actualizadas.");
    } else {
        System.out.println("ID inexistente o plazas negativas.");
    }
    break;
}
case 5: {
    Integer id = Funciones.leerEntero(teclado, "ID a eliminar: ");

    if (id == null) {
        System.out.println("El ID debe ser un número entero.");
    } else if (GestorInscripciones.eliminar(archivo, lista, id)) {
        System.out.println("Inscripción eliminada.");
    } else {
        System.out.println("ID no encontrado.");
    }
    break;
}
case 6: {
    if (Funciones.confirmar(teclado, "¿Restaurar la copia?")) {
        if (GestorInscripciones.restaurar(archivo)) {
            lista = GestorInscripciones.cargar(archivo);   // reload the list
            System.out.println("Copia restaurada.");
        } else {
            System.out.println("No existe ninguna copia.");
        }
    } else {
        System.out.println("Operación cancelada.");
    }
    break;
}
```

---

## 3. Binary: serializable `Producto` + save / load / modify

```java
import java.io.Serializable;

public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    // + empty constructor, full constructor, getters, setters, toString
    // (same as the Producto your teacher gave you, adding "implements Serializable")
}
```

```java
// Save (Relación 3, exercise 1)
try {
    Funciones.guardarObjeto(Path.of("datos", "productos.dat"), productos);
    System.out.println("Guardado correcto.");
} catch (IOException e) {
    System.out.println("No se pudo guardar: " + e.getMessage());
}

// Load with type checking (Relación 3, exercise 2)
Path dat = Path.of("datos", "productos.dat");
List<Producto> productos = new ArrayList<>();

if (Funciones.existeArchivo(dat)) {
    try {
        List<?> leida = Funciones.leerLista(dat);
        List<Producto> temporal = new ArrayList<>();
        boolean todoCorrecto = true;

        for (Object o : leida) {
            if (o instanceof Producto) {
                temporal.add((Producto) o);
            } else {
                todoCorrecto = false;
            }
        }

        if (todoCorrecto) {
            productos = temporal;
        } else {
            System.out.println("El archivo contiene elementos que no son productos.");
        }
    } catch (IOException e) {
        System.out.println("Error al leer: " + e.getMessage());
    }
}

// Modify stock and save again
Integer id = Funciones.leerEntero(teclado, "ID: ");
Integer stock = Funciones.leerEntero(teclado, "Nuevo stock: ");
boolean encontrado = false;

if (id != null && stock != null && stock >= 0) {
    for (Producto p : productos) {
        if (p.getId() == id) {
            p.setStock(stock);
            encontrado = true;
        }
    }
    if (encontrado) {
        Funciones.guardarObjeto(dat, productos);   // inside try/catch IOException
    } else {
        System.out.println("No existe ese ID.");
    }
}
```

---

## 4. XML with DOM

### Export (Relación 3, exercise 3)

```java
try {
    DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
    Document documento = fabrica.newDocumentBuilder().newDocument();

    Element raiz = documento.createElement("productos");
    documento.appendChild(raiz);

    for (Producto p : productos) {
        Element producto = documento.createElement("producto");
        producto.setAttribute("id", String.valueOf(p.getId()));

        Element nombre = documento.createElement("nombre");
        nombre.setTextContent(p.getNombre());        // the & is escaped automatically
        producto.appendChild(nombre);

        Element precio = documento.createElement("precio");
        precio.setTextContent(String.valueOf(p.getPrecio()));
        producto.appendChild(precio);

        Element stock = documento.createElement("stock");
        stock.setTextContent(String.valueOf(p.getStock()));
        producto.appendChild(stock);

        raiz.appendChild(producto);
    }

    Funciones.guardarXml(documento, Path.of("datos", "productos.xml"));
    System.out.println("XML exportado.");

} catch (ParserConfigurationException | TransformerException | IOException e) {
    System.out.println("Error al exportar XML: " + e.getMessage());
}
```

### Safe import (Relación 3, exercise 4) – temporary list, all or nothing

```java
public static List<Producto> importarXml(Path archivo) throws Exception {
    List<Producto> temporal = new ArrayList<>();
    List<Integer> ids = new ArrayList<>();

    if (!Files.exists(archivo)) {
        throw new IOException("No existe el archivo.");
    }

    DocumentBuilder constructor = Funciones.crearConstructorXmlSeguro();
    Document documento = constructor.parse(archivo.toFile());
    NodeList nodos = documento.getElementsByTagName("producto");

    for (int i = 0; i < nodos.getLength(); i++) {
        Element e = (Element) nodos.item(i);

        int id = Integer.parseInt(e.getAttribute("id").trim());
        String nombre = e.getElementsByTagName("nombre").item(0).getTextContent().trim();
        double precio = Double.parseDouble(
                e.getElementsByTagName("precio").item(0).getTextContent().trim());
        int stock = Integer.parseInt(
                e.getElementsByTagName("stock").item(0).getTextContent().trim());

        if (id <= 0 || nombre.isEmpty() || precio < 0 || stock < 0) {
            throw new IllegalArgumentException("Producto inválido (id " + id + ").");
        }
        if (ids.contains(id)) {
            throw new IllegalArgumentException("ID duplicado: " + id);
        }

        ids.add(id);
        temporal.add(new Producto(id, nombre, precio, stock));
    }

    return temporal;
}

// Usage: the main list is only replaced if NO exception is thrown
try {
    List<Producto> importados = importarXml(Path.of("datos", "productos_correctos.xml"));
    productos = importados;
    System.out.println("Importación correcta.");
} catch (Exception e) {
    System.out.println("Importación rechazada: " + e.getMessage());
}
```

Imports: `org.w3c.dom.Document`, `org.w3c.dom.Element`, `org.w3c.dom.NodeList`, `javax.xml.parsers.*`, `javax.xml.transform.TransformerException`.

---

## 5. JSON (Jackson)

Use your teacher's `ProductosJson.java` as is. `pom.xml`:

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.18.3</version>
</dependency>
```

Rules:
- `Producto` needs an **empty constructor + getters/setters**.
- Export with `writerWithDefaultPrettyPrinter().writeValue(...)`.
- Import with `new TypeReference<List<Producto>>() {}`.
- **Validate before replacing** the current data.

---

## 6. Repository (interface + implementation)

```java
// RepositorioProductos.java
import java.io.IOException;
import java.util.List;

public interface RepositorioProductos {
    void guardarTodos(List<Producto> productos) throws IOException;
    List<Producto> cargarTodos() throws IOException;
}
```

```java
// RepositorioTexto.java  (format id;nombre;precio;stock)
public class RepositorioTexto implements RepositorioProductos {
    private final Path archivo;

    public RepositorioTexto(Path archivo) {
        this.archivo = archivo;
    }

    @Override
    public void guardarTodos(List<Producto> productos) throws IOException {
        List<String> lineas = new ArrayList<>();

        for (Producto p : productos) {
            lineas.add(p.getId() + ";" + p.getNombre() + ";" + p.getPrecio() + ";" + p.getStock());
        }

        Funciones.escribirLineas(archivo, lineas);
    }

    @Override
    public List<Producto> cargarTodos() throws IOException {
        List<Producto> productos = new ArrayList<>();

        if (Files.exists(archivo)) {
            List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);

            for (int i = 0; i < lineas.size(); i++) {
                String[] campos = lineas.get(i).split(";", -1);

                if (campos.length != 4) {
                    throw new IOException("Línea " + (i + 1) + ": campos incorrectos.");
                }
                try {
                    productos.add(new Producto(
                            Integer.parseInt(campos[0].trim()),
                            campos[1].trim(),
                            Double.parseDouble(campos[2].trim()),
                            Integer.parseInt(campos[3].trim())));
                } catch (NumberFormatException e) {
                    throw new IOException("Línea " + (i + 1) + ": número no válido.");
                }
            }
        }

        return productos;   // empty list if the file didn't exist
    }
}
```

`RepositorioBinario`: the same two methods, using `Funciones.guardarObjeto(...)` and `Funciones.leerLista(...)` plus `instanceof Producto`.

In `Main`: `RepositorioProductos repo = new RepositorioTexto(Path.of("datos", "productos.txt"));` (to switch to binary you only change this line). `Main` can't contain `Files`, `Path`, `BufferedReader`... so create the `Path` inside the repository (or pass a `String` to its constructor).

---

## 7. Typical mistakes that lose marks

- Reading the header as data (starting at `i = 0` instead of `i = 1`).
- Writing the file when the ID doesn't exist.
- `System.out` inside the manager class (only in `Main`).
- Asking Y/N inside `restaurar` (it belongs in `Main`).
- Forgetting `, -1` in `split`.
- Using `APPEND` when saving binary.
- Not closing resources with `try-with-resources`.
- Making the backup **after** modifying (it must be before).