package com.wendyecommerce.biuwendyecommerce.model;

import java.time.LocalDateTime;
import java.util.List;

public class Orden {

    private int id;
    private int idUsuario;
    private double total;
    private String estado;
    private LocalDateTime fecha;
    private PagoTarjeta pago;   
    private List<OrdenDetalle> detalles;

    public Orden() {
    }

    public Orden(int idUsuario, double total, String estado) {
        this.idUsuario = idUsuario;
        this.total = total;
        this.estado = estado;
        this.fecha = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public PagoTarjeta getPago() {
    return pago;
}

public void setPago(PagoTarjeta pago) {
    this.pago = pago;
}

public List<OrdenDetalle> getDetalles() {
    return detalles;
}

public void setDetalles(List<OrdenDetalle> detalles) {
    this.detalles = detalles;
}

}