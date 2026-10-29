# Explanation of the code: CSV persistence

This module is responsible for **saving and loading** the stolen-bicycle data to and from a CSV file, so the information is not lost when the program ends. It has no `print()` or `input()`: it only reads and writes data and returns results, leaving any user interaction to other modules.

---

## Imports and constants

```python
import csv
from pathlib import Path
from modelos import Bicicleta

CAMPOS = ["codigo", "propietario", "modelo", "valor", "estado"]
```

| Element | Purpose |
|---|---|
| `csv` | Standard library module to read and write CSV files |
| `Path` | Object-oriented way to handle file paths (`pathlib`) |
| `Bicicleta` | The class from the `modelos` module, used to rebuild objects from CSV rows |
| `CAMPOS` | The column names, in order. Used as the CSV header and as the fields for the writer |

---

## Function `cargar_bicicletas(ruta)`

Reads the CSV file and returns a **list of `Bicicleta` objects**.

### Step by step

1. **Converts the path**: `ruta = Path(ruta)` so it works whether you pass a string or a `Path`.
2. **Creates an empty list**: `bicicletas = []`.
3. **Checks the file exists**: if `ruta.exists()` is `False`, it returns the empty list. This means the first run of the program (when no file exists yet) does not crash.
4. **Opens the file** with `encoding="utf-8"` (supports accents and special characters) and `newline=""` (required by the `csv` module to avoid blank lines and newline problems on Windows).
5. **Reads rows as dictionaries**: `csv.DictReader` uses the header line as keys, so each row is a dict like `{"codigo": "A1", "propietario": "Ana", ...}`.
6. **Builds a `Bicicleta` per row**:
   - `float(fila["valor"])` converts the value from text to a number.
   - `fila.get("estado", "ROBADA")` uses `"ROBADA"` as the default if the `estado` column is missing.
7. **Handles bad rows**: the `try/except (ValueError, KeyError)` catches
   - `ValueError`: the value is not a valid number (e.g. `"abc"`),
   - `KeyError`: a required column is missing.

   Those rows are silently skipped with `pass` (no `print()` allowed), so one corrupt line doesn't break the whole load.
8. **Returns** the list of bicycles.

### Example

CSV file:

```csv
codigo,propietario,modelo,valor,estado
ABC1,Ana,Trek X,800.00,ROBADA
XYZ9,Luis,Orbea,abc,ROBADA
```

Result: a list with only **one** bicycle (`ABC1`), because the second row has an invalid value and is ignored.

---

## Function `guardar_bicicletas(ruta, bicicletas)`

Writes all the bicycles to a CSV file and returns `True` when finished.

### Step by step

1. **Opens the file in write mode** (`"w"`), which creates it if it doesn't exist or **overwrites** it if it does. Same `utf-8` and `newline=""` settings as before.
2. **Creates a `csv.DictWriter`** with `fieldnames=CAMPOS`, which defines the columns and their order.
3. **Writes the header**: `escritor.writeheader()` outputs `codigo,propietario,modelo,valor,estado`.
4. **Writes one row per bicycle**: for each `Bicicleta`, it builds a dictionary with its attributes and calls `writerow`.
   - `f"{bicicleta.valor:.2f}"` formats the value with **exactly two decimals** (e.g. `800` becomes `"800.00"`).
5. **Returns `True`** to indicate the save finished.

---

## How both functions work together

```text
Program starts  ->  cargar_bicicletas("datos.csv")  ->  list of Bicicleta
                                  |
                      user adds / recovers bikes
                                  |
Program ends    ->  guardar_bicicletas("datos.csv", lista)
```

Since `guardar_bicicletas` writes with the same columns that `cargar_bicicletas` reads, a file saved by one can always be loaded by the other.

---

## Potential issues worth knowing

1. **Errors are silently swallowed.** Skipped rows leave no trace, so you can't tell that data was lost. A common improvement is to return or log the number of rows that were ignored.
2. **Other errors are not caught.** For example, an empty file with no header, or a `None` value in a row, could raise exceptions that the `try/except` doesn't cover.
3. **`guardar_bicicletas` overwrites the whole file.** If the program crashes midway through writing, the previous data may be lost. Writing to a temporary file and then renaming it is safer.
4. **It always returns `True`.** If writing fails (e.g. no permissions), an exception is raised rather than `False` being returned. The return value carries no real information.
5. **Rows are not validated like in `registrar`.** `cargar_bicicletas` accepts negative values, empty owners, or duplicate codes, because it builds the objects directly. Passing the loaded bikes through `RegistroRobos.registrar()` would apply the same rules.
6. **Codes are not normalized.** A code like `" abc1 "` in the file will be loaded as-is, unlike bikes added through `registrar`, which are uppercased and stripped.