/*Al cargar la página, usa JavaScript para agregar tres <p> dentro del div con id="contenedor" con frases como:

"Descubren una nueva especie en la selva amazónica."
"Avances tecnológicos revolucionan la industria de la salud."
"El mercado financiero experimenta grandes cambios."*/

document.addEventListener("DOMContentLoaded", function() {
    const contenedor = document.getElementById("contenedor");

    const frases = [
        "Descubren una nueva especie en la selva amazónica.",
        "Avances tecnológicos revolucionan la industria de la salud.",
        "El mercado financiero experimenta grandes cambios."
    ];

    frases.forEach(frase => {
        const p = document.createElement("p");
        p.textContent = frase;
        contenedor.appendChild(p);
    }
    );
}); 

