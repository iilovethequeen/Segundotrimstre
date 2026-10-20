# Python + Odoo: exam cheat sheet

Memorize the **shape** of each skeleton. The exam only changes names and numbers.

---

## Step 1: Ask 4 questions about any exercise

1. **Where do the numbers come from?** Given in the text = write them in the code. "Pide..." = use `input()`.
2. **Does it repeat?** Then you need a loop.
3. **Does it decide something?** Then you need `if / elif / else`.
4. **Does it store many things, or mention a function, file or class?** Then use a collection, `def`, `open` or `class`.

---

## Step 2: Decode the teacher's words

| If the exercise says... | It means... | Skeleton |
|---|---|---|
| "Pide..." | the user types it: `input()` | 1 |
| "cantidad, unidades, stock" | whole number: `int()` | 1 |
| "precio, importe, porcentaje" | decimals: `float()` | 1 |
| "con dos decimales" | `:.2f` on money only, never on counts | 1 |
| "Si... si no..." / "entre X y Y" | `if / elif / else` | 2 |
| "y" / "o" / "premium Y supera 250" | `and` / `or` | 2 |
| "N veces", "cada día", "hasta la cantidad" | `for` + `range` | 3 |
| "acumula el total" | `total = 0` **before** the loop, `total += x` inside | 3 |
| "cuenta cuántos" | `contador += 1` | 4 |
| "mientras sea distinto de 0" / "hasta cero" | `while` with an explicit condition | 4 |
| "menú", "vuelve al menú" | `while opcion != "0"` | 5 |
| "lista", "añade, elimina, ordena" | list: `append`, `remove`, `sort` | 6 |
| "campos", "claves", "ficha" | dictionary | 6 |
| "lista de productos con nombre, precio, stock" | list of dictionaries | 6 |
| "únicas / sin repetir" | `set(...)` | 7 |
| "que contenga, ignorando mayúsculas" | `texto.lower() in nombre.lower()` | 7 |
| "devuelva", "la función no debe imprimir" | `return`, no `print` inside | 8 |
| "valor por defecto" | `def f(importe, porcentaje=0)` | 8 |
| "si la lista está vacía, devuelve None" | `if len(x) == 0: return None` | 8 |
| "calcular_total(), main()" | one job per function, `main()` at the end | 8 |
| "valida", "recupérate de 'hola'", "hasta que sea válido" | `try/except ValueError` + the `valido` flag | 9 |
| "una línea por cliente", "guardar en .txt" | `with open(..., "w")` | 10 |
| "CSV", "cabecera" | `csv.DictWriter` and `csv.DictReader` | 10 |
| "módulo", "importe las funciones" | two files, `from calculos import ...` | 9 |
| "clase", "atributos", "método" | `class` with `__init__` and `self` | 11 |
| "hereda", "Comercial(Empleado)" | `super().__init__()` | 12 |
| "guarde una lista de líneas" | list inside the class: `self.lineas = []` | 12 |

---

## Step 3: Border words (the classic trap)

| The exercise says | Write |
|---|---|
| "menos de 5" | `< 5` |
| "más de 250", "supera 500" | `> 250`, `> 500` |
| "desde 100", "a partir de 10", "10 o más" | `>= 100`, `>= 10` |
| "entre 1 y 5" | both ends included: `<= 5` after the earlier checks |
| "distinto de 0" | `!= 0` |

Method: **read the words, then check the symbol.** Test the border values (0, 1, 5, 6).

---

## Step 4: Hidden rules

- The **"Comprobación"** line is a free test. Run those numbers by hand through your code.
- **"No uses..."** is a rule. The teacher always bans `while True` with `break`: use a condition or a flag.
- **"Muestra"** = `print`. **"Devuelve"** = `return`.
- Calculate once. Don't repeat the same formula in several places.
- Names in **snake_case**, no typos. Never call a variable `list`, `sum`, `min`, `max`, `dict`, `set`, `str`, `int`.
- **4 spaces** per indentation level.

---

# The skeletons

## 1. Input + calculation + formatted output (R1, R2)

```python
producto = input("Producto: ")
precio = float(input("Precio: "))
cantidad = int(input("Cantidad: "))

subtotal = precio * cantidad
print(f"Subtotal: {subtotal:.2f} EUR")
```

Rules:
- `input()` always returns **text**. Convert with `int()` (counts) or `float()` (money).
- Phone numbers stay as text (leading zeros).
- `:.2f` for money, never for counts.

R1 style (values given in the text, no input):

```python
precio = 25.50
cantidad = 4
subtotal = precio * cantidad
print(f"Subtotal: {subtotal:.2f} EUR")
```

Discount formula:

```python
descuento = subtotal * descuento_pct / 100
total = subtotal - descuento
```

## 2. Decide with if / elif / else (R5)

```python
if cantidad < 5:
    porcentaje = 0
elif cantidad <= 9:
    porcentaje = 5
else:
    porcentaje = 10

descuento = subtotal * porcentaje / 100
total = subtotal - descuento
```

Stock status (check the error case **first**):

```python
if stock < 0:
    print("Error: el stock no puede ser negativo")
elif stock == 0:
    print("Agotado")
elif stock <= 5:
    print("Stock bajo")
else:
    print("Stock suficiente")
```

`and` / `or` (priority order):

```python
es_premium = input("¿Premium? (S/N): ").strip().upper()

if total > 500 or (es_premium == "S" and total > 250):
    print("Prioritario")
else:
    print("Normal")
```

Nested if + normalized text (shipping):

```python
destino = input("Destino: ").strip().upper()

if destino == "PENINSULA":
    if total >= 100:
        envio = 0
    else:
        envio = 6
elif destino == "BALEARES":
    envio = 12
elif destino == "CANARIAS":
    envio = 18
else:
    print("Destino no válido")
```

Tie detection (best channel):

```python
maximo = max(tienda, web, telefono)
empates = 0
if tienda == maximo:
    empates += 1
if web == maximo:
    empates += 1
if telefono == maximo:
    empates += 1

if empates > 1:
    print("Empate")
elif tienda == maximo:
    print("Tienda")
elif web == maximo:
    print("Web")
else:
    print("Teléfono")
```

## 3. Accumulator with `for` (R6)

```python
total = 0                      # BEFORE the loop (once)
for dia in range(1, 8):        # 7 rounds: 1..7
    venta = float(input(f"Ventas del día {dia}: "))
    total += venta
media = total / 7              # AFTER the loop

print(f"Total: {total:.2f} EUR")
print(f"Media: {media:.2f} EUR")
```

`range(n)` gives 0..n-1. `range(1, n + 1)` gives 1..n (the second number is **not included**).

Order codes (PED-001...):

```python
cantidad = int(input("¿Cuántos pedidos? "))
if cantidad < 1:
    print("La cantidad debe ser al menos 1")
else:
    for numero in range(1, cantidad + 1):
        print(f"PED-{numero:03d}")
```

Nested loops:

```python
productos = int(input("Número de productos: "))
meses = int(input("Número de meses: "))

for producto in range(1, productos + 1):
    for mes in range(1, meses + 1):
        print(f"Producto {producto} - Mes {mes}")
```

## 4. Counter with `while` until 0 (R6)

```python
total = 0
contador = 0
importe = float(input("Importe (0 para terminar): "))   # first input BEFORE the loop

while importe != 0:
    total += importe
    contador += 1
    importe = float(input("Importe (0 para terminar): "))  # next input at the END

print(f"Registrados: {contador}")
print(f"Total: {total:.2f} EUR")
```

If you forget the second `input()`, the loop never ends (stop it with Ctrl+C).

## 5. Menu (R6, R11)

```python
print("--- MENÚ ---")
print("1) Alta ficticia")
print("2) Consulta ficticia")
print("3) Informe ficticio")
print("0) Salir")
opcion = input("Opción: ").strip()

while opcion != "0":
    if opcion == "1":
        print("Alta realizada (ficticia)")
    elif opcion == "2":
        print("Consulta realizada (ficticia)")
    elif opcion == "3":
        print("Informe generado (ficticio)")
    else:
        print("Opción no válida")

    print("\n--- MENÚ ---")
    print("1) Alta ficticia")
    print("2) Consulta ficticia")
    print("3) Informe ficticio")
    print("0) Salir")
    opcion = input("Opción: ").strip()

print("Adiós")
```

Keep the option as **text** (no `int()`), so "hola" can't crash it. The goodbye goes **after** the loop. Inside the loop the option can never be "0".

## 6. Lists, dictionaries, list of dictionaries (R7)

List:

```python
compra = ["pan", "leche", "huevos"]
compra.append("queso")      # add at the end
compra.remove("leche")      # remove
compra.sort()               # alphabetical

for numero, item in enumerate(compra, start=1):
    print(f"{numero}: {item}")

print(len(compra), sum([1, 2, 3]), min([1, 2, 3]), max([1, 2, 3]))
```

Position starts at 0: `frutas[0]` is the first item.

Dictionary:

```python
alumno = {"nombre": "Ana", "nota": 7.5}

print(alumno["nota"])            # read
alumno["nota"] = 9.0             # change
alumno["email"] = "ana@ejemplo.com"   # add

for clave, valor in alumno.items():
    print(f"{clave}: {valor}")
```

Careful: `alumno = {...}` **replaces** the whole dictionary. `alumno["clave"] = valor` changes one field.

List of dictionaries (filter and count):

```python
productos = [
    {"nombre": "Teclado", "precio": 25.5, "stock": 4},
    {"nombre": "Ratón", "precio": 12.0, "stock": 0},
]

disponibles = 0
for producto in productos:
    if producto["stock"] > 0:
        print(producto["nombre"])
        disponibles += 1
print(f"Disponibles: {disponibles}")
```

"Not" checks:

```python
if not libro["prestado"]:        # a True/False value is False
if x not in lista:               # item not in list or text
if clave not in diccionario:     # key not in dictionary
```

## 7. Sets and text search (R7)

```python
categorias = ["Perifericos", "Pantallas", "Perifericos", "Audio"]
for categoria in set(categorias):
    print(categoria)

texto = input("Buscar: ").strip().lower()
for producto in productos:
    if texto in producto["nombre"].lower():
        print(producto["nombre"])
```

## 8. Functions that return (R8)

```python
def calcular_subtotal(precio, cantidad):
    return precio * cantidad

def clasificar_stock(stock):
    if stock == 0:
        return "Agotado"
    elif stock <= 5:
        return "Stock bajo"
    else:
        return "Stock suficiente"

def aplicar_descuento(importe, porcentaje=0):
    return importe - importe * porcentaje / 100

def resumir_importes(importes):
    if len(importes) == 0:
        return None
    return {
        "total": sum(importes),
        "media": sum(importes) / len(importes),
        "minimo": min(importes),
        "maximo": max(importes),
    }

def main():
    subtotal = calcular_subtotal(12.5, 4)
    print(f"{subtotal:.2f}")

    resultado = resumir_importes([])
    if resultado is None:
        print("Lista vacía")

main()
```

- `return` gives a value back. `print` only shows it. If the exercise says "must not print", use `return`.
- A function defined but never called does nothing. Call `main()` at the bottom.
- Don't use `input()` inside a function that "only works with its parameters".

Modular budget program (R8E5):

```python
def pedir_datos():
    cliente = input("Cliente: ")
    precio = float(input("Precio: "))
    cantidad = int(input("Cantidad: "))
    descuento = float(input("Descuento (%): "))
    return cliente, precio, cantidad, descuento

def calcular_total(precio, cantidad, descuento):
    subtotal = precio * cantidad
    return subtotal - subtotal * descuento / 100

def mostrar_resumen(cliente, total):
    print(f"Cliente: {cliente}")
    print(f"Total: {total:.2f} EUR")

def main():
    cliente, precio, cantidad, descuento = pedir_datos()
    total = calcular_total(precio, cantidad, descuento)
    mostrar_resumen(cliente, total)

main()
```

## 9. Safe input with try / except (R9): see the full section below

```python
def leer_entero(mensaje, minimo, maximo):
    valido = False
    while not valido:
        try:
            numero = int(input(mensaje))
            if minimo <= numero <= maximo:
                valido = True
            else:
                print(f"Debe estar entre {minimo} y {maximo}")
        except ValueError:
            print("Escribe un número entero")
    return numero
```

Valid price (0 or more):

```python
valido = False
while not valido:
    try:
        precio = float(input("Precio: "))
        if precio >= 0:
            valido = True
        else:
            print("No puede ser negativo")
    except ValueError:
        print("Escribe un número válido")
```

## 10. Text file and CSV (R9)

```python
# Write and read a text file
with open("clientes.txt", "w", encoding="utf-8") as f:
    f.write("Ana;Granada\n")
    f.write("Luis;Madrid\n")

with open("clientes.txt", "r", encoding="utf-8") as f:
    print(f.read())
```

- `"w"` = write (creates the file or **overwrites** it). `"r"` = read. `"a"` = append (adds at the end).
- `\n` is the line break. `write` doesn't add it for you.
- `with open(...)` closes the file automatically.

```python
import csv

# Write CSV from a list of dictionaries
with open("productos.csv", "w", newline="", encoding="utf-8") as f:
    escritor = csv.DictWriter(f, fieldnames=["nombre", "precio", "stock"])
    escritor.writeheader()
    escritor.writerows(productos)

# Read it back
with open("productos.csv", "r", newline="", encoding="utf-8") as f:
    for fila in csv.DictReader(f):
        print(fila)
```

Everything read from a CSV is **text**: use `float(fila["precio"])` or `int(fila["stock"])` to do math.

Modules (two files in the same folder):

```python
# calculos.py
def calcular_subtotal(precio, cantidad):
    return precio * cantidad

def aplicar_descuento(importe, porcentaje):
    return importe - importe * porcentaje / 100
```

```python
# main.py
from calculos import calcular_subtotal, aplicar_descuento

subtotal = calcular_subtotal(12.5, 4)
print(f"{aplicar_descuento(subtotal, 10):.2f}")
```

## 11. Class with methods (R10)

```python
class Producto:
    def __init__(self, nombre, precio, stock):
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

    def mostrar(self):
        print(f"{self.nombre}: {self.precio:.2f} EUR, stock {self.stock}")

    def vender(self, cantidad):
        if cantidad <= self.stock:
            self.stock -= cantidad
            return True
        return False

    def reponer(self, cantidad):
        self.stock += cantidad


p = Producto("Teclado", 25.5, 10)
p.mostrar()
print(p.vender(3))     # True
print(p.vender(50))    # False
```

- `__init__` is the constructor: it runs when you create the object.
- `self` is the object itself. Attributes are `self.nombre`, not just `nombre`.
- A method that must "return a string" uses `return`, not `print` (R10E3 `ficha()`).

## 12. Inheritance and composition (R10)

```python
class Empleado:
    def __init__(self, nombre, salario):
        self.nombre = nombre
        self.salario = salario

class Comercial(Empleado):
    def __init__(self, nombre, salario, ventas):
        super().__init__(nombre, salario)     # initializes the inherited part
        self.ventas = ventas

    def calcular_bonus(self):
        return self.ventas * 0.05

    def resumen(self):
        return f"{self.nombre}: salario {self.salario:.2f}, bonus {self.calcular_bonus():.2f}"


class LineaPedido:
    def __init__(self, producto, precio, cantidad):
        self.producto = producto
        self.precio = precio
        self.cantidad = cantidad

    def subtotal(self):
        return self.precio * self.cantidad


class Pedido:
    def __init__(self, cliente):
        self.cliente = cliente
        self.lineas = []

    def agregar_linea(self, linea):
        self.lineas.append(linea)

    def total(self):
        total = 0
        for linea in self.lineas:
            total += linea.subtotal()
        return total
```

---

# `try / except` explained (R9)

## What an exception is

When Python can't do something, it **stops the program** and shows an error. That error has a **type**, like `ValueError`. With `try / except` you say: "try this, and if this specific error happens, do this instead of crashing."

```python
try:
    edad = int(input("Edad: "))   # if this fails...
    print("Gracias")
except ValueError:                # ...jump here
    print("Eso no es un número")
```

- The `try` block runs normally. If nothing fails, `except` is skipped.
- If an error of that type happens, Python **jumps straight to `except`**, skipping the rest of the `try`.
- If the error is of a **different** type, it is not caught and the program still crashes.

## `ValueError`: "the value has the wrong form"

Raised when the type is right (text) but the content can't be converted.

| This code | Result |
|---|---|
| `int("hola")` | `ValueError` |
| `int("3.5")` | `ValueError` (text with a decimal point can't become `int`) |
| `int("")` (just pressed Enter) | `ValueError` |
| `float("12,5")` | `ValueError` (Python needs a **dot**, not a comma) |
| `int("7")` | works |
| `float("12.5")` | works |

That's why the teacher says it should recover from `"hola"` and `"12,5"`.

## Other exceptions you should know

| Exception | When it happens | Example |
|---|---|---|
| `ValueError` | wrong content for a conversion | `int("hola")` |
| `ZeroDivisionError` | dividing by 0 | `10 / 0`, or an average of an empty list |
| `TypeError` | wrong type for an operation | `"5" + 3`, `"a" * "b"` |
| `KeyError` | dictionary key doesn't exist | `alumno["telefono"]` when there is none |
| `IndexError` | list position doesn't exist | `lista[10]` in a 3-item list |
| `FileNotFoundError` | file to read doesn't exist | `open("noexiste.txt", "r")` |
| `NameError` | using a variable that doesn't exist (usually a typo!) | `print(decuento)` |
| `Exception` | catches almost any error (generic) | use only as a last resort |

`NameError` is the one a typo in a variable name produces. You normally **fix** it, not catch it.

## Catching several exceptions

Two separate `except` blocks:

```python
try:
    a = int(input("Dividendo: "))
    b = int(input("Divisor: "))
    print(a / b)
except ValueError:
    print("Escribe números enteros")
except ZeroDivisionError:
    print("No se puede dividir entre 0")
```

Or one block for several types, using a tuple:

```python
except (ValueError, ZeroDivisionError):
    print("Dato no válido")
```

## `else` and `finally`

```python
try:
    numero = int(input("Número: "))
except ValueError:
    print("No es un número")
else:
    print(f"Has escrito {numero}")      # runs only if NO error happened
finally:
    print("Fin")                        # ALWAYS runs, error or not
```

- `else`: runs only when the `try` worked.
- `finally`: always runs (useful to clean up).

## `as e`: see the error message

```python
try:
    int("hola")
except ValueError as e:
    print(f"Error: {e}")
```

## Reading a file that might not exist

```python
try:
    with open("clientes.txt", "r", encoding="utf-8") as f:
        print(f.read())
except FileNotFoundError:
    print("El archivo todavía no existe")
```

Useful in R11E2: "when loading the CSV, recover the clients if it exists".

## Repeat until valid (the teacher's rule: no `while True` + `break`)

Use a **flag**: a variable that says whether you already have a good value.

```python
valido = False
while not valido:
    try:
        precio = float(input("Precio: "))
        if precio >= 0:
            valido = True
        else:
            print("No puede ser negativo")
    except ValueError:
        print("Escribe un número válido")
```

Trace with `hola`, `12,5`, `-3`, `12.5`:
- `hola` → `ValueError` → message, repeat
- `12,5` → `ValueError` → message, repeat
- `-3` → converts fine, but `-3 >= 0` is false → "No puede ser negativo", repeat
- `12.5` → valid → `valido = True` → the loop ends

## Common mistakes

- **Writing `except:` with no type.** It catches everything, even your own typos. Always name the type.
- **Putting too much inside `try`.** Only put the lines that can fail.
- **Forgetting that `-3` is not a `ValueError`.** It converts fine. You need an `if` for the range check.
- **Using `int()` for a price.** `int("12.5")` fails. Use `float()`.

---

# Odoo: key concepts (for the concept questions)

| Concept | Idea |
|---|---|
| Master vs transactional data | **Master** (contacts, products) stays and is reused. **Transactional** (opportunities, quotes, orders) records one event. |
| Company vs person | A person is **linked** to a company. Don't create a person as a separate company. |
| Expected revenue vs quote total | Expected revenue in CRM is an **estimate**. The quote total is a **real document**. They can differ. |
| Open opportunity vs sale | An open opportunity is **not** a confirmed sale. A quote in draft is **not** a confirmed order either. |
| Search vs filter vs group by | **Search** finds by text. **Filter** limits which records show. **Group by** organizes them into sections. Removing a filter deletes nothing. |
| CRM activity vs calendar event | Not automatically the same. Describe what you **actually see**. |
| Services and stock | A service has **no** inventory movement. |
| CSV import/export | UTF-8 file with a header. Test the import before the final one. Export only the selected records. |
| Avoiding duplicates | **Search before you create.** Update the existing record instead of making a new one. |

---

# Final checklist before handing in

- [ ] Does each `input()` have the right conversion (`int` for counts, `float` for money, text stays text)?
- [ ] `:.2f` on money only. Counts stay plain.
- [ ] Did I check the **borders** (`>` vs `>=`, `range(1, n + 1)`)?
- [ ] Is the accumulator `= 0` **before** the loop?
- [ ] For a `while`: is there an input/update at the **end** of the loop body?
- [ ] Is the `print` at the right indentation (inside or outside the loop)?
- [ ] "Must not print" → I used `return`.
- [ ] No `while True` + `break`.
- [ ] Variable names spelled the same everywhere, snake_case, none called `list`/`sum`/`max`.
- [ ] I ran the "Comprobación" values by hand.
- [ ] Odoo: search before creating, and don't confirm sales.

# Your common mistakes (watch for these)

1. An extra loop: if the output has too many lines, look for a loop you don't need.
2. `:.2f` on a count (`5.00` instead of `5`).
3. Missing `f` before an f-string, so `{dia}` shows literally.
4. Typos in names (`decuento`, `seles`): reread before sending.
5. `alumno = {...}` instead of `alumno["clave"] = valor`.
6. Naming a variable `list`.
7. A dead branch: `elif opcion == 0` inside a `while opcion != 0`.