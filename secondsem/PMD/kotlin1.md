# Exam analysis – which exercise each part matches

The exam has two parts. Both reuse patterns from Relación 1, 2 and 3 and from the inscripciones exercise (TODO 1.1 to 1.6).

---

## Part 1: Videojuegos (CSV manager + `Main1`)

Same structure as the **inscripciones exercise**.

| Exam part | Matches |
|---|---|
| **TODO 1.1 `cargar`**: check header, skip line 0, split, parse, validate | Inscripciones TODO 1.1. Also **R2 ex. 1** (it literally uses `videojuegos.csv`) and **R2 ex. 6** (validating rows) |
| **TODO 1.2 `actualizarStock`**: only if active, stock ≥ 0, backup, then save | Inscripciones TODO 1.3 (`actualizarPlazas`). Also **R2 ex. 3** (stock update) and **R2 ex. 7** (backup before editing). New rule: only if `activo` |
| **TODO 1.3 `eliminar`**: backup, then save | Inscripciones TODO 1.4. Also **R2 ex. 4** (cancel a reservation) |
| `registrar` / `esValido` (already solved) | **R2 ex. 5**: add records without duplicates, with validation |
| `restaurar`, `crearCopiaSiExiste`, `guardarTodo` (already solved) | Inscripciones TODO 1.5 and **R2 ex. 7** |
| `Main1` menu with Y/N confirm on restore | Inscripciones TODO 1.6 and **R2 ex. 8** (menu keeps running after errors) |

## Part 2: Máquinas (binary + XML)

| Exam part | Matches |
|---|---|
| **TODO 2.1 `cargar`**: `exists`, `ObjectInputStream`, check it's a `List`, `instanceof` on each element, validate IDs | **R3 ex. 2** (recover with type checks) and **R3 ex. 7** (validate type of object and elements). Duplicate-ID check is like R3 ex. 4 and 5 |
| **TODO 2.2 `cambiarUnidades`**: modify in memory only | Modify step of **R3 ex. 2** without saving. Logic like R2 ex. 3 |
| **TODO 2.3 `exportarXml`**: DOM, root, `id` attribute, child elements, `Transformer`, indent | **R3 ex. 3**, almost identical |
| `guardar` (already solved) | **R3 ex. 1**: save the whole list, no `APPEND` |
| `Main` with explicit Save/Reload and a `cargado` flag | **R3 ex. 2** ("don't rewrite if the read didn't complete") and the repository idea of **R3 ex. 6 and 7** |

---

## Quick summary

| Exam TODO | Pattern | Cheat sheet section |
|---|---|---|
| 1.1 `cargar` CSV | Load + validate CSV | 2 |
| 1.2 `actualizarStock` | Update with backup | 2 |
| 1.3 `eliminar` | Delete with backup | 2 |
| 2.1 `cargar` binary | Binary load with `instanceof` | 3 |
| 2.2 `cambiarUnidades` | Modify in memory | 3 |
| 2.3 `exportarXml` | DOM export | 4 |

---

## Points in the submitted answers that may have cost marks

These depend on the exact wording of each TODO, which I haven't seen.

**`cargar` in Part 1**
- It uses `split(";")` without `-1`. An empty last field (for example `1;Zelda;Switch;5;`) would give 4 fields instead of 5.
- Rows with the wrong number of fields are skipped silently. In the inscripciones version, corrupt data had to throw an `IOException`.
- Rows that fail `esValido` are skipped silently, again instead of throwing.
- `Boolean.parseBoolean` treats any text as `false`, so a value like `"maybe"` is not detected as corrupt.
- Repeated IDs are not checked.

Possible fix for the checks, inside the `for` loop:

```java
String[] campos = lineas.get(i).split(";", -1);

if (campos.length != 5) {
    throw new IOException("Línea " + (i + 1) + ": campos incorrectos.");
}
if (!campos[4].equals("true") && !campos[4].equals("false")) {
    throw new IOException("Línea " + (i + 1) + ": activo no válido.");
}
// ... parse, build the object ...
if (!esValido(juego) || buscar(lista, juego.getId()) != null) {
    throw new IOException("Línea " + (i + 1) + ": datos no válidos o ID repetido.");
}
lista.add(juego);
```

**`exportarXml` in Part 2**
- `Files.newOutputStream(archivo)` is never closed. Use `try-with-resources`, like the `guardarXml` helper in `Funciones.java`.
- UTF-8 is not set (`transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8")`).
- The folder is not created if it is missing (`Files.createDirectories(archivo.getParent())`).

Possible fix for the ending:

```java
Transformer transformer = transformerFactory.newTransformer();
transformer.setOutputProperty(OutputKeys.INDENT, "yes");
transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

if (archivo.getParent() != null) {
    Files.createDirectories(archivo.getParent());
}

try (OutputStream salida = Files.newOutputStream(archivo)) {
    transformer.transform(new DOMSource(doc), new StreamResult(salida));
}
```

**Good points**
- `actualizarStock`: backup before saving, and returns `false` without writing when the ID is missing, the game is inactive or the stock is negative.
- `eliminar`: backup before saving, and no write when the ID doesn't exist.
- `cargar` in Part 2: checks `exists`, the `List` type, each element's type, validity and duplicate IDs.
- `cambiarUnidades`: no disk access, as required.