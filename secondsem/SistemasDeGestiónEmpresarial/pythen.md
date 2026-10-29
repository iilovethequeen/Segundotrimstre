# Explanation of the code

This program is a small system for registering stolen bicycles and tracking whether they've been recovered. It has two classes: `Bicicleta` (one bike) and `RegistroRobos` (the collection of bikes).

---

## Class `Bicicleta`

A simple data container that represents one bike.

### `__init__(self, codigo, propietario, modelo, valor, estado="ROBADA")`

Runs when you create a bike and stores its data:

| Attribute | Meaning |
|---|---|
| `codigo` | Unique identifier of the bike (e.g. frame number) |
| `propietario` | Name of the owner |
| `modelo` | Bike model |
| `valor` | Monetary value of the bike |
| `estado` | Status. Defaults to `"ROBADA"` (stolen); can become `"RECUPERADA"` (recovered) |

```python
b = Bicicleta("abc123", "Ana", "Trek X", 800)
```

---

## Class `RegistroRobos`

The "database" of stolen bikes. Internally it uses a **dictionary** where the key is the bike's code and the value is the `Bicicleta` object. This makes lookups by code fast.

### `__init__(self, bicicletas=None)`

Creates the registry, optionally pre-filled with a list of bikes.

- `self.bicicletas = {}` creates an empty dictionary.
- `for b in (bicicletas or [])` loops over the given list. If `None` was passed, `or []` makes it loop over an empty list, so nothing breaks.
- `self.bicicletas[b.codigo] = b` stores each bike using its code as the key.

### `registrar(self, bicicleta)`

Adds a new bike after validating it. Returns `True` if added, `False` if rejected.

Steps:

1. **Normalizes the code**: `.strip().upper()` removes surrounding spaces and uppercases it (`" abc1 "` becomes `"ABC1"`).
2. **Rejects** (returns `False`) if:
   - the code is empty,
   - the code already exists in the dictionary (duplicate),
   - the value is negative,
   - the owner is empty or only spaces.
3. **Saves**: updates the bike's code to the normalized one, stores it in the dictionary, and returns `True`.

### `listar(self)`

Returns all registered bikes as a list of `Bicicleta` objects. `self.bicicletas.values()` gives the objects (without the keys) and `list(...)` converts them into a list.

### `recuperar(self, codigo)`

Marks a stolen bike as recovered.

- Checks that the code exists in the dictionary.
- Checks that its status is currently `"ROBADA"`.
- If both are true, changes it to `"RECUPERADA"` and returns `True`.
- Otherwise returns `False` (code doesn't exist, or the bike was already recovered).

### `buscar(self, texto)`

Searches bikes by a fragment of the owner's name or the model, ignoring case.

- Converts `texto` to lowercase.
- Loops through every bike and checks if the text appears in the lowercase owner **or** model (`in` checks for substrings).
- Collects matches in a list and returns it.

Example: `buscar("tre")` finds a bike with model `"Trek X"`.

### `resumen(self)`

Returns a dictionary with statistics:

| Key | Calculation |
|---|---|
| `total` | Number of bikes in the registry (`len`) |
| `robadas` | Count of bikes with status `"ROBADA"` |
| `recuperadas` | Count of bikes with status `"RECUPERADA"` |
| `valor_pendiente` | Sum of the values of bikes still stolen |

The `sum(1 for ... if ...)` pattern is a generator expression that counts matching items by adding 1 for each.

---

## Potential issues worth knowing

1. **`recuperar` doesn't normalize the code.** `registrar` stores codes in uppercase, so `recuperar("abc123")` fails while `recuperar("ABC123")` works. Fix: `codigo = codigo.strip().upper()` at the start.
2. **Value of 0 is accepted.** The TODO says the value must be *positive*, but `valor < 0` allows 0. Use `valor <= 0` if zero should be rejected.
3. **Bikes passed to the constructor skip validation** and aren't uppercased, so they can be inconsistent with bikes added via `registrar`. Calling `self.registrar(b)` in the loop would fix this.
4. **`buscar` with empty text** matches every bike, since `""` is contained in any string.