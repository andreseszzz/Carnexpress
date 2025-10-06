package com.example.demo.repository;

import com.example.demo.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    
    // Método para buscar administrador por usuario
    Optional<Administrador> findByUsuario(String usuario);
    
    // Método para buscar administrador por usuario y contraseña (para login)
    Optional<Administrador> findByUsuarioAndContrasena(String usuario, String contrasena);
    
    // Método para verificar si existe un administrador con ese usuario
    boolean existsByUsuario(String usuario);
    
    // Método para buscar administradores por rol
    List<Administrador> findByRol(String rol);
}