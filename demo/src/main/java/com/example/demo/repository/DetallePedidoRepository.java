package com.example.demo.repository;

import com.example.demo.model.DetallePedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    
    // Método para encontrar todos los detalles de un pedido
    List<DetallePedido> findByPedido(Pedido pedido);
    
    // Método para encontrar detalles por id de pedido
    List<DetallePedido> findByPedidoIdPedido(Integer idPedido);
    
    // Método para encontrar detalles por producto
    List<DetallePedido> findByProducto(Producto producto);
    
    // Método para encontrar detalles por id de producto
    List<DetallePedido> findByProductoIdProducto(Integer idProducto);
    
    // Consulta para obtener la cantidad total vendida de un producto
    @Query("SELECT SUM(d.cantidad) FROM DetallePedido d WHERE d.producto.idProducto = :idProducto")
    Integer getCantidadTotalVendidaPorProducto(@Param("idProducto") Integer idProducto);
    
    // Consulta para obtener el subtotal de un pedido
    @Query("SELECT SUM(d.subtotal) FROM DetallePedido d WHERE d.pedido.idPedido = :idPedido")
    Float getSubtotalPorPedido(@Param("idPedido") Integer idPedido);
}