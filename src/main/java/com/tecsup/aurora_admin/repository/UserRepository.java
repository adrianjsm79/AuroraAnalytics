package com.tecsup.aurora_admin.repository;

import com.tecsup.aurora_admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Buscar usuario por email (útil para login o validaciones)
    Optional<User> findByEmail(String email);

    // Buscar si existe un número de teléfono
    boolean existsByNumero(String numero);

    // Filtrar usuarios activos/inactivos o staff (para el dashboard)
    List<User> findByIsActiveTrue();
    List<User> findByIsStaffTrue();
}