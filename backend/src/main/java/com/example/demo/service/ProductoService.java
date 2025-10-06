package com.example.demo.service;

import com.example.demo.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    
    // Crear o guardar producto
    Producto guardarProducto(Producto producto);
    
    // Obtener todos los productos
    List<Producto> obtenerTodosLosProductos();
    
    // Obtener producto por ID
    Optional<Producto> obtenerProductoPorId(Integer id);
    
    // Obtener productos por categoría
    List<Producto> obtenerProductosPorCategoria(String categoria);
    
    // Buscar productos por nombre
    List<Producto> buscarProductosPorNombre(String nombre);
    
    // Obtener productos con bajo stock
    List<Producto> obtenerProductosConBajoStock(Integer stock);
    
    // Obtener productos sin stock
    List<Producto> obtenerProductosSinStock();
    
    // Obtener productos por rango de precio
    List<Producto> obtenerProductosPorRangoPrecio(Float precioMin, Float precioMax);
    
    // Obtener productos más vendidos
    List<Producto> obtenerProductosMasVendidos();
    
    // Obtener productos disponibles
    List<Producto> obtenerProductosDisponibles();
    
    // Eliminar producto
    void eliminarProducto(Integer id);
    
    // Actualizar producto
    Producto actualizarProducto(Integer id, Producto producto);
    
    // Actualizar stock del producto
    Producto actualizarStock(Integer id, Integer nuevoStock);
}