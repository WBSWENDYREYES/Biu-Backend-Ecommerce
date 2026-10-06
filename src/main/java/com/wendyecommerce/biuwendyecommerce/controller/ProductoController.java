package com.wendyecommerce.biuwendyecommerce.controller;

import com.wendyecommerce.biuwendyecommerce.BiuwendyecommerceApplication;
import com.wendyecommerce.biuwendyecommerce.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.wendyecommerce.biuwendyecommerce.model.Producto;
import com.wendyecommerce.biuwendyecommerce.model.ProductoDigital;
import com.wendyecommerce.biuwendyecommerce.model.ProductoFisico;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

 private final BiuwendyecommerceApplication biuwendyecommerceApplication;
 private   ProductoService productoService;
   public ProductoController(ProductoService productoService, BiuwendyecommerceApplication biuwendyecommerceApplication) {
        this.productoService = productoService;
        this.biuwendyecommerceApplication = biuwendyecommerceApplication;
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
public ResponseEntity<?> SalvarProducto(@RequestBody Map<String, Object> request) {
    
  System.err.println("Entro a actalizar Producto ");
    
   
  try {
  String nombre = ((String) request.get("nombre")).toString();
  String referencia = ((String) request.get("referencia")).toString();
  String descripcion = ((String) request.get("descripcion")).toString();
  String imagen = ((String) request.get("imagen")).toString();
  
     int id = 0;
     int tipoproducto = 0;
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
if (request.get("tipoproducto") != null && !request.get("tipoproducto").toString().trim().isEmpty()) {
    tipoproducto = Integer.parseInt(request.get("tipoproducto").toString().trim());
}
System.err.println("get precio ");
    //Producto Digital
 if (tipoproducto==1 ){

    String formatoarchivo = ((String) request.get("formatoarchivo")).toString();
    Double tamanoarchivo = 0.0;

    if (request.get("tamanoarchivo") != null && !request.get("tamanoarchivo").toString().trim().isEmpty()) {
    tamanoarchivo = Double.parseDouble(request.get("tamanoarchivo").toString().trim());}

     //  cabe destar que el java la clase que define los atributos es la primaria
     //  si colocamos Producto  producto = new ProductoDigital(); solo se admiten los atributos del padre
     // en este polimorfismo.biuwendyecommerceApplication

     ProductoDigital  producto = new ProductoDigital();

            producto.setid(id);
            producto.setNombre(nombre.trim());
            producto.setReferencia(referencia.trim());
            producto.setDescripcion(descripcion.trim());  
            producto.setExistencia(Existencia);  
            producto.setPrecio(Precio);  
            producto.setidCategoria(IdCategoria);  
            producto.setidMarca(IdMarca);  
            producto.setImagen(imagen.trim());
            producto.setformatoArchivo(formatoarchivo);
            producto.setTamanoArchivo(tamanoarchivo);
            System.out.println(producto);

   return ResponseEntity.ok(productoService.guardarProductoDigital(producto)); 
           
  } 
  // prpducto fisico
 if (tipoproducto==2 ){
     ProductoFisico producto = new ProductoFisico();
    
   Double peso = 0.0;
   Double alto = 0.0;
   Double ancho = 0.0;
   Double profundidad = 0.0;

    if (request.get("peso") != null && !request.get("peso").toString().trim().isEmpty()) {
    peso = Double.parseDouble(request.get("peso").toString().trim()); }
    if (request.get("alto") != null && !request.get("alto").toString().trim().isEmpty()) {
    alto = Double.parseDouble(request.get("alto").toString().trim()); }
 if (request.get("ancho") != null && !request.get("ancho").toString().trim().isEmpty()) {
    ancho = Double.parseDouble(request.get("ancho").toString().trim()); }
if (request.get("profundidad") != null && !request.get("profundidad").toString().trim().isEmpty()) {
    profundidad = Double.parseDouble(request.get("profundidad").toString().trim()); }
    producto.setid(id);
            producto.setNombre(nombre.trim());
            producto.setReferencia(referencia.trim());
            producto.setDescripcion(descripcion.trim());  
            producto.setExistencia(Existencia);  
            producto.setPrecio(Precio);  
            producto.setidCategoria(IdCategoria);  
            producto.setidMarca(IdMarca);  
            producto.setImagen(imagen.trim());
            producto.setPeso(peso);
            producto.setAlto(alto);
            producto.setAncho(ancho);
            producto.setProfundidad(profundidad);
            System.out.println(producto);

  return ResponseEntity.ok(productoService.guardarProductoFisico(producto)); 
                 
 }          
return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al consultar la base de datos");
    } catch (Exception e) {

        System.err.println("ERROR EN LOGIN: " + e.getMessage());
        e.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al consultar la base de datos");
    }
}

}