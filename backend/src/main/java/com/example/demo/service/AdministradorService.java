package com.example.demo.service;

import com.example.demo.model.Administrador;
import java.util.List;
import java.util.Optional;

public interface AdministradorService {
    
    // Crear o guardar administrador
    Administrador guardarAdministrador(Administrador administrador);
    
    // Obtener todos los administradores
    List<Administrador> obtenerTodosLosAdministradores();
    
    // Obtener administrador por ID
    Optional<Administrador> obtenerAdministradorPorId(Integer id);
    
    // Obtener administrador por usuario
    Optional<Administrador> obtenerAdministradorPorUsuario(String usuario);
    
    // Login de administrador
    Optional<Administrador> login(String usuario, String contrasena);
    
    // Verificar si existe administrador por usuario
    boolean existeAdministradorPorUsuario(String usuario);
    
    // Obtener administradores por rol
    List<Administrador> obtenerAdministradoresPorRol(String rol);
    
    // Eliminar administrador
    void eliminarAdministrador(Integer id);
    
    // Actualizar administrador
    Administrador actualizarAdministrador(Integer id, Administrador administrador);
    
    // Cambiar contraseña
    Administrador cambiarContrasena(Integer id, String nuevaContrasena);
}