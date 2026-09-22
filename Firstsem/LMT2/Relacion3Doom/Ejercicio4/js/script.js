

const lista = document.getElementById("integrantes");

// Crear nuevo integrante
const nuevoIntegrante = document.createElement("li");
nuevoIntegrante.textContent = "Trompetista";
lista.appendChild(nuevoIntegrante);

// Eliminar Batería
const items = lista.querySelectorAll("li");

for (let i = 0; i < items.length; i++) {
    if (items[i].textContent === "Batería") {
        items[i].remove();
    }
}