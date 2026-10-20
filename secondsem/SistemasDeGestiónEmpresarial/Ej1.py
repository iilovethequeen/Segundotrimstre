libros = [
    {"titulo": "book1", "autor": "autor1", "paginas": 100, "prestado": False},
    {"titulo": "book2", "autor": "autor2", "paginas": 200, "prestado": True},
    {"titulo": "book3", "autor": "autor3", "paginas": 300, "prestado": False},
    {"titulo": "book4", "autor": "autor4", "paginas": 400, "prestado": True}
]

generos = ["novela", "ciencia", "novela", "historia", "poesía", "ciencia", "terror", "historia"]

# Part 1: unique genres
generos_unicos = set(generos)

print("--- Géneros únicos ---")
for genero in generos_unicos:
    print(genero)

# Part 2: search titles
texto = input("Texto a buscar: ").strip().lower()

print("--- Resultados ---")
for libro in libros:
    if texto in libro["titulo"].lower():
        print(libro["titulo"])