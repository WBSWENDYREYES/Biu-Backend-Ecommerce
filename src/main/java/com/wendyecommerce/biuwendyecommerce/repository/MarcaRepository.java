package com.wendyecommerce.biuwendyecommerce.repository;

import com.wendyecommerce.biuwendyecommerce.config.ConexionDB;
import com.wendyecommerce.biuwendyecommerce.model.Roles;
import com.wendyecommerce.biuwendyecommerce.model.Usuario;
import com.wendyecommerce.biuwendyecommerce.model.UsuarioCliente;
import com.wendyecommerce.biuwendyecommerce.model.Categoria;
import com.wendyecommerce.biuwendyecommerce.model.Marca;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class MarcaRepository {
    
public List<Marca> listaMarca() {
    List<Marca> lista = new ArrayList<>();
    String sql = "SELECT id, nombre FROM Marca"; // Ajusta tus columnas si es necesario
    
    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            Marca marca = new Marca();
            marca.setId(rs.getInt("id"));
            marca.setNombre(rs.getString("nombre"));
            
            lista.add(marca); // Agregamos el objeto Roles directamente a la lista
        }
        
    } catch (SQLException e) {
        System.err.println("Error en lista marca: " + e.getMessage());
    }
    
    return lista; // ✅ Retorna la lista de mapas limpia (vacía o con datos)
}

 public Optional<Marca> buscarPorId(int id) {
    String sql = "SELECT id, nombre FROM marca WHERE id = ?"; // Ajusta tus columnas si es necesario

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
             Marca marca = new Marca();
       while (rs.next()) {
            marca.setId(rs.getInt("id"));
            marca.setNombre(rs.getString("nombre"));
        }
        return Optional.of(marca);
    } catch (SQLException e) {
        System.err.println("Error en listaRoles: " + e.getMessage());
    }
    
    return Optional.empty(); // ✅ Retorna la lista de mapas limpia (vacía o con datos)
}

 public Marca save(Marca marca) {

        if (marca.getId() > 0) {
          System.err.println("update marca ");
    // Si el ID ya existe, ejecutamos un UPDATE
            String sql = "UPDATE marca SET nombre = ? WHERE id = ?";
            try (Connection conn = ConexionDB.obtenerConexion();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
                ps.setString(1, marca.getNombre());
                ps.setInt(2,  marca.getId());
                ps.executeUpdate();
                
            } catch (SQLException e) {
                System.err.println("Error al actualizar marca: " + e.getMessage());
            }
        } else {
            // Si el ID es 0 o nuevo, ejecutamos un INSERT
            System.err.println("insert marca ");
             String sql = "INSERT INTO marca (nombre) VALUES (?)";
            try (Connection conn = ConexionDB.obtenerConexion();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, marca.getNombre());
                ps.executeUpdate();
                
                // Obtener el ID auto-generado por SQL Server
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        marca.setId(generatedKeys.getInt(1));
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al insertar usuario: " + e.getMessage());
            }
        }
        return marca;
    }

// eliminar registro
public String BorrarPorId(int id) {
    List<Roles> lista = new ArrayList<>();
    String sql = "DELETE FROM marca WHERE id = ?"; // Ajusta tus columnas si es necesario

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
    } catch (SQLException e) {
        System.err.println("Error en lista marcas: " + e.getMessage());
    }
    
    return "BORRADO"; // ✅ Retorna la lista de mapas limpia (vacía o con datos)
}
}

