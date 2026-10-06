package com.wendyecommerce.biuwendyecommerce.repository;

import com.wendyecommerce.biuwendyecommerce.config.ConexionDB;
import com.wendyecommerce.biuwendyecommerce.model.Roles;
import com.wendyecommerce.biuwendyecommerce.model.Usuario;
import com.wendyecommerce.biuwendyecommerce.model.OrdenDetalle;
import com.wendyecommerce.biuwendyecommerce.model.Orden;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class DetalleOrdenRepository {

    public void guardarDetalle(
            OrdenDetalle detalle,
            Connection conn) throws SQLException {

        String sql = """
            INSERT INTO DetalleOrden
                (idOrden, idProducto, cantidad, precio)
            VALUES (?, ?, ?, ?)
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, detalle.getIdOrden());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setBigDecimal(
                4,
                BigDecimal.valueOf(detalle.getPrecio())
            );

            ps.executeUpdate();
        }
    }
}