package com.wendyecommerce.biuwendyecommerce.controller;

import com.wendyecommerce.biuwendyecommerce.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.wendyecommerce.biuwendyecommerce.model.Producto;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

 private   ProductoService productoService;
   public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

//consultar todos los Categorias
@PostMapping("/lista")
public ResponseEntity<?> lista(@RequestBody Map<String, Object> request) {
    
  System.err.println("ENTRO A LA LISTA ");
    
     try {
        
        return ResponseEntity.ok(productoService.listarTodos()); 
    } catch (Exception e) {

        System.err.println("ERROR EN LOGIN: " + e.getMessage());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al consultar la base de datos");
    }
}

@PostMapping("/salvarProducto")
public ResponseEntity<?> SalvarCategoria(@RequestBody Map<String, Object> request) {
    
  System.err.println("Entro a actalizar Producto ");
    
   
  try {
  String nombre = ((String) request.get("nombre")).toString();
  String referencia = ((String) request.get("referencia")).toString();
  String descripcion = ((String) request.get("descripcion")).toString();
  String imagen = ((String) request.get("imagen")).toString();
  
     int id = 0;
     int Existencia = 0;
     int IdCategoria = 0;
     int IdMarca = 0;
     Double Precio = 0.0;
//Object idValue = request.get("id");

//System.err.println("ID RECIBIDO = [" + idValue + "]");
//System.err.println("TIPO = " + (idValue != null ? idValue.getClass().getName() : "NULL"));

if (request.get("id") != null && !request.get("id").toString().trim().isEmpty()) {
    id = Integer.parseInt(request.get("id").toString().trim());
}
if (request.get("idcategoria") != null && !request.get("idcategoria").toString().trim().isEmpty()) {
    IdCategoria = Integer.parseInt(request.get("idcategoria").toString().trim());
}
if (request.get("idmarca") != null && !request.get("idmarca").toString().trim().isEmpty()) {
    IdMarca = Integer.parseInt(request.get("idmarca").toString().trim());
}
if (request.get("precio") != null && !request.get("precio").toString().trim().isEmpty()) {
    Precio = Double.parseDouble(request.get("precio").toString().trim());
}
  
System.err.println("get precio ");
  Producto producto = new Producto();
                 producto.setid(id);
                 producto.setNombre(nombre.trim());
             producto.setReferencia(referencia.trim());
            producto.setDescripcion(descripcion.trim());  
            producto.setExistencia(Existencia);  
            producto.setPrecio(Precio);  
            producto.setidCategoria(IdCategoria);  
            producto.setidMarca(IdMarca);  
            producto.setImagen(imagen.trim());

System.out.println(producto);
     
        return ResponseEntity.ok(productoService.guardarProducto(producto)); 
    } catch (Exception e) {

        System.err.println("ERROR EN LOGIN: " + e.getMessage());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al consultar la base de datos");
    }
}

}