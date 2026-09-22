//Al cargar la página:

//Aplica fontWeight = "bold" a los precios.
//Si un precio es mayor a 50, cámbialo a color rojo.
document.addEventListener("DOMContentLoaded", function() {
    const precios = document.querySelectorAll(".precio");
    precios.forEach(precio => {
        precio.style.fontWeight = "bold";
        if (parseInt(precio.textContent) > 50) {
            precio.style.color = "red";
        }
    });
});