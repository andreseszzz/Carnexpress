package com.example.demo.repository;

import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    // Método para buscar cliente por correo
    Optional<Cliente> findByCorreo(String correo);
    
    // Método para buscar cliente por cookie_id
    Optional<Cliente> findByCookieId(String cookieId);
    
    // Método para verificar si existe un cliente con ese correo
    boolean existsByCorreo(String correo);
    
    // Método para buscar clientes por nombre (búsqueda parcial)
    Optional<Cliente> findByNombreContainingIgnoreCase(String nombre);
}