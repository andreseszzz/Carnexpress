package com.example.demo.controller;

import com.example.demo.model.Administrador;
import com.example.demo.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/administradores")
@CrossOrigin(origins = "*")
public class AdministradorController {
    
    @Autowired
    private AdministradorService administradorService;
    
    // Obtener todos los administradores
    @GetMapping
    public ResponseEntity<List<Administrador>> obtenerTodosLosAdministradores() {
        List<Administrador> administradores = administradorService.obtenerTodosLosAdministradores();
        return new ResponseEntity<>(administradores, HttpStatus.OK);
    }
    
    // Obtener administrador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> obtenerAdministradorPorId(@PathVariable Integer id) {
        Optional<Administrador> administrador = administradorService.obtenerAdministradorPorId(id);
        return administrador.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                           .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Obtener administrador por usuario
    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<Administrador> obtenerAdministradorPorUsuario(@PathVariable String usuario) {
        Optional<Administrador> administrador = administradorService.obtenerAdministradorPorUsuario(usuario);
        return administrador.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                           .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Obtener administradores por rol
    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<Administrador>> obtenerAdministradoresPorRol(@PathVariable String rol) {
        List<Administrador> administradores = administradorService.obtenerAdministradoresPorRol(rol);
        return new ResponseEntity<>(administradores, HttpStatus.OK);
    }
    
    // Login de administrador
    @PostMapping("/login")
    public ResponseEntity<Administrador> login(@RequestBody Map<String, String> credenciales) {
        String usuario = credenciales.get("usuario");
        String contrasena = credenciales.get("contrasena");
        Optional<Administrador> administrador = administradorService.login(usuario, contrasena);
        return administrador.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                           .orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }
    
    // Verificar si existe administrador por usuario
    @GetMapping("/existe/{usuario}")
    public ResponseEntity<Boolean> existeAdministradorPorUsuario(@PathVariable String usuario) {
        boolean existe = administradorService.existeAdministradorPorUsuario(usuario);
        return new ResponseEntity<>(existe, HttpStatus.OK);
    }
    
    // Crear nuevo administrador
    @PostMapping
    public ResponseEntity<Administrador> crearAdministrador(@RequestBody Administrador administrador) {
        if (administradorService.existeAdministradorPorUsuario(administrador.getUsuario())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Administrador nuevoAdministrador = administradorService.guardarAdministrador(administrador);
        return new ResponseEntity<>(nuevoAdministrador, HttpStatus.CREATED);
    }
    
    // Actualizar administrador
    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizarAdministrador(@PathVariable Integer id, @RequestBody Administrador administrador) {
        Administrador administradorActualizado = administradorService.actualizarAdministrador(id, administrador);
        if (administradorActualizado != null) {
            return new ResponseEntity<>(administradorActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Cambiar contraseña
    @PatchMapping("/{id}/contrasena")
    public ResponseEntity<Administrador> cambiarContrasena(@PathVariable Integer id, @RequestBody Map<String, String> datos) {
        String nuevaContrasena = datos.get("nuevaContrasena");
        Administrador administradorActualizado = administradorService.cambiarContrasena(id, nuevaContrasena);
        if (administradorActualizado != null) {
            return new ResponseEntity<>(administradorActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Eliminar administrador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdministrador(@PathVariable Integer id) {
        Optional<Administrador> administrador = administradorService.obtenerAdministradorPorId(id);
        if (administrador.isPresent()) {
            administradorService.eliminarAdministrador(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}