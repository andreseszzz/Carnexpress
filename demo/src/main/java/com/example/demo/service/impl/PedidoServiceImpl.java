package com.example.demo.service.impl;

import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Override
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    
    @Override
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }
    
    @Override
    public Optional<Pedido> obtenerPedidoPorId(Integer id) {
        return pedidoRepository.findById(id);
    }
    
    @Override
    public List<Pedido> obtenerPedidosPorClienteId(Integer idCliente) {
        return pedidoRepository.findByClienteIdCliente(idCliente);
    }
    
    @Override
    public List<Pedido> obtenerPedidosPorFecha(LocalDate fecha) {
        return pedidoRepository.findByFecha(fecha);
    }
    
    @Override
    public List<Pedido> obtenerPedidosEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return pedidoRepository.findByFechaBetween(fechaInicio, fechaFin);
    }
    
    @Override
    public List<Pedido> obtenerPedidosPorEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }
    
    @Override
    public Float obtenerTotalVentasPorFecha(LocalDate fecha) {
        return pedidoRepository.getTotalVentasPorFecha(fecha);
    }
    
    @Override
    public List<Pedido> obtenerUltimosPedidos() {
        return pedidoRepository.findTop10ByOrderByFechaDesc();
    }
    
    @Override
    public void eliminarPedido(Integer id) {
        pedidoRepository.deleteById(id);
    }
    
    @Override
    public Pedido actualizarPedido(Integer id, Pedido pedido) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);
        if (pedidoExistente.isPresent()) {
            Pedido pedidoActualizado = pedidoExistente.get();
            pedidoActualizado.setFecha(pedido.getFecha());
            pedidoActualizado.setEstado(pedido.getEstado());
            pedidoActualizado.setTotal(pedido.getTotal());
            pedidoActualizado.setCliente(pedido.getCliente());
            return pedidoRepository.save(pedidoActualizado);
        }
        return null;
    }
}