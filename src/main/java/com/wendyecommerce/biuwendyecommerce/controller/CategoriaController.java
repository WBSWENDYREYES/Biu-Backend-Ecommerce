package com.wendyecommerce.biuwendyecommerce.controller;

import com.wendyecommerce.biuwendyecommerce.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.wendyecommerce.biuwendyecommerce.model.Categoria;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

 private   CategoriaService categoriaService;
   public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

//consultar todos los Categorias
@PostMapping("/lista")
public ResponseEntity<?> lista(@RequestBody Map<String, Object> request) {
    
  System.err.println("ENTRO A LA LISTA ");
    
     try {
        
        return ResponseEntity.ok(categoriaService.listarTodos()); 
    } catch (Exception e) {

        System.err.println("ERROR EN LOGIN: " + e.getMessage());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al consultar la base de datos");
    }
}

@PostMapping("/salvarCategoria")
public ResponseEntity<?> SalvarCategoria(@RequestBody Map<String, Object> request) {
    
  System.err.println("Entro a actalizar Categoria ");
    
   
  try {
     String nombre = ((String) request.get("nombre")).toString();

     int id = 0;
if (request.get("id") != null && !request.get("id").toString().trim().isEmpty()) {
    id = Integer.parseInt(request.get("id").toString().trim());
}

         Categoria categoria = new Categoria();
                 categoria.setId(id);
                 categoria.setNombre(nombre.trim());
     
        return ResponseEntity.ok(categoriaService.guardarCategoria(categoria)); 
    } catch (Exception e) {

        System.err.println("ERROR EN LOGIN: " + e.getMessage());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al consultar la base de datos");
    }
}
// eliminar por Id

@PostMapping("/eliminarCategoria")
public ResponseEntity<?> EliminarCategoria(@RequestBody Map<String, Object> request) {
    
  System.err.println("Entro a Eliminar Categoria ");
    
   
  try {
  //   String nombre = ((String) request.get("nombre")).toString();

     int id = 0;
if (request.get("id") != null && !request.get("id").toString().trim().isEmpty()) {
    id = Integer.parseInt(request.get("id").toString().trim());
}
     categoriaService.eliminarCategoria(id);
      return ResponseEntity
                .status(HttpStatus.OK)
                .body("CATEGORIA ELIMINADA . . .");
    } catch (Exception e) {

        System.err.println("ERROR AL ELIMINAR CATEGORIA: " + e.getMessage());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al consultar la base de datos");
    }
}

}