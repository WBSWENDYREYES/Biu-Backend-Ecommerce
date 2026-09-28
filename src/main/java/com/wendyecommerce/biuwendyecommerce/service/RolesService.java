// esta es la capa de negocio donde se definen los servicios a solicitar a la db con relacion 
// a los usuarios, como iniciar sesion, listar todos los usuarios, guardar un usuario y buscar por id

package com.wendyecommerce.biuwendyecommerce.service;

import com.wendyecommerce.biuwendyecommerce.model.Roles;
import com.wendyecommerce.biuwendyecommerce.repository.RolesRepository;
import java.util.List;
import java.util.Optional;

public class RolesService {

    private RolesRepository rolesRepository;

    // 1. Validar Login
   
    // 2. Listar Usuarios
    public List<Roles> listarTodos() {
        return rolesRepository.listaRoles();
    }

    // 3. Crear o Guardar Roles
    public Optional<Roles> guardarRoles(int id,String nombre ) {
        return rolesRepository.SalvarRoles(id,nombre).stream().findFirst();
    }

    // 4. Buscar por ID (Auxiliar para actualizar)
    public Optional<Roles> buscarPorId(int id) {
        return rolesRepository.buscarPorId(id).stream().findFirst();
    }

    // 4. Eliminar un producto por su ID
public void eliminarProducto(int id) {
     rolesRepository.BorrarPorId(id);
}
}