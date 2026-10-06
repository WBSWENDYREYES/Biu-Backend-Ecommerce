package com.wendyecommerce.biuwendyecommerce.repository;

import com.wendyecommerce.biuwendyecommerce.config.ConexionDB;
import com.wendyecommerce.biuwendyecommerce.model.Roles;
import com.wendyecommerce.biuwendyecommerce.model.Producto;
import com.wendyecommerce.biuwendyecommerce.model.ProductoDigital;
import com.wendyecommerce.biuwendyecommerce.model.ProductoFisico;
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
public class ProductoRepository {
    
public List<Producto> listaProducto() {
    List<Producto> lista = new ArrayList<>();
    String sql = "SELECT p.*, m.nombre as nombreMarca, c.nombre as nombreCategoria FROM Producto p "+
                 "INNER JOIN Categoria c ON c.id  = p.idCategoria "+
                 "INNER JOIN Marca m ON m.id  = p.IdMarca ";
    
    
    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            Producto producto = new Producto();
            producto.setid(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setReferencia(rs.getString("referencia"));
            producto.setDescripcion(rs.getString("Descripcion"));  
            producto.setExistencia(rs.getInt("Existencia"));  
            producto.setPrecio(rs.getFloat("Precio"));  
            producto.setidCategoria(rs.getInt("IdCategoria"));  
            producto.setidMarca(rs.getInt("IdMarca"));  
            producto.setImagen(rs.getString("Imagen"));  
             producto.setnombreCategoria(rs.getString("nombreCategoria"));  
             producto.setnombreMarca(rs.getString("nombreMarca"));  
           lista.add(producto); // Agregamos el objeto Roles directamente a la lista
        }
        
    } catch (SQLException e) {
        System.err.println("Error en lista producto: " + e.getMessage());
    }
    
    return lista; // ✅ Retorna la lista de mapas limpia (vacía o con datos)
}

 public Optional<Producto> buscarPorId(int id) {
    String sql = "SELECT * nombre FROM producto WHERE id = ?"; // Ajusta tus columnas si es necesario

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
             Producto producto = new Producto();
       while (rs.next()) {
            producto.setid(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setReferencia(rs.getString("referencia"));
            producto.setDescripcion(rs.getString("Descripcion"));  
            producto.setExistencia(rs.getInt("Existencia"));  
            producto.setPrecio(rs.getFloat("Precio"));  
            producto.setidCategoria(rs.getInt("IdCategoria"));  
            producto.setidMarca(rs.getInt("IdMarca"));  
            producto.setImagen(rs.getString("Imagen"));  
        }
        return Optional.of(producto);
    } catch (SQLException e) {
        System.err.println("Error en listaRoles: " + e.getMessage());
    }
    
    return Optional.empty(); // ✅ Retorna la lista de mapas limpia (vacía o con datos)
}

 public Producto save(Producto producto) {

        if (producto.getid() > 0) {
          System.err.println(producto);
    // Si el ID ya existe, ejecutamos un UPDATE
            String sql = "UPDATE producto SET nombre = ?, referencia = ?, Descripcion = ? , "+
            " Existencia = ?, Precio = ?, IdCategoria = ?, IdMarca = ? ,Imagen = ? WHERE id = ?";
            try (Connection conn = ConexionDB.obtenerConexion();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
               ps.setString(1, producto.getNombre());
               ps.setString(2, producto.getReferencia());
               ps.setString(3, producto.getDescripcion());
               ps.setInt(4, producto.getExistencia());
               ps.setDouble(5, producto.getPrecio());
               ps.setInt(6, producto.getidCategoria());
               ps.setInt(7, producto.getidMarca());
               ps.setString(8, producto.getImagen());
               ps.setInt(9, producto.getid());
               ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar categoria: " + e.getMessage());
            }
        } else {
            // Si el ID es 0 o nuevo, ejecutamos un INSERT
            System.err.println("insert into producto ");
            String sql = "INSERT INTO  producto (nombre,referencia,Descripcion,Existencia,Precio,IdCategoria,IdMarca,Imagen,TipoProducto ) values (?, ?, ? , "+
            " ?,  ?,  ?,  ? , ?, ? ) ";
       

            try (Connection conn = ConexionDB.obtenerConexion();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                 ps.setString(1, producto.getNombre());
                 ps.setString(2, producto.getReferencia());
                 ps.setString(3, producto.getDescripcion());
                 ps.setInt(4, producto.getExistencia());
                 ps.setDouble(5, producto.getPrecio());
                 ps.setInt(6, producto.getidCategoria());
                 ps.setInt(7, producto.getidMarca());
                 ps.setString(8, producto.getImagen());
                 ps.setInt(9, producto.getTipoproducto());
                ps.executeUpdate();
                
                // Obtener el ID auto-generado por SQL Server
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        producto.setid(generatedKeys.getInt(1));
                    }

                }
            } catch (SQLException e) {
                System.err.println("Error al insertar usuario: " + e.getMessage());
            }
        }
        return producto;
    }
// guardar producto Digital

public Producto saveProductoDigital(ProductoDigital producto) {

        if (producto.getid() > 0) {
          System.err.println(producto);
    // Si el ID ya existe, ejecutamos un UPDATE
            String sql = "UPDATE producto SET nombre = ?, referencia = ?, Descripcion = ? , "+
            " Existencia = ?, Precio = ?, IdCategoria = ?, IdMarca = ? ,Imagen = ? WHERE id = ?";
            try (Connection conn = ConexionDB.obtenerConexion();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
               ps.setString(1, producto.getNombre());
               ps.setString(2, producto.getReferencia());
               ps.setString(3, producto.getDescripcion());
               ps.setInt(4, producto.getExistencia());
               ps.setDouble(5, producto.getPrecio());
               ps.setInt(6, producto.getidCategoria());
               ps.setInt(7, producto.getidMarca());
               ps.setString(8, producto.getImagen());
               ps.setInt(9, producto.getid());
               ps.executeUpdate();
               
               sql = "SELECT COUNT(*) FROM productoDigital WHERE idProducto = ?";

               PreparedStatement bp = conn.prepareStatement(sql);
               bp.setInt(1, producto.getid());
               ResultSet resultado = bp.executeQuery();
               if (resultado.next()) {
    // Aquí lees los datos si existen
               sql = "UPDATE productoDigital SET formatoArchivo = ?, tamanoArchivo = ? WHERE idProducto = ?";
                 PreparedStatement ps1 = conn.prepareStatement(sql);
                   ps1.setString(1, producto.getformatoArchivo());
                   ps1.setDouble(2, producto.getTamanoArchivo());
                   ps1.setInt(3, producto.getid());
               ps1.executeUpdate();
               } else {

                 sql = "INSERT INTO productoDigital (idProducto, formatoArchivo,tamanoArchivo) values (?, ?, ?) ";
                 PreparedStatement ps1 = conn.prepareStatement(sql);
                   ps1.setInt(1, producto.getid());
                   ps1.setString(2, producto.getformatoArchivo());
                   ps1.setDouble(3, producto.getTamanoArchivo());
               ps1.executeUpdate();
               }
            } catch (SQLException e) {
                System.err.println("Error al actualizar Producto : " + e.getMessage());
            }
        } else {
            // Si el ID es 0 o nuevo, ejecutamos un INSERT
            System.err.println("insert into producto Digital ");
            String sql = "INSERT INTO  producto (nombre,referencia,Descripcion,Existencia,Precio,IdCategoria,IdMarca,Imagen,tipoproducto ) values (?, ?, ? , "+
            " ?,  ?,  ?,  ? , ?, ? ) ";
       

            try (Connection conn = ConexionDB.obtenerConexion();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
               ps.setString(1, producto.getNombre());
               ps.setString(2, producto.getReferencia());
               ps.setString(3, producto.getDescripcion());
               ps.setInt(4, producto.getExistencia());
               ps.setDouble(5, producto.getPrecio());
               ps.setInt(6, producto.getidCategoria());
               ps.setInt(7, producto.getidMarca());
               ps.setString(8, producto.getImagen());
               ps.setInt(9, producto.getTipoproducto());
               ps.executeUpdate();
                
            sql = "INSERT INTO productoDigital (idProducto, formatoArchivo,tamanoArchivo) values (?,?,?) ";
                 PreparedStatement ps1 = conn.prepareStatement(sql);
                   ps1.setInt(1, producto.getid());
                   ps1.setString(2, producto.getformatoArchivo());
                   ps1.setDouble(3, producto.getTamanoArchivo());
                   ps1.executeUpdate();
       
                // Obtener el ID auto-generado por SQL Server
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        producto.setid(generatedKeys.getInt(1));
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al insertar usuario: " + e.getMessage());
            }
        }
        return producto;
    }

public Producto saveProductoFisico(ProductoFisico producto) {
         
        if (producto.getid() > 0) {
          System.err.println(producto);
    // Si el ID ya existe, ejecutamos un UPDATE
            String sql = "UPDATE producto SET nombre = ?, referencia = ?, Descripcion = ? , "+
            " Existencia = ?, Precio = ?, IdCategoria = ?, IdMarca = ? ,Imagen = ? WHERE id = ?";
            try (Connection conn = ConexionDB.obtenerConexion();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
               ps.setString(1, producto.getNombre());
               ps.setString(2, producto.getReferencia());
               ps.setString(3, producto.getDescripcion());
               ps.setInt(4, producto.getExistencia());
               ps.setDouble(5, producto.getPrecio());
               ps.setInt(6, producto.getidCategoria());
               ps.setInt(7, producto.getidMarca());
               ps.setString(8, producto.getImagen());
               ps.setInt(9, producto.getid());
               ps.executeUpdate();
               
               sql = "SELECT COUNT(*) FROM productoDigital WHERE idProducto = ?";

               PreparedStatement bp = conn.prepareStatement(sql);
               bp.setInt(1, producto.getid());
               ResultSet resultado = bp.executeQuery();
               if (resultado.next()) {
    // Aquí lees los datos si existen
               sql = "UPDATE productoFisoco SET peso = ?, altura = ?, ancho = ?, profundidad = ? WHERE idProducto = ?";
                 PreparedStatement ps1 = conn.prepareStatement(sql);
                   ps1.setDouble(1, producto.getPeso());
                   ps1.setDouble(2, producto.getAlto());
                   ps1.setDouble(3, producto.getAncho());
                   ps1.setDouble(4, producto.getProfundidad());
                   ps1.setInt(5, producto.getid());
                   ps1.executeUpdate();
               } else {

                 sql = "INSERT INTO productoFisico (peso,alto, ancho,profundidad ) values (?,?,?,?)";
                 PreparedStatement ps1 = conn.prepareStatement(sql);
                   ps1.setDouble(1, producto.getPeso());
                   ps1.setDouble(2, producto.getAlto());
                   ps1.setDouble(3, producto.getAncho());
                   ps1.setDouble(4, producto.getProfundidad());
                   ps1.executeUpdate();
               }
            } catch (SQLException e) {
                System.err.println("Error al actualizar Producto : " + e.getMessage());
            }
        } else {
            // Si el ID es 0 o nuevo, ejecutamos un INSERT
            System.err.println("insert into producto Digital ");
            String sql = "INSERT INTO  producto (nombre,referencia,Descripcion,Existencia,Precio,IdCategoria,IdMarca,Imagen,tipoproducto ) values (?, ?, ? , "+
            " ?,  ?,  ?,  ? , ?, ? ) ";
       

            try (Connection conn = ConexionDB.obtenerConexion();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
               ps.setString(1, producto.getNombre());
               ps.setString(2, producto.getReferencia());
               ps.setString(3, producto.getDescripcion());
               ps.setInt(4, producto.getExistencia());
               ps.setDouble(5, producto.getPrecio());
               ps.setInt(6, producto.getidCategoria());
               ps.setInt(7, producto.getidMarca());
               ps.setString(8, producto.getImagen());
               ps.setInt(9, producto.getTipoproducto());
               ps.executeUpdate();
                
                 sql = "INSERT INTO productoFisico (peso,alto, ancho,profundidad ) values (?,?,?,?)";
                 PreparedStatement ps1 = conn.prepareStatement(sql);
                   ps1.setDouble(1, producto.getPeso());
                   ps1.setDouble(2, producto.getAlto());
                   ps1.setDouble(3, producto.getAncho());
                   ps1.setDouble(4, producto.getProfundidad());
                   ps1.executeUpdate();
       
                // Obtener el ID auto-generado por SQL Server
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        producto.setid(generatedKeys.getInt(1));
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al insertar usuario: " + e.getMessage());
            }
        }
        return producto;
    }

// eliminar registro
public String BorrarPorId(int id) {
    List<Producto> lista = new ArrayList<>();
    String sql = "DELETE FROM producto WHERE id = ?"; // Ajusta tus columnas si es necesario

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
    } catch (SQLException e) {
        System.err.println("Error en lista producto: " + e.getMessage());
    }
    
    return "BORRADO"; // ✅ Retorna la lista de mapas limpia (vacía o con datos)
}
}

