package com.example.demo.service.impl;

import com.example.demo.model.Administrador;
import com.example.demo.repository.AdministradorRepository;
import com.example.demo.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServiceImpl implements AdministradorService {
    
    @Autowired
    private AdministradorRepository administradorRepository;
    
    @Override
    public Administrador guardarAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }
    
    @Override
    public List<Administrador> obtenerTodosLosAdministradores() {
        return administradorRepository.findAll();
    }
    
    @Override
    public Optional<Administrador> obtenerAdministradorPorId(Integer id) {
        return administradorRepository.findById(id);
    }
    
    @Override
    public Optional<Administrador> obtenerAdministradorPorUsuario(String usuario) {
        return administradorRepository.findByUsuario(usuario);
    }
    
    @Override
    public Optional<Administrador> login(String usuario, String contrasena) {
        return administradorRepository.findByUsuarioAndContrasena(usuario, contrasena);
    }
    
    @Override
    public boolean existeAdministradorPorUsuario(String usuario) {
        return administradorRepository.existsByUsuario(usuario);
    }
    
    @Override
    public List<Administrador> obtenerAdministradoresPorRol(String rol) {
        return administradorRepository.findByRol(rol);
    }
    
    @Override
    public void eliminarAdministrador(Integer id) {
        administradorRepository.deleteById(id);
    }
    
    @Override
    public Administrador actualizarAdministrador(Integer id, Administrador administrador) {
        Optional<Administrador> adminExistente = administradorRepository.findById(id);
        if (adminExistente.isPresent()) {
            Administrador adminActualizado = adminExistente.get();
            adminActualizado.setUsuario(administrador.getUsuario());
            adminActualizado.setContrasena(administrador.getContrasena());
            adminActualizado.setRol(administrador.getRol());
            return administradorRepository.save(adminActualizado);
        }
        return null;
    }
    
    @Override
    public Administrador cambiarContrasena(Integer id, String nuevaContrasena) {
        Optional<Administrador> adminExistente = administradorRepository.findById(id);
        if (adminExistente.isPresent()) {
            Administrador admin = adminExistente.get();
            admin.setContrasena(nuevaContrasena);
            return administradorRepository.save(admin);
        }
        return null;
    }
}