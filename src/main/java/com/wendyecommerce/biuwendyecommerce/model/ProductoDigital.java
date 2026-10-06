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
// AQUI MOSTRAMOS EN EL EJEMPLO DE LA SOBRE ESCRITURA DEL METODO DE LA CLASE PADRE, PARA MOSTRAR LOS DETALLES DEL PRODUCTO DIGITAL
    @Override
    public String mostrarDetalle() {
        return "Producto Digital: " + getNombre() + " | Ref: " + getReferencia() + " | Descripción: " + getDescripcion() + " | Precio: $" + getPrecio() +
                " | Formato de Archivo: " + formatoArchivo + " | Tamaño de Archivo: " + tamanoArchivo + " MB";
    }
    

}