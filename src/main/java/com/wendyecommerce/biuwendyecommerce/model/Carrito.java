package com.wendyecommerce.biuwendyecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    
    private List<Producto> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    // =========================================================================
    // VERSIÓN 1: Agregar pasándole el Objeto completo (Producto, Digital o Físico)
    // =========================================================================
    public void agregarProducto(Producto producto) {
        this.items.add(producto);
        System.out.println("Añadido por OBJETO: " + producto.getNombre() + " al carrito.");
    }

    // =========================================================================
    // VERSIÓN 2: Agregar solo por ID (Busca en la Base de Datos antes de agregar)
    // =========================================================================
    public void agregarProducto(int idProducto) {
        // Aquí simularías ir a la Base de Datos a buscar el producto con tu conexión JDBC
        // Por ejemplo: Producto p = productoDAO.buscarPorId(idProducto);
        
        Producto productoSimulado = new Producto();
        productoSimulado.setid(idProducto);
        productoSimulado.setNombre("Producto ID: " + idProducto);
        productoSimulado.setPrecio(15.99);

        this.items.add(productoSimulado);
        System.out.println("Añadido por ID: Se encontró el producto '" + productoSimulado.getNombre() + "' y se sumó al carrito.");
    }

    // =========================================================================
    // VERSIÓN 3: Agregar al vuelo por Nombre y Precio (Útil para artículos genéricos o servicios rápidos)
    // =========================================================================
    public void agregarProducto(String nombre, double precio) {
        Producto productoRapido = new Producto();
        productoRapido.setNombre(nombre);
        productoRapido.setPrecio(precio);
        productoRapido.setExistencia(1);

        this.items.add(productoRapido);
        System.out.println("Añadido AL VUELO: '" + nombre + "' con un precio de $" + precio);
    }

    // Método auxiliar para ver el total del carrito
    public int getCantidadItems() {
        return this.items.size();
    }
}