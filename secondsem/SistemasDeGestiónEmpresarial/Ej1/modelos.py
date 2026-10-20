"""Clases de dominio de Floristería Lila. Sin print() ni input()."""

DESCUENTO = 0.10
UMBRAL_DESCUENTO = 50.0


class Producto:
    def __init__(self, codigo, nombre, categoria, precio, stock):
        self.codigo = codigo
        self.nombre = nombre
        self.categoria = categoria
        self.precio = precio
        self.stock = stock

    def __str__(self):
        return (f"{self.codigo:<6} {self.nombre:<12} {self.categoria:<10} "
                f"{self.precio:>6.2f} € | stock: {self.stock}")


class LineaVenta:
    def __init__(self, producto, cantidad, precio_unitario):
        self.producto = producto
        self.cantidad = cantidad
        self.precio_unitario = precio_unitario  # precio en el momento de la venta

    def subtotal(self):
        return round(self.cantidad * self.precio_unitario, 2)


class Venta:
    def __init__(self, numero, cliente):
        self.numero = numero
        self.cliente = cliente
        self.lineas = []

    def agregar_linea(self, producto, cantidad):
        for linea in self.lineas:
            if linea.producto.codigo == producto.codigo and linea.precio_unitario == producto.precio:
                linea.cantidad += cantidad
                return linea
        linea = LineaVenta(producto, cantidad, producto.precio)
        self.lineas.append(linea)
        return linea

    def subtotal(self):
        return round(sum(l.subtotal() for l in self.lineas), 2)

    def descuento(self):
        """10 % sobre el total (no por línea) si el subtotal es >= 50 €."""
        sub = self.subtotal()
        return round(sub * DESCUENTO, 2) if sub >= UMBRAL_DESCUENTO else 0.0

    def total(self):
        return round(self.subtotal() - self.descuento(), 2)

    def detalle(self):
        """Devuelve una lista de cadenas; la impresión la hace main()."""
        filas = [f"{l.cantidad} x {l.producto.nombre} @ {l.precio_unitario:.2f} € = {l.subtotal():.2f} €"
                 for l in self.lineas]
        filas.append(f"Subtotal: {self.subtotal():.2f} €")
        filas.append(f"Descuento: -{self.descuento():.2f} €")
        filas.append(f"TOTAL: {self.total():.2f} €")
        return filas


class Floristeria:
    """Clase gestora: catálogo, venta en curso e historial."""

    def __init__(self, productos=None, ventas_cerradas=None):
        self.productos = dict(productos or {})
        self.ventas_cerradas = list(ventas_cerradas or [])
        self.venta_actual = None

    # --- catálogo ---
    def alta_producto(self, codigo, nombre, categoria, precio, stock):
        codigo = codigo.strip().upper()
        if not codigo or not nombre.strip():
            return False, "Código y nombre no pueden estar vacíos."
        if codigo in self.productos:
            return False, "Ya existe un producto con ese código."
        if precio < 0:
            return False, "El precio no puede ser negativo."
        if stock < 0:
            return False, "El stock no puede ser negativo."
        self.productos[codigo] = Producto(codigo, nombre.strip(), categoria.strip(), precio, stock)
        return True, "Producto dado de alta."

    def reponer(self, codigo, cantidad):
        producto = self.productos.get(codigo.strip().upper())
        if producto is None:
            return False, "Producto inexistente."
        if cantidad <= 0:
            return False, "La cantidad a reponer debe ser positiva."
        producto.stock += cantidad
        return True, f"Nuevo stock de {producto.nombre}: {producto.stock}."

    # --- ventas ---
    def siguiente_numero(self):
        numeros = [v.numero for v in self.ventas_cerradas]
        return (max(numeros) if numeros else 0) + 1

    def nueva_venta(self, cliente):
        if self.venta_actual is not None:
            return False, "Ya hay una venta en curso."
        if not cliente.strip():
            return False, "El cliente no puede estar vacío."
        self.venta_actual = Venta(self.siguiente_numero(), cliente.strip())
        return True, f"Venta {self.venta_actual.numero} abierta."

    def agregar_linea(self, codigo, cantidad):
        if self.venta_actual is None:
            return False, "No hay venta en curso."
        producto = self.productos.get(codigo.strip().upper())
        if producto is None:
            return False, "Producto inexistente."
        if cantidad <= 0:
            return False, "La cantidad debe ser mayor que cero."
        if cantidad > producto.stock:
            return False, f"Stock insuficiente (disponible: {producto.stock})."
        # Línea válida: se descuenta el stock una sola vez
        producto.stock -= cantidad
        self.venta_actual.agregar_linea(producto, cantidad)
        return True, "Línea añadida."

    def cerrar_venta(self):
        if self.venta_actual is None:
            return False, "No hay venta en curso."
        if not self.venta_actual.lineas:
            return False, "La venta no tiene líneas."
        self.ventas_cerradas.append(self.venta_actual)
        total = self.venta_actual.total()
        self.venta_actual = None
        return True, f"Venta cerrada. Total: {total:.2f} €."

    def cancelar_venta(self):
        """Devuelve al stock todas las unidades reservadas; no toca ventas cerradas."""
        if self.venta_actual is None:
            return False, "No hay venta en curso."
        for linea in self.venta_actual.lineas:
            linea.producto.stock += linea.cantidad
        self.venta_actual = None
        return True, "Venta cancelada y stock devuelto."

    def importe_acumulado(self):
        return round(sum(v.total() for v in self.ventas_cerradas), 2)