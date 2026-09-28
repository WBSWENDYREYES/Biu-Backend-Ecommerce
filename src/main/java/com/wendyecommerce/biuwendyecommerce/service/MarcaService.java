// esta es la capa de negocio donde se definen los servicios a solicitar a la db con relacion 
// a los usuarios, como iniciar sesion, listar todos los usuarios, guardar un usuario y buscar por id

package com.wendyecommerce.biuwendyecommerce.service;

import com.wendyecommerce.biuwendyecommerce.model.Marca;
import com.wendyecommerce.biuwendyecommerce.repository.MarcaRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaService {
    
@Autowired
private MarcaRepository marcaRepository;

   
    // 2. Listar Usuarios
    public List<Marca> listarTodos() {
        return marcaRepository.listaMarca();
    }

    // 3. Crear o Guardar Usuario
    public Marca guardarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    // 4. Buscar por ID (Auxiliar para actualizar)
    public Optional<Marca> buscarPorId(int id) {
        return marcaRepository.buscarPorId(id);
    }

    // 4. Eliminar un producto por su ID
public void eliminarMarca(int id) {
    marcaRepository.BorrarPorId(id);
}
}