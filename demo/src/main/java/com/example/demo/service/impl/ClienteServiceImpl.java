package com.example.demo.service.impl;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }
    
    @Override
    public Optional<Cliente> obtenerClientePorId(Integer id) {
        return clienteRepository.findById(id);
    }
    
    @Override
    public Optional<Cliente> obtenerClientePorCorreo(String correo) {
        return clienteRepository.findByCorreo(correo);
    }
    
    @Override
    public Optional<Cliente> obtenerClientePorCookieId(String cookieId) {
        return clienteRepository.findByCookieId(cookieId);
    }
    
    @Override
    public boolean existeClientePorCorreo(String correo) {
        return clienteRepository.existsByCorreo(correo);
    }
    
    @Override
    public Optional<Cliente> buscarClientePorNombre(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    @Override
    public void eliminarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
    
    @Override
    public Cliente actualizarCliente(Integer id, Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente clienteActualizado = clienteExistente.get();
            clienteActualizado.setNombre(cliente.getNombre());
            clienteActualizado.setCorreo(cliente.getCorreo());
            clienteActualizado.setDireccion(cliente.getDireccion());
            clienteActualizado.setTelefono(cliente.getTelefono());
            clienteActualizado.setCookieId(cliente.getCookieId());
            return clienteRepository.save(clienteActualizado);
        }
        return null;
    }
}