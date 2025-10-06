package com.example.demo.repository;

import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByCorreo(String correo);

    Optional<Cliente> findByCookieId(String cookieId);

    boolean existsByCorreo(String correo);

    Optional<Cliente> findByNombreContainingIgnoreCase(String nombre);

    // 🆕 Nuevo método para buscar por token
    Optional<Cliente> findByTokenRecuperacion(String tokenRecuperacion);
}
