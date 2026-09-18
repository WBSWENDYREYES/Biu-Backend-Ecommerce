//Crea el archivo UsuarioController.java  
// Este controlador responderá como una API REST enviando datos JSON, 
// ideal para probar flujos de backend en herramientas de desarrollo o Postman 
// de manera rápida.
package com.wendyecommerce.biuwendyecommerce.controller;

import com.wendyecommerce.biuwendyecommerce.model.UsuarioCliente;
import com.wendyecommerce.biuwendyecommerce.model.Roles;
import com.wendyecommerce.biuwendyecommerce.model.Usuario;
import com.wendyecommerce.biuwendyecommerce.repository.RolesRepository;
import com.wendyecommerce.biuwendyecommerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolesRepository rolRepository;

    // 🔑 A. LOGIN DE USUARIOS (POST)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        
             try {
            // 1. Intentamos realizar la consulta en SQL Server
            Optional<Usuario> usuario = usuarioService.iniciarSesion(email, password);
            
            if (usuario.isPresent()) {
                return ResponseEntity.ok(usuario.get()); // 200 OK - Retorna el usuario en JSON
            }
            
            // 401 Unauthorized - Credenciales incorrectas pero el servidor funciona bien
            return ResponseEntity.status(401).body("Credenciales inválidas, intente de nuevo.");
            
        } catch (Exception e) {
            // 2. Si ocurre un fallo técnico (Base de datos caída, error de tabla, etc.), lo capturamos
            System.err.println("ERROR CRÍTICO EN LOGIN: " + e.getMessage());
            
            // Devuelve un código 500 pero con un mensaje personalizado y controlado para el frontend
            return ResponseEntity.status(500).body("Error de conexión con la base de datos. Intente más tarde."+ e.getMessage());
        }   
      

        }

    // 📋 B. LISTAR USUARIOS (GET)
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    // ➕ C. CREAR USUARIO - Especializado en Cliente (POST)
    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioCliente nuevoCliente, @RequestParam int idroles) {
        Optional<Roles> rolOpt = rolRepository.findById(idroles);
        if (!rolOpt.isPresent()) {
            return ResponseEntity.badRequest().body("El ID del rol especificado no existe.");
        }
        
        // 🔄 CORREGIDO: Se usa .setRol() que es el método real de tu clase Usuario
        nuevoCliente.setRol(rolOpt.get());
        
        Usuario creado = usuarioService.guardarUsuario(nuevoCliente);
        return ResponseEntity.ok(creado);
    }

    // 🔄 D. ACTUALIZAR USUARIO (PUT)
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable int id, @RequestBody UsuarioCliente datosActualizados) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(id);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuarioExistente = usuarioOpt.get();
            
            // Actualizamos los campos tradicionales individuales
            usuarioExistente.setNombre(datosActualizados.getNombre());
            usuarioExistente.setTelefonos(datosActualizados.getTelefonos());
            usuarioExistente.setEmail(datosActualizados.getEmail());
            usuarioExistente.setPassword(datosActualizados.getPassword());
            usuarioExistente.setIddireccion(datosActualizados.getIddireccion());
            
            // 🔄 CORREGIDO: Evaluamos con 'UsuarioCliente' (tu clase real) en vez de 'Cliente'
            if (usuarioExistente instanceof UsuarioCliente) {
                ((UsuarioCliente) usuarioExistente).setPreferencias(datosActualizados.getPreferencias());
            }

            Usuario actualizado = usuarioService.guardarUsuario(usuarioExistente);
            return ResponseEntity.ok(actualizado);
        }
        
        return ResponseEntity.notFound().build();
    }
}