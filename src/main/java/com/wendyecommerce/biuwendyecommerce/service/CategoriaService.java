// esta es la capa de negocio donde se definen los servicios a solicitar a la db con relacion 
// a los usuarios, como iniciar sesion, listar todos los usuarios, guardar un usuario y buscar por id

package com.wendyecommerce.biuwendyecommerce.service;

import com.wendyecommerce.biuwendyecommerce.model.Categoria;
import com.wendyecommerce.biuwendyecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // 2. Listar Usuarios
    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    // 3. Crear o Guardar Usuario
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // 4. Buscar por ID (Auxiliar para actualizar)
    public Optional<Categoria> buscarPorId(int id) {
        return categoriaRepository.findById(id);
    }

    // 4. Eliminar un producto por su ID
public void eliminarProducto(int id) {
    categoriaRepository.deleteById(id);
}
}
