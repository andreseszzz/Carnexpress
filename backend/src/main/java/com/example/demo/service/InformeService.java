package com.example.demo.service;

import com.example.demo.model.Informe;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InformeService {
    
    // Crear o guardar informe
    Informe guardarInforme(Informe informe);
    
    // Obtener todos los informes
    List<Informe> obtenerTodosLosInformes();
    
    // Obtener informe por ID
    Optional<Informe> obtenerInformePorId(Integer id);
    
    // Obtener informes por administrador ID
    List<Informe> obtenerInformesPorAdministradorId(Integer idAdmin);
    
    // Obtener informes por tipo
    List<Informe> obtenerInformesPorTipo(String tipo);
    
    // Obtener informes por fecha de generación
    List<Informe> obtenerInformesPorFecha(LocalDate fechaGeneracion);
    
    // Obtener informes entre fechas
    List<Informe> obtenerInformesEntreFechas(LocalDate fechaInicio, LocalDate fechaFin);
    
    // Obtener últimos 10 informes
    List<Informe> obtenerUltimosInformes();
    
    // Obtener informes por tipo y fecha
    List<Informe> obtenerInformesPorTipoYFecha(String tipo, LocalDate fechaGeneracion);
    
    // Eliminar informe
    void eliminarInforme(Integer id);
    
    // Actualizar informe
    Informe actualizarInforme(Integer id, Informe informe);
}