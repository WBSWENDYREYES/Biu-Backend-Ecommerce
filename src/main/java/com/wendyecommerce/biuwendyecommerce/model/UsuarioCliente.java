package com.wendyecommerce.biuwendyecommerce.model;
public class UsuarioCliente extends Usuario{
    private String preferencias;

    public UsuarioCliente() {
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
