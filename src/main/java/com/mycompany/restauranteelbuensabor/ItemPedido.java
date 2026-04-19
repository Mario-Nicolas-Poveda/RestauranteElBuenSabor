package com.mycompany.restauranteelbuensabor;

public class ItemPedido {
    private final Producto producto;
    private int cantidad;

    public ItemPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public void agregarCantidad(int cantidadAdicional) {
        this.cantidad += cantidadAdicional;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}
