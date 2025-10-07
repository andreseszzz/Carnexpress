package com.example.demo.service;

import com.example.demo.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    
    // Crear o actualizar cliente
    Cliente guardarCliente(Cliente cliente);
    
    // Obtener todos los clientes
    List<Cliente> obtenerTodosLosClientes();
    
    // Obtener cliente por ID
    Optional<Cliente> obtenerClientePorId(Integer id);
    
    // Obtener cliente por correo
    Optional<Cliente> obtenerClientePorCorreo(String correo);
    
    // Obtener cliente por cookie ID
    Optional<Cliente> obtenerClientePorCookieId(String cookieId);
    
    // Verificar si existe cliente por correo
    boolean existeClientePorCorreo(String correo);
    
    // Buscar cliente por nombre
    Optional<Cliente> buscarClientePorNombre(String nombre);
    
    // Eliminar cliente por ID
    void eliminarCliente(Integer id);
    
    // Actualizar cliente
    Cliente actualizarCliente(Integer id, Cliente cliente);
}