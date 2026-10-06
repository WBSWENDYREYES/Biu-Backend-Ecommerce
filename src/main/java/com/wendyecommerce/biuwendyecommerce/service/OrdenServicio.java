package com.wendyecommerce.biuwendyecommerce.service;

import com.wendyecommerce.biuwendyecommerce.config.ConexionDB;
import com.wendyecommerce.biuwendyecommerce.model.Roles;
import com.wendyecommerce.biuwendyecommerce.model.Usuario;
import com.wendyecommerce.biuwendyecommerce.model.UsuarioCliente;
import com.wendyecommerce.biuwendyecommerce.model.Orden;
import com.wendyecommerce.biuwendyecommerce.model.OrdenDetalle;
import com.wendyecommerce.biuwendyecommerce.repository.OrdenRepository;

import com.wendyecommerce.biuwendyecommerce.repository.DetalleOrdenRepository;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OrdenServicio {


    private final OrdenRepository ordenRepository;
    private final DetalleOrdenRepository detalleOrdenRepository;
    private final IPagoTarjeta pagoTarjeta;

    public OrdenServicio(
            OrdenRepository ordenRepository,
            DetalleOrdenRepository detalleOrdenRepository,
            IPagoTarjeta pagoTarjeta) {

        this.ordenRepository = ordenRepository;
        this.detalleOrdenRepository = detalleOrdenRepository;
        this.pagoTarjeta = pagoTarjeta;
    }

    public int crearOrden(
            int idUsuario,
            double total,
            String numeroTarjeta,
            String titular,
            String fechaExpiracion,
            String cvv,
            List<OrdenDetalle> detalles) {

        // 1. Procesar pago
        boolean pagoAprobado =
                pagoTarjeta.procesarPago(
                        numeroTarjeta,
                        titular,
                        fechaExpiracion,
                        cvv,
                        total
                );

        if (!pagoAprobado) {
            throw new RuntimeException(
                    "El pago fue rechazado"
            );
        }

        Connection conn = null;

        try {

            conn = ConexionDB.obtenerConexion();

            conn.setAutoCommit(false);

            // 2. Crear objeto Orden
            Orden orden = new Orden(
                    idUsuario,
                    total,
                    "PAGADA"
            );

            // 3. Guardar Orden
            int idOrden =
                    ordenRepository.guardarOrden(
                            orden,
                            conn
                    );

            if (idOrden <= 0) {
                throw new SQLException(
                        "No se pudo crear la orden"
                );
            }

            // 4. Guardar detalles
            for (OrdenDetalle detalle : detalles) {

                detalle.setIdOrden(idOrden);

                detalleOrdenRepository.guardarDetalle(
                        detalle,
                        conn
                );
            }

            // 5. Confirmar transacción
            conn.commit();

            return idOrden;

        } catch (Exception e) {

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            throw new RuntimeException(
                    "Error creando la orden",
                    e
            );

        } finally {

            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}