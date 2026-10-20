"""Punto de entrada. Floristería Lila: inventario y ventas.
Toda la interacción por consola (print/input) vive aquí."""
from pathlib import Path

from modelos import Floristeria
from persistencia import (cargar_productos, guardar_productos,
                          cargar_ventas, guardar_ventas)

BASE = Path(__file__).parent
RUTA_PRODUCTOS = BASE / "productos.csv"
RUTA_VENTAS = BASE / "ventas.csv"

MENU = """
=== Floristería Lila ===
1) Ver productos   2) Alta   3) Reponer
4) Nueva venta / añadir líneas   5) Cerrar venta
6) Consultar ventas   7) Guardar   8) Cancelar venta actual
0) Salir"""


def pedir_numero(texto, tipo):
    """Devuelve un número del tipo pedido o None si la entrada no es válida."""
    try:
        return tipo(input(texto).strip().replace(",", "."))
    except ValueError:
        print("Entrada no válida: introduce un número.")
        return None


def ver_productos(tienda):
    for p in tienda.productos.values():
        print(p)


def alta(tienda):
    codigo = input("Código: ")
    nombre = input("Nombre: ")
    categoria = input("Categoría: ")
    precio = pedir_numero("Precio: ", float)
    stock = pedir_numero("Stock: ", int)
    if precio is None or stock is None:
        return
    print(tienda.alta_producto(codigo, nombre, categoria, precio, stock)[1])


def reponer(tienda):
    codigo = input("Código: ")
    cantidad = pedir_numero("Cantidad: ", int)
    if cantidad is not None:
        print(tienda.reponer(codigo, cantidad)[1])


def nueva_venta(tienda):
    if tienda.venta_actual is None:
        ok, msg = tienda.nueva_venta(input("Cliente: "))
        print(msg)
        if not ok:
            return
    codigo = "x"
    while codigo != "":
        codigo = input("Código de producto (vacío para terminar): ").strip()
        if codigo != "":
            cantidad = pedir_numero("Cantidad: ", int)
            if cantidad is not None:
                print(tienda.agregar_linea(codigo, cantidad)[1])
    for fila in tienda.venta_actual.detalle():
        print(fila)


def cerrar(tienda):
    if tienda.venta_actual is not None:
        for fila in tienda.venta_actual.detalle():
            print(fila)
    print(tienda.cerrar_venta()[1])


def consultar(tienda):
    for v in tienda.ventas_cerradas:
        print(f"Venta {v.numero} - {v.cliente} - {v.total():.2f} €")
    print(f"Importe acumulado: {tienda.importe_acumulado():.2f} €")


def guardar(tienda):
    if tienda.venta_actual is not None:
        print("Cierra o cancela la venta actual antes de guardar.")
        return
    guardar_productos(RUTA_PRODUCTOS, tienda.productos)
    guardar_ventas(RUTA_VENTAS, tienda.ventas_cerradas)
    print("Datos guardados.")


def main():
    productos = cargar_productos(RUTA_PRODUCTOS)
    tienda = Floristeria(productos, cargar_ventas(RUTA_VENTAS, productos))
    acciones = {1: ver_productos, 2: alta, 3: reponer, 4: nueva_venta,
                5: cerrar, 6: consultar, 7: guardar,
                8: lambda t: print(t.cancelar_venta()[1])}
    opcion = -1
    while opcion != 0:
        print(MENU)
        opcion = pedir_numero("Opción: ", int)
        if opcion is None:
            opcion = -1
        elif opcion in acciones:
            acciones[opcion](tienda)
        elif opcion != 0:
            print("Opción no válida.")
    print("¡Hasta pronto!")


if __name__ == "__main__":
    main()