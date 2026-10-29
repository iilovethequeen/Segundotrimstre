# PMDM UD1 – Exam Kit (Kotlin + Android Views/XML)

Ready-made code for all 14 exercises (8 from the *Cuaderno de enunciados* + 6 from *Relación 2*), plus a cheat sheet of every piece you need.

**How to use this in the exam**
1. Read what they ask → find the closest exercise below → copy → change names/numbers/rules.
2. Every exercise follows the same 5 steps: **read inputs → validate → calculate (function) → show result → clear/reset**.
3. Test the 3–4 cases from the statement before submitting. Take screenshots.

> ⚠️ **Your `id`s must match** between XML (`@+id/xxx`) and Kotlin (`R.id.xxx`). If the base files already have ids, use THEIRS, not mine.

---

## 0. Project setup (do this every time)

- New project → **Empty Views Activity** (NOT Compose) → Language: Kotlin.
- Keep your own `package com.xxx...` line at the top of `MainActivity.kt`.
- Edit `res/layout/activity_main.xml` and `MainActivity.kt`.
- If the template added lines with `enableEdgeToEdge()` / `ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)...)` → **delete them** (or keep `android:id="@+id/main"` on the root layout), otherwise the app can crash.

### Kotlin skeleton (copy-paste base)

```kotlin
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Calculation functions go HERE (outside onCreate, they don't touch widgets)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)      // 1) FIRST this

        val etDato = findViewById<EditText>(R.id.etDato)   // 2) THEN find views
        val btnOk = findViewById<Button>(R.id.btnOk)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnOk.setOnClickListener {                  // 3) events
            // read → validate → calculate → show
        }
    }
}
```

### XML skeleton (copy-paste base)

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/etDato"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Dato"
        android:inputType="number" />

    <Button
        android:id="@+id/btnOk"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Aceptar" />

    <TextView
        android:id="@+id/tvResultado"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Resultado"
        android:textSize="18sp" />
</LinearLayout>
```

---

## 1. Cheat sheet – what each thing is for

### Kotlin basics

| Concept | Code | What it's for |
|---|---|---|
| Constant | `val nombre = "Ana"` | Value that never changes |
| Variable | `var contador = 0` | Value that changes (`contador++`, `contador += 1`) |
| Types | `Int` (7), `Double` (7.5), `String` ("hi"), `Boolean` (true/false) | Whole number / decimal / text / yes-no |
| String template | `"Hola $nombre, total ${a + b}"` | Put variables/expressions inside text. `$x` for a name, `${...}` for an expression |
| Arithmetic | `+ - * / %` | `7 / 2 = 3` with Int, `7 / 2.0 = 3.5` with Double. `%` = remainder |
| Comparison | `== != < > <= >=` | Conditions |
| Logic | `&&` (and) `||` (or) `!` (not) | Combine conditions |
| Range | `x in 1..10`, `x !in 1..10` | Inclusive range check (1 and 10 included) |
| if/else (expression) | `val r = if (x > 5) "A" else "B"` | Choose between two values |
| when | `when { x < 5 -> "A"; x < 7 -> "B"; else -> "C" }` | Many conditions. First true one wins. Returns a value |
| when (value) | `when (op) { "+" -> a + b; "-" -> a - b; else -> 0.0 }` | Compare one variable to many values |
| when (ranges) | `when (n) { in 1..3 -> "x"; in 4..6 -> "y"; else -> "z" }` | Categories by range |
| Function | `fun doble(n: Int): Int { return n * 2 }` or `fun doble(n: Int) = n * 2` | Reusable calculation (parameters in, `return` out) |
| Safe conversion | `texto.toIntOrNull()` / `toDoubleOrNull()` | Returns the number or `null` if invalid/empty. **Never crashes** |
| Null check | `if (x == null \|\| x < 0)` | Check `null` FIRST, then compare (smart-cast makes `x` non-null afterwards) |
| Trim | `texto.trim()` | Removes spaces at the ends (`"  "` → `""`) |
| Empty? | `texto.isEmpty()` / `isBlank()` / `isNotEmpty()` | Detect empty text |
| Min / max | `minOf(a, b)`, `maxOf(a, b)` | Apply a cap/limit |
| 2 decimals | `String.format(Locale("es","ES"), "%.2f €", x)` | Shows `12,50 €` |
| Log | `Log.d("PMDM", "mensaje")` | Prints to Logcat (filter by tag `PMDM`) |

### Android views

| Concept | Code | What it's for |
|---|---|---|
| Find a view | `findViewById<EditText>(R.id.etNombre)` | Link XML widget ↔ Kotlin (**after** `setContentView`) |
| Read EditText | `et.text.toString()` | Get the text as `String` |
| Read + clean | `et.text.toString().trim()` | Text without edge spaces |
| Show text | `tv.text = "Hola"` | Change a TextView |
| Click event | `btn.setOnClickListener { ... }` | Code that runs when pressed |
| Exit listener early | `return@setOnClickListener` | Stop after an error (does NOT close the app) |
| Clear EditText | `et.setText("")` | Empty a field |
| CheckBox | `cb.isChecked` | `true`/`false` |
| RadioGroup | `rg.checkedRadioButtonId` | id of selected radio, **`-1` = none** |
| Clear RadioGroup | `rg.clearCheck()` | Deselect all |
| Text color | `tv.setTextColor(Color.parseColor("#2E7D32"))` | Change color from Kotlin (`import android.graphics.Color`) |

### XML attributes

| Attribute | Meaning |
|---|---|
| `android:id="@+id/nombre"` | Creates the id (in Kotlin: `R.id.nombre`) |
| `layout_width/height` | `match_parent` (fill) / `wrap_content` (fit content) |
| `android:hint` | Grey placeholder text in EditText |
| `android:text` | Fixed text |
| `android:inputType` | `text`, `number` (integers), `numberDecimal` (decimals), `numberSigned` (allows `-`), `numberDecimal\|numberSigned` (both) |
| `android:orientation` | `vertical` / `horizontal` |
| `android:padding` / `layout_margin...` | Space inside / outside a view |
| `android:textSize="16sp"` | Letter size (sp for text, dp for the rest) |
| `style="@style/Nombre"` | Apply a reusable style |
| `android:background="@drawable/fondo_panel"` | Rounded background |

### Typical errors to avoid
- `findViewById` **before** `setContentView` → crash.
- id typo (`etNombre` vs `etnombre`) → red `R.id...` or crash.
- Forgetting `.toString()` on `et.text`.
- Calculating before validating.
- `ScrollView` with **more than 1** direct child → error. Put one `LinearLayout` inside.
- Using `Int` division when you need decimals (`(f - 32) * 5 / 9` with Double: write `5.0 / 9.0`).
- To type negative numbers (test `-1`) the EditText needs `numberSigned`.
- If the keyboard types a comma `,` for decimals: `texto.replace(',', '.').toDoubleOrNull()`.

---

## 2. Shared resources for Relación 2 (styles, colors, drawable)

**`res/values/colors.xml`** (add inside `<resources>`):
```xml
<color name="azul_principal">#19364F</color>
<color name="turquesa">#008E9B</color>
<color name="fondo_app">#F3F7FA</color>
```

**`res/values/styles.xml`** (new file):
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="CampoFormulario">
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:textSize">16sp</item>
        <item name="android:padding">12dp</item>
        <item name="android:layout_marginBottom">8dp</item>
    </style>

    <style name="BotonPrincipal">
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:textColor">#FFFFFF</item>
        <item name="android:backgroundTint">@color/turquesa</item>
        <item name="android:layout_marginBottom">8dp</item>
    </style>

    <style name="TextoResultado">
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:textSize">18sp</item>
        <item name="android:textColor">@color/azul_principal</item>
        <item name="android:padding">12dp</item>
    </style>

    <!-- For label/figure pairs (exercise 2.5) -->
    <style name="Etiqueta">
        <item name="android:layout_width">0dp</item>
        <item name="android:layout_weight">1</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:textSize">16sp</item>
        <item name="android:textColor">#5A6B78</item>
    </style>
    <style name="Cifra">
        <item name="android:layout_width">wrap_content</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:textSize">16sp</item>
        <item name="android:textStyle">bold</item>
        <item name="android:textColor">@color/azul_principal</item>
    </style>
</resources>
```

**`res/drawable/fondo_panel.xml`** (new file, right-click `drawable` → New → Drawable resource file):
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/fondo_app" />
    <corners android:radius="16dp" />
    <stroke android:width="1dp" android:color="#CDDDE3" />
</shape>
```
Use it with: `android:background="@drawable/fondo_panel"` + `android:padding="12dp"`.

**Money formatting helper** (put it in the class, before `onCreate`):
```kotlin
// import java.util.Locale
private fun euros(x: Double): String = String.format(Locale("es", "ES"), "%.2f €", x)
```

---
---

# PART A – Cuaderno de enunciados (8 exercises)

## A01 – Tarjeta de presentación
**Use it for:** `val`/`var`, String templates, click counter, Logcat. Key trick: **`var contador` declared OUTSIDE the listener** so it remembers its value.

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/tvTarjeta"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="20sp"
        android:text="Tarjeta" />

    <Button
        android:id="@+id/btnPulsar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Pulsar" />
</LinearLayout>
```

**Kotlin**
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nombre: String = "Ana"
        val edad: Int = 20
        val ciudad = "Granada"          // ampliación
        var contador = 0                // OUTSIDE the listener

        val tvTarjeta = findViewById<TextView>(R.id.tvTarjeta)
        val btnPulsar = findViewById<Button>(R.id.btnPulsar)

        tvTarjeta.text = "Nombre: $nombre\nEdad: $edad\nCiudad: $ciudad\nTodavía no has pulsado"

        btnPulsar.setOnClickListener {
            contador++
            val mensaje = "Nombre: $nombre\nEdad: $edad\nCiudad: $ciudad\nPulsaciones: $contador"
            tvTarjeta.text = mensaje
            Log.d("PMDM", mensaje)
        }
    }
}
```
Logcat: filter by `PMDM` in Android Studio's Logcat tab and screenshot it.

---

## A02 – Ticket de compra
**Use it for:** reading numbers from EditText, safe conversion, validation, `Double * Int`.

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/etPrecio"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Precio unitario"
        android:inputType="numberDecimal|numberSigned" />

    <EditText
        android:id="@+id/etCantidad"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Cantidad"
        android:inputType="numberSigned" />

    <Button
        android:id="@+id/btnCalcular"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Calcular" />

    <TextView
        android:id="@+id/tvTicket"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="18sp"
        android:text="Ticket" />
</LinearLayout>
```

**Kotlin**
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val etPrecio = findViewById<EditText>(R.id.etPrecio)
    val etCantidad = findViewById<EditText>(R.id.etCantidad)
    val btnCalcular = findViewById<Button>(R.id.btnCalcular)
    val tvTicket = findViewById<TextView>(R.id.tvTicket)

    btnCalcular.setOnClickListener {
        val precio = etPrecio.text.toString().toDoubleOrNull()
        val cantidad = etCantidad.text.toString().toIntOrNull()

        if (precio == null || cantidad == null || precio < 0 || cantidad <= 0) {
            tvTicket.text = "Introduce números válidos"
            return@setOnClickListener
        }

        val total = precio * cantidad
        tvTicket.text = "$precio x $cantidad = $total"
    }
}
```
*Ampliación (descuento):* read `etDescuento` as `Double`, validate `descuento in 0.0..total`, show `final = total - descuento`.

---

## A03 – Control de acceso (18–65)
**Use it for:** `if/else`, `&&`, `||`, Boolean, checking `null` first.

**XML:** same as A02 with one `EditText` `etEdad` (`inputType="numberSigned"`), `btnComprobar`, `tvResultado`.

**Kotlin**
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val etEdad = findViewById<EditText>(R.id.etEdad)
    val btnComprobar = findViewById<Button>(R.id.btnComprobar)
    val tvResultado = findViewById<TextView>(R.id.tvResultado)

    btnComprobar.setOnClickListener {
        val edad = etEdad.text.toString().toIntOrNull()

        // null FIRST, then compare. || for the invalid range
        if (edad == null || edad < 0 || edad > 120) {
            tvResultado.text = "Edad no válida"
            return@setOnClickListener
        }

        val puedeEntrar = edad >= 18 && edad <= 65     // && for access

        tvResultado.text = when {
            puedeEntrar && edad == 18 -> "Acceso permitido. Entrada gratuita"   // ampliación
            puedeEntrar -> "Acceso permitido"
            edad < 18 -> "No puede entrar: menor de edad"
            else -> "No puede entrar: supera la edad máxima"
        }
    }
}
```

---

## A04 – Evaluador de notas
**Use it for:** `when` with ordered conditions, range validation.

**XML:** `etNota` (`number`), `btnEvaluar`, `tvResultado`, `tvSuperada` (extra).

**Kotlin**
```kotlin
btnEvaluar.setOnClickListener {
    val nota = etNota.text.toString().toIntOrNull()

    if (nota == null || nota !in 0..10) {
        tvResultado.text = "Nota no válida"
        tvSuperada.text = ""
        return@setOnClickListener
    }

    // Ordered: the first true condition wins
    val calificacion = when {
        nota <= 4 -> "Suspenso"
        nota <= 6 -> "Aprobado"
        nota <= 8 -> "Notable"
        else -> "Sobresaliente"
    }
    tvResultado.text = calificacion
    tvSuperada.text = if (nota >= 5) "Superada" else "No superada"   // ampliación
}
```
(Make sure `etNota` is `numberSigned` so `-1` can be typed.)

---

## A05 – Conversor de temperatura
**Use it for:** functions with parameters and `return`, called from different buttons.

**XML:** `etTemperatura` (`numberDecimal|numberSigned`), buttons `btnCaF`, `btnFaC`, `btnCaK`, `tvResultado`.

**Kotlin**
```kotlin
class MainActivity : AppCompatActivity() {

    fun celsiusAFahrenheit(celsius: Double): Double {
        return celsius * 9.0 / 5.0 + 32
    }

    fun fahrenheitACelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5.0 / 9.0
    }

    fun celsiusAKelvin(celsius: Double): Double = celsius + 273.15   // ampliación

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etTemperatura = findViewById<EditText>(R.id.etTemperatura)
        val btnCaF = findViewById<Button>(R.id.btnCaF)
        val btnFaC = findViewById<Button>(R.id.btnFaC)
        val btnCaK = findViewById<Button>(R.id.btnCaK)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnCaF.setOnClickListener {
            val t = etTemperatura.text.toString().toDoubleOrNull()
            if (t == null) {
                tvResultado.text = "Introduce una temperatura válida"
                return@setOnClickListener
            }
            tvResultado.text = "$t ºC = ${celsiusAFahrenheit(t)} ºF"
        }

        btnFaC.setOnClickListener {
            val t = etTemperatura.text.toString().toDoubleOrNull()
            if (t == null) {
                tvResultado.text = "Introduce una temperatura válida"
                return@setOnClickListener
            }
            tvResultado.text = "$t ºF = ${fahrenheitACelsius(t)} ºC"
        }

        btnCaK.setOnClickListener {
            val t = etTemperatura.text.toString().toDoubleOrNull()
            if (t == null) {
                tvResultado.text = "Introduce una temperatura válida"
                return@setOnClickListener
            }
            tvResultado.text = "$t ºC = ${celsiusAKelvin(t)} K"
        }
    }
}
```

---

## A06 – Saludo personalizado
**Use it for:** building the XML yourself, `trim()`, `isEmpty()`.

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/etNombre"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Nombre"
        android:inputType="textPersonName" />

    <EditText
        android:id="@+id/etCiudad"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Ciudad"
        android:inputType="text" />

    <Button
        android:id="@+id/btnSaludar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Saludar" />

    <Button
        android:id="@+id/btnLimpiar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Limpiar" />

    <TextView
        android:id="@+id/tvResultado"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="18sp"
        android:text="Introduce tus datos" />
</LinearLayout>
```

**Kotlin**
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val etNombre = findViewById<EditText>(R.id.etNombre)
    val etCiudad = findViewById<EditText>(R.id.etCiudad)
    val btnSaludar = findViewById<Button>(R.id.btnSaludar)
    val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
    val tvResultado = findViewById<TextView>(R.id.tvResultado)

    btnSaludar.setOnClickListener {
        val nombre = etNombre.text.toString().trim()
        val ciudad = etCiudad.text.toString().trim()

        if (nombre.isEmpty() || ciudad.isEmpty()) {
            tvResultado.text = "Completa el nombre y la ciudad"
        } else {
            tvResultado.text = "Hola, $nombre, te damos la bienvenida desde $ciudad"
        }
    }

    btnLimpiar.setOnClickListener {
        etNombre.setText("")
        etCiudad.setText("")
        tvResultado.text = "Introduce tus datos"
    }
}
```

---

## A07 – Calculadora de cuatro operaciones
**Use it for:** local function inside `onCreate`, `when` on a String, division by zero, 4 buttons → 1 function.

**XML:** `etNumero1`, `etNumero2` (`numberDecimal|numberSigned`), buttons `btnSumar`, `btnRestar`, `btnMultiplicar`, `btnDividir`, `btnLimpiar`, `tvResultado`.

**Kotlin**
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val etNumero1 = findViewById<EditText>(R.id.etNumero1)
    val etNumero2 = findViewById<EditText>(R.id.etNumero2)
    val btnSumar = findViewById<Button>(R.id.btnSumar)
    val btnRestar = findViewById<Button>(R.id.btnRestar)
    val btnMultiplicar = findViewById<Button>(R.id.btnMultiplicar)
    val btnDividir = findViewById<Button>(R.id.btnDividir)
    val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
    val tvResultado = findViewById<TextView>(R.id.tvResultado)

    // Local function: can use the views declared above
    fun calcular(operacion: String) {
        val a = etNumero1.text.toString().toDoubleOrNull()
        val b = etNumero2.text.toString().toDoubleOrNull()

        if (a == null || b == null) {
            tvResultado.text = "Introduce dos números válidos"
            return                      // stops the function, NOT the app
        }
        if (operacion == "/" && b == 0.0) {
            tvResultado.text = "No se puede dividir entre cero"
            return
        }

        val resultado = when (operacion) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> a / b
            else -> 0.0
        }
        tvResultado.text = "Resultado: $resultado"
    }

    btnSumar.setOnClickListener { calcular("+") }
    btnRestar.setOnClickListener { calcular("-") }
    btnMultiplicar.setOnClickListener { calcular("*") }
    btnDividir.setOnClickListener { calcular("/") }

    btnLimpiar.setOnClickListener {
        etNumero1.setText("")
        etNumero2.setText("")
        tvResultado.text = "Resultado: "
    }
}
```

---

## A08 – Presupuesto semanal (reto final)
**Use it for:** function with return, validate 3 fields, `when` for category, Limpiar.

**XML:** `etIngresos`, `etComida`, `etTransporte` (`numberDecimal|numberSigned`), `btnCalcular`, `btnLimpiar`, `tvResultado`.

**Kotlin**
```kotlin
class MainActivity : AppCompatActivity() {

    fun saldo(ingresos: Double, comida: Double, transporte: Double): Double {
        return ingresos - comida - transporte
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etIngresos = findViewById<EditText>(R.id.etIngresos)
        val etComida = findViewById<EditText>(R.id.etComida)
        val etTransporte = findViewById<EditText>(R.id.etTransporte)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val textoInicial = "Introduce tus datos"
        tvResultado.text = textoInicial

        btnCalcular.setOnClickListener {
            val ingresos = etIngresos.text.toString().toDoubleOrNull()
            val comida = etComida.text.toString().toDoubleOrNull()
            val transporte = etTransporte.text.toString().toDoubleOrNull()

            if (ingresos == null || comida == null || transporte == null ||
                ingresos < 0 || comida < 0 || transporte < 0) {
                tvResultado.text = "Entrada no válida"
                return@setOnClickListener
            }

            val s = saldo(ingresos, comida, transporte)
            val categoria = when {
                s < 0 -> "Déficit"
                s == 0.0 -> "Equilibrio"
                else -> "Superávit"
            }
            tvResultado.text = "Saldo: $s\n$categoria"
        }

        btnLimpiar.setOnClickListener {
            etIngresos.setText("")
            etComida.setText("")
            etTransporte.setText("")
            tvResultado.text = textoInicial
        }
    }
}
```
*Ampliación (ocio opcional):* `val ocio = etOcio.text.toString().ifEmpty { "0" }.toDoubleOrNull()` → if `null` or `< 0` error; `gastoTotal = comida + transporte + ocio`.

---
---

# PART B – Relación 2 (6 exercises)

> For all of these: create `colors.xml`, `styles.xml`, `fondo_panel.xml` from **section 2**, and add `import java.util.Locale` plus the `euros()` helper where money is shown.

## B01 – Registro de entrenamiento
**Use it for:** text + range validation, function with `Int`, `when` with ranges, reusable styles.

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp"
    android:background="@color/fondo_app">

    <EditText
        android:id="@+id/etNombre"
        style="@style/CampoFormulario"
        android:hint="Nombre"
        android:inputType="textPersonName" />

    <EditText
        android:id="@+id/etMinutos"
        style="@style/CampoFormulario"
        android:hint="Minutos (1-300)"
        android:inputType="number" />

    <EditText
        android:id="@+id/etIntensidad"
        style="@style/CampoFormulario"
        android:hint="Intensidad (1-10)"
        android:inputType="number" />

    <Button
        android:id="@+id/btnRegistrar"
        style="@style/BotonPrincipal"
        android:text="Registrar" />

    <Button
        android:id="@+id/btnLimpiar"
        style="@style/BotonPrincipal"
        android:text="Limpiar" />

    <TextView
        android:id="@+id/tvResultado"
        style="@style/TextoResultado"
        android:background="@drawable/fondo_panel"
        android:text="Introduce los datos de tu sesión" />
</LinearLayout>
```

**Kotlin**
```kotlin
class MainActivity : AppCompatActivity() {

    fun calcularPuntos(minutos: Int, intensidad: Int): Int = minutos * intensidad

    fun descripcion(categoria: String): String = when (categoria) {   // ampliación
        "Suave" -> "Sesión ligera, ideal para recuperar."
        "Moderada" -> "Buen ritmo de entrenamiento."
        else -> "Esfuerzo alto: recuerda descansar."
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etMinutos = findViewById<EditText>(R.id.etMinutos)
        val etIntensidad = findViewById<EditText>(R.id.etIntensidad)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val textoInicial = "Introduce los datos de tu sesión"

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val minutos = etMinutos.text.toString().toIntOrNull()
            val intensidad = etIntensidad.text.toString().toIntOrNull()

            if (nombre.isEmpty()) {
                tvResultado.text = "Nombre obligatorio"
                return@setOnClickListener
            }
            if (minutos == null || minutos !in 1..300) {
                tvResultado.text = "Minutos no válidos (1-300)"
                return@setOnClickListener
            }
            if (intensidad == null || intensidad !in 1..10) {
                tvResultado.text = "Intensidad no válida (1-10)"
                return@setOnClickListener
            }

            val categoria = when (intensidad) {
                in 1..3 -> "Suave"
                in 4..6 -> "Moderada"
                else -> "Intensa"
            }
            val puntos = calcularPuntos(minutos, intensidad)

            tvResultado.text = "Nombre: $nombre\nMinutos: $minutos\nIntensidad: $intensidad\n" +
                    "Categoría: $categoria\nPuntos: $puntos\n${descripcion(categoria)}"
        }

        btnLimpiar.setOnClickListener {
            etNombre.setText("")
            etMinutos.setText("")
            etIntensidad.setText("")
            tvResultado.text = textoInicial
        }
    }
}
```

---

## B02 – Pedido con gastos de envío
**Use it for:** decimals, tiered `when`, CheckBox, a function with 3 parameters (`Double, Double, Boolean`).

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp"
    android:background="@color/fondo_app">

    <EditText
        android:id="@+id/etImporte"
        style="@style/CampoFormulario"
        android:hint="Importe de los artículos (€)"
        android:inputType="numberDecimal" />

    <EditText
        android:id="@+id/etPeso"
        style="@style/CampoFormulario"
        android:hint="Peso (kg)"
        android:inputType="numberDecimal" />

    <CheckBox
        android:id="@+id/cbUrgente"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Envío urgente" />

    <Button
        android:id="@+id/btnCalcular"
        style="@style/BotonPrincipal"
        android:text="Calcular" />

    <Button
        android:id="@+id/btnLimpiar"
        style="@style/BotonPrincipal"
        android:text="Limpiar" />

    <TextView
        android:id="@+id/tvResultado"
        style="@style/TextoResultado"
        android:background="@drawable/fondo_panel"
        android:text="Introduce los datos del pedido" />
</LinearLayout>
```

**Kotlin**
```kotlin
import java.util.Locale
// ...
class MainActivity : AppCompatActivity() {

    private fun euros(x: Double): String = String.format(Locale("es", "ES"), "%.2f €", x)

    fun costeEnvio(peso: Double, importe: Double, urgente: Boolean): Double {
        val ordinario = if (importe >= 60.0) {
            0.0
        } else {
            when {
                peso <= 1.0 -> 3.5
                peso <= 5.0 -> 6.5
                else -> 9.5
            }
        }
        return if (urgente) ordinario + 4.0 else ordinario
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etImporte = findViewById<EditText>(R.id.etImporte)
        val etPeso = findViewById<EditText>(R.id.etPeso)
        val cbUrgente = findViewById<CheckBox>(R.id.cbUrgente)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val textoInicial = "Introduce los datos del pedido"

        btnCalcular.setOnClickListener {
            val importe = etImporte.text.toString().toDoubleOrNull()
            val peso = etPeso.text.toString().toDoubleOrNull()

            if (importe == null || importe < 0 || peso == null || peso <= 0 || peso > 30) {
                tvResultado.text = "Datos no válidos: importe ≥ 0 y peso entre 0 y 30 kg"
                return@setOnClickListener
            }

            val urgente = cbUrgente.isChecked
            val envio = costeEnvio(peso, importe, urgente)
            val total = importe + envio

            tvResultado.text = "Artículos: ${euros(importe)}\n" +
                    "Envío${if (urgente) " (urgente)" else ""}: ${euros(envio)}\n" +
                    "TOTAL: ${euros(total)}"
        }

        btnLimpiar.setOnClickListener {
            etImporte.setText("")
            etPeso.setText("")
            cbUrgente.isChecked = false
            tvResultado.text = textoInicial
        }
    }
}
```
*Ampliación ENVIO2:* add `etPromo`; after `costeEnvio`: `val envioFinal = if (promo == "ENVIO2") maxOf(0.0, envio - 2.0) else envio`.

---

## B03 – Aparcamiento por tramos
**Use it for:** integer division (round up), RadioGroup, cap (`minOf`), discount after cap, rounded card.

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp"
    android:background="@color/fondo_app">

    <EditText
        android:id="@+id/etMinutos"
        style="@style/CampoFormulario"
        android:hint="Minutos (1-1440)"
        android:inputType="number" />

    <RadioGroup
        android:id="@+id/rgVehiculo"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <RadioButton
            android:id="@+id/rbCoche"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Coche" />

        <RadioButton
            android:id="@+id/rbMoto"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Moto" />
    </RadioGroup>

    <CheckBox
        android:id="@+id/cbNocturno"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Tarifa nocturna (-20 %)" />

    <Button
        android:id="@+id/btnCalcular"
        style="@style/BotonPrincipal"
        android:text="Calcular" />

    <TextView
        android:id="@+id/tvResultado"
        style="@style/TextoResultado"
        android:background="@drawable/fondo_panel"
        android:text="Introduce los datos" />

    <TextView
        android:id="@+id/tvRegla"
        style="@style/TextoResultado"
        android:text="" />
</LinearLayout>
```

**Kotlin**
```kotlin
import java.util.Locale
// ...
class MainActivity : AppCompatActivity() {

    private fun euros(x: Double): String = String.format(Locale("es", "ES"), "%.2f €", x)

    // Base amount (before night discount), already applying the daily cap
    fun importeBase(minutos: Int, esCoche: Boolean): Double {
        val horas = (minutos + 59) / 60               // integer division = round up
        val tarifa = if (esCoche) 1.80 else 1.10
        val tope = if (esCoche) 15.0 else 9.0
        return minOf(horas * tarifa, tope)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etMinutos = findViewById<EditText>(R.id.etMinutos)
        val rgVehiculo = findViewById<RadioGroup>(R.id.rgVehiculo)
        val cbNocturno = findViewById<CheckBox>(R.id.cbNocturno)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val tvRegla = findViewById<TextView>(R.id.tvRegla)

        btnCalcular.setOnClickListener {
            val minutos = etMinutos.text.toString().toIntOrNull()
            val seleccion = rgVehiculo.checkedRadioButtonId

            if (minutos == null || minutos !in 1..1440 || seleccion == -1) {
                tvResultado.text = "Error: minutos entre 1 y 1440 y elige un vehículo"
                tvRegla.text = ""
                return@setOnClickListener
            }

            val esCoche = seleccion == R.id.rbCoche
            val horas = (minutos + 59) / 60
            val tarifa = if (esCoche) 1.80 else 1.10
            val tope = if (esCoche) 15.0 else 9.0

            val base = importeBase(minutos, esCoche)
            val nocturno = cbNocturno.isChecked
            val descuento = if (nocturno) base * 0.20 else 0.0
            val total = base - descuento

            tvResultado.text = "Vehículo: ${if (esCoche) "Coche" else "Moto"}\n" +
                    "Horas: $horas\n" +
                    "Base: ${euros(base)}\n" +
                    "Descuento nocturno: ${euros(descuento)}\n" +
                    "TOTAL: ${euros(total)}"

            // ampliación
            tvRegla.text = if (horas * tarifa > tope) "Regla: máximo diario" else "Regla: tarifa normal"
        }
    }
}
```

---

## B04 – Boletín de evaluación
**Use it for:** weighted average, combined rules (`&&`), reasons, text colour from Kotlin, two result TextViews.

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp"
    android:background="@color/fondo_app">

    <EditText
        android:id="@+id/etBloque1"
        style="@style/CampoFormulario"
        android:hint="Bloque 1 (20 %)"
        android:inputType="numberDecimal|numberSigned" />

    <EditText
        android:id="@+id/etBloque2"
        style="@style/CampoFormulario"
        android:hint="Bloque 2 (30 %)"
        android:inputType="numberDecimal|numberSigned" />

    <EditText
        android:id="@+id/etBloque3"
        style="@style/CampoFormulario"
        android:hint="Bloque 3 (50 %)"
        android:inputType="numberDecimal|numberSigned" />

    <Button
        android:id="@+id/btnEvaluar"
        style="@style/BotonPrincipal"
        android:text="Evaluar" />

    <Button
        android:id="@+id/btnLimpiar"
        style="@style/BotonPrincipal"
        android:text="Limpiar" />

    <TextView
        android:id="@+id/tvNota"
        style="@style/TextoResultado"
        android:background="@drawable/fondo_panel"
        android:text="Introduce las notas" />

    <TextView
        android:id="@+id/tvEstado"
        style="@style/TextoResultado"
        android:textStyle="bold"
        android:text="" />
</LinearLayout>
```

**Kotlin**
```kotlin
import android.graphics.Color
import java.util.Locale
// ...
class MainActivity : AppCompatActivity() {

    fun calcularMedia(b1: Double, b2: Double, b3: Double): Double {
        return (b1 * 20 + b2 * 30 + b3 * 50) / 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etBloque1 = findViewById<EditText>(R.id.etBloque1)
        val etBloque2 = findViewById<EditText>(R.id.etBloque2)
        val etBloque3 = findViewById<EditText>(R.id.etBloque3)
        val btnEvaluar = findViewById<Button>(R.id.btnEvaluar)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val tvNota = findViewById<TextView>(R.id.tvNota)
        val tvEstado = findViewById<TextView>(R.id.tvEstado)
        val textoInicial = "Introduce las notas"

        btnEvaluar.setOnClickListener {
            val b1 = etBloque1.text.toString().toDoubleOrNull()
            val b2 = etBloque2.text.toString().toDoubleOrNull()
            val b3 = etBloque3.text.toString().toDoubleOrNull()

            if (b1 == null || b2 == null || b3 == null ||
                b1 !in 0.0..10.0 || b2 !in 0.0..10.0 || b3 !in 0.0..10.0) {
                tvNota.text = "Notas no válidas (0-10)"
                tvEstado.text = ""
                return@setOnClickListener
            }

            val media = calcularMedia(b1, b2, b3)
            val mediaOk = media >= 5.0
            val bloquesOk = b1 >= 4.0 && b2 >= 4.0 && b3 >= 4.0
            val mediaTxt = String.format(Locale("es", "ES"), "%.2f", media)
            val distancia = String.format(Locale("es", "ES"), "%+.2f", media - 5.0)   // ampliación

            tvNota.text = "Media: $mediaTxt (distancia al 5: $distancia)"

            if (mediaOk && bloquesOk) {
                val calif = when {
                    media < 7.0 -> "Aprobado"
                    media < 9.0 -> "Notable"
                    else -> "Sobresaliente"
                }
                tvEstado.text = "SUPERADA: $calif"
                tvEstado.setTextColor(Color.parseColor("#2E7D32"))      // green
            } else {
                val motivo = when {
                    !mediaOk && !bloquesOk -> "media < 5 y algún bloque < 4"
                    !mediaOk -> "media < 5"
                    else -> "algún bloque < 4"
                }
                tvEstado.text = "NO SUPERADA: $motivo"
                tvEstado.setTextColor(Color.parseColor("#C62828"))      // red
            }
        }

        btnLimpiar.setOnClickListener {
            etBloque1.setText("")
            etBloque2.setText("")
            etBloque3.setText("")
            tvNota.text = textoInicial
            tvEstado.text = ""
        }
    }
}
```

---

## B05 – Factura eléctrica simplificada
**Use it for:** progressive tariff (tiers), discount on ONE part only, VAT on top, functions, label/figure styles.

**XML** (the `ScrollView` is optional here but harmless)
```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/fondo_app">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <EditText
            android:id="@+id/etKwh"
            style="@style/CampoFormulario"
            android:hint="Consumo (kWh)"
            android:inputType="numberDecimal|numberSigned" />

        <EditText
            android:id="@+id/etDias"
            style="@style/CampoFormulario"
            android:hint="Días facturados (1-31)"
            android:inputType="numberSigned" />

        <CheckBox
            android:id="@+id/cbBonoSocial"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Bono social (-25 % energía)" />

        <Button
            android:id="@+id/btnCalcular"
            style="@style/BotonPrincipal"
            android:text="Calcular" />

        <Button
            android:id="@+id/btnLimpiar"
            style="@style/BotonPrincipal"
            android:text="Limpiar" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            android:padding="12dp"
            android:background="@drawable/fondo_panel">

            <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="horizontal">
                <TextView style="@style/Etiqueta" android:text="Término fijo" />
                <TextView android:id="@+id/tvFijo" style="@style/Cifra" android:text="-" />
            </LinearLayout>
            <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="horizontal">
                <TextView style="@style/Etiqueta" android:text="Energía" />
                <TextView android:id="@+id/tvEnergia" style="@style/Cifra" android:text="-" />
            </LinearLayout>
            <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="horizontal">
                <TextView style="@style/Etiqueta" android:text="Energía tras bono" />
                <TextView android:id="@+id/tvEnergiaBono" style="@style/Cifra" android:text="-" />
            </LinearLayout>
            <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="horizontal">
                <TextView style="@style/Etiqueta" android:text="Base imponible" />
                <TextView android:id="@+id/tvBase" style="@style/Cifra" android:text="-" />
            </LinearLayout>
            <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="horizontal">
                <TextView style="@style/Etiqueta" android:text="IVA (21 %)" />
                <TextView android:id="@+id/tvIva" style="@style/Cifra" android:text="-" />
            </LinearLayout>
        </LinearLayout>

        <TextView
            android:id="@+id/tvResultado"
            style="@style/TextoResultado"
            android:textStyle="bold"
            android:text="Introduce los datos" />
    </LinearLayout>
</ScrollView>
```

**Kotlin**
```kotlin
import java.util.Locale
// ...
class MainActivity : AppCompatActivity() {

    private fun euros(x: Double): String = String.format(Locale("es", "ES"), "%.2f €", x)

    // First 100 kWh at 0.12, the excess at 0.18 (only the excess!)
    fun costeEnergia(kwh: Double): Double {
        return if (kwh <= 100.0) {
            kwh * 0.12
        } else {
            100.0 * 0.12 + (kwh - 100.0) * 0.18
        }
    }

    fun totalConIva(base: Double): Double = base * 1.21

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etKwh = findViewById<EditText>(R.id.etKwh)
        val etDias = findViewById<EditText>(R.id.etDias)
        val cbBono = findViewById<CheckBox>(R.id.cbBonoSocial)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val tvFijo = findViewById<TextView>(R.id.tvFijo)
        val tvEnergia = findViewById<TextView>(R.id.tvEnergia)
        val tvEnergiaBono = findViewById<TextView>(R.id.tvEnergiaBono)
        val tvBase = findViewById<TextView>(R.id.tvBase)
        val tvIva = findViewById<TextView>(R.id.tvIva)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnCalcular.setOnClickListener {
            val kwh = etKwh.text.toString().toDoubleOrNull()
            val dias = etDias.text.toString().toIntOrNull()

            if (kwh == null || kwh < 0 || dias == null || dias !in 1..31) {
                tvResultado.text = "Error: consumo ≥ 0 y días entre 1 y 31"
                return@setOnClickListener
            }

            val fijo = dias * 0.45
            val energia = costeEnergia(kwh)
            val energiaBonificada = if (cbBono.isChecked) energia * 0.75 else energia
            val base = fijo + energiaBonificada          // bono ONLY affects energy
            val total = totalConIva(base)
            val iva = total - base

            tvFijo.text = euros(fijo)
            tvEnergia.text = euros(energia)
            tvEnergiaBono.text = euros(energiaBonificada)
            tvBase.text = euros(base)
            tvIva.text = euros(iva)

            var texto = "TOTAL: ${euros(total)}"
            if (cbBono.isChecked) {                      // ampliación: cost without bono
                val sinBono = totalConIva(fijo + energia)
                texto += "\nSin bono pagarías: ${euros(sinBono)}"
            }
            tvResultado.text = texto
        }

        btnLimpiar.setOnClickListener {
            etKwh.setText("")
            etDias.setText("")
            cbBono.isChecked = false
            tvFijo.text = "-"
            tvEnergia.text = "-"
            tvEnergiaBono.text = "-"
            tvBase.text = "-"
            tvIva.text = "-"
            tvResultado.text = "Introduce los datos"
        }
    }
}
```
Checked: 100 kWh/10 d → 19,97 €; 150/10 → 30,86 €; 150/10 + bono → 24,50 €; 0/1 → 0,54 €.

---

## B06 – Reto final: reservas con aforo
**Use it for:** everything together: state between clicks (`var plazasDisponibles`), RadioGroup, CheckBox, promo code rules, ScrollView, several functions, Limpiar that does NOT restore seats.

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/fondo_app">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <TextView
            android:id="@+id/tvPlazas"
            style="@style/TextoResultado"
            android:textStyle="bold"
            android:text="Plazas disponibles: 20" />

        <EditText
            android:id="@+id/etNombre"
            style="@style/CampoFormulario"
            android:hint="Nombre"
            android:inputType="textPersonName" />

        <EditText
            android:id="@+id/etCantidad"
            style="@style/CampoFormulario"
            android:hint="Cantidad (1-8)"
            android:inputType="numberSigned" />

        <EditText
            android:id="@+id/etCodigo"
            style="@style/CampoFormulario"
            android:hint="Código promocional (opcional)"
            android:inputType="text" />

        <RadioGroup
            android:id="@+id/rgEntrada"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <RadioButton
                android:id="@+id/rbGeneral"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="General (18 €)" />

            <RadioButton
                android:id="@+id/rbVip"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="VIP (32 €)" />
        </RadioGroup>

        <CheckBox
            android:id="@+id/cbFinSemana"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Fin de semana (+20 %)" />

        <Button
            android:id="@+id/btnReservar"
            style="@style/BotonPrincipal"
            android:text="Reservar" />

        <Button
            android:id="@+id/btnLimpiar"
            style="@style/BotonPrincipal"
            android:text="Limpiar" />

        <TextView
            android:id="@+id/tvResumen"
            style="@style/TextoResultado"
            android:background="@drawable/fondo_panel"
            android:text="Realiza tu reserva" />
    </LinearLayout>
</ScrollView>
```

**Kotlin**
```kotlin
import java.util.Locale
// ...
class MainActivity : AppCompatActivity() {

    private fun euros(x: Double): String = String.format(Locale("es", "ES"), "%.2f €", x)

    fun precioEntrada(esVip: Boolean): Double = if (esVip) 32.0 else 18.0

    fun calcularBase(precio: Double, cantidad: Int): Double = precio * cantidad

    fun calcularRecargo(base: Double, finSemana: Boolean): Double =
        if (finSemana) base * 0.20 else 0.0

    // Discount is applied AFTER the surcharge
    fun calcularDescuento(baseConRecargo: Double, aplicaCodigo: Boolean): Double =
        if (aplicaCodigo) baseConRecargo * 0.10 else 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etCantidad = findViewById<EditText>(R.id.etCantidad)
        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val rgEntrada = findViewById<RadioGroup>(R.id.rgEntrada)
        val cbFinSemana = findViewById<CheckBox>(R.id.cbFinSemana)
        val btnReservar = findViewById<Button>(R.id.btnReservar)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val tvResumen = findViewById<TextView>(R.id.tvResumen)
        val tvPlazas = findViewById<TextView>(R.id.tvPlazas)

        var plazasDisponibles = 20          // OUTSIDE the listener: keeps its value
        tvPlazas.text = "Plazas disponibles: $plazasDisponibles"

        btnReservar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val cantidad = etCantidad.text.toString().toIntOrNull()
            val codigo = etCodigo.text.toString().trim()
            val seleccion = rgEntrada.checkedRadioButtonId

            // ---- VALIDATIONS (nothing is changed if one fails) ----
            if (nombre.isEmpty()) {
                tvResumen.text = "Error: el nombre es obligatorio"
                return@setOnClickListener
            }
            if (cantidad == null || cantidad !in 1..8) {
                tvResumen.text = "Error: la cantidad debe ser un entero entre 1 y 8"
                return@setOnClickListener
            }
            if (seleccion == -1) {
                tvResumen.text = "Error: elige el tipo de entrada"
                return@setOnClickListener
            }
            if (codigo.isNotEmpty() && codigo != "DAM10") {
                tvResumen.text = "Error: código promocional no válido"
                return@setOnClickListener
            }
            if (codigo == "DAM10" && cantidad < 3) {
                tvResumen.text = "Error: DAM10 requiere 3 o más entradas"
                return@setOnClickListener
            }
            if (cantidad > plazasDisponibles) {
                tvResumen.text = "Error: solo quedan $plazasDisponibles plazas"
                return@setOnClickListener
            }

            // ---- CALCULATIONS ----
            val esVip = seleccion == R.id.rbVip
            val base = calcularBase(precioEntrada(esVip), cantidad)
            val recargo = calcularRecargo(base, cbFinSemana.isChecked)
            val descuento = calcularDescuento(base + recargo, codigo == "DAM10")
            val total = base + recargo - descuento

            plazasDisponibles -= cantidad   // ONLY here, once everything is valid

            tvResumen.text = "Nombre: $nombre\n" +
                    "Tipo: ${if (esVip) "VIP" else "General"}\n" +
                    "Cantidad: $cantidad\n" +
                    "Base: ${euros(base)}\n" +
                    "Recargo: ${euros(recargo)}\n" +
                    "Descuento: ${euros(descuento)}\n" +
                    "TOTAL: ${euros(total)}"

            tvPlazas.text = if (plazasDisponibles <= 5)
                "Últimas plazas: $plazasDisponibles"          // ampliación
            else
                "Plazas disponibles: $plazasDisponibles"
        }

        btnLimpiar.setOnClickListener {
            etNombre.setText("")
            etCantidad.setText("")
            etCodigo.setText("")
            rgEntrada.clearCheck()
            cbFinSemana.isChecked = false
            tvResumen.text = "Realiza tu reserva"
            // plazasDisponibles is NOT touched
        }
    }
}
```
Checked: Ana 2 General laborable → 36 €, quedan 18. Luis 3 VIP fin de semana DAM10 → base 96, recargo 19,20, descuento 11,52, total 103,68, quedan 15.

---
---

# PART C – Quick modifications (what they might change in the exam)

| If they ask… | Change this |
|---|---|
| Another range (e.g. age 16–70) | `edad >= 16 && edad <= 70` or `edad in 16..70` |
| Another grade scale | Edit the `when { nota <= X -> ... }` limits |
| A new category | Add a branch in the `when` BEFORE `else` |
| A 3rd/4th input field | New `EditText` in XML + `findViewById` + `toXOrNull()` + add it to the `null`/range check |
| A new operation (%, power) | New button + `btn.setOnClickListener { calcular("%") }` + new branch in `when` (`a % b`, `Math.pow(a, b)`) |
| Different prices/rates | Change the numbers in the cost function only (that's why logic lives in functions) |
| Percentage discount | `total - total * 0.15` or `total * 0.85` |
| Fixed discount, not below zero | `maxOf(0.0, total - 5.0)` |
| Cap/limit | `minOf(valor, tope)` |
| Round up integer division | `(x + n - 1) / n` (as in `(minutos + 59) / 60`) |
| Round to 2 decimals (shown) | `String.format(Locale("es","ES"), "%.2f", x)` |
| Show text in red/green | `tv.setTextColor(Color.parseColor("#C62828"))` / `"#2E7D32"` |
| Count something between clicks | `var contador = 0` OUTSIDE the listener, `contador++` inside |
| Add / use a CheckBox | XML `<CheckBox android:id="@+id/cbX" .../>` + `cbX.isChecked` |
| Add radio buttons | `RadioGroup` + `RadioButton`s; `rg.checkedRadioButtonId == R.id.rbX`; `-1` means none |
| Reset button | `et.setText("")`, `cb.isChecked = false`, `rg.clearCheck()`, `tv.text = "inicial"` |
| Several results at once | Two `TextView`s (`tvNota`, `tvEstado`) |
| Make it scroll | Root `ScrollView` with ONE `LinearLayout` child |
| New reusable style | `<style name="X">` in `styles.xml` + `style="@style/X"` in the widget |
| Print to Logcat | `Log.d("PMDM", "texto $variable")` |
| Validate text length / not empty | `texto.trim().isEmpty()`, `texto.length >= 3` |
| Check text equals code | `codigo == "DAM10"` (or `codigo.equals("dam10", ignoreCase = true)`) |

## Last-minute checklist ✅
- [ ] `setContentView` first, then `findViewById`
- [ ] ids identical in XML and Kotlin
- [ ] Validate (null / range / empty) **before** calculating
- [ ] `return@setOnClickListener` after every error message
- [ ] Calculation functions take parameters and `return` (no widgets inside)
- [ ] `inputType` allows what you test (`numberSigned` for negatives, `numberDecimal` for decimals)
- [ ] Test every case in the statement + one invalid case → screenshots
- [ ] Zip/save each exercise as its own project

Good luck! 🍀