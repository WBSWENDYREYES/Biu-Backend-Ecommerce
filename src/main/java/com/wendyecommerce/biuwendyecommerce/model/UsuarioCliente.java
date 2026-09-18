package com.wendyecommerce.biuwendyecommerce.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class UsuarioCliente extends Usuario{
    private String preferencias;

    public UsuarioCliente() {
        super();
    }

    public UsuarioCliente(int idusuario, String nombre, int iddireccion, String telefonos, String email, String password, Roles rol, String preferencias) {
        super(idusuario, nombre, iddireccion, telefonos, email, password, rol);
        this.preferencias = preferencias;
    }

    // Getter y Setter (Hacen que el UsuarioController deje de marcar error)
    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }
}
