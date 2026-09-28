package com.wendyecommerce.biuwendyecommerce.model;



public class UsuarioAdministrador extends Usuario {
    private String preferencias;
    
    public UsuarioAdministrador() {
        super();
    }

        // Getter y Setter (Hacen que el UsuarioController deje de marcar error)
    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }
}