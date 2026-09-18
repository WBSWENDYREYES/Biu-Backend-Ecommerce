package com.wendyecommerce.biuwendyecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idroles;
    
    private String nombreRol; // Aquí guardarás "CLIENTE", "ADMINISTRADOR", etc.

    // Constructor vacío obligatorio
    public Roles() {
    }

    // Constructor con parámetros
    public Roles(int idroles, String nombreRol) {
        this.idroles = idroles;
        this.nombreRol = nombreRol;
    }

    // Getters y Setters
    public int getIdroles() { return idroles; }
    public void setIdroles(int idroles) { this.idroles = idroles; }

    public String getNombreRol() { return nombreRol; }
    public void setNombreRol(String nombreRol) { this.nombreRol = nombreRol; }
}