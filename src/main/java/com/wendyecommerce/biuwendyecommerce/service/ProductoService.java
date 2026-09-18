// esta es la capa de negocio donde se definen los servicios a solicitar a la db con relacion 
// a los usuarios, como iniciar sesion, listar todos los usuarios, guardar un usuario y buscar por id

package com.wendyecommerce.biuwendyecommerce.service;

import com.wendyecommerce.biuwendyecommerce.model.Producto;
import com.wendyecommerce.biuwendyecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;


    // 2. Listar Usuarios
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    // 3. Crear o Guardar Usuario
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // 4. Buscar por ID (Auxiliar para actualizar)
    public Optional<Producto> buscarPorId(int id) {
        return productoRepository.findById(id);
    }

    // 4. Eliminar un producto por su ID
public void eliminarProducto(int id) {
    productoRepository.deleteById(id);
}
}
