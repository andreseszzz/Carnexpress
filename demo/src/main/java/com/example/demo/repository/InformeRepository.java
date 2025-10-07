package com.example.demo.repository;

import com.example.demo.model.Informe;
import com.example.demo.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface InformeRepository extends JpaRepository<Informe, Integer> {
    
    // Método para encontrar informes por administrador
    List<Informe> findByAdministrador(Administrador administrador);
    
    // Método para encontrar informes por id de administrador
    List<Informe> findByAdministradorIdAdmin(Integer idAdmin);
    
    // Método para encontrar informes por tipo
    List<Informe> findByTipo(String tipo);
    
    // Método para encontrar informes por fecha de generación
    List<Informe> findByFechaGeneracion(LocalDate fechaGeneracion);
    
    // Método para encontrar informes entre fechas
    List<Informe> findByFechaGeneracionBetween(LocalDate fechaInicio, LocalDate fechaFin);
    
    // Método para obtener los últimos informes generados
    List<Informe> findTop10ByOrderByFechaGeneracionDesc();
    
    // Método para encontrar informes por tipo y fecha
    List<Informe> findByTipoAndFechaGeneracion(String tipo, LocalDate fechaGeneracion);
}