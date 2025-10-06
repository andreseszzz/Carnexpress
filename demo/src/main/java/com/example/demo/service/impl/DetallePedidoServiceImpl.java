package com.example.demo.service.impl;

import com.example.demo.model.DetallePedido;
import com.example.demo.repository.DetallePedidoRepository;
import com.example.demo.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {
    
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    
    @Override
    public DetallePedido guardarDetallePedido(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }
    
    @Override
    public List<DetallePedido> obtenerTodosLosDetalles() {
        return detallePedidoRepository.findAll();
    }
    
    @Override
    public Optional<DetallePedido> obtenerDetallePorId(Integer id) {
        return detallePedidoRepository.findById(id);
    }
    
    @Override
    public List<DetallePedido> obtenerDetallesPorPedidoId(Integer idPedido) {
        return detallePedidoRepository.findByPedidoIdPedido(idPedido);
    }
    
    @Override
    public List<DetallePedido> obtenerDetallesPorProductoId(Integer idProducto) {
        return detallePedidoRepository.findByProductoIdProducto(idProducto);
    }
    
    @Override
    public Integer obtenerCantidadTotalVendidaPorProducto(Integer idProducto) {
        return detallePedidoRepository.getCantidadTotalVendidaPorProducto(idProducto);
    }
    
    @Override
    public Float obtenerSubtotalPorPedido(Integer idPedido) {
        return detallePedidoRepository.getSubtotalPorPedido(idPedido);
    }
    
    @Override
    public void eliminarDetalle(Integer id) {
        detallePedidoRepository.deleteById(id);
    }
    
    @Override
    public DetallePedido actualizarDetalle(Integer id, DetallePedido detallePedido) {
        Optional<DetallePedido> detalleExistente = detallePedidoRepository.findById(id);
        if (detalleExistente.isPresent()) {
            DetallePedido detalleActualizado = detalleExistente.get();
            detalleActualizado.setCantidad(detallePedido.getCantidad());
            detalleActualizado.setSubtotal(detallePedido.getSubtotal());
            detalleActualizado.setPedido(detallePedido.getPedido());
            detalleActualizado.setProducto(detallePedido.getProducto());
            return detallePedidoRepository.save(detalleActualizado);
        }
        return null;
    }
}