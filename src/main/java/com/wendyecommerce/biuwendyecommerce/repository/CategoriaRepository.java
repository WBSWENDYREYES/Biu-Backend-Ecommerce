package com.wendyecommerce.biuwendyecommerce.repository;

import com.wendyecommerce.biuwendyecommerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}