package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

public class RestauranteElBuenSabor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        int opcionMenu = 0;
        int intentosMalos = 0;

        Pedido pedidoActual = new Pedido();
        Mesa mesaActual = null;
        int siguienteNumeroFactura = 1;

        FacturaImpresor.imprimirEncabezado();

        while (flag) {
            System.out.println("1. Ver carta");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Generar factura");
            System.out.println("5. Nueva mesa");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");

            opcionMenu = sc.nextInt();

            if (opcionMenu == 1) {
                FacturaImpresor.mostrarCarta();
                System.out.println();
            } else if (opcionMenu == 2) {
                System.out.println("--- AGREGAR PRODUCTO ---");
                System.out.print("Numero de producto (1-" + MenuRestaurante.getCantidadProductos() + "): ");
                int numeroProducto = sc.nextInt();
                System.out.print("Cantidad: ");
                int cantidad = sc.nextInt();

                if (numeroProducto > 0 && numeroProducto <= MenuRestaurante.getCantidadProductos()) {
                    if (cantidad > 0) {
                        if (mesaActual == null || !mesaActual.isActiva()) {
                            System.out.print("Ingrese numero de mesa: ");
                            int numMesa = sc.nextInt();
                            if (numMesa > 0) {
                                mesaActual = new Mesa(numMesa);
                            } else {
                                mesaActual = new Mesa(1);
                            }
                        }

                        Producto p = MenuRestaurante.getProducto(numeroProducto);
                        if (p != null) {
                            pedidoActual.agregarItem(p, cantidad);
                            System.out.println("Producto agregado al pedido.");
                            System.out.println("  -> " + p.getNombre() + " x" + cantidad);
                        }
                    } else {
                        if (cantidad == 0) {
                            System.out.println("La cantidad no puede ser cero.");
                        } else {
                            System.out.println("Cantidad invalida. Ingrese un valor positivo.");
                        }
                    }
                } else {
                    if (numeroProducto <= 0) {
                        System.out.println("El numero debe ser mayor a cero.");
                    } else {
                        System.out.println("Producto no existe. La carta tiene "
                                + MenuRestaurante.getCantidadProductos() + " productos.");
                    }
                }
                System.out.println();
            } else if (opcionMenu == 3) {
                System.out.println();
                if (pedidoActual.tieneProductos()) {
                    FacturaImpresor.mostrarPedido(pedidoActual);
                } else {
                    System.out.println("No hay productos en el pedido actual.");
                    System.out.println("Use la opcion 2 para agregar productos.");
                }
                System.out.println();
            } else if (opcionMenu == 4) {
                System.out.println();
                if (pedidoActual.tieneProductos()) {
                    Factura factura = new Factura(pedidoActual, siguienteNumeroFactura++);
                    FacturaImpresor.imprimirFacturaCompleta(factura);
                    if (mesaActual != null) {
                        mesaActual.desactivar();
                    }
                    pedidoActual = new Pedido();
                    System.out.println();
                } else {
                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println("Use la opcion 2 para agregar productos primero.");
                }
            } else if (opcionMenu == 5) {
                System.out.println();
                pedidoActual = new Pedido();
                mesaActual = null;
                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();
            } else if (opcionMenu == 0) {
                flag = false;
                System.out.println("Hasta luego!");
            } else {
                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
                intentosMalos++;
                if (intentosMalos > 3) {
                    System.out.println("Demasiados intentos invalidos.");
                    intentosMalos = 0;
                    if (sc.hasNextLine())
                        sc.nextLine();
                }
            }
        }
        sc.close();
    }
}
