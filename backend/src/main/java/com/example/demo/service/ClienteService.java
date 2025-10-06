package com.example.demo.service;

import com.example.demo.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente guardarCliente(Cliente cliente);

    List<Cliente> obtenerTodosLosClientes();

    Optional<Cliente> obtenerClientePorId(Integer id);

    Optional<Cliente> obtenerClientePorCorreo(String correo);

    Optional<Cliente> obtenerClientePorCookieId(String cookieId);

    boolean existeClientePorCorreo(String correo);

    Optional<Cliente> buscarClientePorNombre(String nombre);

    void eliminarCliente(Integer id);

    Cliente actualizarCliente(Integer id, Cliente cliente);

    // 🆕 Nuevo método
    Optional<Cliente> obtenerPorToken(String token);
}
