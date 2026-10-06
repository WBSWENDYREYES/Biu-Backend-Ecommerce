package com.wendyecommerce.biuwendyecommerce.controller;

import com.wendyecommerce.biuwendyecommerce.model.Orden;
import com.wendyecommerce.biuwendyecommerce.service.OrdenServicio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "*")
public class OrdenController {

    private final OrdenServicio ordenService;

    public OrdenController(OrdenServicio ordenService) {
        this.ordenService = ordenService;
    }

    @PostMapping
    public ResponseEntity<?> crearOrden(@RequestBody Orden orden) {

        try {

            int idOrden = ordenService.crearOrden(
                    orden.getIdUsuario(),
                    orden.getTotal(),
                    orden.getPago().getNumeroTarjeta(),
                    orden.getPago().getTitular(),
                    orden.getPago().getFechaExpiracion(),
                    orden.getPago().getCvv(),
                    orden.getDetalles()
            );

            Map<String, Object> respuesta = new HashMap<>();

            respuesta.put("ok", true);
            respuesta.put("mensaje", "Orden creada correctamente");
            respuesta.put("idOrden", idOrden);
            respuesta.put("total", orden.getTotal());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(respuesta);

        } catch (Exception e) {

            Map<String, Object> respuesta = new HashMap<>();

            respuesta.put("ok", false);
            respuesta.put("mensaje", "No se pudo crear la orden");
            respuesta.put("error", e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(respuesta);
        }
    }
}