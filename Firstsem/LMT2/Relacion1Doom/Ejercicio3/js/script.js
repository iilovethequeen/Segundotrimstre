/*Al cargar la página:
Cambia el color de fondo de las noticias impares a lightgray.
Cambia el color del texto de las noticias pares a blue.
Añade un emoji 🔥 al inicio de cada noticia.*/
window.onload = function() {
    // Selecciona todas las noticias
    const noticias = document.querySelectorAll('.noticia');
    // Itera sobre las noticias y aplica los estilos
    noticias.forEach((noticia, index) => {
        if (index % 2 === 0) {
            // Noticias pares (índice 0, 2, 4, ...)
            noticia.style.color = 'blue';
        }
        if (index % 2 !== 0) {
            // Noticias impares (índice 1, 3, 5, ...)
            noticia.style.backgroundColor = 'lightgray';
        }
        // Añade el emoji 🔥 al inicio de cada noticia
        noticia.innerHTML = '🔥 ' + noticia.innerHTML;
    });
};