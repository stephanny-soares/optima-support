package com.optima.support.repository;

import com.optima.support.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // JpaRepository já traz os métodos findAll, findById, save, deleteById
    Optional<Usuario> findByEmail(String email);
}
