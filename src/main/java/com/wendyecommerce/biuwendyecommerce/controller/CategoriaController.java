package com.wendyecommerce.biuwendyecommerce.controller;

import com.wendyecommerce.biuwendyecommerce.model.Categoria;
import com.wendyecommerce.biuwendyecommerce.model.Usuario;
import com.wendyecommerce.biuwendyecommerce.service.CategoriaService;
import com.wendyecommerce.biuwendyecommerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/categorias")
@CrossOrigin(origins = "*") // Permite la comunicación limpia con tu Frontend
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
  
    @Autowired
    private UsuarioService usuarioService;

    // 1. LISTAR TODOS: Acceso 100% público
    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        System.out.println("--> ¡LLEGÓ UNA PETICIÓN GET A API/CATEGORIAS! <--");
        List<Categoria> categorias = categoriaService.listarTodos();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    // 2. CREAR CATEGORÍA: Validación manual por ID de usuario
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> request) {
        System.out.println("--> ¡LLEGÓ PETICIÓN POST PARA CREAR CATEGORÍA! <--");

        if (!request.containsKey("usuarioId") || request.get("usuarioId") == null) {
            return new ResponseEntity<>("Falta el ID de usuario", HttpStatus.BAD_REQUEST);
        }

        // Convertimos el ID de forma segura sin importar si viene como String o Integer en el JSON
        int usuarioId = Integer.parseInt(request.get("usuarioId").toString());

        // Buscamos el usuario en tu base de datos
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(usuarioId); 

        if (usuarioOpt.isEmpty()) {
            return new ResponseEntity<>("Usuario no encontrado en el sistema", HttpStatus.UNAUTHORIZED);
        }

        Usuario usuarioActual = usuarioOpt.get();

        // 🚨 VERIFICACIÓN CLAVE DE TAREA: Evaluamos si el rol es nulo o si no es Administrador (ID != 1)
        if (usuarioActual.getRol() == null || usuarioActual.getRol().getIdroles() != 1) {
            System.out.println("--> [DENEGADO]: El usuario con ID " + usuarioId + " NO es Administrador.");
            return new ResponseEntity<>("Acceso denegado: Requiere rol de Administrador", HttpStatus.FORBIDDEN);
        }

        // Si pasó la validación, procedemos a guardar
        System.out.println("--> [PERMITIDO]: El usuario es Administrador. Procediendo a guardar.");
        
        Categoria nuevaCategoria = new Categoria();
        // Asegúrate de que el método en tu modelo Categoria sea setnombre o setNombre
        nuevaCategoria.setnombre((String) request.get("nombre")); 
        
        // Asegúrate de que tu servicio use guardarCategoria o guardar
        Categoria guardada = categoriaService.guardarCategoria(nuevaCategoria);
        
        return new ResponseEntity<>(guardada, HttpStatus.CREATED);
    }
}