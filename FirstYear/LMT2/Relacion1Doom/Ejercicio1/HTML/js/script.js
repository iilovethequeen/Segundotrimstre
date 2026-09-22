
const resaltarAnimalesButton = document.getElementById("resaltarAnimales");
resaltarAnimalesButton.addEventListener("click", function () {
  const animales = document.querySelectorAll(".animal");
  animales.forEach(animal => {
    animal.style.backgroundColor = "yellow";
  });
});
