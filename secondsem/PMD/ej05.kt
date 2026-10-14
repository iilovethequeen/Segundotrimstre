class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nota = findViewById<EditText>(R.id.etNota)
        val resultado = findViewById<TextView>(R.id.tvResultado)
        val boton = findViewById<Button>(R.id.btnEvaluar)

        boton.setOnClickListener {
            // convierte y valida nota (Int entre 0 y 10).
            val notaInt = nota.text.toString().toIntOrNull()

            if (notaInt == null || notaInt < 0 || notaInt > 10) {
                // muestra el error si la nota no es válida.
                resultado.text = "Nota no es válida"
            } else {
                //  si es válida usa when para asignar la calificación.
                val calificacion = when (notaInt) {
                    in 0..4 -> "Suspenso"
                    5, 6 -> "Aprobado"
                    7, 8 -> "Notable"
                    9, 10 -> "Sobresaliente"
                    else -> "Nota no es válida"
                }

                //  muestra el resultado.
                resultado.text = "Calificación: $calificacion"
            }
        }
    }
}