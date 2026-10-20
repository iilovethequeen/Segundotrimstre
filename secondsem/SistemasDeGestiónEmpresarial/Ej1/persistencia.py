"""Lectura/escritura de CSV. Sin print() ni input()."""
import csv
from pathlib import Path

from modelos import Producto, LineaVenta, Venta

CAMPOS_PRODUCTOS = ["codigo", "nombre", "categoria", "precio", "stock"]
CAMPOS_VENTAS = ["numero", "cliente", "codigo_producto", "cantidad", "precio_unitario"]


def productos_iniciales():
    return {
        "FLO01": Producto("FLO01", "Rosas", "Flor", 3.50, 15),
        "FLO02": Producto("FLO02", "Tulipanes", "Flor", 2.25, 24),
        "FLO03": Producto("FLO03", "Orquídeas", "Flor", 18.00, 4),
    }


def cargar_productos(ruta):
    ruta = Path(ruta)
    if not ruta.exists():
        return productos_iniciales()
    productos = {}
    with open(ruta, newline="", encoding="utf-8") as f:
        for fila in csv.DictReader(f):
            try:
                codigo = fila["codigo"].strip().upper()
                precio = float(fila["precio"])
                stock = int(fila["stock"])
                if not codigo or codigo in productos or precio < 0 or stock < 0:
                    continue
                productos[codigo] = Producto(codigo, fila["nombre"], fila["categoria"], precio, stock)
            except (ValueError, KeyError, TypeError):
                continue
    return productos


def guardar_productos(ruta, productos):
    with open(ruta, "w", newline="", encoding="utf-8") as f:
        w = csv.writer(f)
        w.writerow(CAMPOS_PRODUCTOS)
        for p in productos.values():
            w.writerow([p.codigo, p.nombre, p.categoria, f"{p.precio:.2f}", p.stock])


def cargar_ventas(ruta, productos):
    """Reconstruye ventas cerradas (una fila por línea). Ignora filas con productos inexistentes.
    No modifica el stock: ya se guardó actualizado en productos.csv."""
    ruta = Path(ruta)
    ventas = {}
    if not ruta.exists():
        return []
    with open(ruta, newline="", encoding="utf-8") as f:
        for fila in csv.DictReader(f):
            try:
                numero = int(fila["numero"])
                cantidad = int(fila["cantidad"])
                precio = float(fila["precio_unitario"])
                producto = productos.get(fila["codigo_producto"].strip().upper())
                if producto is None or cantidad <= 0 or precio < 0:
                    continue
                if numero not in ventas:
                    ventas[numero] = Venta(numero, fila["cliente"])
                ventas[numero].lineas.append(LineaVenta(producto, cantidad, precio))
            except (ValueError, KeyError, TypeError):
                continue
    return [v for v in ventas.values() if v.lineas]


def guardar_ventas(ruta, ventas):
    with open(ruta, "w", newline="", encoding="utf-8") as f:
        w = csv.writer(f)
        w.writerow(CAMPOS_VENTAS)
        for v in ventas:
            for l in v.lineas:
                w.writerow([v.numero, v.cliente, l.producto.codigo, l.cantidad, f"{l.precio_unitario:.2f}"])