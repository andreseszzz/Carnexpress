package com.example.demo.service.impl;

import com.example.demo.model.Informe;
import com.example.demo.repository.InformeRepository;
import com.example.demo.service.InformeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InformeServiceImpl implements InformeService {
    
    @Autowired
    private InformeRepository informeRepository;
    
    @Override
    public Informe guardarInforme(Informe informe) {
        return informeRepository.save(informe);
    }
    
    @Override
    public List<Informe> obtenerTodosLosInformes() {
        return informeRepository.findAll();
    }
    
    @Override
    public Optional<Informe> obtenerInformePorId(Integer id) {
        return informeRepository.findById(id);
    }
    
    @Override
    public List<Informe> obtenerInformesPorAdministradorId(Integer idAdmin) {
        return informeRepository.findByAdministradorIdAdmin(idAdmin);
    }
    
    @Override
    public List<Informe> obtenerInformesPorTipo(String tipo) {
        return informeRepository.findByTipo(tipo);
    }
    
    @Override
    public List<Informe> obtenerInformesPorFecha(LocalDate fechaGeneracion) {
        return informeRepository.findByFechaGeneracion(fechaGeneracion);
    }
    
    @Override
    public List<Informe> obtenerInformesEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return informeRepository.findByFechaGeneracionBetween(fechaInicio, fechaFin);
    }
    
    @Override
    public List<Informe> obtenerUltimosInformes() {
        return informeRepository.findTop10ByOrderByFechaGeneracionDesc();
    }
    
    @Override
    public List<Informe> obtenerInformesPorTipoYFecha(String tipo, LocalDate fechaGeneracion) {
        return informeRepository.findByTipoAndFechaGeneracion(tipo, fechaGeneracion);
    }
    
    @Override
    public void eliminarInforme(Integer id) {
        informeRepository.deleteById(id);
    }
    
    @Override
    public Informe actualizarInforme(Integer id, Informe informe) {
        Optional<Informe> informeExistente = informeRepository.findById(id);
        if (informeExistente.isPresent()) {
            Informe informeActualizado = informeExistente.get();
            informeActualizado.setTipo(informe.getTipo());
            informeActualizado.setFechaGeneracion(informe.getFechaGeneracion());
            informeActualizado.setAdministrador(informe.getAdministrador());
            return informeRepository.save(informeActualizado);
        }
        return null;
    }
}