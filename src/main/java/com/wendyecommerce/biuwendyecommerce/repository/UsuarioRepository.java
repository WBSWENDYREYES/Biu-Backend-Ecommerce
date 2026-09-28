package com.wendyecommerce.biuwendyecommerce.repository;

import com.wendyecommerce.biuwendyecommerce.config.ConexionDB;
import com.wendyecommerce.biuwendyecommerce.model.Usuario;
import com.wendyecommerce.biuwendyecommerce.model.UsuarioCliente;
import com.wendyecommerce.biuwendyecommerce.model.Roles;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {

    

    // 1. Validar Login con consulta SQL real
    public Optional<Usuario> findByEmailAndPasswordConSqlReal(String email, String password) {
        String sql = "SELECT TOP 1 * FROM usuario where email = ? and password = ? ";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
           ps.setString(1, email);
           ps.setString(2, password);
           ResultSet meta = ps.executeQuery();
                if (meta.next()) {
                    return Optional.of(mapearUsuario(meta));
                }

        System.out.println("======================================");
        } catch (SQLException e) {
            System.err.println("Error en findByEmailAndPasswordConSqlReal: " + e.getMessage());
        }
        return Optional.empty();
    }

    // 2. Listar Todos los Usuarios
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT Id,Nombre,Telefonos,email,password,idroles FROM usuario  order by  Id ";
        
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en findAll: " + e.getMessage());
        }
        return usuarios;
    }

    // 3. Guardar o Actualizar Usuario
    public Usuario save(Usuario usuario) {
        if (usuario.getId() > 0) {
          System.err.println("update usuario ");
    // Si el ID ya existe, ejecutamos un UPDATE
            String sql = "UPDATE usuario SET nombre = ?, telefonos = ?, email = ?, password = ? WHERE id = ?";
            try (Connection conn = ConexionDB.obtenerConexion();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
                ps.setString(1, usuario.getNombre());
                ps.setString(2, usuario.getTelefonos());
                ps.setString(3, usuario.getEmail());
                ps.setString(4, usuario.getPassword());
                ps.setInt(5, usuario.getId());
                ps.executeUpdate();
                
            } catch (SQLException e) {
                System.err.println("Error al actualizar usuario: " + e.getMessage());
            }
        } else {
            // Si el ID es 0 o nuevo, ejecutamos un INSERT
            System.err.println("insert usuario ");
             String sql = "INSERT INTO usuario (nombre, telefonos, email, password, idroles, preferencias) VALUES (?, ?, ?, ?, ?, ?)";
            try (Connection conn = ConexionDB.obtenerConexion();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
                ps.setString(1, usuario.getNombre());
                ps.setString(2, usuario.getTelefonos());
                ps.setString(3, usuario.getEmail());
                ps.setString(4, usuario.getPassword());
                ps.setInt(5, (usuario.getIdroles() != 1) ? usuario.getIdroles() : 2); // 2 por defecto si es cliente
                
                if (usuario instanceof UsuarioCliente) {
                    ps.setString(6, ((UsuarioCliente) usuario).getPreferencias());
                } else {
                    ps.setNull(6, Types.VARCHAR);
                }
                
                ps.executeUpdate();
                
                // Obtener el ID auto-generado por SQL Server
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuario.setId(generatedKeys.getInt(1));
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al insertar usuario: " + e.getMessage());
            }
        }
        return usuario;
    }

    // 4. Buscar Usuario por ID
    public Optional<Usuario> findById(int id) {
        String sql = "SELECT u.*, r.id, r.nombre FROM usuario u " +
                     "LEFT JOIN roles r ON u.idroles = r.id WHERE u.id = ?";
        
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearUsuario(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en findById: " + e.getMessage());
        }
        return Optional.empty();
    }

    // 5. Eliminar un registro por ID
    public void deleteById(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Registro eliminado con éxito.");
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar por ID: " + e.getMessage());
        }
    }

    // Mapeador auxiliar: Transforma una fila de SQL Server a un Objeto Java
    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        // Determinamos si creamos un Usuario base o UsuarioCliente (POO Polimorfismo)
        UsuarioCliente u = new UsuarioCliente();
        u.setId(rs.getInt("Id"));
        u.setNombre(rs.getString("nombre"));
        u.setTelefonos(rs.getString("telefonos"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        u.setIdroles(rs.getInt("idroles"));
       
        // Verificar si la columna preferencias existe en tu tabla y mapearla
        try {
            u.setPreferencias(rs.getString("preferencias"));
        } catch (SQLException e) {
            // Ignorar si la columna no existe en la BD
        }

        // Mapear Objeto Rol asociado
       

        return u;
    }
}