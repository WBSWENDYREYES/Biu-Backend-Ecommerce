package com.wendyecommerce.biuwendyecommerce.controller;

import com.wendyecommerce.biuwendyecommerce.model.Producto;
import com.wendyecommerce.biuwendyecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.wendyecommerce.biuwendyecommerce.model.UsuarioCliente;
import com.wendyecommerce.biuwendyecommerce.model.Roles;
import com.wendyecommerce.biuwendyecommerce.model.Usuario;
import com.wendyecommerce.biuwendyecommerce.model.Producto;
import com.wendyecommerce.biuwendyecommerce.repository.ProductoRepository;
import com.wendyecommerce.biuwendyecommerce.repository.RolesRepository;
import com.wendyecommerce.biuwendyecommerce.service.ProductoService;
import com.wendyecommerce.biuwendyecommerce.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;
  
    @Autowired
    private UsuarioService usuarioService;


    // 1. LISTAR TODOS: Acceso público (Clientes y Administradores)
    @GetMapping
    public List<Producto> listar() {
        return productoService.listarTodos();
    }

    // 2. BUSCAR POR ID: Acceso público
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable int id) {
        Optional<Producto> productoOpt = productoService.buscarPorId(id);
        return productoOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
  
 // 3. CREAR: Solo Administrador
    @PostMapping
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardarProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }



      // 4. ACTUALIZAR: Solo Administrador
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<Producto> actualizar(@PathVariable int id, @RequestBody Producto producto) {
        Optional<Producto> productoExistente = productoService.buscarPorId(id);
        
        if (productoExistente.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 si el producto no existe
        }

        // Asignamos el ID de la URL al objeto para asegurar la actualización del registro correcto
        producto.setidproducto(id); 
        Producto productoActualizado = productoService.guardarProducto(producto);
        
        return ResponseEntity.ok(productoActualizado);
    } 

     // 5. ELIMINAR: Solo Administrador
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        Optional<Producto> productoExistente = productoService.buscarPorId(id);
        
        if (productoExistente.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no hay nada que eliminar
        }

        productoService.eliminarProducto(id); // Llama al método de eliminación en el servicio
        return ResponseEntity.noContent().build(); // Retorna 24 No Content (eliminación exitosa)
    }
    
}
