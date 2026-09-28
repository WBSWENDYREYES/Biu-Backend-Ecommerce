package com.wendyecommerce.biuwendyecommerce.model;

public class Usuario {
    private int Id;
    private String nombre;
    private String telefonos;
    private String email;
    private String password;
    private int idroles; // Atributo numérico directo para el Rol

    // Constructor vacío
    public Usuario() {}

    // Constructor completo
    public Usuario(int id, String nombre, String telefonos, String email, String password, int idroles) {
        this.Id = id;
        this.nombre = nombre;
        this.telefonos = telefonos;
        this.email = email;
        this.password = password;
        this.idroles = idroles;
    }

    // Getters y Setters
    public int getId() { return Id; }
    public void setId(int id) { this.Id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefonos() { return telefonos; }
    public void setTelefonos(String telefonos) { this.telefonos = telefonos; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getIdroles() { return idroles; }
    public void setIdroles(int idroles) { this.idroles = idroles; }
}