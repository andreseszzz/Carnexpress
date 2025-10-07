package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    
    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Integer id) {
        Optional<Producto> producto = productoService.obtenerProductoPorId(id);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Obtener productos por categoría
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@PathVariable String categoria) {
        List<Producto> productos = productoService.obtenerProductosPorCategoria(categoria);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    
    // Buscar productos por nombre
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Producto>> buscarProductosPorNombre(@PathVariable String nombre) {
        List<Producto> productos = productoService.buscarProductosPorNombre(nombre);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    
    // Obtener productos con bajo stock
    @GetMapping("/bajo-stock/{stock}")
    public ResponseEntity<List<Producto>> obtenerProductosConBajoStock(@PathVariable Integer stock) {
        List<Producto> productos = productoService.obtenerProductosConBajoStock(stock);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    
    // Obtener productos sin stock
    @GetMapping("/sin-stock")
    public ResponseEntity<List<Producto>> obtenerProductosSinStock() {
        List<Producto> productos = productoService.obtenerProductosSinStock();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    
    // Obtener productos por rango de precio
    @GetMapping("/precio/rango")
    public ResponseEntity<List<Producto>> obtenerProductosPorRangoPrecio(
            @RequestParam Float precioMin, 
            @RequestParam Float precioMax) {
        List<Producto> productos = productoService.obtenerProductosPorRangoPrecio(precioMin, precioMax);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    
    // Obtener productos más vendidos
    @GetMapping("/mas-vendidos")
    public ResponseEntity<List<Producto>> obtenerProductosMasVendidos() {
        List<Producto> productos = productoService.obtenerProductosMasVendidos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    
    // Obtener productos disponibles
    @GetMapping("/disponibles")
    public ResponseEntity<List<Producto>> obtenerProductosDisponibles() {
        List<Producto> productos = productoService.obtenerProductosDisponibles();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    
    // Crear nuevo producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardarProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }
    
    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizarProducto(id, producto);
        if (productoActualizado != null) {
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Actualizar solo el stock del producto
    @PatchMapping("/{id}/stock")
    public ResponseEntity<Producto> actualizarStock(@PathVariable Integer id, @RequestParam Integer nuevoStock) {
        Producto productoActualizado = productoService.actualizarStock(id, nuevoStock);
        if (productoActualizado != null) {
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        Optional<Producto> producto = productoService.obtenerProductoPorId(id);
        if (producto.isPresent()) {
            productoService.eliminarProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}