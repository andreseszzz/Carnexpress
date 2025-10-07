package com.example.demo.repository;

import com.example.demo.model.Pedido;
import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
    // Método para encontrar todos los pedidos de un cliente
    List<Pedido> findByCliente(Cliente cliente);
    
    // Método para encontrar pedidos por id de cliente
    List<Pedido> findByClienteIdCliente(Integer idCliente);
    
    // Método para encontrar pedidos por fecha
    List<Pedido> findByFecha(LocalDate fecha);
    
    // Método para encontrar pedidos entre fechas
    List<Pedido> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
    
    // Método para encontrar pedidos por estado (estado)
    List<Pedido> findByEstado(String estado);
    
    // Consulta personalizada para obtener el total de ventas por fecha
    @Query("SELECT SUM(p.total) FROM Pedido p WHERE p.fecha = :fecha")
    Float getTotalVentasPorFecha(@Param("fecha") LocalDate fecha);
    
    // Consulta para obtener los últimos N pedidos
    List<Pedido> findTop10ByOrderByFechaDesc();
}