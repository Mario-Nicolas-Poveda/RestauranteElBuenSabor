package com.mycompany.restauranteelbuensabor;

public class FacturaImpresor {

    private static final String NOMBRE_RESTAURANTE = "El Buen Sabor";
    private static final String DIRECCION = "Calle 15 #8-32, Valledupar";
    private static final String NIT = "900.123.456-7";

    public static void imprimirEncabezado() {
        System.out.println("========================================");
        System.out.println("    RESTAURANTE " + NOMBRE_RESTAURANTE.toUpperCase());
        System.out.println("    " + DIRECCION);
        System.out.println("    NIT: " + NIT);
        System.out.println("========================================");
    }

    public static void mostrarCarta() {
        imprimirEncabezado();
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println("========================================");
        int indice = 1;
        for (Producto p : MenuRestaurante.getProductos()) {
            System.out.printf("%d. %-22s $%,.0f%n", indice, p.getNombre(), p.getPrecio());
            indice++;
        }
        System.out.println("========================================");
    }

    public static void mostrarPedido(Pedido pedido) {
        System.out.println("--- PEDIDO ACTUAL ---");
        for (ItemPedido item : pedido.getItems()) {
            System.out.printf("%-20s x%-6d $%,.0f%n", item.getProducto().getNombre(), item.getCantidad(), item.calcularSubtotal());
        }
        System.out.println("--------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", pedido.calcularSubtotal());
    }

    private static void imprimirCuerpoFactura(Factura factura, boolean esResumen) {
        String sep = "========================================";
        imprimirEncabezado();
        
        if (esResumen) {
            System.out.printf("FACTURA No. %03d (RESUMEN)%n", factura.getNumero());
        } else {
            System.out.printf("FACTURA No. %03d%n", factura.getNumero());
        }
        System.out.println("----------------------------------------");
        
        if (!esResumen) {
            for (ItemPedido item : factura.getPedido().getItems()) {
                System.out.printf("%-20s x%-6d $%,.0f%n", item.getProducto().getNombre(), item.getCantidad(), item.calcularSubtotal());
            }
            System.out.println("----------------------------------------");
        }

        double auxiliar = factura.calcularSubtotal() - factura.calcularDescuento();
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", auxiliar);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%%):", factura.calcularIVA());
        
        double prop = factura.calcularPropina();
        if (prop > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%%):", prop);
        }
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", factura.calcularTotal());
        System.out.println(sep);
        
        if (!esResumen) {
            System.out.println("Gracias por su visita!");
            System.out.println(NOMBRE_RESTAURANTE + " - Valledupar");
            System.out.println(sep);
        }
    }

    public static void imprimirFacturaCompleta(Factura factura) {
        imprimirCuerpoFactura(factura, false);
    }

    public static void imprimirFacturaResumen(Factura factura) {
        imprimirCuerpoFactura(factura, true);
    }
}
