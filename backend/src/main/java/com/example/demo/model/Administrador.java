package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "administrador")
public class Administrador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Integer idAdmin;
    
    @Column(name = "usuario", nullable = false, unique = true)
    private String usuario;
    
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    
    @Column(name = "rol")
    private String rol;
    
    // Relación uno a muchos con Informe
    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Informe> informes;
    
    // Constructores
    public Administrador() {
    }
    
    public Administrador(String usuario, String contrasena, String rol) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }
    
    // Getters y Setters
    public Integer getIdAdmin() {
        return idAdmin;
    }
    
    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public String getRol() {
        return rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public List<Informe> getInformes() {
        return informes;
    }
    
    public void setInformes(List<Informe> informes) {
        this.informes = informes;
    }
}