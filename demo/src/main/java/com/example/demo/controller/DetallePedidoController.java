package com.example.demo.controller;

import com.example.demo.model.DetallePedido;
import com.example.demo.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalles-pedido")
@CrossOrigin(origins = "*")
public class DetallePedidoController {
    
    @Autowired
    private DetallePedidoService detallePedidoService;
    
    // Obtener todos los detalles de pedidos
    @GetMapping
    public ResponseEntity<List<DetallePedido>> obtenerTodosLosDetalles() {
        List<DetallePedido> detalles = detallePedidoService.obtenerTodosLosDetalles();
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }
    
    // Obtener detalle por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> obtenerDetallePorId(@PathVariable Integer id) {
        Optional<DetallePedido> detalle = detallePedidoService.obtenerDetallePorId(id);
        return detalle.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Obtener detalles por pedido ID
    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<List<DetallePedido>> obtenerDetallesPorPedidoId(@PathVariable Integer idPedido) {
        List<DetallePedido> detalles = detallePedidoService.obtenerDetallesPorPedidoId(idPedido);
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }
    
    // Obtener detalles por producto ID
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<DetallePedido>> obtenerDetallesPorProductoId(@PathVariable Integer idProducto) {
        List<DetallePedido> detalles = detallePedidoService.obtenerDetallesPorProductoId(idProducto);
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }
    
    // Obtener cantidad total vendida de un producto
    @GetMapping("/producto/{idProducto}/cantidad-vendida")
    public ResponseEntity<Integer> obtenerCantidadTotalVendidaPorProducto(@PathVariable Integer idProducto) {
        Integer cantidad = detallePedidoService.obtenerCantidadTotalVendidaPorProducto(idProducto);
        return new ResponseEntity<>(cantidad, HttpStatus.OK);
    }
    
    // Obtener subtotal de un pedido
    @GetMapping("/pedido/{idPedido}/subtotal")
    public ResponseEntity<Float> obtenerSubtotalPorPedido(@PathVariable Integer idPedido) {
        Float subtotal = detallePedidoService.obtenerSubtotalPorPedido(idPedido);
        return new ResponseEntity<>(subtotal, HttpStatus.OK);
    }
    
    // Crear nuevo detalle de pedido
    @PostMapping
    public ResponseEntity<DetallePedido> crearDetallePedido(@RequestBody DetallePedido detallePedido) {
        DetallePedido nuevoDetalle = detallePedidoService.guardarDetallePedido(detallePedido);
        return new ResponseEntity<>(nuevoDetalle, HttpStatus.CREATED);
    }
    
    // Actualizar detalle de pedido
    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> actualizarDetalle(@PathVariable Integer id, @RequestBody DetallePedido detallePedido) {
        DetallePedido detalleActualizado = detallePedidoService.actualizarDetalle(id, detallePedido);
        if (detalleActualizado != null) {
            return new ResponseEntity<>(detalleActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Eliminar detalle de pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Integer id) {
        Optional<DetallePedido> detalle = detallePedidoService.obtenerDetallePorId(id);
        if (detalle.isPresent()) {
            detallePedidoService.eliminarDetalle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}