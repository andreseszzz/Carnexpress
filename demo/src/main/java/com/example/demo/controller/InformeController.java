package com.example.demo.controller;

import com.example.demo.model.Informe;
import com.example.demo.service.InformeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/informes")
@CrossOrigin(origins = "*")
public class InformeController {
    
    @Autowired
    private InformeService informeService;
    
    // Obtener todos los informes
    @GetMapping
    public ResponseEntity<List<Informe>> obtenerTodosLosInformes() {
        List<Informe> informes = informeService.obtenerTodosLosInformes();
        return new ResponseEntity<>(informes, HttpStatus.OK);
    }
    
    // Obtener informe por ID
    @GetMapping("/{id}")
    public ResponseEntity<Informe> obtenerInformePorId(@PathVariable Integer id) {
        Optional<Informe> informe = informeService.obtenerInformePorId(id);
        return informe.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Obtener informes por administrador ID
    @GetMapping("/administrador/{idAdmin}")
    public ResponseEntity<List<Informe>> obtenerInformesPorAdministradorId(@PathVariable Integer idAdmin) {
        List<Informe> informes = informeService.obtenerInformesPorAdministradorId(idAdmin);
        return new ResponseEntity<>(informes, HttpStatus.OK);
    }
    
    // Obtener informes por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Informe>> obtenerInformesPorTipo(@PathVariable String tipo) {
        List<Informe> informes = informeService.obtenerInformesPorTipo(tipo);
        return new ResponseEntity<>(informes, HttpStatus.OK);
    }
    
    // Obtener informes por fecha de generación
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Informe>> obtenerInformesPorFecha(@PathVariable String fecha) {
        LocalDate localDate = LocalDate.parse(fecha);
        List<Informe> informes = informeService.obtenerInformesPorFecha(localDate);
        return new ResponseEntity<>(informes, HttpStatus.OK);
    }
    
    // Obtener informes entre fechas
    @GetMapping("/fecha/rango")
    public ResponseEntity<List<Informe>> obtenerInformesEntreFechas(
            @RequestParam String fechaInicio, 
            @RequestParam String fechaFin) {
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        List<Informe> informes = informeService.obtenerInformesEntreFechas(inicio, fin);
        return new ResponseEntity<>(informes, HttpStatus.OK);
    }
    
    // Obtener últimos 10 informes
    @GetMapping("/ultimos")
    public ResponseEntity<List<Informe>> obtenerUltimosInformes() {
        List<Informe> informes = informeService.obtenerUltimosInformes();
        return new ResponseEntity<>(informes, HttpStatus.OK);
    }
    
    // Obtener informes por tipo y fecha
    @GetMapping("/buscar")
    public ResponseEntity<List<Informe>> obtenerInformesPorTipoYFecha(
            @RequestParam String tipo, 
            @RequestParam String fecha) {
        LocalDate localDate = LocalDate.parse(fecha);
        List<Informe> informes = informeService.obtenerInformesPorTipoYFecha(tipo, localDate);
        return new ResponseEntity<>(informes, HttpStatus.OK);
    }
    
    // Crear nuevo informe
    @PostMapping
    public ResponseEntity<Informe> crearInforme(@RequestBody Informe informe) {
        Informe nuevoInforme = informeService.guardarInforme(informe);
        return new ResponseEntity<>(nuevoInforme, HttpStatus.CREATED);
    }
    
    // Actualizar informe
    @PutMapping("/{id}")
    public ResponseEntity<Informe> actualizarInforme(@PathVariable Integer id, @RequestBody Informe informe) {
        Informe informeActualizado = informeService.actualizarInforme(id, informe);
        if (informeActualizado != null) {
            return new ResponseEntity<>(informeActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Eliminar informe
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInforme(@PathVariable Integer id) {
        Optional<Informe> informe = informeService.obtenerInformePorId(id);
        if (informe.isPresent()) {
            informeService.eliminarInforme(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}