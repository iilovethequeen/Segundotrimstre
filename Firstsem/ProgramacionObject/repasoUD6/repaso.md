# ¿Cuántos archivos .java necesito?

## La regla es simple

**1 clase o interfaz pública = 1 archivo .java**

Y el nombre del archivo **debe coincidir exactamente** con el nombre de la clase.

---

## ¿Cómo saber cuántos archivos necesito?

Cuenta cuántas veces escribes estas palabras clave con `public`:

| Lo que escribes | Necesita su propio archivo |
|---|---|
| `public class X` | `X.java` |
| `public abstract class X` | `X.java` |
| `public interface X`  `X.java` |
| `public enum X` | `X.java` |

**Número de archivos = número de `public class` / `public interface` que tienes.**

---

## Ejemplo práctico

Imagina que tu ejercicio tiene esto:

```
public abstract class Animal        → Animal.java
public interface Comible            → Comible.java
public interface Movible            → Movible.java
public class Perro extends Animal   → Perro.java
public class Gato extends Animal    → Gato.java
public class Main                   → Main.java
```

**Total: 6 clases/interfaces = 6 archivos .java**

---

## La única excepción

Puedes poner una clase SIN `public` en el mismo archivo que otra:

```java
// Archivo: Perro.java
public class Perro { ... }

class Ayudante { ... }  // sin "public" → permitido en el mismo archivo
```

Pero en los exámenes de clase **casi nunca se hace esto**, así que no te preocupes.

---

## Regla de oro para el examen

> Escribe todas tus clases e interfaces → cuenta cuántas son → eso es cuántos archivos .java necesitas.