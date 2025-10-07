package com.example.demo.controller;

import com.example.demo.model.Pedido;
import com.example.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
    
    // Obtener todos los pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodosLosPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
    
    // Obtener pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Integer id) {
        Optional<Pedido> pedido = pedidoService.obtenerPedidoPorId(id);
        return pedido.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Obtener pedidos por cliente ID
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Pedido>> obtenerPedidosPorClienteId(@PathVariable Integer idCliente) {
        List<Pedido> pedidos = pedidoService.obtenerPedidosPorClienteId(idCliente);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
    
    // Obtener pedidos por fecha
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Pedido>> obtenerPedidosPorFecha(@PathVariable String fecha) {
        LocalDate localDate = LocalDate.parse(fecha);
        List<Pedido> pedidos = pedidoService.obtenerPedidosPorFecha(localDate);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
    
    // Obtener pedidos entre fechas
    @GetMapping("/fecha/rango")
    public ResponseEntity<List<Pedido>> obtenerPedidosEntreFechas(
            @RequestParam String fechaInicio, 
            @RequestParam String fechaFin) {
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        List<Pedido> pedidos = pedidoService.obtenerPedidosEntreFechas(inicio, fin);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
    
    // Obtener pedidos por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pedido>> obtenerPedidosPorEstado(@PathVariable String estado) {
        List<Pedido> pedidos = pedidoService.obtenerPedidosPorEstado(estado);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
    
    // Obtener total de ventas por fecha
    @GetMapping("/ventas/total/{fecha}")
    public ResponseEntity<Float> obtenerTotalVentasPorFecha(@PathVariable String fecha) {
        LocalDate localDate = LocalDate.parse(fecha);
        Float total = pedidoService.obtenerTotalVentasPorFecha(localDate);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
    
    // Obtener últimos 10 pedidos
    @GetMapping("/ultimos")
    public ResponseEntity<List<Pedido>> obtenerUltimosPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerUltimosPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
    
    // Crear nuevo pedido
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.guardarPedido(pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }
    
    // Actualizar pedido
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        Pedido pedidoActualizado = pedidoService.actualizarPedido(id, pedido);
        if (pedidoActualizado != null) {
            return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Eliminar pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Integer id) {
        Optional<Pedido> pedido = pedidoService.obtenerPedidoPorId(id);
        if (pedido.isPresent()) {
            pedidoService.eliminarPedido(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}