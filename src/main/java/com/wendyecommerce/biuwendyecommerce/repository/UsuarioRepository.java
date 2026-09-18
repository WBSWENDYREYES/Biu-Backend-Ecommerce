package com.wendyecommerce.biuwendyecommerce.repository;

import com.wendyecommerce.biuwendyecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
@Query(value = "SELECT * FROM Usuario WHERE RTRIM(LTRIM(email)) = RTRIM(LTRIM(:email)) AND RTRIM(LTRIM(password)) = RTRIM(LTRIM(:password))", nativeQuery = true)
    Optional<Usuario> findByEmailAndPasswordConSqlReal(@Param("email") String email, @Param("password") String password);
}
