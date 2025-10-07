package com.example.demo.service.impl;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    
    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }
    
    @Override
    public Optional<Producto> obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id);
    }
    
    @Override
    public List<Producto> obtenerProductosPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }
    
    @Override
    public List<Producto> buscarProductosPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    @Override
    public List<Producto> obtenerProductosConBajoStock(Integer stock) {
        return productoRepository.findByStockLessThan(stock);
    }
    
    @Override
    public List<Producto> obtenerProductosSinStock() {
        return productoRepository.findByStock(0);
    }
    
    @Override
    public List<Producto> obtenerProductosPorRangoPrecio(Float precioMin, Float precioMax) {
        return productoRepository.findByPrecioBetween(precioMin, precioMax);
    }
    
    @Override
    public List<Producto> obtenerProductosMasVendidos() {
        return productoRepository.findProductosMasVendidos();
    }
    
    @Override
    public List<Producto> obtenerProductosDisponibles() {
        return productoRepository.findProductosDisponibles();
    }
    
    @Override
    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }
    
    @Override
    public Producto actualizarProducto(Integer id, Producto producto) {
        Optional<Producto> productoExistente = productoRepository.findById(id);
        if (productoExistente.isPresent()) {
            Producto productoActualizado = productoExistente.get();
            productoActualizado.setNombre(producto.getNombre());
            productoActualizado.setDescripcion(producto.getDescripcion());
            productoActualizado.setPrecio(producto.getPrecio());
            productoActualizado.setStock(producto.getStock());
            productoActualizado.setCategoria(producto.getCategoria());
            return productoRepository.save(productoActualizado);
        }
        return null;
    }
    
    @Override
    public Producto actualizarStock(Integer id, Integer nuevoStock) {
        Optional<Producto> productoExistente = productoRepository.findById(id);
        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setStock(nuevoStock);
            return productoRepository.save(producto);
        }
        return null;
    }
}