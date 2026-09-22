const lista = document.getElementById("albumes");
const talco = document.getElementById("talco");

// Crear nuevo álbum
const nuevoAlbum = document.createElement("li");
nuevoAlbum.textContent = '"Xapomelon" - La Pegatina';

// Insertar antes de Talco
lista.insertBefore(nuevoAlbum, talco);

// Agregar Street Gigs al final
const ultimoAlbum = document.createElement("li");
ultimoAlbum.textContent = '"Street Gigs" - The Locos';

lista.appendChild(ultimoAlbum);