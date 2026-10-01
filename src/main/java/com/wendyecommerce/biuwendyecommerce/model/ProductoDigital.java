package com.wendyecommerce.biuwendyecommerce.model;
public class ProductoDigital extends Producto {
    private String formatoArchivo;
    private double tamanoArchivo;

    public ProductoDigital() {
        super();
    }

    public String getformatoArchivo() {
        return formatoArchivo;
    }

    public void setformatoArchivo(String formatoArchivo) {
        this.formatoArchivo = formatoArchivo;
    }

    public double getTamanoArchivo() {
        return tamanoArchivo;
    }

    public void setTamanoArchivo(double tamanoArchivo) {
        this.tamanoArchivo = tamanoArchivo;
    }
@Override    
public String mostrarDetalle() {
        return "Producto Digital: " + nombre + " | Ref: " + referencia + " | Descripción: " + descripcion + " | Precio: $" + precio;
}
}