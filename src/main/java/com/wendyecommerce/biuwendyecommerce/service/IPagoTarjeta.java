package com.wendyecommerce.biuwendyecommerce.service;

public interface IPagoTarjeta {

    boolean procesarPago(
        String numeroTarjeta,
        String titular,
        String fechaExpiracion,
        String cvv,
        double monto
    );
}