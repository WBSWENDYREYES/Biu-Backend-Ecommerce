package com.wendyecommerce.biuwendyecommerce.controller;

import com.wendyecommerce.biuwendyecommerce.model.UsuarioCliente;
import com.wendyecommerce.biuwendyecommerce.model.Usuario;
import com.wendyecommerce.biuwendyecommerce.model.Roles;
import com.wendyecommerce.biuwendyecommerce.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

 private   UsuarioService usuarioService;
   public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, Object> request) {

    System.out.println("ENTRO EN LOGIN");

    String email = request.get("email").toString();
    String password = request.get("password").toString();

    System.out.println("EMAIL RECIBIDO: " + email);
    System.out.println("PASSWORD RECIBIDO: " + password);

     try {

        Optional<Usuario> usuario = usuarioService.iniciarSesion(email, password);
    System.out.println("REGRESO DEL REPOSITORY");
    System.out.println("RESULTADO PRESENTE: " + usuario.isPresent());
        if (usuario.isPresent()) {

            System.out.println("USUARIO ENCONTRADO: " + usuario.get().getNombre());

            return ResponseEntity.ok(usuario.get());

        } else {

            System.out.println("USUARIO NO ENCONTRADO");

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        }

    } catch (Exception e) {

        System.err.println("ERROR EN LOGIN: " + e.getMessage());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al consultar la base de datos");
    }
}
//consultar todos los usuarios
@PostMapping("/lista")
public ResponseEntity<?> lista(@RequestBody Map<String, Object> request) {
    
  System.err.println("ENTRO A LA LISTA ");
    
    int rol = ((Number) request.get("Rol")).intValue();
     try {
         if (rol != 1)
            {
               return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Esta opcion es para usuarios administradores ");
            }    
        return ResponseEntity.ok(usuarioService.listarTodos()); 
    } catch (Exception e) {

        System.err.println("ERROR EN LOGIN: " + e.getMessage());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al consultar la base de datos");
    }
}

@PostMapping("/SalvarUsuario")
public ResponseEntity<?> SalvarUsuario(@RequestBody Map<String, Object> request) {
    
  System.err.println("Entro a actalizar usuario ");
    
    int rol = ((Number) request.get("Rol")).intValue();
    String nombre = ((String) request.get("nombre")).toString();
    String email = ((String) request.get("email")).toString();
    String telefonos = ((String) request.get("telefonos")).toString();
    String password = ((String) request.get("password")).toString();
    int id = request.get("id") == null
        ? 0
        : ((Number) request.get("id")).intValue();
  try {
         if (rol != 1) { rol = 2;}    
         Usuario usuario = new Usuario();
                 usuario.setId(id);
                 usuario.setNombre(nombre.trim());
                 usuario.setEmail(email.trim());
                 usuario.setIdroles(rol);
                 usuario.setTelefonos(telefonos.trim());
                 usuario.setPassword(password.trim());
                
     
        return ResponseEntity.ok(usuarioService.guardarUsuario(usuario)); 
    } catch (Exception e) {

        System.err.println("ERROR EN LOGIN: " + e.getMessage());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al consultar la base de datos");
    }
}

}