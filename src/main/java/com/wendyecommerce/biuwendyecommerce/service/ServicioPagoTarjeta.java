package com.wendyecommerce.biuwendyecommerce.service;

import org.springframework.stereotype.Service;

@Service
public class ServicioPagoTarjeta implements IPagoTarjeta {

    @Override
    public boolean procesarPago(
            String numeroTarjeta,
            String titular,
            String fechaExpiracion,
            String cvv,
            double monto) {

        System.out.println("Procesando pago...");
        System.out.println("Monto: " + monto);

        // Aquí posteriormente conectaremos
        // Stripe, Azul, CardNET, etc.

        return true;
    }
}