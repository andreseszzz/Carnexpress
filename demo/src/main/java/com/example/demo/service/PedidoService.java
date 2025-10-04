package com.example.demo.service;

import com.example.demo.model.Pedido;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoService {
    
    // Crear o guardar pedido
    Pedido guardarPedido(Pedido pedido);
    
    // Obtener todos los pedidos
    List<Pedido> obtenerTodosLosPedidos();
    
    // Obtener pedido por ID
    Optional<Pedido> obtenerPedidoPorId(Integer id);
    
    // Obtener pedidos por cliente ID
    List<Pedido> obtenerPedidosPorClienteId(Integer idCliente);
    
    // Obtener pedidos por fecha
    List<Pedido> obtenerPedidosPorFecha(LocalDate fecha);
    
    // Obtener pedidos entre fechas
    List<Pedido> obtenerPedidosEntreFechas(LocalDate fechaInicio, LocalDate fechaFin);
    
    // Obtener pedidos por estado (casado)
    List<Pedido> obtenerPedidosPorEstado(String casado);
    
    // Obtener total de ventas por fecha
    Float obtenerTotalVentasPorFecha(LocalDate fecha);
    
    // Obtener últimos 10 pedidos
    List<Pedido> obtenerUltimosPedidos();
    
    // Eliminar pedido
    void eliminarPedido(Integer id);
    
    // Actualizar pedido
    Pedido actualizarPedido(Integer id, Pedido pedido);
}