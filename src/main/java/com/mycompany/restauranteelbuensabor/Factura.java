package com.mycompany.restauranteelbuensabor;

public class Factura {
    private final Pedido pedido;
    private final int numero;

    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;
    private static final double UMBRAL_PROPINA = 50000;
    private static final int MIN_ITEMS_DESCUENTO = 3;

    public Factura(Pedido pedido, int numero) {
        this.pedido = pedido;
        this.numero = numero;
    }

    public Pedido getPedido() { return pedido; }
    public int getNumero() { return numero; }

    public double calcularSubtotal() {
        return pedido.calcularSubtotal();
    }

    public double calcularDescuento() {
        double subtotal = calcularSubtotal();
        if (pedido.contarItemsDiferentes() > MIN_ITEMS_DESCUENTO && subtotal > 0) {
            return subtotal * TASA_DESCUENTO;
        }
        return 0;
    }

    public double calcularIVA() {
        double subtotalConDescuento = calcularSubtotal() - calcularDescuento();
        return subtotalConDescuento * TASA_IVA;
    }

    public double calcularPropina() {
        double subtotalConDescuento = calcularSubtotal() - calcularDescuento();
        if (subtotalConDescuento > UMBRAL_PROPINA) {
            double montoIva = calcularIVA();
            return (subtotalConDescuento + montoIva) * TASA_PROPINA;
        }
        return 0;
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento() + calcularIVA() + calcularPropina();
    }
}
