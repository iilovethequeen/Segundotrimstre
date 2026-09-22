

const canciones = document.querySelectorAll("li");

for (let i = 0; i < canciones.length; i++) {
    canciones[i].style.color = "green";
    canciones[i].textContent += " 🎶";
}
