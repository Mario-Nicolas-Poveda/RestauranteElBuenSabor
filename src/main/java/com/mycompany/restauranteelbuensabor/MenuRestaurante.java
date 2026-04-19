package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.List;

public class MenuRestaurante {
    private static final List<Producto> productos = new ArrayList<>();

    static {
        productos.add(new Producto("Bandeja Paisa", 32000));
        productos.add(new Producto("Sancocho de Gallina", 28000));
        productos.add(new Producto("Arepa con Huevo", 8000));
        productos.add(new Producto("Jugo Natural", 7000));
        productos.add(new Producto("Gaseosa", 4500));
        productos.add(new Producto("Cerveza Poker", 6000));
        productos.add(new Producto("Agua Panela", 3500));
        productos.add(new Producto("Arroz con Pollo", 25000));
    }

    public static List<Producto> getProductos() {
        return productos;
    }

    public static Producto getProducto(int indice) {
        if (indice > 0 && indice <= productos.size()) {
            return productos.get(indice - 1);
        }
        return null;
    }
    
    public static int getCantidadProductos() {
        return productos.size();
    }
}
