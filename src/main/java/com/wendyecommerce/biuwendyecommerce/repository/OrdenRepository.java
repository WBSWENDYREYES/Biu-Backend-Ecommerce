package com.wendyecommerce.biuwendyecommerce.repository;

import com.wendyecommerce.biuwendyecommerce.config.ConexionDB;
import com.wendyecommerce.biuwendyecommerce.model.Roles;
import com.wendyecommerce.biuwendyecommerce.model.Usuario;
import com.wendyecommerce.biuwendyecommerce.model.UsuarioCliente;
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
public class OrdenRepository {

    public int guardarOrden(Orden orden, Connection conn) throws SQLException {

        String sql = """
            INSERT INTO Orden
                (idUsuario, total, estado, fecha)
            OUTPUT INSERTED.id
            VALUES (?, ?, ?, ?)
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orden.getIdUsuario());
            ps.setBigDecimal(
                2,
                BigDecimal.valueOf(orden.getTotal())
            );
            ps.setString(3, orden.getEstado());
            ps.setTimestamp(
                4,
                Timestamp.valueOf(orden.getFecha())
            );

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }

        throw new SQLException("No se pudo obtener el ID de la orden");
    }
}