package com.example.demo.service;

import com.example.demo.model.DetallePedido;
import java.util.List;
import java.util.Optional;

public interface DetallePedidoService {
    
    // Crear o guardar detalle de pedido
    DetallePedido guardarDetallePedido(DetallePedido detallePedido);
    
    // Obtener todos los detalles de pedidos
    List<DetallePedido> obtenerTodosLosDetalles();
    
    // Obtener detalle por ID
    Optional<DetallePedido> obtenerDetallePorId(Integer id);
    
    // Obtener detalles por pedido ID
    List<DetallePedido> obtenerDetallesPorPedidoId(Integer idPedido);
    
    // Obtener detalles por producto ID
    List<DetallePedido> obtenerDetallesPorProductoId(Integer idProducto);
    
    // Obtener cantidad total vendida de un producto
    Integer obtenerCantidadTotalVendidaPorProducto(Integer idProducto);
    
    // Obtener subtotal de un pedido
    Float obtenerSubtotalPorPedido(Integer idPedido);
    
    // Eliminar detalle
    void eliminarDetalle(Integer id);
    
    // Actualizar detalle
    DetallePedido actualizarDetalle(Integer id, DetallePedido detallePedido);
}