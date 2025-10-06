package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        return new ResponseEntity<>(clienteService.obtenerTodosLosClientes(), HttpStatus.OK);
    }

    // Crear nuevo cliente
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        if (clienteService.existeClientePorCorreo(cliente.getCorreo())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    // 🔐 Login de cliente
    @PostMapping("/login")
    public ResponseEntity<Cliente> loginCliente(@RequestBody Map<String, String> credenciales) {
        String correo = credenciales.get("correo");
        String contrasena = credenciales.get("contrasena");

        Optional<Cliente> cliente = clienteService.obtenerClientePorCorreo(correo);

        if (cliente.isPresent() && cliente.get().getContrasena().equals(contrasena)) {
            return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    // 🧠 Paso 1: Solicitar token (correo + dirección)
    @PostMapping("/solicitar-recuperacion")
    public ResponseEntity<?> solicitarRecuperacion(@RequestBody Map<String, String> datos) {
        String correo = datos.get("correo");
        String direccion = datos.get("direccion");

        Optional<Cliente> clienteOpt = clienteService.obtenerClientePorCorreo(correo);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();

            if (cliente.getDireccion() != null && cliente.getDireccion().equalsIgnoreCase(direccion)) {
                String token = UUID.randomUUID().toString();
                cliente.setTokenRecuperacion(token);
                cliente.setTokenExpira(Timestamp.valueOf(LocalDateTime.now().plusMinutes(10)));
                clienteService.guardarCliente(cliente);

                return ResponseEntity.ok(Map.of(
                        "mensaje", "✅ Token generado correctamente. Guárdalo para restablecer tu contraseña.",
                        "token", token
                ));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("❌ Correo o dirección incorrectos.");
    }

    // 🧠 Paso 2: Restablecer contraseña con token
    @PostMapping("/restablecer")
    public ResponseEntity<String> restablecerContrasena(@RequestBody Map<String, String> datos) {
        String token = datos.get("token");
        String nuevaContrasena = datos.get("nuevaContrasena");

        Optional<Cliente> clienteOpt = clienteService.obtenerPorToken(token);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();

            if (cliente.getTokenExpira().after(Timestamp.valueOf(LocalDateTime.now()))) {
                cliente.setContrasena(nuevaContrasena);
                cliente.setTokenRecuperacion(null);
                cliente.setTokenExpira(null);
                clienteService.guardarCliente(cliente);
                return ResponseEntity.ok("✅ Contraseña restablecida correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("⚠️ Token expirado. Solicita uno nuevo.");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("⚠️ Token inválido.");
    }
}
