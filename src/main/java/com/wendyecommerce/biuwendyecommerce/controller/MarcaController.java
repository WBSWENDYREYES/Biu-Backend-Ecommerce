package com.wendyecommerce.biuwendyecommerce.controller;

import com.wendyecommerce.biuwendyecommerce.service.MarcaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.wendyecommerce.biuwendyecommerce.model.Marca;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/marcas")
@CrossOrigin(origins = "*")
public class MarcaController {

 private   MarcaService marcaService;
   public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

//consultar todos los marca
@PostMapping("/lista")
public ResponseEntity<?> lista(@RequestBody Map<String, Object> request) {
    
  System.err.println("ENTRO A LA LISTA ");
    
     try {
        
        return ResponseEntity.ok(marcaService.listarTodos()); 
    } catch (Exception e) {

        System.err.println("ERROR EN LOGIN: " + e.getMessage());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al consultar la base de datos");
    }
}

@PostMapping("/salvarMarca")
public ResponseEntity<?> SalvarCategoria(@RequestBody Map<String, Object> request) {
    
  System.err.println("Entro a actalizar marca ");
    
   
  try {
     String nombre = ((String) request.get("nombre")).toString();

     int id = 0;

if (request.get("id") != null) {
    id = Integer.parseInt(request.get("id").toString());
}
         Marca marca = new Marca();
                 marca.setId(id);
                 marca.setNombre(nombre.trim());
     
        return ResponseEntity.ok(marcaService.guardarMarca(marca)); 
    } catch (Exception e) {

        System.err.println("ERROR EN LOGIN: " + e.getMessage());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al consultar la base de datos");
    }
}

}