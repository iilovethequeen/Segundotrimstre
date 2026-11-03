# PMDM UD1 – Practice Questions + Answers

Since you think the exam will look like **Relación 2 – Exercise 6** (integration challenge), this file has:

| Part | What | Why |
|---|---|---|
| **1** | 6 full "exam-style" exercises (statement → test cases → XML → Kotlin) | Most likely format. Each one changes the rules of B06 in a different way |
| **2** | 12 mini coding questions with answers | Quick warm-ups / possible small parts of the exam |
| **3** | 30 theory & "predict the output" questions with answers | In case there are short questions |
| **4** | 8 "find the bug" questions | Debugging practice |
| **5** | Strategy for the exam | How to spend your time |

> Needs the shared files from the first file (`PMDM_UD1_Examen_Codigos.md`, section 2): `colors.xml`, `styles.xml` (`CampoFormulario`, `BotonPrincipal`, `TextoResultado`) and `fondo_panel.xml`. Add `import java.util.Locale` + the `euros()` helper where money is shown.
>
> **Best way to practice:** read ONLY the statement, try to solve it yourself for 15–20 min, then compare with the solution.

---
---

# PART 1 – Full exam-style exercises

Every exercise follows the same pattern as B06:
**validate everything → calculate with functions → only then change state → show summary → Limpiar clears fields but not the state.**

Standard imports for all Kotlin files below:
```kotlin
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
```

---

## EX 1 – Gimnasio: reserva de clases con aforo

**Statement**
A gym has **15 places** left today (kept while the app is open).
- Fields: `etNombre`, `etPersonas` (integer 1–4), `rgClase` (`rbYoga` 12 €/person, `rbSpinning` 15 €/person), `etCodigo` (optional), `cbHoraPunta`, `btnReservar`, `btnLimpiar`, `tvResumen`, `tvPlazas`.
- Name is required. People: 1 to 4. A class must be chosen.
- Peak hour: **+15 %** surcharge on the subtotal.
- Code `SOCIO`: **20 % off AFTER the surcharge**, but only if the **subtotal is ≥ 40 €**. Any other code is invalid.
- Do not change places or summary if there's any error or not enough places.
- Show name, class, people, subtotal, surcharge, discount, total, remaining places.
- Limpiar clears fields and summary but does NOT restore places.
- Use functions for calculations, `ScrollView`, styles and `fondo_panel`.

**Test cases**
| Input | Expected |
|---|---|
| Ana, 3, Spinning, peak, SOCIO | Subtotal 45; surcharge 6,75; discount 10,35; total **41,40 €**; 12 places left |
| Luis, 2, Yoga, SOCIO | Error (subtotal 24 < 40); places unchanged |
| Eva, 5 people | Error |
| Code `ABC` | Error |
| Limpiar | Fields empty; places stay |

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent"
    android:background="@color/fondo_app">

    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
        android:orientation="vertical" android:padding="16dp">

        <TextView android:id="@+id/tvPlazas" style="@style/TextoResultado"
            android:textStyle="bold" android:text="Plazas disponibles: 15" />

        <EditText android:id="@+id/etNombre" style="@style/CampoFormulario"
            android:hint="Nombre" android:inputType="textPersonName" />
        <EditText android:id="@+id/etPersonas" style="@style/CampoFormulario"
            android:hint="Personas (1-4)" android:inputType="numberSigned" />
        <EditText android:id="@+id/etCodigo" style="@style/CampoFormulario"
            android:hint="Código (opcional)" android:inputType="text" />

        <RadioGroup android:id="@+id/rgClase" android:layout_width="match_parent"
            android:layout_height="wrap_content" android:orientation="horizontal">
            <RadioButton android:id="@+id/rbYoga" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:text="Yoga (12 €)" />
            <RadioButton android:id="@+id/rbSpinning" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:text="Spinning (15 €)" />
        </RadioGroup>

        <CheckBox android:id="@+id/cbHoraPunta" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="Hora punta (+15 %)" />

        <Button android:id="@+id/btnReservar" style="@style/BotonPrincipal" android:text="Reservar" />
        <Button android:id="@+id/btnLimpiar" style="@style/BotonPrincipal" android:text="Limpiar" />

        <TextView android:id="@+id/tvResumen" style="@style/TextoResultado"
            android:background="@drawable/fondo_panel" android:text="Haz tu reserva" />
    </LinearLayout>
</ScrollView>
```

**Kotlin**
```kotlin
class MainActivity : AppCompatActivity() {

    private fun euros(x: Double): String = String.format(Locale("es", "ES"), "%.2f €", x)

    fun precioClase(esSpinning: Boolean): Double = if (esSpinning) 15.0 else 12.0
    fun calcularSubtotal(precio: Double, personas: Int): Double = precio * personas
    fun calcularRecargo(subtotal: Double, horaPunta: Boolean): Double =
        if (horaPunta) subtotal * 0.15 else 0.0
    fun calcularDescuento(importe: Double, aplica: Boolean): Double =
        if (aplica) importe * 0.20 else 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etPersonas = findViewById<EditText>(R.id.etPersonas)
        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val rgClase = findViewById<RadioGroup>(R.id.rgClase)
        val cbHoraPunta = findViewById<CheckBox>(R.id.cbHoraPunta)
        val btnReservar = findViewById<Button>(R.id.btnReservar)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val tvResumen = findViewById<TextView>(R.id.tvResumen)
        val tvPlazas = findViewById<TextView>(R.id.tvPlazas)

        var plazas = 15                                   // STATE (outside listener)
        tvPlazas.text = "Plazas disponibles: $plazas"

        btnReservar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val personas = etPersonas.text.toString().toIntOrNull()
            val codigo = etCodigo.text.toString().trim()
            val seleccion = rgClase.checkedRadioButtonId

            if (nombre.isEmpty()) { tvResumen.text = "Error: nombre obligatorio"; return@setOnClickListener }
            if (personas == null || personas !in 1..4) { tvResumen.text = "Error: personas entre 1 y 4"; return@setOnClickListener }
            if (seleccion == -1) { tvResumen.text = "Error: elige una clase"; return@setOnClickListener }

            val esSpinning = seleccion == R.id.rbSpinning
            val subtotal = calcularSubtotal(precioClase(esSpinning), personas)

            if (codigo.isNotEmpty() && codigo != "SOCIO") { tvResumen.text = "Error: código no válido"; return@setOnClickListener }
            if (codigo == "SOCIO" && subtotal < 40.0) { tvResumen.text = "Error: SOCIO requiere subtotal de 40 € o más"; return@setOnClickListener }
            if (personas > plazas) { tvResumen.text = "Error: solo quedan $plazas plazas"; return@setOnClickListener }

            val recargo = calcularRecargo(subtotal, cbHoraPunta.isChecked)
            val descuento = calcularDescuento(subtotal + recargo, codigo == "SOCIO")
            val total = subtotal + recargo - descuento

            plazas -= personas                            // only now

            tvResumen.text = "Nombre: $nombre\nClase: ${if (esSpinning) "Spinning" else "Yoga"}\n" +
                    "Personas: $personas\nSubtotal: ${euros(subtotal)}\nRecargo: ${euros(recargo)}\n" +
                    "Descuento: ${euros(descuento)}\nTOTAL: ${euros(total)}"
            tvPlazas.text = "Plazas disponibles: $plazas"
        }

        btnLimpiar.setOnClickListener {
            etNombre.setText(""); etPersonas.setText(""); etCodigo.setText("")
            rgClase.clearCheck(); cbHoraPunta.isChecked = false
            tvResumen.text = "Haz tu reserva"
        }
    }
}
```

---

## EX 2 – Cafetería: pedidos y caja acumulada

**Statement**
A café keeps a **running cash total (caja)** and an **order counter** while the app is open.
- Fields: `etUnidades` (integer 1–10), `rgBebida` (`rbCafe` 1,50 €, `rbTe` 1,20 €, `rbZumo` 2,50 €), `cbTerraza` (+10 % on the amount after discount), `etPropina` (optional, euros ≥ 0; empty = 0), `btnCobrar`, `btnLimpiar`, `tvTicket`, `tvCaja`.
- Order of calculation: subtotal → **volume discount (10 % if 5 or more units)** → terrace surcharge → add tip.
- On errors, don't change the cash or the counter.
- Limpiar clears fields and ticket, but NOT the cash.
- `tvCaja` shows: "Pedidos: N · Caja: X €".

**Test cases**
| Input | Expected |
|---|---|
| 2 café, no terraza, no tip | Total 3,00 € |
| 6 zumo, terraza, tip 1 | 15 → discount 1,50 → 13,50 → ×1,10 = 14,85 → +1 = **15,85 €** |
| After those two | Orders 2 · Caja **18,85 €** |
| 11 units, tip -1, tip "abc" | Error |

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent"
    android:background="@color/fondo_app">

    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
        android:orientation="vertical" android:padding="16dp">

        <TextView android:id="@+id/tvCaja" style="@style/TextoResultado"
            android:textStyle="bold" android:text="Pedidos: 0 · Caja: 0,00 €" />

        <EditText android:id="@+id/etUnidades" style="@style/CampoFormulario"
            android:hint="Unidades (1-10)" android:inputType="numberSigned" />
        <EditText android:id="@+id/etPropina" style="@style/CampoFormulario"
            android:hint="Propina € (opcional)" android:inputType="numberDecimal|numberSigned" />

        <RadioGroup android:id="@+id/rgBebida" android:layout_width="match_parent"
            android:layout_height="wrap_content" android:orientation="horizontal">
            <RadioButton android:id="@+id/rbCafe" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:text="Café" />
            <RadioButton android:id="@+id/rbTe" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:text="Té" />
            <RadioButton android:id="@+id/rbZumo" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:text="Zumo" />
        </RadioGroup>

        <CheckBox android:id="@+id/cbTerraza" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="Terraza (+10 %)" />

        <Button android:id="@+id/btnCobrar" style="@style/BotonPrincipal" android:text="Cobrar" />
        <Button android:id="@+id/btnLimpiar" style="@style/BotonPrincipal" android:text="Limpiar" />

        <TextView android:id="@+id/tvTicket" style="@style/TextoResultado"
            android:background="@drawable/fondo_panel" android:text="Nuevo pedido" />
    </LinearLayout>
</ScrollView>
```

**Kotlin**
```kotlin
class MainActivity : AppCompatActivity() {

    private fun euros(x: Double): String = String.format(Locale("es", "ES"), "%.2f €", x)

    fun precioBebida(bebida: String): Double = when (bebida) {
        "Café" -> 1.50
        "Té" -> 1.20
        else -> 2.50
    }
    fun descuentoVolumen(subtotal: Double, unidades: Int): Double =
        if (unidades >= 5) subtotal * 0.10 else 0.0
    fun recargoTerraza(importe: Double, terraza: Boolean): Double =
        if (terraza) importe * 0.10 else 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUnidades = findViewById<EditText>(R.id.etUnidades)
        val etPropina = findViewById<EditText>(R.id.etPropina)
        val rgBebida = findViewById<RadioGroup>(R.id.rgBebida)
        val cbTerraza = findViewById<CheckBox>(R.id.cbTerraza)
        val btnCobrar = findViewById<Button>(R.id.btnCobrar)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val tvTicket = findViewById<TextView>(R.id.tvTicket)
        val tvCaja = findViewById<TextView>(R.id.tvCaja)

        var caja = 0.0                    // STATE
        var pedidos = 0                   // STATE

        btnCobrar.setOnClickListener {
            val unidades = etUnidades.text.toString().toIntOrNull()
            val propina = etPropina.text.toString().trim().ifEmpty { "0" }.toDoubleOrNull()
            val seleccion = rgBebida.checkedRadioButtonId

            if (unidades == null || unidades !in 1..10) { tvTicket.text = "Error: unidades entre 1 y 10"; return@setOnClickListener }
            if (propina == null || propina < 0) { tvTicket.text = "Error: propina no válida"; return@setOnClickListener }
            if (seleccion == -1) { tvTicket.text = "Error: elige una bebida"; return@setOnClickListener }

            val bebida = when (seleccion) {
                R.id.rbCafe -> "Café"
                R.id.rbTe -> "Té"
                else -> "Zumo"
            }
            val subtotal = precioBebida(bebida) * unidades
            val descuento = descuentoVolumen(subtotal, unidades)
            val neto = subtotal - descuento
            val terraza = recargoTerraza(neto, cbTerraza.isChecked)
            val total = neto + terraza + propina

            caja += total
            pedidos++

            tvTicket.text = "$unidades x $bebida\nSubtotal: ${euros(subtotal)}\nDescuento: ${euros(descuento)}\n" +
                    "Terraza: ${euros(terraza)}\nPropina: ${euros(propina)}\nTOTAL: ${euros(total)}"
            tvCaja.text = "Pedidos: $pedidos · Caja: ${euros(caja)}"
        }

        btnLimpiar.setOnClickListener {
            etUnidades.setText(""); etPropina.setText("")
            rgBebida.clearCheck(); cbTerraza.isChecked = false
            tvTicket.text = "Nuevo pedido"
        }
    }
}
```

---

## EX 3 – Alquiler de bicicletas (alquilar + devolver)

**Statement**
A rental shop has a fleet of **10 bikes**. Two actions: **Alquilar** and **Devolver**.
- Fields: `etNombre`, `etBicis` (integer 1–3), `etHoras` (integer 1–12), `rgTipo` (`rbUrbana` 3 €/h, `rbElectrica` 5 €/h), `cbSeguro` (+2 € per bike), `btnAlquilar`, `btnDevolver`, `btnLimpiar`, `tvResumen`, `tvDisponibles`.
- **Price per bike = min(hours × rate, cap)**; cap: urbana 15 €, eléctrica 25 €. Insurance is added AFTER the cap.
- Alquilar: validate everything; can't rent more than available.
- **Devolver** uses `etBicis` too: can't return more bikes than are currently rented.
- Limpiar doesn't change the fleet count.

**Test cases**
| Input | Expected |
|---|---|
| Ana, 2 bikes, 6 h, urbana, no insurance | 6×3=18 → cap 15 → ×2 = **30 €**; 8 available |
| 2 bikes, 6 h, urbana, insurance | (15+2)×2 = **34 €** |
| 1 bike, 4 h, eléctrica | 20 € (under cap) |
| Devolver 3 when only 2 rented | Error; available unchanged |
| Devolver 2 | Available back to 10 |

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent"
    android:background="@color/fondo_app">

    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
        android:orientation="vertical" android:padding="16dp">

        <TextView android:id="@+id/tvDisponibles" style="@style/TextoResultado"
            android:textStyle="bold" android:text="Bicis disponibles: 10" />

        <EditText android:id="@+id/etNombre" style="@style/CampoFormulario"
            android:hint="Nombre" android:inputType="textPersonName" />
        <EditText android:id="@+id/etBicis" style="@style/CampoFormulario"
            android:hint="Número de bicis (1-3)" android:inputType="numberSigned" />
        <EditText android:id="@+id/etHoras" style="@style/CampoFormulario"
            android:hint="Horas (1-12)" android:inputType="numberSigned" />

        <RadioGroup android:id="@+id/rgTipo" android:layout_width="match_parent"
            android:layout_height="wrap_content" android:orientation="horizontal">
            <RadioButton android:id="@+id/rbUrbana" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:text="Urbana (3 €/h)" />
            <RadioButton android:id="@+id/rbElectrica" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:text="Eléctrica (5 €/h)" />
        </RadioGroup>

        <CheckBox android:id="@+id/cbSeguro" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="Seguro (+2 € por bici)" />

        <Button android:id="@+id/btnAlquilar" style="@style/BotonPrincipal" android:text="Alquilar" />
        <Button android:id="@+id/btnDevolver" style="@style/BotonPrincipal" android:text="Devolver" />
        <Button android:id="@+id/btnLimpiar" style="@style/BotonPrincipal" android:text="Limpiar" />

        <TextView android:id="@+id/tvResumen" style="@style/TextoResultado"
            android:background="@drawable/fondo_panel" android:text="Elige una opción" />
    </LinearLayout>
</ScrollView>
```

**Kotlin**
```kotlin
class MainActivity : AppCompatActivity() {

    private fun euros(x: Double): String = String.format(Locale("es", "ES"), "%.2f €", x)

    fun precioPorBici(horas: Int, electrica: Boolean): Double {
        val tarifa = if (electrica) 5.0 else 3.0
        val tope = if (electrica) 25.0 else 15.0
        return minOf(horas * tarifa, tope)
    }
    fun calcularTotal(precioBici: Double, bicis: Int, seguro: Boolean): Double {
        val extra = if (seguro) 2.0 else 0.0
        return (precioBici + extra) * bicis
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etBicis = findViewById<EditText>(R.id.etBicis)
        val etHoras = findViewById<EditText>(R.id.etHoras)
        val rgTipo = findViewById<RadioGroup>(R.id.rgTipo)
        val cbSeguro = findViewById<CheckBox>(R.id.cbSeguro)
        val btnAlquilar = findViewById<Button>(R.id.btnAlquilar)
        val btnDevolver = findViewById<Button>(R.id.btnDevolver)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val tvResumen = findViewById<TextView>(R.id.tvResumen)
        val tvDisponibles = findViewById<TextView>(R.id.tvDisponibles)

        val flota = 10
        var disponibles = flota                          // STATE
        tvDisponibles.text = "Bicis disponibles: $disponibles"

        btnAlquilar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val bicis = etBicis.text.toString().toIntOrNull()
            val horas = etHoras.text.toString().toIntOrNull()
            val seleccion = rgTipo.checkedRadioButtonId

            if (nombre.isEmpty()) { tvResumen.text = "Error: nombre obligatorio"; return@setOnClickListener }
            if (bicis == null || bicis !in 1..3) { tvResumen.text = "Error: bicis entre 1 y 3"; return@setOnClickListener }
            if (horas == null || horas !in 1..12) { tvResumen.text = "Error: horas entre 1 y 12"; return@setOnClickListener }
            if (seleccion == -1) { tvResumen.text = "Error: elige el tipo"; return@setOnClickListener }
            if (bicis > disponibles) { tvResumen.text = "Error: solo hay $disponibles bicis"; return@setOnClickListener }

            val electrica = seleccion == R.id.rbElectrica
            val porBici = precioPorBici(horas, electrica)
            val total = calcularTotal(porBici, bicis, cbSeguro.isChecked)

            disponibles -= bicis

            tvResumen.text = "Cliente: $nombre\nTipo: ${if (electrica) "Eléctrica" else "Urbana"}\n" +
                    "Bicis: $bicis · Horas: $horas\nPrecio por bici: ${euros(porBici)}\n" +
                    "Seguro: ${if (cbSeguro.isChecked) "sí" else "no"}\nTOTAL: ${euros(total)}"
            tvDisponibles.text = "Bicis disponibles: $disponibles"
        }

        btnDevolver.setOnClickListener {
            val bicis = etBicis.text.toString().toIntOrNull()
            val alquiladas = flota - disponibles

            if (bicis == null || bicis <= 0) { tvResumen.text = "Error: cantidad no válida"; return@setOnClickListener }
            if (bicis > alquiladas) { tvResumen.text = "Error: solo hay $alquiladas bicis alquiladas"; return@setOnClickListener }

            disponibles += bicis
            tvResumen.text = "Devueltas $bicis bicis"
            tvDisponibles.text = "Bicis disponibles: $disponibles"
        }

        btnLimpiar.setOnClickListener {
            etNombre.setText(""); etBicis.setText(""); etHoras.setText("")
            rgTipo.clearCheck(); cbSeguro.isChecked = false
            tvResumen.text = "Elige una opción"
        }
    }
}
```

---

## EX 4 – Hotel: reservas con habitaciones limitadas

**Statement**
A small hotel has **6 free rooms**.
- Fields: `etNombre`, `etNoches` (1–14), `etHabitaciones` (1–3), `rgHab` (`rbDoble` 80 €/night, `rbSuite` 150 €/night), `cbDesayuno` (8 € per night per room), `cbTemporadaAlta` (+25 % on the room base), `etCodigo`, `btnReservar`, `btnLimpiar`, `tvResumen`, `tvLibres`.
- Base = nights × price × rooms. Surcharge = 25 % of base if high season.
- Code `HOTEL10`: **10 % off (base + surcharge)**, only if **3 or more nights**. Breakfast is NOT discounted. Other codes are invalid.
- Total = base + surcharge − discount + breakfast.
- Don't book more rooms than free ones; nothing changes on error.

**Test cases**
| Input | Expected |
|---|---|
| 3 nights, suite, 1 room, breakfast, high season, HOTEL10 | Base 450; surcharge 112,50; discount 56,25; breakfast 24; total **530,25 €**; 5 rooms left |
| 2 nights, doble, 1 room, nothing else | **160 €** |
| 2 nights + HOTEL10 | Error |
| 3 rooms when 2 free | Error |

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent"
    android:background="@color/fondo_app">

    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
        android:orientation="vertical" android:padding="16dp">

        <TextView android:id="@+id/tvLibres" style="@style/TextoResultado"
            android:textStyle="bold" android:text="Habitaciones libres: 6" />

        <EditText android:id="@+id/etNombre" style="@style/CampoFormulario"
            android:hint="Nombre" android:inputType="textPersonName" />
        <EditText android:id="@+id/etNoches" style="@style/CampoFormulario"
            android:hint="Noches (1-14)" android:inputType="numberSigned" />
        <EditText android:id="@+id/etHabitaciones" style="@style/CampoFormulario"
            android:hint="Habitaciones (1-3)" android:inputType="numberSigned" />
        <EditText android:id="@+id/etCodigo" style="@style/CampoFormulario"
            android:hint="Código (opcional)" android:inputType="text" />

        <RadioGroup android:id="@+id/rgHab" android:layout_width="match_parent"
            android:layout_height="wrap_content" android:orientation="horizontal">
            <RadioButton android:id="@+id/rbDoble" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:text="Doble (80 €)" />
            <RadioButton android:id="@+id/rbSuite" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:text="Suite (150 €)" />
        </RadioGroup>

        <CheckBox android:id="@+id/cbDesayuno" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="Desayuno (8 €/noche/hab.)" />
        <CheckBox android:id="@+id/cbTemporadaAlta" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="Temporada alta (+25 %)" />

        <Button android:id="@+id/btnReservar" style="@style/BotonPrincipal" android:text="Reservar" />
        <Button android:id="@+id/btnLimpiar" style="@style/BotonPrincipal" android:text="Limpiar" />

        <TextView android:id="@+id/tvResumen" style="@style/TextoResultado"
            android:background="@drawable/fondo_panel" android:text="Haz tu reserva" />
    </LinearLayout>
</ScrollView>
```

**Kotlin**
```kotlin
class MainActivity : AppCompatActivity() {

    private fun euros(x: Double): String = String.format(Locale("es", "ES"), "%.2f €", x)

    fun precioNoche(esSuite: Boolean): Double = if (esSuite) 150.0 else 80.0
    fun calcularBase(noches: Int, precio: Double, habitaciones: Int): Double = noches * precio * habitaciones
    fun calcularRecargo(base: Double, alta: Boolean): Double = if (alta) base * 0.25 else 0.0
    fun calcularDescuento(importe: Double, aplica: Boolean): Double = if (aplica) importe * 0.10 else 0.0
    fun calcularDesayuno(noches: Int, habitaciones: Int, incluido: Boolean): Double =
        if (incluido) noches * 8.0 * habitaciones else 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etNoches = findViewById<EditText>(R.id.etNoches)
        val etHabitaciones = findViewById<EditText>(R.id.etHabitaciones)
        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val rgHab = findViewById<RadioGroup>(R.id.rgHab)
        val cbDesayuno = findViewById<CheckBox>(R.id.cbDesayuno)
        val cbAlta = findViewById<CheckBox>(R.id.cbTemporadaAlta)
        val btnReservar = findViewById<Button>(R.id.btnReservar)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val tvResumen = findViewById<TextView>(R.id.tvResumen)
        val tvLibres = findViewById<TextView>(R.id.tvLibres)

        var libres = 6                                    // STATE
        tvLibres.text = "Habitaciones libres: $libres"

        btnReservar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val noches = etNoches.text.toString().toIntOrNull()
            val habs = etHabitaciones.text.toString().toIntOrNull()
            val codigo = etCodigo.text.toString().trim()
            val seleccion = rgHab.checkedRadioButtonId

            if (nombre.isEmpty()) { tvResumen.text = "Error: nombre obligatorio"; return@setOnClickListener }
            if (noches == null || noches !in 1..14) { tvResumen.text = "Error: noches entre 1 y 14"; return@setOnClickListener }
            if (habs == null || habs !in 1..3) { tvResumen.text = "Error: habitaciones entre 1 y 3"; return@setOnClickListener }
            if (seleccion == -1) { tvResumen.text = "Error: elige el tipo de habitación"; return@setOnClickListener }
            if (codigo.isNotEmpty() && codigo != "HOTEL10") { tvResumen.text = "Error: código no válido"; return@setOnClickListener }
            if (codigo == "HOTEL10" && noches < 3) { tvResumen.text = "Error: HOTEL10 requiere 3 o más noches"; return@setOnClickListener }
            if (habs > libres) { tvResumen.text = "Error: solo quedan $libres habitaciones"; return@setOnClickListener }

            val esSuite = seleccion == R.id.rbSuite
            val base = calcularBase(noches, precioNoche(esSuite), habs)
            val recargo = calcularRecargo(base, cbAlta.isChecked)
            val descuento = calcularDescuento(base + recargo, codigo == "HOTEL10")
            val desayuno = calcularDesayuno(noches, habs, cbDesayuno.isChecked)
            val total = base + recargo - descuento + desayuno

            libres -= habs

            tvResumen.text = "Cliente: $nombre\nHabitación: ${if (esSuite) "Suite" else "Doble"} x$habs\n" +
                    "Noches: $noches\nBase: ${euros(base)}\nRecargo: ${euros(recargo)}\n" +
                    "Descuento: ${euros(descuento)}\nDesayuno: ${euros(desayuno)}\nTOTAL: ${euros(total)}"
            tvLibres.text = "Habitaciones libres: $libres"
        }

        btnLimpiar.setOnClickListener {
            etNombre.setText(""); etNoches.setText(""); etHabitaciones.setText(""); etCodigo.setText("")
            rgHab.clearCheck(); cbDesayuno.isChecked = false; cbAlta.isChecked = false
            tvResumen.text = "Haz tu reserva"
        }
    }
}
```

---

## EX 5 – Tienda: stock, descuento por volumen, IVA y cupón

**Statement**
A shop has **30 units in stock** and keeps a **total of sales** while the app is open.
- Fields: `etCliente`, `etUnidades` (1–20), `rgProducto` (`rbCamiseta` 12,50 €, `rbSudadera` 28 €), `cbRegalo` (+3 € wrapping, once per order), `etCupon`, `btnVender`, `btnLimpiar`, `tvTicket`, `tvStock`.
- Volume discount with `when`: **10 or more units → 10 %**, **5 to 9 → 5 %**, otherwise 0 %. Applied to the subtotal.
- IVA 21 % on the discounted amount (net). Then add wrapping.
- Coupon `TIENDA5`: **−5 € on the total** (never below 0), only if **net ≥ 30 €**. Other coupon = invalid.
- Stock and total sales change only after a valid sale. Limpiar doesn't change stock/sales.

**Test cases**
| Input | Expected |
|---|---|
| 6 camisetas, no extras | Base 75; discount 3,75; net 71,25; IVA 14,96; total **86,21 €**; stock 24 |
| Same with TIENDA5 | 81,21 € |
| 1 sudadera + gift | 28 + 5,88 + 3 = **36,88 €** |
| 1 sudadera + TIENDA5 | Error (net 28 < 30) |
| 25 units | Error |

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent"
    android:background="@color/fondo_app">

    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
        android:orientation="vertical" android:padding="16dp">

        <TextView android:id="@+id/tvStock" style="@style/TextoResultado"
            android:textStyle="bold" android:text="Stock: 30 · Ventas: 0,00 €" />

        <EditText android:id="@+id/etCliente" style="@style/CampoFormulario"
            android:hint="Cliente" android:inputType="textPersonName" />
        <EditText android:id="@+id/etUnidades" style="@style/CampoFormulario"
            android:hint="Unidades (1-20)" android:inputType="numberSigned" />
        <EditText android:id="@+id/etCupon" style="@style/CampoFormulario"
            android:hint="Cupón (opcional)" android:inputType="text" />

        <RadioGroup android:id="@+id/rgProducto" android:layout_width="match_parent"
            android:layout_height="wrap_content" android:orientation="horizontal">
            <RadioButton android:id="@+id/rbCamiseta" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:text="Camiseta (12,50 €)" />
            <RadioButton android:id="@+id/rbSudadera" android:layout_width="wrap_content"
                android:layout_height="wrap_content" android:text="Sudadera (28 €)" />
        </RadioGroup>

        <CheckBox android:id="@+id/cbRegalo" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:text="Envoltorio regalo (+3 €)" />

        <Button android:id="@+id/btnVender" style="@style/BotonPrincipal" android:text="Vender" />
        <Button android:id="@+id/btnLimpiar" style="@style/BotonPrincipal" android:text="Limpiar" />

        <TextView android:id="@+id/tvTicket" style="@style/TextoResultado"
            android:background="@drawable/fondo_panel" android:text="Nueva venta" />
    </LinearLayout>
</ScrollView>
```

**Kotlin**
```kotlin
class MainActivity : AppCompatActivity() {

    private fun euros(x: Double): String = String.format(Locale("es", "ES"), "%.2f €", x)

    fun precioProducto(esSudadera: Boolean): Double = if (esSudadera) 28.0 else 12.5

    fun porcentajeVolumen(unidades: Int): Double = when {
        unidades >= 10 -> 0.10
        unidades >= 5 -> 0.05
        else -> 0.0
    }
    fun calcularIva(neto: Double): Double = neto * 0.21

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etCliente = findViewById<EditText>(R.id.etCliente)
        val etUnidades = findViewById<EditText>(R.id.etUnidades)
        val etCupon = findViewById<EditText>(R.id.etCupon)
        val rgProducto = findViewById<RadioGroup>(R.id.rgProducto)
        val cbRegalo = findViewById<CheckBox>(R.id.cbRegalo)
        val btnVender = findViewById<Button>(R.id.btnVender)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val tvTicket = findViewById<TextView>(R.id.tvTicket)
        val tvStock = findViewById<TextView>(R.id.tvStock)

        var stock = 30                    // STATE
        var ventas = 0.0                  // STATE
        tvStock.text = "Stock: $stock · Ventas: ${euros(ventas)}"

        btnVender.setOnClickListener {
            val cliente = etCliente.text.toString().trim()
            val unidades = etUnidades.text.toString().toIntOrNull()
            val cupon = etCupon.text.toString().trim()
            val seleccion = rgProducto.checkedRadioButtonId

            if (cliente.isEmpty()) { tvTicket.text = "Error: cliente obligatorio"; return@setOnClickListener }
            if (unidades == null || unidades !in 1..20) { tvTicket.text = "Error: unidades entre 1 y 20"; return@setOnClickListener }
            if (seleccion == -1) { tvTicket.text = "Error: elige un producto"; return@setOnClickListener }

            val esSudadera = seleccion == R.id.rbSudadera
            val base = precioProducto(esSudadera) * unidades
            val descVol = base * porcentajeVolumen(unidades)
            val neto = base - descVol

            if (cupon.isNotEmpty() && cupon != "TIENDA5") { tvTicket.text = "Error: cupón no válido"; return@setOnClickListener }
            if (cupon == "TIENDA5" && neto < 30.0) { tvTicket.text = "Error: TIENDA5 requiere neto de 30 € o más"; return@setOnClickListener }
            if (unidades > stock) { tvTicket.text = "Error: solo quedan $stock unidades"; return@setOnClickListener }

            val iva = calcularIva(neto)
            val regalo = if (cbRegalo.isChecked) 3.0 else 0.0
            val rebaja = if (cupon == "TIENDA5") 5.0 else 0.0
            val total = maxOf(0.0, neto + iva + regalo - rebaja)

            stock -= unidades
            ventas += total

            tvTicket.text = "Cliente: $cliente\n$unidades x ${if (esSudadera) "Sudadera" else "Camiseta"}\n" +
                    "Base: ${euros(base)}\nDescuento volumen: ${euros(descVol)}\nNeto: ${euros(neto)}\n" +
                    "IVA: ${euros(iva)}\nRegalo: ${euros(regalo)}\nCupón: -${euros(rebaja)}\nTOTAL: ${euros(total)}"
            tvStock.text = "Stock: $stock · Ventas: ${euros(ventas)}"
        }

        btnLimpiar.setOnClickListener {
            etCliente.setText(""); etUnidades.setText(""); etCupon.setText("")
            rgProducto.clearCheck(); cbRegalo.isChecked = false
            tvTicket.text = "Nueva venta"
        }
    }
}
```

---

## EX 6 – Monedero: ingresar y retirar (varios botones + estado + color)

**Statement**
A virtual wallet starts with **100 €**.
- Fields: `etImporte`, `cbCajeroAjeno` (commission of 1,50 € on withdrawals), `btnIngresar`, `btnRetirar`, `btnLimpiar`, `tvSaldo`, `tvMensaje`.
- Amount must be a number **> 0**. Max deposit per operation: **1000 €**.
- Withdrawal: allowed only if `saldo − importe − comisión ≥ 0`. Otherwise "Saldo insuficiente" and nothing changes.
- `tvSaldo` shows the balance and the number of operations. Colour: **red** if balance < 50, **green** otherwise.
- Every valid operation writes to Logcat with tag `PMDM`.
- Limpiar clears the field and message but NOT the balance.

**Test cases**
| Action | Expected |
|---|---|
| Ingresar 50 | 150 € (green), 1 operation |
| Retirar 20 with foreign ATM | 150 − 20 − 1,50 = **128,50 €** |
| Retirar 200 | "Saldo insuficiente" |
| Ingresar 1500 / 0 / "abc" | Error |
| Retirar 90 | 38,50 € → red |

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent"
    android:orientation="vertical" android:padding="16dp"
    android:background="@color/fondo_app">

    <TextView android:id="@+id/tvSaldo" style="@style/TextoResultado"
        android:textSize="24sp" android:textStyle="bold" android:text="Saldo: 100,00 €" />

    <EditText android:id="@+id/etImporte" style="@style/CampoFormulario"
        android:hint="Importe (€)" android:inputType="numberDecimal|numberSigned" />

    <CheckBox android:id="@+id/cbCajeroAjeno" android:layout_width="wrap_content"
        android:layout_height="wrap_content" android:text="Cajero ajeno (comisión 1,50 €)" />

    <Button android:id="@+id/btnIngresar" style="@style/BotonPrincipal" android:text="Ingresar" />
    <Button android:id="@+id/btnRetirar" style="@style/BotonPrincipal" android:text="Retirar" />
    <Button android:id="@+id/btnLimpiar" style="@style/BotonPrincipal" android:text="Limpiar" />

    <TextView android:id="@+id/tvMensaje" style="@style/TextoResultado"
        android:background="@drawable/fondo_panel" android:text="Realiza una operación" />
</LinearLayout>
```

**Kotlin**
```kotlin
class MainActivity : AppCompatActivity() {

    private fun euros(x: Double): String = String.format(Locale("es", "ES"), "%.2f €", x)

    fun comision(cajeroAjeno: Boolean): Double = if (cajeroAjeno) 1.5 else 0.0
    fun puedeRetirar(saldo: Double, importe: Double, comision: Double): Boolean =
        saldo - importe - comision >= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etImporte = findViewById<EditText>(R.id.etImporte)
        val cbAjeno = findViewById<CheckBox>(R.id.cbCajeroAjeno)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val btnRetirar = findViewById<Button>(R.id.btnRetirar)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val tvSaldo = findViewById<TextView>(R.id.tvSaldo)
        val tvMensaje = findViewById<TextView>(R.id.tvMensaje)

        var saldo = 100.0                 // STATE
        var operaciones = 0               // STATE

        // Local function: refreshes the balance label and its colour
        fun actualizarSaldo() {
            tvSaldo.text = "Saldo: ${euros(saldo)} · Operaciones: $operaciones"
            tvSaldo.setTextColor(if (saldo < 50.0) Color.parseColor("#C62828") else Color.parseColor("#2E7D32"))
        }
        actualizarSaldo()

        btnIngresar.setOnClickListener {
            val importe = etImporte.text.toString().toDoubleOrNull()
            if (importe == null || importe <= 0 || importe > 1000) {
                tvMensaje.text = "Error: importe entre 0 y 1000 €"
                return@setOnClickListener
            }
            saldo += importe
            operaciones++
            tvMensaje.text = "Ingresados ${euros(importe)}"
            Log.d("PMDM", "Ingreso de $importe. Saldo: $saldo")
            actualizarSaldo()
        }

        btnRetirar.setOnClickListener {
            val importe = etImporte.text.toString().toDoubleOrNull()
            if (importe == null || importe <= 0) {
                tvMensaje.text = "Error: importe no válido"
                return@setOnClickListener
            }
            val com = comision(cbAjeno.isChecked)
            if (!puedeRetirar(saldo, importe, com)) {
                tvMensaje.text = "Saldo insuficiente"
                return@setOnClickListener
            }
            saldo -= importe + com
            operaciones++
            tvMensaje.text = "Retirados ${euros(importe)} (comisión ${euros(com)})"
            Log.d("PMDM", "Retirada de $importe + comisión $com. Saldo: $saldo")
            actualizarSaldo()
        }

        btnLimpiar.setOnClickListener {
            etImporte.setText("")
            cbAjeno.isChecked = false
            tvMensaje.text = "Realiza una operación"
            // saldo is NOT touched
        }
    }
}
```

---
---

# PART 2 – Mini coding questions (with answers)

> Assume the views are already found with `findViewById`.

### M1. IMC (BMI) with categories
*Read weight (kg) and height (m). Show the BMI and category: < 18,5 Bajo peso; < 25 Normal; < 30 Sobrepeso; otherwise Obesidad.*
```kotlin
fun calcularImc(peso: Double, altura: Double): Double = peso / (altura * altura)

btnCalcular.setOnClickListener {
    val peso = etPeso.text.toString().toDoubleOrNull()
    val altura = etAltura.text.toString().toDoubleOrNull()
    if (peso == null || altura == null || peso <= 0 || altura <= 0) {
        tvResultado.text = "Datos no válidos"; return@setOnClickListener
    }
    val imc = calcularImc(peso, altura)
    val categoria = when {
        imc < 18.5 -> "Bajo peso"
        imc < 25.0 -> "Normal"
        imc < 30.0 -> "Sobrepeso"
        else -> "Obesidad"
    }
    tvResultado.text = "IMC: ${String.format(Locale("es","ES"), "%.1f", imc)} – $categoria"
}
```

### M2. Even or odd
```kotlin
val n = etNumero.text.toString().toIntOrNull()
if (n == null) { tvResultado.text = "Número no válido"; return@setOnClickListener }
tvResultado.text = if (n % 2 == 0) "$n es par" else "$n es impar"
```

### M3. Largest of three numbers
```kotlin
fun mayorDeTres(a: Double, b: Double, c: Double): Double = maxOf(a, maxOf(b, c))
// without maxOf:
fun mayorDeTres2(a: Double, b: Double, c: Double): Double =
    if (a >= b && a >= c) a else if (b >= c) b else c
```

### M4. Leap year
```kotlin
fun esBisiesto(anio: Int): Boolean = (anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0
// 2024 → true, 1900 → false, 2000 → true
```

### M5. Tip calculator (function with return)
```kotlin
fun calcularPropina(cuenta: Double, porcentaje: Double): Double = cuenta * porcentaje / 100
// total = cuenta + calcularPropina(cuenta, porcentaje)
```

### M6. km ↔ miles
```kotlin
fun kmAMillas(km: Double): Double = km * 0.621371
fun millasAKm(millas: Double): Double = millas / 0.621371
```

### M7. Counter with + and − (never below 0)
```kotlin
var contador = 0                                  // outside the listeners
tvContador.text = "$contador"
btnMas.setOnClickListener { contador++; tvContador.text = "$contador" }
btnMenos.setOnClickListener {
    if (contador > 0) contador--
    tvContador.text = "$contador"
}
```

### M8. Traffic light (three buttons change colour and text)
```kotlin
btnRojo.setOnClickListener { tvSemaforo.setBackgroundColor(Color.RED); tvSemaforo.text = "STOP" }
btnAmbar.setOnClickListener { tvSemaforo.setBackgroundColor(Color.YELLOW); tvSemaforo.text = "PRECAUCIÓN" }
btnVerde.setOnClickListener { tvSemaforo.setBackgroundColor(Color.GREEN); tvSemaforo.text = "PASE" }
```

### M9. Triangle type (and validity)
*Valid if each side is shorter than the sum of the other two.*
```kotlin
fun tipoTriangulo(a: Double, b: Double, c: Double): String {
    if (a <= 0 || b <= 0 || c <= 0) return "Lados no válidos"
    if (a >= b + c || b >= a + c || c >= a + b) return "No forma un triángulo"
    return when {
        a == b && b == c -> "Equilátero"
        a == b || b == c || a == c -> "Isósceles"
        else -> "Escaleno"
    }
}
```

### M10. Discount by purchase tiers
*< 50 € → 0 %; < 100 € → 5 %; < 200 € → 10 %; otherwise 15 %.*
```kotlin
fun porcentajeDescuento(importe: Double): Double = when {
    importe < 50.0 -> 0.0
    importe < 100.0 -> 0.05
    importe < 200.0 -> 0.10
    else -> 0.15
}
// final = importe - importe * porcentajeDescuento(importe)
```

### M11. Minutes → hours and minutes
```kotlin
val totalMin = etMinutos.text.toString().toIntOrNull()
if (totalMin == null || totalMin < 0) { tvResultado.text = "Dato no válido"; return@setOnClickListener }
tvResultado.text = "${totalMin / 60} h ${totalMin % 60} min"     // 135 → 2 h 15 min
```

### M12. Days in a month (with `when`)
```kotlin
fun diasDelMes(mes: Int, bisiesto: Boolean): Int = when (mes) {
    1, 3, 5, 7, 8, 10, 12 -> 31
    4, 6, 9, 11 -> 30
    2 -> if (bisiesto) 29 else 28
    else -> 0                                       // invalid month
}
```

---
---

# PART 3 – Theory & predict-the-output (with answers)

**1. Difference between `val` and `var`?**
`val` is read-only (can't be reassigned); `var` can change. Use `val` by default.

**2. Result of `7 / 2`, `7 % 2`, `7 / 2.0`, `7.toDouble() / 2`?**
`3`, `1`, `3.5`, `3.5`. Int ÷ Int is an integer division.

**3. What does `"abc".toIntOrNull()` return? And `""`? And `"12"`? And `"3.5".toIntOrNull()`?**
`null`, `null`, `12`, `null` (3.5 isn't an Int).

**4. Why use `toIntOrNull()` instead of `toInt()`?**
`toInt()` throws an exception (app crashes) with invalid text; `toIntOrNull()` returns `null` so we can show an error message.

**5. Why check `null` before comparing?**
You can't compare `null` with `<`/`>`. After `if (x == null || ...) return`, Kotlin treats `x` as non-null.

**6. `&&` vs `||`?**
`&&`: both must be true. `||`: at least one true. Valid range: `x >= 18 && x <= 65`. Invalid: `x < 0 || x > 120`.

**7. What does this print? `val x = 5; println("x vale ${x * 2} y $x")`**
`x vale 10 y 5`.

**8. Difference between `if` and `when`?**
Both are expressions that return a value. `when` is cleaner with many branches. `when` branches are checked in order, and the first true one wins.

**9. What's wrong with this `when`? `when { n <= 9 -> "A"; n <= 4 -> "B"; else -> "C" }` with n = 3.**
Returns `"A"`: the first true branch wins, so `"B"` is unreachable. Order from the most restrictive limit to the least.

**10. Why must `var contador` be declared outside the listener?**
Variables inside the lambda are recreated on every click. Outside, it keeps its value between clicks.

**11. What does `return@setOnClickListener` do?**
Exits only the click code (the lambda). It doesn't close the app. Used after showing an error.

**12. What happens if you call `findViewById` before `setContentView`?**
The views don't exist yet → returns `null` → crash (NullPointerException / IllegalStateException).

**13. What does `R.id.btnOk` mean?**
It's the numeric ID Android generated for `android:id="@+id/btnOk"` in the XML.

**14. `match_parent` vs `wrap_content`?**
`match_parent` fills the available space; `wrap_content` fits the content.

**15. `dp` vs `sp`?**
`dp` for sizes/margins (density independent). `sp` for text size (also respects user font settings).

**16. `padding` vs `margin`?**
Padding: space *inside* a view, between content and border. Margin: space *outside*, between views.

**17. How do you read the text of an EditText as a number?**
`et.text.toString().toIntOrNull()` / `.toDoubleOrNull()`.

**18. How do you know that no RadioButton is selected?**
`rg.checkedRadioButtonId == -1`.

**19. How do you clear a RadioGroup, an EditText, and a CheckBox?**
`rg.clearCheck()`, `et.setText("")`, `cb.isChecked = false`.

**20. What's `trim()` for?**
Removes spaces at both ends: `"  ".trim()` → `""`. So a name of just spaces counts as empty.

**21. Why is `(minutos + 59) / 60` used?**
Integer division that rounds up: 60 → 1, 61 → 2. Each started hour is charged in full.

**22. Why should calculation functions not use widgets?**
They should receive data and return results; that makes them reusable, testable and independent of the UI.

**23. What's a style in XML?**
A reusable set of presentation attributes in `res/values/styles.xml`, applied with `style="@style/Name"`. Ids and specific attributes (`hint`, `inputType`) stay on each view.

**24. What's `inputType="numberDecimal"` for? And `numberSigned`?**
`numberDecimal` allows a decimal point. `numberSigned` allows the minus sign. Both: `numberDecimal|numberSigned`.

**25. How do you show a double with two decimals in Spanish format?**
`String.format(Locale("es","ES"), "%.2f", x)` → `12,50`.

**26. What does this print? `0.1 + 0.2`**
`0.30000000000000004` (binary floating point). That's why we format to 2 decimals when displaying.

**27. `"5".toInt() + "2".toInt()` vs `"5" + "2"`?**
`7` (numbers added) vs `"52"` (texts concatenated).

**28. What does `var c = 0; c++; c += 2` leave in `c`?**
`3`.

**29. How do you write to Logcat and how do you filter it?**
`Log.d("PMDM", "mensaje")`; in the Logcat panel type `tag:PMDM` in the filter.

**30. `1..10` vs `1 until 10`?**
`1..10` includes 10; `1 until 10` excludes 10. `x in 1..10` checks the range; `x !in 1..10` is "outside".

---
---

# PART 4 – Find the bug (with fix)

**B1.** Crash on start:
```kotlin
val btn = findViewById<Button>(R.id.btnOk)
setContentView(R.layout.activity_main)
```
✅ Fix: `setContentView(...)` first, then `findViewById`.

**B2.** The counter always shows 1:
```kotlin
btn.setOnClickListener {
    var contador = 0
    contador++
    tv.text = "$contador"
}
```
✅ Fix: declare `var contador = 0` **outside** the listener.

**B3.** Compile error:
```kotlin
val n = etNumero.text.toIntOrNull()
```
✅ Fix: `etNumero.text.toString().toIntOrNull()` (`text` is Editable, not String).

**B4.** Compile error:
```kotlin
val edad = etEdad.text.toString().toIntOrNull()
if (edad < 0 || edad == null) { ... }
```
✅ Fix: check null first: `if (edad == null || edad < 0)`.

**B5.** Always says "Equilibrio" and the compiler complains:
```kotlin
if (saldo = 0.0) { ... }
```
✅ Fix: comparison is `==`, assignment is `=`.

**B6.** Average of 7 and 8 shows `7` instead of `7.5`:
```kotlin
val a = 7; val b = 8
val media = (a + b) / 2
```
✅ Fix: use `Double`: `val media = (a + b) / 2.0` (or read with `toDoubleOrNull`).

**B7.** Layout error: "ScrollView can host only one direct child":
```xml
<ScrollView> <EditText/> <Button/> </ScrollView>
```
✅ Fix: put one `LinearLayout` inside the ScrollView and move all views into it.

**B8.** After pressing Limpiar, seats are back to 20 but the statement said they must not recover:
```kotlin
btnLimpiar.setOnClickListener { plazasDisponibles = 20; ... }
```
✅ Fix: remove that line; Limpiar only clears fields/summary.

---
---

# PART 5 – Exam strategy (5 hours left → exam)

**Right now (next ~3 h):**
1. Solve **EX 1** and **B06** (from the first file) from scratch without looking → shows you the skeleton.
2. Re-read the cheat sheet (Part 3 here + section 1 of the first file).
3. Do **one more** exercise of your choice (EX 3 or EX 5 are good: they add buttons/discounts).

**During the exam (suggested order):**
1. **Read the whole statement**, underline every rule (ranges, percentages, "AFTER", "only if", "does NOT restore").
2. Create the project and **XML first** (ids from the statement's "elementos mínimos"). Run it once to check it displays.
3. Write **functions** (pure calculations).
4. Write the **listener**: validate → (return on error) → calculate → update state → show.
5. Write **Limpiar**.
6. Test every case in the table + 1 invalid case. Take screenshots.
7. Last 10 min: check styles used (`style=`), `ScrollView`, `fondo_panel`, nothing hardcoded that should be a function.

**Typical "traps" in these statements** (read for them!):
- "DESPUÉS del recargo" → discount base includes the surcharge.
- "únicamente si…" → extra condition to validate before applying a code.
- "NO recupera plazas" → Limpiar must not touch the state.
- "No descuentes plazas si es inválido" → modify state **last**.
- "Otro código es inválido" → code not empty and not equal → error.
- "Primero aplica el máximo, después el descuento" → order of operations matters.
- "ÚNICAMENTE de la energía" → discount only on one part.

Good luck! 🍀

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declaración de vistas según los IDs de activity_main.xml
    private lateinit var etNombre: EditText
    private lateinit var etCantidad: EditText
    private lateinit var rgPosicion: RadioGroup
    private lateinit var cbBrillo: CheckBox
    private lateinit var btnPrepararLimpiar: Button
    private lateinit var tvResumen: TextView

    // Stock inicial de los frascos (18 frascos disponibles en total al abrir la app)
    private var stockCalabaza = 6
    private var stockFantasmas = 6
    private var stockVampiros = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtención de controles mediante findViewById[span_2](start_span)[span_2](end_span)
        etNombre = findViewById(R.id.etNombre)
        etCantidad = findViewById(R.id.etCantidad)
        rgPosicion = rgPosicion ?: findViewById(R.id.rgPosicion) // O el ID correspondiente del RadioGroup
        cbBrillo = findViewById(R.id.cbBrillo)
        btnPrepararLimpiar = findViewById(R.id.btnPrepararLimpiar)
        tvResumen = findViewById(R.id.tvResumen)

        // Mostrar stock inicial en el TextView de resumen (o estado inicial)
        actualizarTextoResumen("Stock disponible -> Calabaza: $stockCalabaza, Fantasma: $stockFantasmas, Vampiro: $stockVampiros")

        btnPrepararLimpiar.setOnClickListener {
            // Evaluamos si el botón actúa como "Preparar pedido" o "Limpiar formulario"
            // Por simplicidad del ejemplo, verificamos si hay texto o según la lógica del botón:
            if (btnPrepararLimpiar.text.toString().contains("Limpiar", ignoreCase = true)) {
                limpiarFormulario()
            } else {
                procesarPedido()
            }
        }
    }

    // --- FUNCIONES DE CÁLCULO (Reciben parámetros y devuelven resultados, sin leer directamente widgets) ---

    // 1. Calcula el coste base según la porción elegida y la cantidad
    private fun calcularCosteBase(tipoPosicion: Int, cantidad: Int): Double {
        val precioUnitario = when (tipoPosicion) {
            R.id.rbCalabaza -> 1.50
            R.id.rbFantasma -> 2.00
            R.id.rbVampiro -> 2.50
            else -> 0.0
        }
        return precioUnitario * cantidad
    }

    // 2. Calcula el coste extra por brillo mágico (1.50 € por frasco si está marcado)
    private fun calcularExtraBrillo(tieneBrillo: Boolean, cantidad: Int): Double {
        return if (tieneBrillo) 1.50 * cantidad else 0.0
    }

    // 3. Calcula el descuento aplicable (10% únicamente sobre el coste base si se piden 4 o más frascos)
    private fun calcularDescuento(cantidad: Int, costeBase: Double): Double {
        return if (cantidad >= 4) costeBase * 0.10 else 0.0
    }

    // --- LÓGICA DE PROCESAMIENTO DEL PEDIDO ---

    private fun procesarPedido() {
        val nombre = etNombre.text.toString().trim()
        val cantidadStr = etCantidad.text.toString()

        // 1. Validaciones
        if (nombre.isEmpty()) {
            etNombre.error = getString(R.string.error_nombre_vacio) // Usando R.string tal como pide
            return
        }

        if (cantidadStr.isEmpty()) {
            etCantidad.error = getString(R.string.error_cantidad_vacia)
            return
        }

        val cantidad = cantidadStr.toIntOrNull()
        if (cantidad == null || cantidad !in 1..6) {
            etCantidad.error = getString(R.string.error_cantidad_invalida)
            return
        }

        val idSeleccionado = rgPosicion.checkedRadioButtonId
        if (idSeleccionado == -1) {
            Toast.makeText(this, getString(R.string.error_porcion_no_seleccionada), Toast.LENGTH_SHORT).show()
            return
        }

        val tieneBrillo = cbBrillo.isChecked

        // 4. Control de Stock
        val stockActual = when (idSeleccionado) {
            R.id.rbCalabaza -> stockCalabaza
            R.id.rbFantasma -> stockFantasmas
            R.id.rbVampiro -> stockVampiros
            else -> 0
        }

        if (cantidad > stockActual) {
            tvResumen.text = getString(R.string.error_stock_insuficiente)
            return
        }

        // Descontar del stock correspondiente
        when (idSeleccionado) {
            R.id.rbCalabaza -> stockCalabaza -= cantidad
            R.id.rbFantasma -> stockFantasmas -= cantidad
            R.id.rbVampiro -> stockVampiros -= cantidad
        }

        // 2 & 3. Realizar cálculos llamando a las funciones independientes
        val costeBase = calcularCosteBase(idSeleccionado, cantidad)
        val extraBrillo = calcularExtraBrillo(tieneBrillo, cantidad)
        val descuento = calcularDescuento(cantidad, costeBase)
        val totalAPagar = (costeBase + extraBrillo) - descuento

        // 5. Mostrar Resumen detallado con dos decimales
        val nombrePorcion = when (idSeleccionado) {
            R.id.rbCalabaza -> "Calabaza"
            R.id.rbFantasma -> "Fantasma"
            R.id.rbVampiro -> "Vampiro"
            else -> ""
        }

        val resumenTexto = String.format(
            "Cliente: %s\nPorción: %s, Cantidad: %d\nCoste base: %.2f €\nExtra por brillo: %.2f €\nDescuento: %.2f €\nTotal a pagar: %.2f €\nFrascos restantes -> Calabaza: %d, Fantasma: %d, Vampiro: %d",
            nombre, nombrePorcion, cantidad, costeBase, extraBrillo, descuento, totalAPagar,
            stockCalabaza, stockFantasmas, stockVampiros
        )

        tvResumen.text = resumenTexto

        // Cambiar funcionalidad del botón a "Limpiar formulario" tras realizar el pedido con éxito
        btnPrepararLimpiar.text = getString(R.string.btn_limpiar)
    }

    // 6. Limpiar campos (no recupera los frascos gastados, solo vacía campos y restaura el estado del resumen)
    private fun limpiarFormulario() {
        etNombre.text.clear()
        etCantidad.text.clear()
        rgPosicion.clearCheck()
        cbBrillo.isChecked = false

        actualizarTextoResumen("Stock disponible -> Calabaza: $stockCalabaza, Fantasma: $stockFantasmas, Vampiro: $stockVampiros")
        btnPrepararLimpiar.text = getString(R.string.btn_preparar)
    }

    private fun actualizarTextoResumen(texto: String) {
        tvResumen.text = texto
    }
}
