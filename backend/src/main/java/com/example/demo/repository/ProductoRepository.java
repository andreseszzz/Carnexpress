package com.example.demo.repository;

import com.example.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
    // Método para buscar productos por categoría
    List<Producto> findByCategoria(String categoria);
    
    // Método para buscar productos por nombre (búsqueda parcial)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    
    // Método para buscar productos con stock menor a un valor
    List<Producto> findByStockLessThan(Integer stock);
    
    // Método para buscar productos sin stock
    List<Producto> findByStock(Integer stock);
    
    // Método para buscar productos por rango de precio
    List<Producto> findByPrecioBetween(Float precioMin, Float precioMax);
    
    // Consulta personalizada para obtener productos más vendidos
    @Query("SELECT p FROM Producto p JOIN p.detalles d GROUP BY p.idProducto ORDER BY SUM(d.cantidad) DESC")
    List<Producto> findProductosMasVendidos();
    
    // Método para buscar productos disponibles (con stock)
    @Query("SELECT p FROM Producto p WHERE p.stock > 0")
    List<Producto> findProductosDisponibles();
}