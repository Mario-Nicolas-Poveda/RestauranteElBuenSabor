package com.mycompany.restauranteelbuensabor;

public class Mesa {
    private final int numero;
    private boolean activa;

    public Mesa(int numero) {
        this.numero = numero;
        this.activa = true;
    }

    public int getNumero() { return numero; }
    public boolean isActiva() { return activa; }

    public void desactivar() { this.activa = false; }
}
