package com.wendyecommerce.biuwendyecommerce.repository;

import com.wendyecommerce.biuwendyecommerce.config.ConexionDB;
import com.wendyecommerce.biuwendyecommerce.model.Roles;
import com.wendyecommerce.biuwendyecommerce.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class RolesRepository {
    
public List<Roles> listaRoles() {
    List<Roles> lista = new ArrayList<>();
    String sql = "SELECT id, nombre FROM roles"; // Ajusta tus columnas si es necesario
    
    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            Roles roles = new Roles();
            roles.setId(rs.getInt("id"));
            roles.setNombre(rs.getString("nombre"));
            
            lista.add(roles); // Agregamos el objeto Roles directamente a la lista
        }
        
    } catch (SQLException e) {
        System.err.println("Error en listaRoles: " + e.getMessage());
    }
    
    return lista; // ✅ Retorna la lista de mapas limpia (vacía o con datos)
}

public List<Roles> buscarPorId(int id) {
    List<Roles> lista = new ArrayList<>();
    String sql = "SELECT id, nombre FROM roles WHERE id = ?"; // Ajusta tus columnas si es necesario

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Roles roles = new Roles();
            roles.setId(rs.getInt("id"));
            roles.setNombre(rs.getString("nombre"));
            lista.add(roles); // Agregamos el objeto Roles directamente a la lista
        }
        
    } catch (SQLException e) {
        System.err.println("Error en listaRoles: " + e.getMessage());
    }
    
    return lista; // ✅ Retorna la lista de mapas limpia (vacía o con datos)
}

public List<Roles> SalvarRoles(int id, String nombre ) {
    List<Roles> lista = new ArrayList<>();
     String sql = ""; // Ajusta tus columnas si es necesario
 if (id > 0 ) { sql = "UPDATE ROLES SET nombre = ? where id = ?";}
     else { sql = "INSERT INTO ROLES NOMBRE VALUES (? )"; }

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        if (id > 0) {    
        ps.setInt(1, id);
        ps.setString(2, nombre);
        } else { ps.setString(1, nombre);}
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Roles roles = new Roles();
            roles.setId(rs.getInt("id"));
            roles.setNombre(rs.getString("nombre"));
            
            lista.add(roles); // Agregamos el objeto Roles directamente a la lista
        }
        
    } catch (SQLException e) {
        System.err.println("Error en listaRoles: " + e.getMessage());
    }
    
    return lista; // ✅ Retorna la lista de mapas limpia (vacía o con datos)
}
// eliminar registro
public String BorrarPorId(int id) {
    List<Roles> lista = new ArrayList<>();
    String sql = "DELETE FROM roles WHERE id = ?"; // Ajusta tus columnas si es necesario

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
    } catch (SQLException e) {
        System.err.println("Error en listaRoles: " + e.getMessage());
    }
    
    return "BORRADO"; // ✅ Retorna la lista de mapas limpia (vacía o con datos)
}
}

