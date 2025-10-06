package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "informe")
public class Informe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informe")
    private Integer idInforme;
    
    @Column(name = "tipo", nullable = false)
    private String tipo;
    
    @Column(name = "fecha_generacion", nullable = false)
    private LocalDate fechaGeneracion;
    
    // Relación muchos a uno con Administrador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin", nullable = false)
    private Administrador administrador;
    
    // Constructores
    public Informe() {
    }
    
    public Informe(String tipo, LocalDate fechaGeneracion, Administrador administrador) {
        this.tipo = tipo;
        this.fechaGeneracion = fechaGeneracion;
        this.administrador = administrador;
    }
    
    // Getters y Setters
    public Integer getIdInforme() {
        return idInforme;
    }
    
    public void setIdInforme(Integer idInforme) {
        this.idInforme = idInforme;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }
    
    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
    
    public Administrador getAdministrador() {
        return administrador;
    }
    
    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
}