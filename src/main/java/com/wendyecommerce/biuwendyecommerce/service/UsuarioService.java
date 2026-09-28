// esta es la capa de negocio donde se definen los servicios a solicitar a la db con relacion 
// a los usuarios, como iniciar sesion, listar todos los usuarios, guardar un usuario y buscar por id

package com.wendyecommerce.biuwendyecommerce.service;

import com.wendyecommerce.biuwendyecommerce.model.Usuario;
import com.wendyecommerce.biuwendyecommerce.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
@Autowired
private UsuarioRepository usuarioRepository;

    // 1. Validar Login
    public Optional<Usuario> iniciarSesion(String email, String password) {
        return usuarioRepository.findByEmailAndPasswordConSqlReal(email, password);
    }

    // 2. Listar Usuarios
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // 3. Crear o Guardar Usuario
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // 4. Buscar por ID (Auxiliar para actualizar)
    public Optional<Usuario> buscarPorId(int id) {
        return usuarioRepository.findById(id);
    }

    // 4. Eliminar un producto por su ID
public void eliminarProducto(int id) {
    usuarioRepository.deleteById(id);
}
}